package com.dao;

import com.adapter.ArticleAdapter;
import com.adapter.NoteAdapter;

public interface Show{
	
	/**
	 * ��ʾ��������
	 * @return ArticleAdapter
	 */
	public ArticleAdapter showAllArticle();
	
	/**
	 * ��ʾ��������
	 * @return NoteAdapter
	 */
	public NoteAdapter showAllNote();
		
}
