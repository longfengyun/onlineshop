package com.amaker.shop.order.service;

import java.util.List;

import com.amaker.shop.categorysecond.bean.CategorySecond;
import com.amaker.shop.order.bean.Order;
import com.amaker.shop.order.bean.OrderItem;
import com.amaker.shop.order.dao.OrderDao;
import com.amaker.shop.util.PageBean;

/**
 * ����ģ���ҵ���ģ��
 * @author ChangchunLong
 *
 */
public class OrderService {
    //ע��orderDao;
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
    //ҵ��㱣�涩��
	public void save(Order order) {
		orderDao.save(order);
	}
	//ҵ�������û�id��ѯ�����б�
	public PageBean<Order> findByUid(Integer uid, Integer page) {
		PageBean<Order>  pageBean = new PageBean<Order>();
		//����pageBean
		pageBean.setPage(page);
		//����ҳ����ʾ��������
		int limit=5;
		pageBean.setLimit(limit);
		//�����ܼ�¼��
		int totalCount = orderDao.findCountUid(uid);
		//������ҳ��
		int totalPage= 0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		//������ʼ����λ��
		int begin=(page-1)*limit;
		//��ȡ�����б�
		List<Order> list = orderDao.findByPageUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//ҵ�����ݶ���id��ѯ����
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}
	//ҵ����޸Ķ���
	public void update(Order currOrder) {
		orderDao.update(currOrder);
		
	}
	//ҵ����ҳ��ѯ����
	public PageBean<Order> findByPage(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//����page
		pageBean.setPage(page);
		//����limit
		int limit=10;
		pageBean.setLimit(limit);
		//��ȡ�����ܼ�¼��
		int totalCount = orderDao.findCount();
		pageBean.setTotalCount(totalCount);
		//��ȡ��ҳ��
		int totalPage = 0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit+1;
		}
		//���ö�������ҳ��
		pageBean.setTotalPage(totalPage);
		//���ö�������ĳҳ�ļ���
		int begin=(page-1)*limit;
		List<Order> list = orderDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//ҵ�����ݶ���id��ѯ������
	public List<OrderItem> findOrderItemByOid(Integer oid) {
		return orderDao.findOrderItem(oid);
	}
	
}
