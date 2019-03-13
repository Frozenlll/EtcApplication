package com.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.adapter.ArticleAdapter;
import com.adapter.NoteAdapter;
import com.entity.Articlelist;
import com.entity.Notelist;
import com.example.fragmentactivity.ArticleContent;
import com.example.fragmentactivity.ContentLogin;
import com.example.fragmentactivity.ContentSet;
import com.example.fragmentactivity.IndexContent;
import com.example.fragmentactivity.NoteContent;
import com.example.fragmentactivity.R;
import com.example.net.HttpCallBack;
import com.example.net.HttpTask;
import com.utils.Util;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements
		android.view.View.OnClickListener, HttpCallBack {
	private ImageView iv1, iv2, iv3, iv4;
	private Fragment Fragment1, Fragment2, Fragment3, Fragment4;
	private Fragment contentset;
	public static MainActivity instance = null;
	public static int userid = 0;
	public static String name = null;
	
	
	public TextView title_tv, username_tv;
	Map<String, String> map = new HashMap<String, String>();
	HttpTask task;
	String data = null;

	// ����Article��������
	ArticleAdapter articleAdapter = null;
	List<Articlelist> articlelist = new ArrayList<Articlelist>();
	// ����Note��������
	NoteAdapter noteAdapter = null;
	List<Notelist> notelist = new ArrayList<Notelist>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		instance = this;

		Intent intt = getIntent();
		name = intt.getStringExtra("username");
		userid = intt.getIntExtra("userid", 0);

		username_tv = (TextView) findViewById(R.id.username_tv);
		username_tv.setText(name);

		iv1 = (ImageView) findViewById(R.id.bottom_iv1);
		iv2 = (ImageView) findViewById(R.id.bottom_iv2);
		iv3 = (ImageView) findViewById(R.id.bottom_iv3);
		iv4 = (ImageView) findViewById(R.id.bottom_iv4);

		Fragment1 = new IndexContent();
		Fragment2 = new ArticleContent();
		Fragment3 = new NoteContent();
		Fragment4 = new ContentLogin();

		contentset = new ContentSet();

		iv1.setOnClickListener(this);
		iv2.setOnClickListener(this);
		iv3.setOnClickListener(this);
		iv4.setOnClickListener(this);

		// ����Ĭ�ϵ�ContentFragment
		setFragment(Fragment1);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bottom_iv1:
			// ��ҳ

			setFragment(Fragment1);
			break;
		case R.id.bottom_iv2:
			// ��ʾ��������

			map.put("type", "article");
			data = Util.getPair(map);

			task = new HttpTask(MainActivity.this);
			task.setCallback(this);

			task.execute(Util.URL + Util.init, data);
			setFragment(Fragment2);
			break;
		case R.id.bottom_iv3:
			// ��ʾ��������

			map.put("type", "note");
			data = Util.getPair(map);

			task = new HttpTask(MainActivity.this);
			task.setCallback(this);

			task.execute(Util.URL + Util.init, data);

			setFragment(Fragment3);
			break;
		case R.id.bottom_iv4:
			// ��������

			if (name == null) {
				// ���û�е�¼ʱ���������������ʾ��¼
				setFragment(Fragment4);
			} else {
				// ����Ѿ���¼ʱ�������������
				setFragment(contentset);
			}
			break;
		default:
			break;
		}
	}

	// ������ʾ��fragment
	private void setFragment(Fragment fragment) {

		// ��Activity�в���Fragment����Ҫ��ȡfragmentManage
		FragmentManager fm = getSupportFragmentManager();

		// ����һ������
		FragmentTransaction transaction = fm.beginTransaction();

		// ʹ����һfragment�滻��ǰ��fragment
		// int arg0:fragment����ĵط�
		// arg1:Ҫ��ʾ��fragment
		transaction.replace(R.id.content_fragment, fragment);

		// �ύ����
		transaction.commit();
	}

	//���մӺ�̨������������
	@Override
	public void callBack(String str) {
		// TODO Auto-generated method stub
		String index = str.substring(0, str.indexOf("?"));
		Log.d("index", index);
		str = str.substring(str.indexOf("?") + 1);

		// �ж�index��ֵ����ΪArticlelist������ArticleAdapter�м���ֵ�������������ӵ�contentfragment2��
		if (index.equals("Articlelist")) {

			try {

				JSONArray array = new JSONArray(str);
				for (int i = 0; i < array.length(); i++) {

					JSONObject obj = array.getJSONObject(i);

					Articlelist list = new Articlelist();

					list.setArticlename(obj.getString("articlename"));

					list.setArticleid(obj.getInt("articleid"));
					list.setArticlecontent(obj.getString("articlecontent"));
					list.setPublishedtime(obj.getString("publishedtime"));
					list.setUserid(obj.getInt("userid"));
					list.setUsername(obj.getString("username"));

					articlelist.add(list);
				}
				articleAdapter = new ArticleAdapter(MainActivity.this,
						articlelist);

				articleAdapter.notifyDataSetChanged();
				((ArticleContent) Fragment2).setArticlelist(articlelist);
				((ArticleContent) Fragment2).setArticleAdapter(articleAdapter);

			} catch (Exception e) {
				// TODO: handle exception

				e.printStackTrace();
			}
		}
		// �ж�index��ֵ����ΪNotelist������ArticleAdapter�м���ֵ�������������ӵ�contentfragment2��
		else if (index.equals("Notelist")) {
			try {

				JSONArray array = new JSONArray(str);
				for (int i = 0; i < array.length(); i++) {

					JSONObject obj = array.getJSONObject(i);

					Notelist list = new Notelist();

					list.setNotename(obj.getString("notename"));
					list.setNoteid(obj.getInt("noteid"));
					list.setNotecontent(obj.getString("notecontent"));
					list.setPublishedtime(obj.getString("publishedtime"));
					list.setUserid(obj.getInt("userid"));
					list.setUsername(obj.getString("username"));

					notelist.add(list);
				}
				noteAdapter = new NoteAdapter(MainActivity.this, notelist);

				noteAdapter.notifyDataSetChanged();
				((NoteContent) Fragment3).setNotelist(notelist);
				((NoteContent) Fragment3).setNoteAdapter(noteAdapter);

			} catch (Exception e) {
				// TODO: handle exception
				Log.d("xie", "Exception");
				e.printStackTrace();
			}

		}

	}
}
