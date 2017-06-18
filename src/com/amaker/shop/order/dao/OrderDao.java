package com.amaker.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.amaker.shop.categorysecond.bean.CategorySecond;
import com.amaker.shop.order.bean.Order;
import com.amaker.shop.order.bean.OrderItem;
import com.amaker.shop.util.PageHibernateCallback;

/**
 * ����ģ��ĳ־ò�
 * @author ChangchunLong
 *
 */
public class OrderDao extends HibernateDaoSupport{
    //Dao�㱣�涩��
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}
    //Dao�����uid����ѯ�û��Ķ�������
	public int findCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//Dao���ѯ�ҵĶ�����ҳ��ѯ:��ѯ����
	public List<Order> findByPageUid(Integer uid, int begin,
			int limit) {
		String hql ="from Order o where o.user.uid=? order by o.ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[] { uid },
						begin, limit));
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	//Dao����ݶ���id��ѯ����
	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}
	//Dao�㶩���޸�
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}
	//Dao�㶩��������ѯ
	public int findCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//Dao���ҳ��ȡ����
	public List<Order> findByPage(int begin, int limit) {
		String hql ="from Order order by orderTime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, null, begin,
						limit));
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	//Dao����ݶ�����ѯ������
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem o where o.order.oid=?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql,oid);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

}
