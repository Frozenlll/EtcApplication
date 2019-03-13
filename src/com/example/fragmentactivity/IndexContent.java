package com.example.fragmentactivity;

import java.util.ArrayList;
import java.util.List;

import com.adapter.ArticleAdapter;
import com.entity.Articlelist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class IndexContent extends Fragment {
	
	private ListView listv;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//1.�Ҳ���
		View viewcontent=inflater.inflate(R.layout.index_content, null);
		//2.�����
		listv=(ListView) viewcontent.findViewById(R.id.listview);
		
		//3.���ָ�ֵ
		//����һ��ListValue����װ��Ϣ
		
		Articlelist list=new Articlelist();
		
//		list.setArticlename(aritlename);
//		list.setArticlecontent(artilcecontent);
//		list.setUsername(username);
		//list.setPublishedtime(publishedtime);
		
		//����һ��List�������洢ListValue����Ϣ
		List<Articlelist> listvalue=new ArrayList<Articlelist>();
		listvalue.add(list);
		
		
		//��list��ֵ���뵽�Զ���������Adapter��
		ArticleAdapter articleAdapter=new ArticleAdapter(getActivity(), listvalue);
		
		//���Զ�������������õ�listview
		listv.setAdapter(articleAdapter);
		
		return viewcontent;
	}
}
