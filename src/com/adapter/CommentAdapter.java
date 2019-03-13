package com.adapter;

import java.util.List;

import com.entity.Commentlist;
import com.example.fragmentactivity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CommentAdapter extends BaseAdapter {

	private Context context;// ������
	private List<Commentlist> list;// ����
	Commentsclass commentview = new Commentsclass();

	public CommentAdapter(Context context, List<Commentlist> list) {
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
		if (convertView == null) {
			// ����������
			LayoutInflater inflater = LayoutInflater.from(context);
			// �����Ӳ���item
			convertView = inflater.inflate(R.layout.list_single, null);

			// 2.�������

			commentview.content = (TextView) convertView
					.findViewById(R.id.list_content);
			commentview.date = (TextView) convertView
					.findViewById(R.id.list_date);
			commentview.username = (TextView) convertView
					.findViewById(R.id.list_author);

			// ���������뵽View
			convertView.setTag(commentview);
		} else {
			convertView.getTag();
		}
		// 3.�������ֵ
		commentview.content.setText(list.get(position).getContent());
		commentview.date.setText(list.get(position).getCommenttime());
		commentview.username.setText(list.get(position).getUsername());
		// 4.����View
		return convertView;
	}

}

class Commentsclass {
	TextView content, username, date;
}
