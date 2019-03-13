package com.adapter;

import java.util.List;

import com.entity.Notelist;
import com.example.fragmentactivity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NoteAdapter extends BaseAdapter {

	private Context context;// ������
	private List<Notelist> list;// ����
	Noteclass noteview=new Noteclass();
	
	public NoteAdapter(Context context, List<Notelist> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		// 1.�Ҳ��� item.xml
		if (convertView ==null){
			// ����������
			LayoutInflater inflater = LayoutInflater.from(context); 
			// �����Ӳ���item
			convertView = inflater.inflate(R.layout.list_main, null);
			
			//2.�������
			
			noteview.title=(TextView) convertView.findViewById(R.id.list_title);
			noteview.content=(TextView) convertView.findViewById(R.id.list_content);
			noteview.date=(TextView) convertView.findViewById(R.id.list_date);
			noteview.username=(TextView) convertView.findViewById(R.id.list_author);
			
			//���������뵽View
			convertView.setTag(noteview);
		}
		else{
			convertView.getTag();
		}
		//3.�������ֵ
		noteview.title.setText(list.get(position).getNotename());
		noteview.content.setText(list.get(position).getNotecontent());
		noteview.date.setText((CharSequence) list.get(position).getPublishedtime());
		noteview.username.setText(list.get(position).getUsername());
		//4.����View
		return convertView;
	}
}

class Noteclass {
	TextView title, content,username,date;
}