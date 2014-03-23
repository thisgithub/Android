package com.test.android.push.action;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.test.android.push.pojo.ApnUser;
import com.test.android.push.pojo.Message;
import com.test.android.push.service.AndroidService;
import com.test.android.push.service.MessageService;
import com.test.android.push.util.Config;
import com.test.android.push.util.JSONStringBuilder;
import com.test.android.push.xmpp.push.NotificationManager;

@Namespace("/manage")
@ParentPackage("manage")
public class NotificationAction extends BaseAction{

	private static final long serialVersionUID = 8421574735525029208L;
	
	private NotificationManager notificationManager;
	private AndroidService androidService;
	private MessageService messageService;
	
	private String username;
	private String title;
	private String message;
	private String uri;
	private Long id;
	private List<ApnUser> userList;
	
	/**
	 * 转到单发消息页面
	 * @return
	 */
	//@Action(value="findUserBorrowInfo",results = { @Result(name = "success", type = "json", params={"root","jsonmap"})},interceptorRefs = @InterceptorRef("auth"))
	@Action(value="listSingle",results = { @Result(name = "success", location = "/android/notification/formSingle.jsp")})
	public String listSingle() {
		return SUCCESS;
	}
	/**
	 * 转到群发消息页面
	 * @return
	 */
	@Action(value="listAll",results = { @Result(name = "success", location = "/android/notification/formAll.jsp")})
	public String listAll() {
		return SUCCESS;
	}
	
	/**
	 * 给指定的用户发送系统消息
	 * @return
	 * @throws Exception
	 */
	@Action(value="sendSingle",results = { @Result(name = "success", location = "/android/notification/formSingle.jsp")})
	public String sendSingle() throws Exception {
		System.out.println("-----sendSingle() start----");
		notificationManager = new NotificationManager();
		//保存数据库
		Message sysMessage = new Message();
		sysMessage.setOwnerId(id);
		sysMessage.setSenderId(1L);
		sysMessage.setReceiverId(id);
		sysMessage.setMessage(message);
		sysMessage.setFlag(0);//默认没有接收
		sysMessage.setDeleted(0);
		sysMessage.setCreateTime(new Date());
		messageService.insertMessage(sysMessage);
		
		 Map<String,Object> element = new LinkedHashMap<String, Object>();
		 element.put("senderId", "");
		 element.put("receiverId", "");
		 element.put("message", message);
		 String content = JSONStringBuilder.getAjaxString(element) ;
		
        String apiKey = Config.getString("apiKey", "");
        notificationManager.sendNotifcationToUser(apiKey, username, title, content, uri);
		System.out.println("-----sendSingle() end----");
        return SUCCESS;
    }
	
	/**
	 * 给所有的用户发送系统消息
	 * @return
	 * @throws Exception
	 */
	@Action(value="sendAll",results = { @Result(name = "success", location = "/android/notification/formAll.jsp")})
	public String sendAll() throws Exception {
		System.out.println("-----sendAll() start----");
		notificationManager = new NotificationManager();
		userList = androidService.getAndroidPnsList();
		if(null != userList){
			for(ApnUser apUser : userList){
				//保存数据库
				Message sysMessage = new Message();
				sysMessage.setOwnerId(apUser.getId());
				sysMessage.setSenderId(1L);
				sysMessage.setReceiverId(apUser.getId());
				sysMessage.setMessage(message);
				sysMessage.setFlag(0);//默认没有接收
				sysMessage.setDeleted(0);
				sysMessage.setCreateTime(new Date());
				messageService.insertMessage(sysMessage);
			}
		}
		
		String apiKey = Config.getString("apiKey", "");

		 Map<String,Object> element = new LinkedHashMap<String, Object>();
		 element.put("senderId", "");
		 element.put("receiverId", "");
		 //element.put("msgCount", 1);
		 element.put("message", message);
		 String content = JSONStringBuilder.getAjaxString(element) ;
        
        notificationManager.sendBroadcast(apiKey, title, content, uri);
        System.out.println("-----sendAll() end----");
        return SUCCESS;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Long getId() {
		return id;
	}
	public MessageService getMessageService() {
		return messageService;
	}
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public AndroidService getAndroidService() {
		return androidService;
	}
	public List<ApnUser> getUserList() {
		return userList;
	}
	public void setUserList(List<ApnUser> userList) {
		this.userList = userList;
	}
	public void setAndroidService(AndroidService androidService) {
		this.androidService = androidService;
	}
	
}
