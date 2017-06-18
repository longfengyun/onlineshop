package com.amaker.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import com.amaker.shop.categorysecond.bean.CategorySecond;
import com.amaker.shop.util.PageHibernateCallback;

/**
 * 后台二级分类管理的Dao
 * @author ChangchunLong
 *
 */
public class CategorySecondDao extends HibernateDaoSupport{
    //按页获取二级分类的集合
	public List<CategorySecond> findByPage(int begin, int limit) {
		String hql ="from CategorySecond order by csid desc";
		List<CategorySecond> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<CategorySecond>(hql, null, begin,
						limit));
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
    //获取二级分类总记录数
	public int findCountCsid() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//Dao层保存二级分类
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}
	//Dao层删除二级分类
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
		
	}
	//Dao层根据csid查询二级分类
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}
	//Dao层二级分类的修改
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}
	//Dao层查询所有二级分类列表
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		List<CategorySecond> list = this.getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
    
}
