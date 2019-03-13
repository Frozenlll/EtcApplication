package com.example.fragmentactivity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.activity.MainActivity;
import com.activity.RegisteActivity;
import com.example.net.HttpCallBack;
import com.example.net.HttpTask;
import com.utils.Util;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContentLogin extends Fragment implements HttpCallBack {
	private EditText username,userpwd;
	private Button login,register;
	int contentloginResultcode=4;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View viewcontent=inflater.inflate(R.layout.content_login, null);
		//TextView tv=(TextView) viewcontent.findViewById(R.id.content_tv);
		
		username=(EditText) viewcontent.findViewById(R.id.username);
		userpwd=(EditText) viewcontent.findViewById(R.id.userpwd);
		
		login=(Button) viewcontent.findViewById(R.id.login_but);
		register=(Button) viewcontent.findViewById(R.id.register_but);
		
		//��¼����¼�
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String name = username.getText().toString().trim();
				String pwd = userpwd.getText().toString().trim();
				String action="login";
				
				//��ֵ����Map��
				Map<String, String> map = new HashMap<String, String>();
				map.put("action",action);
				map.put("username", name);
				map.put("userpwd", pwd);
				
				String data = Util.getPair(map);
				HttpTask task = new HttpTask(getActivity());
				task.setCallback(ContentLogin.this);
				
				task.execute(Util.URL + Util.user, data);
			}
		});
		
		//ע�����¼�
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				//���ע�ᣬ��ת��ע��ҳ��
				Intent intent = new Intent(getActivity(), RegisteActivity.class);
				intent.putExtra("action", "regist");
				
				Log.d("contentloginResultcode",contentloginResultcode+"");
				startActivityForResult(intent,contentloginResultcode);
				
			}
		});
		
		return viewcontent;
	}

	@Override
	public void callBack(String str) {

		if (str != null) {
			try {
				
				JSONObject json = new JSONObject(str);
				boolean flag = json.getBoolean("flag");
				Log.d("xie", flag+"");
				String username = json.getString("username");
				int userid=json.getInt("userid");
				if (flag) {
					//��ת��MainActivity
					Intent intent = new Intent(getActivity(), MainActivity.class);
					//����intent��ֵ
					intent.putExtra("username", username);
					intent.putExtra("userid", userid);
					//���ý����
					
					startActivityForResult(intent,contentloginResultcode);
				
				} else {
					
					Toast.makeText(getActivity(), "��¼ʧ��", 0).show();
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
	}
}
