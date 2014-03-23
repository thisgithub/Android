package com.test.android.push.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.test.android.push.util.JSONStringBuilder;

public abstract class BaseAction extends ActionSupport {
	
	protected Logger log = Logger.getLogger(getClass());

	private static final long serialVersionUID = 2109773313593099908L;
	
	
	public Object getSessionAttribute(String key) {
		return ActionContext.getContext().getSession().get(key);
	}
	
	public void setSessionAttribute(String key, Object value) {
		ActionContext.getContext().getSession().put(key, value);
	}
	
	public void removeSessionAttribute(String key) {
		ActionContext.getContext().getSession().remove(key);
	}

	/**
	 * 返回Ajax响应字符串，将处理结果返回
	 */
	protected void sendAjaxResponse(String message) {
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/json");
		try {
			res.getWriter().print(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回Ajax响应字符串，将处理结果返回
	 */
	protected void sendAjaxResponse(Object obj) throws Exception {
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/json");
		res.getWriter().print(JSONStringBuilder.getAjaxString(obj));
	}

	/**
	 * 返回Ajax响应字符串，将处理结果返回
	 * 
	 * @param list 查询数据
	 * @throws Exception 异常信息
	 */
	@SuppressWarnings("unchecked")
	protected void sendAjaxResponse(List list) throws Exception {
		JSONArray array = JSONArray.fromObject(list);
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/json");
		res.getWriter().print(array.toString());
	}
    
	/**
	 * 获得request
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获得response
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 获得session
	 * 
	 * @return
	 */
	public HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 获得servlet上下文
	 * 
	 * @return
	 */
	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	public String getRealPath(String path) {
		return getServletContext().getRealPath(path);
	}
    
	/**
	 * 新文件名称创建
	 * @param fileName
	 * @return
	 */
	protected String generateFileName(String fileName) {   
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");   
        String formatDate = format.format(new Date());   
           
        int random = new Random().nextInt(10000);   
           
        int position = fileName.lastIndexOf(".");   
        String extension = fileName.substring(position);   
           
        return formatDate + random + extension;   
    }
	
	protected int getAge(Date birthday) {
		if (birthday == null) {
			return 99;
		}
		Calendar now = Calendar.getInstance();
		Calendar birth = Calendar.getInstance();
		birth.setTime(birthday);
		return now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
	}
}

