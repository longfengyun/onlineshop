package com.amaker.shop.category.service;

import java.util.List;

import com.amaker.shop.category.bean.Category;
import com.amaker.shop.category.dao.CategoryDao;

/**
 * 业务层的category对象
 * @author Administrator
 *
 */
public class CategoryService {
    //注入categoryDao对象
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
   //业务层中查询所有的一级分类
	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	//业务层添加一级分类
	public void save(Category category) {
		categoryDao.save(category);
	}
	//业务层删除一级分类
	public void delete(Category category) {
		categoryDao.delete(category);
	}
	//业务层根据cid查询一级分类
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}
	//业务层更新一级分类的方法
	public void update(Category category) {
		categoryDao.update(category);
	}
	
}
