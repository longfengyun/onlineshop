package com.amaker.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.amaker.shop.categorysecond.bean.CategorySecond;
import com.amaker.shop.order.bean.Order;
import com.amaker.shop.order.bean.OrderItem;
import com.amaker.shop.util.PageHibernateCallback;

/**
 * 订单模块的持久层
 * @author ChangchunLong
 *
 */
public class OrderDao extends HibernateDaoSupport{
    //Dao层保存订单
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}
    //Dao层根据uid来查询用户的订单数量
	public int findCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//Dao层查询我的订单分页查询:查询数据
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
	//Dao层根据订单id查询订单
	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}
	//Dao层订单修改
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}
	//Dao层订单数量查询
	public int findCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}
	//Dao层分页获取订单
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
	//Dao层根据订单查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem o where o.order.oid=?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql,oid);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

}
