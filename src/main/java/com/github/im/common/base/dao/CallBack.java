/**
 * 回调函数
 */
package com.github.im.common.base.dao;

import java.sql.ResultSet;

/**
 * @author haoxuewu
 * @since  2011年4月22日
 */
public interface CallBack {
	Object getResultObject(ResultSet rs);
}
