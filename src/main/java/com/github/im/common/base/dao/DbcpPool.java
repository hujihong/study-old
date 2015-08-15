package com.github.im.common.base.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.im.common.config.ZkHelp;
import com.github.zkclient.IZkDataListener;

/**
 * dbcp连接池 单例 线程安全
 */
public class DbcpPool {
	private static DbcpPool instance = null;
	private final static Logger logger = LoggerFactory
			.getLogger(DbcpPool.class);
	private final static ZkHelp zkHelp = ZkHelp.getInstance();

	private List<BasicDataSource> dataSource = null;
	private int maxActive = 100;// 最大连接数量
	private int maxIdle = 100;// 最大空闲连接
	private int minIdle = 20;// 最小空闲连接
	private int maxWait = 1000;// 超时等待时间以毫秒为单位 1000等于60秒
	private int initialSize = 10;// 初始化连接
	private boolean removeAbandoned = true;// 是否自动回收超时连接
	private int removeAbandonedTimeout = 60;// 超时时间(以秒数为单位)
	private boolean testOnBorrow = true;
	private boolean logAbandoned = true;// 是否在自动回收超时连接的时候打印连接的超时错误
    private int timeBetweenEvictionRunsMillis=86400;//失效检查线程运行时间间隔，要小于MySQL的'wait_timeout'时间（如果小于等于0，不会启动检查线程）
	private String validationQuery="SELECT 1 FROM dual";//检查连接有效性的SQL语句
	private boolean testWhileIdle=true; //检查连接是否有效
	/**
	 * 实例化
	 * @param zkPath
	 * @return
	 */
	public synchronized static DbcpPool getInstance(String zkPath) {
		if (instance == null) {
			instance = new DbcpPool();
			IZkDataListener listener = new IZkDataListener() {
				public void handleDataDeleted(String dataPath) throws Exception {
					logger.info("!!! DbcpPool node data has been deleted !!!"
							+ dataPath);
				}

				public void handleDataChange(String dataPath, byte[] data)
						throws Exception {
					logger.info("!!! DbcpPool node data has been changed !!!"
							+ dataPath);
					String redisServerInf = toStr(data);
					instance.initPools(dataPath);
					logger.info("!!! DbcpPool node[" + redisServerInf
							+ "] connection pool has been rebuild !!!"
							+ dataPath);
				}
			};
			//节点添加监控
			zkHelp.subscribeDataChanges(zkPath, listener);
			// 初始化
			instance.initPools(zkPath);
		}
		return instance;
	}


	public static String toStr(byte[] b) {
		try {
			return b != null ? new String(b, "UTF-8") : null;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 初始化连接池
	 * 
	 * @param zkPath
	 */
	public void initPools(String zkPath) {
		logger.info("start init connection pools");
		dataSource = new ArrayList<BasicDataSource>();
		try {
			ArrayList<Map<String, String>> list = getAddress(zkPath);
			for (int i = 0; i < list.size(); i++) {
				BasicDataSource bds = new BasicDataSource();
				Map<String, String> addr = (Map<String, String>) list.get(i);

				bds = new BasicDataSource();
				bds.setDriverClassName("com.mysql.jdbc.Driver");
				bds.setUrl(addr.get("url").toString());
				bds.setUsername(addr.get("user").toString());
				bds.setPassword(addr.get("pwd").toString());

				bds.setMaxActive(this.maxActive);// 最大连接数量
				bds.setMaxIdle(this.maxIdle);// 最大空闲连接
				bds.setMinIdle(this.minIdle);// 最小空闲连接
				bds.setMaxWait(this.maxWait);// 超时等待时间以毫秒为单位 1000等于60秒
				bds.setInitialSize(this.initialSize);// 初始化连接
				bds.setRemoveAbandoned(this.removeAbandoned);
				bds.setRemoveAbandonedTimeout(this.removeAbandonedTimeout);
				bds.setTestOnBorrow(this.testOnBorrow);
				bds.setLogAbandoned(this.logAbandoned);
				bds.setTestWhileIdle(this.testWhileIdle);
				bds.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
				bds.setValidationQuery(this.validationQuery);
				dataSource.add(bds);
			}
			logger.info("end init connection pools");
		} catch (Exception e) {
			logger.error("init error", e);
		}
	}

	/**
	 * 获得mysql数据库配置
	 * @param zkPath
	 * @return
	 */
	private ArrayList<Map<String, String>> getAddress(String zkPath) {
		String mysqlArray[] = new String(zkHelp.getValue(zkPath)).split(",");
		ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (int i = 0; i < mysqlArray.length; i++) {
			Map<String, String> addr = new HashMap<String, String>();
			String address[] = mysqlArray[i].split(":");
			addr.put("url", "jdbc:mysql://" + address[0] + ":" + address[1]
					+ "/" + address[2]+"?useUnicode=true&characterEncoding=utf-8&autoReconnect=true");
			addr.put("user", address[3]);
			addr.put("pwd", address[4]);
			list.add(addr);
		}
		return list;
	}

	/**
	 * 按key取模分库
	 * 
	 * @param key
	 * @return
	 */
	public int getDbIndex(long key) {
		long result = key % dataSource.size();
		return (int) result;
	}

	/** 从数据源获得一个连接 */
	public Connection getConn(long key) {

		Connection conn = null;
		try {
			int index = getDbIndex(key);
			BasicDataSource bds = dataSource.get(index);
			conn = bds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("getConn error", e);
		}
		return conn;
	}

	/**
	 * 获得数据源连接状态
	 * 
	 * @param key
	 * @return
	 */
	public Map<String, Integer> getDataSourceStats(long key) {
		BasicDataSource bds = null;
		try {
			int index = getDbIndex(key);
			bds = dataSource.get(index);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("getDataSourceStats error", e);
		}
		Map<String, Integer> map = new HashMap<String, Integer>(2);
		map.put("active_number", bds.getNumActive());
		map.put("idle_number", bds.getNumIdle());
		return map;
	}

	/**
	 * 关闭数据源
	 * 
	 * @param key
	 * @throws SQLException
	 */
	protected void shutdownDataSource(long key) throws SQLException {
		BasicDataSource bds = null;
		try {
			int index = getDbIndex(key);
			bds = dataSource.get(index);
			bds.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("shutdownDataSource error", e);
		}

	}

	/**
	 * 关闭执行查询操作的连接
	 * 
	 * @param resultSet
	 * @param statement
	 * @param connection
	 */
	public void closeConnection(ResultSet resultSet, Statement statement,
			Connection connection) {
		try {
			if (resultSet != null) {
				resultSet.close();
				resultSet = null;
			}
			if (statement != null) {
				statement.close();
				statement = null;
			}
			if (connection != null) {
				connection.close();
				connection = null;
			}
		} catch (SQLException e) {
			logger.error("closeConnection error", e);
		}
	}
}