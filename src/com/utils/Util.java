package com.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class Util {

	public static String URL="http://192.168.2.31:8080/EtcStudentSystem";
	public static String user="/UserAppServlet";  //servlet
	
	public static String init="/InitAppServlet";
	
	public static String comment="/CommentAppServlet";
	//�ֱ��ȡ������Ϣ����ȫ����Ϣ�����ֱ�ʶ�� 
	//�ͺ�̨��һһ��Ӧ
	
	public static String Articlelist="Articlelist";
	public static String Notelist="Notelist";
	
	public static String Article="Article";
	public static String Note="Note";
	
	public static String Articlecommentlist="Articlecommentlist";
	public static String Notecommentlist="Notecommentlist";
	
	public static String MyArticlelist="MyArticlelist";
	public static String MyNotelist="MyNotelist";
	
	public static String Users="User";
	
	
	/**
	 * ������ֵ�Խڵ�ķ�װ��:name=clh&pwd=clh
	 * @param map
	 * @return
	 */
	public static String getPair(Map<String, String> map){
		List<NameValuePair> androidParams=new ArrayList<NameValuePair>();
		for(String key:map.keySet()){
			androidParams.add(new BasicNameValuePair(key,map.get(key).toString()));
		}
		String data=URLEncodedUtils.format(androidParams, "UTF-8");
		Log.d("xie", data.toString());
		return data;		
	}
	
}

