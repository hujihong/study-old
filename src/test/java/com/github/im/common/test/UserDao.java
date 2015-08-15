package com.github.im.common.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.im.common.base.dao.BaseDbcpDao;
import com.github.im.common.base.dao.CallBack;
import com.github.im.common.config.RouteUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDao  {
	
	private final static Logger logger = LoggerFactory.getLogger(UserDao.class);
	private static BaseDbcpDao baseDao = BaseDbcpDao.getInstance("/my/mysqltest");
	private static RouteUtil routeUtil = RouteUtil.getInstance();

	/**
	 * 插入例子
	 * @param id
	 * @param name
	 * @return
	 */
	public int save(long id,String name) {
		String table = routeUtil.getTestTableName(id);
		String sql = "insert into " + table + " values(?,?)";
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(id);
		parameters.add(name);
		long dbkey = id;
		return baseDao.insert(dbkey, sql, parameters);
	}


	/**
	 * 插入批量例子
	 * @param listUser
	 * @return
	 */
	public int[] saveBatch(List<ArrayList> listUser) {
		String table = routeUtil.getTestTableName(0);
		String sql = "insert into " + table + " values(?,?)";
		return baseDao.executeBatchUpdate(0,sql,listUser);
	}

	/**
	 * 插入例子
	 * @param id
	 * @param name
	 * @return
	 */
	public int saveId(long id,String name) {
		String table = routeUtil.getTestTableName(id);
		String sql = "insert into " + table + " values(?,?)";
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(id);
		parameters.add(name);
		long dbkey = id;
		return baseDao.insertReturnId(dbkey, sql, parameters);
	}
	/**
	 * 统计例子
	 * @return
	 */
	public int count() {
		String table = "user1";
		String sql = "select count(*) from " + table ;
		ArrayList<Object> parameters = new ArrayList<Object>();
		return (Integer) baseDao.query(0l, sql, parameters, new CallBack() {
			// @Override
			public Object getResultObject(ResultSet rs) {
				try {
					if (rs.next()) {
						return rs.getInt(1);

					}
				} catch (SQLException e) {
					logger.error("", e);
				}
				return null;
			}
		});

	}
	/**
	 * 根据id查单个例子
	 * @param id
	 * @return
	 */
	public User findById(long id) {
		long dbkey = id;
		String table = routeUtil.getTestTableName(id);
		String sql = "select * from " + table + " where id=?";
		ArrayList<Long> parameters = new ArrayList<Long>();
		parameters.add(id);
		Object o =  baseDao.query(dbkey, sql, parameters, new CallBack() {
			// @Override
			public Object getResultObject(ResultSet rs) {
				try {
					if (rs.next()) {
						User user = new User();
						user.setId(rs.getInt("id"));
						user.setName(rs.getString("name"));
						return user;

					}
				} catch (SQLException e) {
					logger.error("", e);
				}
				return null;
			}
		});
		if(o!=null)
		return  (User)o;
		else
		return null;

	}
	/**
	 * 查找列表例子
	 * @param id
	 * @return
	 */
	public List<User> findUsers(long id) {
		long dbkey = id;
		String table = routeUtil.getTestTableName(id);
		String sql = "select * from " + table + " where id<?";
		ArrayList<Long> parameters = new ArrayList<Long>();
		parameters.add(id);
		Object o = baseDao.query(dbkey, sql, parameters, new CallBack() {
			// @Override
			public Object getResultObject(ResultSet rs) {
				List<User> list = new ArrayList<User>();
				try {
					while (rs.next()) {
						User user = new User();
						user.setId(rs.getInt("id"));
						user.setName(rs.getString("name"));
						list.add(user);
					}
					return list;

				} catch (SQLException e) {
					logger.error("", e);
				}
				return null;
			}
		});

		if(o!=null)
			return  (List<User>)o;
		else
			return null;

	}
	
	/**
	 * 查找列表翻页例子
	 * @param id
	 * @return
	 */
	public List<User> findUsersByPage(long id,int limit,int offset) {
		long dbkey = id;
		String table = routeUtil.getTestTableName(id);
		String sql = "select * from " + table + " where id<? limit ? offset ?";
		ArrayList parameters = new ArrayList();
		parameters.add(id);
		parameters.add(limit);
		parameters.add(offset);
		Object o =  (List<User>)baseDao.query(dbkey, sql, parameters, new CallBack() {
			// @Override
			public Object getResultObject(ResultSet rs) {
				List<User> list = new ArrayList<User>();
				try {
					while (rs.next()) {
						User user = new User();
						user.setId(rs.getInt("id"));
						user.setName(rs.getString("name"));
						list.add(user);
					}
					return list;

				} catch (SQLException e) {
					logger.error("", e);
				}
				return null;
			}
		});
		if(o!=null)
			return  (List<User>)o;
		else
			return null;
	}

	/**
	 * 删除记录列子
	 * @param id
	 * @return
	 */
	public int removeById(long id) {
		String table = routeUtil.getTestTableName(id);
		String sql = "delete   from " + table + " where id=?";
		ArrayList<Long> parameters = new ArrayList<Long>();
		parameters.add(id);
		long dbkey = id;
		return baseDao.delete(dbkey, sql, parameters);
	}

	/**
	 * 修改记录例子
	 * @param id
	 * @param name
	 * @return
	 */
	public int updateNameById(long id, String name) {
		String table = routeUtil.getTestTableName(id);
		String sql = "update  " + table + " set name =? where id = ?";
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(name);
		parameters.add(id);
		long dbkey = id;
		return baseDao.update(dbkey, sql, parameters);
	}

}
