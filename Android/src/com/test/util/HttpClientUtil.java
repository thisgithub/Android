package com.test.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.message.BasicNameValuePair;

/**
 * 访问网络相关处理
 * @author Admin
 *
 */
public class HttpClientUtil {
	
	
	public static String getStringByGet(String url, Map params, int timeOut){
		LogUtil.debug("-------get url----------"  + url);
		HttpGet get = new HttpGet(url);
		HttpClient client = new DefaultHttpClient();
		StringBuffer sb = new StringBuffer();
		InputStream is;
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while((line = br.readLine()) != null){
				sb.append(line);
			}
			LogUtil.debug("----sb.string---------" + sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static String getStringByPost(String url){
		HttpPost post;
		HttpClient client;
		HttpResponse response;
		UrlEncodedFormEntity urlEntity;
		HttpEntity entity;
		InputStream is;
		StringBuffer sb = new StringBuffer();
		try {
			post = new HttpPost(url);
			client = new DefaultHttpClient();
			List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
			pairs.add(new BasicNameValuePair("username", "admin"));
			pairs.add(new BasicNameValuePair("password", "admin"));
			urlEntity = new UrlEncodedFormEntity(pairs);
			post.setEntity(urlEntity);
			
			response = client.execute(post);
			entity = response.getEntity();
			is = entity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while((line = br.readLine()) != null){
				sb.append(line);
			}
			LogUtil.debug("---------sb.string-----------------" + sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("post error------------------------", e);
		}
		return sb.toString();
	}
}
