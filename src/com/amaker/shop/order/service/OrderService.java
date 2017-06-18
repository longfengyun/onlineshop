package com.amaker.shop.order.service;

import java.util.List;

import com.amaker.shop.categorysecond.bean.CategorySecond;
import com.amaker.shop.order.bean.Order;
import com.amaker.shop.order.bean.OrderItem;
import com.amaker.shop.order.dao.OrderDao;
import com.amaker.shop.util.PageBean;

/**
 * 订单模块的业务层模块
 * @author ChangchunLong
 *
 */
public class OrderService {
    //注入orderDao;
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
    //业务层保存订单
	public void save(Order order) {
		orderDao.save(order);
	}
	//业务层根据用户id查询订单列表
	public PageBean<Order> findByUid(Integer uid, Integer page) {
		PageBean<Order>  pageBean = new PageBean<Order>();
		//设置pageBean
		pageBean.setPage(page);
		//设置页面显示订单数量
		int limit=5;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = orderDao.findCountUid(uid);
		//设置总页数
		int totalPage= 0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		//设置起始订单位置
		int begin=(page-1)*limit;
		//获取订单列表
		List<Order> list = orderDao.findByPageUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//业务层根据订单id查询订单
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}
	//业务层修改订单
	public void update(Order currOrder) {
		orderDao.update(currOrder);
		
	}
	//业务层分页查询订单
	public PageBean<Order> findByPage(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//设置page
		pageBean.setPage(page);
		//设置limit
		int limit=10;
		pageBean.setLimit(limit);
		//获取订单总记录数
		int totalCount = orderDao.findCount();
		pageBean.setTotalCount(totalCount);
		//获取总页数
		int totalPage = 0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit+1;
		}
		//设置订单的总页数
		pageBean.setTotalPage(totalPage);
		//设置二级分类某页的集合
		int begin=(page-1)*limit;
		List<Order> list = orderDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//业务层根据订单id查询订单项
	public List<OrderItem> findOrderItemByOid(Integer oid) {
		return orderDao.findOrderItem(oid);
	}
	
}
