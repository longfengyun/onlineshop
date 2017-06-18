package com.amaker.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.amaker.shop.category.bean.Category;

/**
 * category�ĳ־ò����
 * @author Administrator
 *
 */
public class CategoryDao extends HibernateDaoSupport{
     //ע��Category����
	private Category category;

	public void setCategory(Category category) {
		this.category = category;
	}
    //dao���ȡ���е�һ������
	public List<Category> findAll() {
		//hql��ѯ���
		String hql ="from Category";
		List<Category> cList =this.getHibernateTemplate().find(hql);
		if(cList.size()>0){
		  return cList;
		}
		else{
		  return null;
		}
	}
	//dao�����һ������
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}
	//Dao��ɾ��һ������
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}
	//Dao�����cid��ѯһ������
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}
	//Dao�����һ������
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}
	
}
