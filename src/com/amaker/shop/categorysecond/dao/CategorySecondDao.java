package com.amaker.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import com.amaker.shop.categorysecond.bean.CategorySecond;
import com.amaker.shop.util.PageHibernateCallback;

/**
 * ��̨������������Dao
 * @author ChangchunLong
 *
 */
public class CategorySecondDao extends HibernateDaoSupport{
    //��ҳ��ȡ��������ļ���
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
    //��ȡ���������ܼ�¼��
	public int findCountCsid() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//Dao�㱣���������
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}
	//Dao��ɾ����������
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
		
	}
	//Dao�����csid��ѯ��������
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}
	//Dao�����������޸�
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}
	//Dao���ѯ���ж��������б�
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		List<CategorySecond> list = this.getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
    
}
