package com.amaker.shop.categorysecond.service;

import java.util.List;

import com.amaker.shop.categorysecond.bean.CategorySecond;
import com.amaker.shop.categorysecond.dao.CategorySecondDao;
import com.amaker.shop.util.PageBean;

/**
 * 后台二级分类管理的Service
 * @author ChangchunLong
 *
 */
public class CategorySecondService {
	//注入CategorySecondDao
    private CategorySecondDao categorySecondDao;
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	//业务层按页查询二级分类
	public PageBean findByPage(Integer page) {
		PageBean pageBean = new PageBean();
		//设置page
		pageBean.setPage(page);
		//设置limit
		int limit=10;
		pageBean.setLimit(limit);
		//获取二级分类总记录数
		int totalCount = categorySecondDao.findCountCsid();
		pageBean.setTotalCount(totalCount);
		//获取总页数
		int totalPage = 0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit+1;
		}
		//设置二级分类的总页数
		pageBean.setTotalPage(totalPage);
		//设置二级分类某页的集合
		int begin=(page-1)*limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//业务层保存二级分类
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}
	//业务层根据二级分类id查询
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}
	//业务层删除二级分类
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
		
	}
	//业务层修改二级分类
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}
	//业务层查询所有的二级分类
	public List<CategorySecond> findAll() {
		List<CategorySecond> list = categorySecondDao.findAll();
		return list;
	}
    
}
