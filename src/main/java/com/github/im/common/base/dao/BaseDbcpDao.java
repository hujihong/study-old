package com.github.im.common.base.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseDbcpDao {
	private final static Logger logger = LoggerFactory.getLogger(BaseDbcpDao.class);
	private static DbcpPool dbPool = null;
	private Connection conn = null;
	private PreparedStatement smt = null;
	private ResultSet rs = null;
	private static BaseDbcpDao instance = null;

	public synchronized static BaseDbcpDao getInstance(String zkPath){
		if(instance==null){
			instance = new BaseDbcpDao();
			dbPool = DbcpPool.getInstance(zkPath);
		}
		return instance;
	}

	/**
	 * 查询数据 返回一条或多条结果集
	 * 
	 * @param dbkey
	 *            按照此key作为分库的依据
	 * @param sql
	 *            prepare sql
	 * @param parameters
	 *            占位符参数
	 * @param callBack
	 *            回调函数
	 * @return Object
	 */
	@SuppressWarnings("rawtypes")
	public Object query(long dbkey, String sql, ArrayList parameters,
			CallBack callBack) {
		return executeQuery(dbkey, sql, parameters, callBack);
	}

	/**
	 * 刪除数据
	 * 
	 * @param dbkey
	 *            按照此key作为分库的依据
	 * @param sql
	 *            prepare sql
	 * @param parameters
	 *            占位符参数
	 * @return int
	 */
	@SuppressWarnings("rawtypes")
	public int delete(long dbkey, String sql, ArrayList parameters) {
		return executeUpdate(dbkey, sql, parameters);
	}

	/**
	 * 修改数据
	 * 
	 * @param dbkey
	 *            按照此key作为分库的依据
	 * @param sql
	 *            prepare sql
	 * @param parameters
	 *            占位符参数
	 * @return int
	 */
	@SuppressWarnings("rawtypes")
	public int update(long dbkey, String sql, ArrayList parameters) {
		return executeUpdate(dbkey, sql, parameters);
	}

	/**
	 * 插入数据
	 * 
	 * @param dbkey
	 *            按照此key作为分库的依据
	 * @param sql
	 *            prepare sql
	 * @param parameters
	 *            占位符参数
	 * @return int
	 */
	@SuppressWarnings("rawtypes")
	public int insert(long dbkey, String sql, ArrayList parameters) {
		return executeUpdate(dbkey, sql, parameters);
	}


	/**
	 * 插入数据返回id
	 *
	 * @param dbkey
	 *            按照此key作为分库的依据
	 * @param sql
	 *            prepare sql
	 * @param parameters
	 *            占位符参数
	 * @return int
	 */
	@SuppressWarnings("rawtypes")
	public int insertReturnId(long dbkey, String sql, ArrayList parameters) {
		return executeUpdateReturnId(dbkey, sql, parameters);
	}
	/**
	 * 执行操作数据
	 * 
	 * @param dbkey
	 *            按照此key作为分库的依据
	 * @param sql
	 *            prepare sql
	 * @param parameters
	 *            占位符参数
	 * @return int
	 */
	@SuppressWarnings("rawtypes")
	public int executeUpdate(long dbkey, String sql, ArrayList parameters) {
		int num = 0;
		try {
			int dbindex = dbPool.getDbIndex(dbkey);
			logger.info("execute sql={},dbkey={}", sql, dbkey + "");

			conn = dbPool.getConn(dbindex);
			smt = conn.prepareStatement(sql);
			if (parameters != null) {
			  logger.info("execute parameters={}", parameters.toString());
				for (int i = 0; i < parameters.size(); i++) {
					smt.setObject(i + 1, parameters.get(i));
				}
			}
			num = smt.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e.getMessage());
		} finally {
			dbPool.closeConnection(rs, smt, conn);
			logger.info("end executeUpdate");
		}
		return num;
	}

	/**
	 * 执行操作数据
	 *
	 * @param dbkey
	 *            按照此key作为分库的依据
	 * @param sql
	 *            prepare sql
	 * @param parametersList
	 *            占位符参数
	 * @return int
	 */
	@SuppressWarnings("rawtypes")
	public int[] executeBatchUpdate(long dbkey, String sql, List<ArrayList> parametersList) {
		int[] num ;
		try {
			int dbindex = dbPool.getDbIndex(dbkey);
			logger.info("execute sql={},dbkey={}", sql, dbkey);

			conn = dbPool.getConn(dbindex);
			conn.setAutoCommit(false);
			smt = conn.prepareStatement(sql);
			if (parametersList != null) {
			  logger.info("execute parameters={}", parametersList.toString());
				//参数个数
				int paramnum = parametersList.get(0).size();
				for (int i = 0; i < parametersList.size(); i++) {
					for (int j = 0; j < paramnum; j++) {
						smt.setObject(j + 1, parametersList.get(i).get(j));
					}
					smt.addBatch();
				}
			}
			num = smt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e.getMessage());
		} finally {
			dbPool.closeConnection(rs, smt, conn);
			logger.info("end executeUpdate");
		}
		return num;
	}
	/**
	 * 执行操作数据
	 *
	 * @param dbkey
	 *            按照此key作为分库的依据
	 * @param sql
	 *            prepare sql
	 * @param parameters
	 *            占位符参数
	 * @return int
	 */
	@SuppressWarnings("rawtypes")
	public int executeUpdateReturnId(long dbkey, String sql, ArrayList parameters) {
		int id = 0;
		try {
			int dbindex = dbPool.getDbIndex(dbkey);
			logger.info("execute sql={}, dbkey={}", sql, dbkey);

			conn = dbPool.getConn(dbindex);
			smt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (parameters != null) {
			  logger.info("execute parameters={}", parameters.toString());
				for (int i = 0; i < parameters.size(); i++) {
					smt.setObject(i + 1, parameters.get(i));
				}
			}
			smt.executeUpdate();
			rs = smt.getGeneratedKeys();
			if (rs.next()) {
				id =  rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e.getMessage());
		} finally {
			dbPool.closeConnection(rs, smt, conn);
			logger.info("end executeUpdate");
		}
		return id;
	}
	/**
	 * 查询数据 返回一条或多条结果集
	 * 
	 * @param dbkey
	 *            按照此key作为分库的依据
	 * @param sql
	 *            prepare sql
	 * @param parameters
	 *            占位符参数
	 * @param callBack
	 *            回调函数
	 * @return Object
	 */
	@SuppressWarnings({ "rawtypes" })
	public Object executeQuery(long dbkey, String sql, ArrayList parameters,
			CallBack callBack) {
		try {
			int dbindex = dbPool.getDbIndex(dbkey);
			logger.info("execute sql={},dbkey={}", sql, dbkey);

			conn = dbPool.getConn(dbindex);
			smt = conn.prepareStatement(sql);
			if (parameters != null) {
			  logger.info("execute parameters={}", parameters.toString());
				for (int i = 0; i < parameters.size(); i++) {
					smt.setObject(i + 1, parameters.get(i));
				}
			}
			rs = smt.executeQuery();
			return callBack.getResultObject(rs);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
			throw new RuntimeException(e.getMessage());
		} finally {
			dbPool.closeConnection(rs, smt, conn);
			logger.info("end executeQuery");
		}
	}

}
