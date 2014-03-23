package com.test.android.push.service;

import java.sql.SQLException;

import com.test.android.push.dao.MessageDao;
import com.test.android.push.pojo.Message;

public class MessageServiceImpl implements MessageService {
	
	private MessageDao messageDao;

	@Override
	public void insertMessage(Message message) {
		try {
			messageDao.insert(message);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public MessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

}
