package com.test.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

/**
 * 访问网络相关处理
 * @author Admin
 *
 */
public class HttpClientUtil {
	
	
	/**
	 * 处理get  请求
	 * @param url
	 * @param params
	 * @param timeOut
	 * @return
	 */
	public static String getStringByGet(String url, Map params, int timeOut) throws Exception{
		LogUtil.debug("-------get url----------"  + url);
		String responseStr;
		String requestUrl;
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		String pairsStr = "";
		if(params != null){
			for(Object key : params.keySet()){
				Object value = params.get(key);
				pairs.add(new BasicNameValuePair(key.toString(), value.toString()));
			}
			pairsStr = URLEncodedUtils.format(pairs, "utf-8");
		}
		if(pairsStr == null || "".equals(pairsStr)){
			requestUrl = url;
		} else {
			requestUrl = url + "?" + pairsStr;
		}
		
		HttpGet request = new HttpGet(requestUrl);
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeOut);
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeOut);
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		responseStr = EntityUtils.toString(entity, "utf-8");
		return responseStr;
	}
	
	/**
	 * 处理post请求
	 * @param url
	 * @return
	 */
	public static String getStringByPost(String url, Map params, int timeOut) throws Exception{
		LogUtil.debug("----post url--------" + url);
		String result = null;
		StringBuilder sb = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeOut);
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeOut * 1000);
		HttpPost request = new HttpPost(url);
		LogUtil.debug("上传的参数： " + params);
		if(params != null){
			List<NameValuePair> postParams = new ArrayList<NameValuePair>();
			for(Object key : params.keySet()){
				Object value = params.get(key);
				if(null == value){
					continue;
				} else {
					postParams.add(new BasicNameValuePair(key.toString(), value.toString()));
				}
			}
			LogUtil.debug("posparams:   " + postParams.size());
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParams);
			request.setEntity(formEntity);
		}
		
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		LogUtil.debug("entity" + (entity == null));
		if(entity != null){
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
			String line = null;
			while((line = reader.readLine()) != null){
				sb.append(line);
				LogUtil.debug("line:  " + line);
			}
			reader.close();
		}
		
		result = sb.toString();
		return result;
	}
}
