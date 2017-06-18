package com.amaker.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import com.amaker.shop.product.bean.Product;
import com.amaker.shop.util.PageHibernateCallback;

/**
 * ��Ʒ�ĳ־ò�
 * @author Administrator
 *
 */
public class ProductDao extends HibernateDaoSupport{
    //dao��������Ʒ����ʾ
	public List<Product> findHotProduct() {
		//ʹ�����߲�ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//��ѯ���ŵ���Ʒ����������is_hot=1
		criteria.add(Restrictions.eq("is_hot", 1));
		//��������Ʒ��¼��ʱ��ĵ�������
		criteria.addOrder(Order.desc("pdate"));
		//��ѯ��Ʒ
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}
    //dao��������Ʒ����ʾ
	public List<Product> findNewProduct() {
		//ʹ�����߲�ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//��������Ʒ��¼��ʱ��ĵ�������
		criteria.addOrder(Order.desc("pdate"));
		//��ѯ��Ʒ
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}
	//������Ʒid��ѯ
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class,pid);
	}

	//����һ�������Լ���ҳ��ȡ��Ӧ��Ʒ����
	public List<Product> findByPageCid(int cid, int begin, int limit) {
		// select p.* from category c,categorysecond cs,product p where c.cid = cs.cid and cs.csid = p.csid and c.cid = 2
		// select p from Category c,CategorySecond cs,Product p where c.cid = cs.category.cid and cs.csid = p.categorySecond.csid and c.cid = ?
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		// ��ҳ��һ��д��:
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//������Ʒһ�������ȡ�ܼ�¼��
	public int countCid(int cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,cid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//Dao����ݶ��������ѯ��Ʒ
	public List<Product> findByPageCsid(int csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs  where cs.csid = ?";
		// ��ҳ��һ��д��:
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
	//Dao����ݶ�������id��ѯ��Ʒ
	public int countCsid(int csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,csid);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//Dao���ѯ��Ʒ������
	public int findCountProduct() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//Dao�㰴ҳ��ѯ��Ʒ
	public List<Product> findByPageCid(int begin, int limit) {
		String hql = "from Product  order by pdate desc";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,null,begin,limit));
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	//Dao������Ʒ
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
		
	}
	//Dao��ɾ����Ʒ
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}
	//Dao�޸���Ʒ
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}
    
}
