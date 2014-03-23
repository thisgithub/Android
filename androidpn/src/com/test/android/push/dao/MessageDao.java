package com.test.android.push.dao;

import java.sql.SQLException;

import com.test.android.push.pojo.Message;

public interface MessageDao {
	
	public void insert(Message message)throws SQLException;

}
