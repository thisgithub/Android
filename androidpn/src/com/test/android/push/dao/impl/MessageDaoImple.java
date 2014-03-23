package com.test.android.push.dao.impl;

import java.sql.SQLException;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.test.android.push.dao.MessageDao;
import com.test.android.push.pojo.Message;

public class MessageDaoImple extends SqlMapClientDaoSupport implements MessageDao {

	@Override
	public void insert(Message message) throws SQLException{
		try {
			getSqlMapClientTemplate().insert("message.insertMessage", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
