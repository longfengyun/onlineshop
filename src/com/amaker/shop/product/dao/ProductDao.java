package com.amaker.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import com.amaker.shop.product.bean.Product;
import com.amaker.shop.util.PageHibernateCallback;

/**
 * 商品的持久层
 * @author Administrator
 *
 */
public class ProductDao extends HibernateDaoSupport{
    //dao层最热商品的显示
	public List<Product> findHotProduct() {
		//使用离线查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//查询热门的商品，条件就是is_hot=1
		criteria.add(Restrictions.eq("is_hot", 1));
		//将热门商品按录入时间的倒叙排列
		criteria.addOrder(Order.desc("pdate"));
		//查询商品
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}
    //dao层最新商品的显示
	public List<Product> findNewProduct() {
		//使用离线查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//将最新商品按录入时间的倒叙排列
		criteria.addOrder(Order.desc("pdate"));
		//查询商品
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}
	//根据商品id查询
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class,pid);
	}

	//根据一级分类以及分页获取相应商品集合
	public List<Product> findByPageCid(int cid, int begin, int limit) {
		// select p.* from category c,categorysecond cs,product p where c.cid = cs.cid and cs.csid = p.csid and c.cid = 2
		// select p from Category c,CategorySecond cs,Product p where c.cid = cs.category.cid and cs.csid = p.categorySecond.csid and c.cid = ?
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		// 分页另一种写法:
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//根据商品一级分类获取总记录数
	public int countCid(int cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,cid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//Dao层根据二级分类查询商品
	public List<Product> findByPageCsid(int csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs  where cs.csid = ?";
		// 分页另一种写法:
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//Dao层根据二级分类id查询商品
	public int countCsid(int csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,csid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//Dao层查询商品总数量
	public int findCountProduct() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//Dao层按页查询商品
	public List<Product> findByPageCid(int begin, int limit) {
		String hql = "from Product  order by pdate desc";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,null,begin,limit));
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	//Dao保存商品
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
		
	}
	//Dao层删除商品
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}
	//Dao修改商品
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}
    
}
