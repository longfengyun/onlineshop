package com.amaker.shop.order.adminaction;

import java.util.List;

import com.amaker.shop.category.bean.Category;
import com.amaker.shop.order.bean.Order;
import com.amaker.shop.order.bean.OrderItem;
import com.amaker.shop.order.service.OrderService;
import com.amaker.shop.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ���������Action
 * @author ChangchunLong
 *
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{
	 //ģ���������ն���
	private Order order = new Order();

	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	//ע��orderService
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	//����page
	Integer page;
	public void setPage(int page) {
		this.page = page;
	}
    
	//��ҳ��ȡ�����б�
	public String findAll(){
		PageBean<Order> pageBean = orderService.findByPage(page);
		//��pageBean�浽ֵջ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//��ȡ������
	public String findOrderItem(){
		//����oid��ȡ������
		List<OrderItem> list = orderService.findOrderItemByOid(order.getOid());
		//�����������ֵջ��
	    ActionContext.getContext().getValueStack().set("list", list);
	    return "findOrderItem";
	}
	//�޸Ķ���״̬
    public String updateState(){
    	//���ݶ���id��ѯ����
    	Order currentOrder = orderService.findByOid(order.getOid());
    	//�ı䶩��״̬
    	currentOrder.setState(3);
    	orderService.update(currentOrder);
    	//ҳ����ת
    	return "updateStateSuccess";
    }
}
