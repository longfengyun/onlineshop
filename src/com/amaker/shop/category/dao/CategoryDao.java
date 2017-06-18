package com.amaker.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.amaker.shop.category.bean.Category;

/**
 * category的持久层代码
 * @author Administrator
 *
 */
public class CategoryDao extends HibernateDaoSupport{
     //注入Category对象
	private Category category;

	public void setCategory(Category category) {
		this.category = category;
	}
    //dao层获取所有的一级分类
	public List<Category> findAll() {
		//hql查询语句
		String hql ="from Category";
		List<Category> cList =this.getHibernateTemplate().find(hql);
		if(cList.size()>0){
		  return cList;
		}
		else{
		  return null;
		}
	}
	//dao层添加一级分类
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}
	//Dao层删除一级分类
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}
	//Dao层根据cid查询一级分类
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}
	//Dao层更新一级分类
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}
	
}
