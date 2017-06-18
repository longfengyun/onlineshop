package com.amaker.shop.category.service;

import java.util.List;

import com.amaker.shop.category.bean.Category;
import com.amaker.shop.category.dao.CategoryDao;

/**
 * ҵ����category����
 * @author Administrator
 *
 */
public class CategoryService {
    //ע��categoryDao����
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
   //ҵ����в�ѯ���е�һ������
	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	//ҵ������һ������
	public void save(Category category) {
		categoryDao.save(category);
	}
	//ҵ���ɾ��һ������
	public void delete(Category category) {
		categoryDao.delete(category);
	}
	//ҵ������cid��ѯһ������
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}
	//ҵ������һ������ķ���
	public void update(Category category) {
		categoryDao.update(category);
	}
	
}
