package com.github.im.common.config;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.zkclient.IZkChildListener;
import com.github.zkclient.IZkDataListener;
import com.github.zkclient.ZkClient;
import com.github.zkclient.exception.ZkInterruptedException;

/**
 * zookeeper配置中心 单例线程安全
 * 
 */

public class ZkHelp {
	public static final Logger logger = LoggerFactory.getLogger(ZkHelp.class);
	private static ZkHelp instance = null;

	private ZkHelp() {
	}

	public synchronized static ZkHelp getInstance() {
		if (instance == null) {
			instance = new ZkHelp();
			instance.init();
		}
		return instance;
	}

	// 环境标志 默认测试环境 test
	public static String environmentFlag = Env.TEST.getName();
	public ZkClient client = null;
	
	// zookeeper集群地址 开发环境
	// public String zooKeeperCluster = "172.18.22.11:2181,172.18.22.12:2181,172.18.22.13:2181";
	// public String zooKeeperCluster = "172.18.22.56:2181,172.18.22.57:2181,172.18.22.58:2181";  // 
	// zookeeper集群地址 开发环境
	public String zooKeeperCluster = "127.0.0.1:2181";
	
	public int sessionTimeout = 10000;
	public int connectionTimeout = 10000;

	/**
	 * 初始化
	 */
	public void init() {
		String config = System.getProperty("config.type");
		logger.info("config="+config);
		if (!StringUtils.isEmpty(config)) {
			environmentFlag = "/" + config;
			//测试环境
			if (environmentFlag.equals(Env.UNI.getName())||environmentFlag.equals(Env.SHAHE.getName())||environmentFlag.equals(Env.INTE.getName())) {
				zooKeeperCluster = "172.18.19.52:2181,172.18.19.53:2181,172.18.19.54:2181";
			}
			//模拟  生产环境
			if (environmentFlag.equals(Env.MONI.getName())||environmentFlag.equals(Env.PROD.getName())) {
				zooKeeperCluster = "10.17.5.106:2181,10.17.5.107:2181,10.17.5.108:2181,10.17.5.109:2181,10.17.5.110:2181";
			}
			
		}
		client = new ZkClient(zooKeeperCluster, sessionTimeout,
				connectionTimeout);
	}

	/**
	 * 重建实例
	 */
	public void reInit() {
		closeZk();
		init();
	}

	/**
	 * 检查是否需要增加环境标示
	 * 
	 * @param path
	 * @return
	 */
	public boolean checkEnv(String path) {
		boolean b = false;
		for (Env c : Env.values()) {
			if (path.startsWith(c.getName())) {
				b = true;
				break;
			}
		}
		return b;
	}

	/**
	 * 关闭zk实例
	 */
	public void closeZk() {
		try {
			client.close();
		} catch (ZkInterruptedException e) {
			logger.error("zookeeper client  close error!", e);
		}
	}

	/**
	 * 节点数据变化监听
	 * 
	 * @param path
	 * @param listener
	 */
	public void subscribeDataChanges(String path, IZkDataListener listener) {
		if (!checkEnv(path))
			path = environmentFlag + path;
		logger.info("path:{}", path);
		client.subscribeDataChanges(path, listener);
	}

	/**
	 * 节点子节点变化监听
	 * 
	 * @param path
	 * @param listener
	 */
	public void subscribeChildChanges(String path, IZkChildListener listener) {
		if (!checkEnv(path))
			path = environmentFlag + path;
		client.subscribeChildChanges(path, listener);
	}

	/**
	 * 获取path节点下的儿子节点列表
	 * 
	 * @param path
	 * @return
	 */
	public List<String> getChildren(String path) {
		if (!checkEnv(path))
			path = environmentFlag + path;
		return client.getChildren(path);
	}

	/**
	 * 获取节点数据
	 * 
	 * @param path
	 * @return
	 */
	public byte[] getValue(String path) {
		if (!checkEnv(path))
			path = environmentFlag + path;
		return client.readData(path);
	}

	/**
	 * thrift server 启动服务时候调增加临时节点
	 * 
	 * @param path
	 * @param servername
	 */
	public boolean regInCluster(String path, String serverName) {
		String newPath = path + "/" + serverName;
		logger.info(">>>>> start register " + newPath + " in cluster <<<<<");
		setPathData(path, null);
		boolean b = createEphemeral(newPath, serverName);
		if (b) {
			logger.info("Servers:" + getChildren(path));
			logger.info(">>>>> register server " + serverName + " ok <<<<<");
		}
		return b;
	}

	/**
	 * 创建临时节点
	 * 
	 * @param path
	 * @param value
	 * @return
	 */
	public boolean createEphemeral(String path, String value) {
		if (!checkEnv(path))
			path = environmentFlag + path;
		boolean b = false;
		try {
			client.createEphemeral(path, value.getBytes());
			b = true;
		} catch (Exception e) {
			logger.error("!!!! register " + path + " in cluster error !!!", e);
		}
		return b;
	}

	/**
	 * 节点设置值
	 * 
	 * @param path
	 *            需要传入全路径
	 * @param value
	 *            值
	 */
	public void setPathData(String path, String value) {
		if (!checkEnv(path))
			path = environmentFlag + path;
		String tmpPath = "";
		String array[] = path.split("/");
		int length = array.length;
		for (int i = 0; i < length; i++) {
			if (array[i].equals("")) {
				continue;
			}
			tmpPath = tmpPath + "/" + array[i];
			// 节点不存在先创建
			if (!client.exists(tmpPath)) {
				client.createPersistent(tmpPath, null);
			}
			if (i == length - 1) {
				client.writeData(tmpPath,
						value == null ? null : value.getBytes());
			}
		}
	}

}