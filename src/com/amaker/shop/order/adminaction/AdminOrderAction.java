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
 * 订单管理的Action
 * @author ChangchunLong
 *
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{
	 //模型驱动接收对象
	private Order order = new Order();

	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	//注入orderService
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	//接收page
	Integer page;
	public void setPage(int page) {
		this.page = page;
	}
    
	//分页获取订单列表
	public String findAll(){
		PageBean<Order> pageBean = orderService.findByPage(page);
		//将pageBean存到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//获取订单项
	public String findOrderItem(){
		//根据oid获取订单项
		List<OrderItem> list = orderService.findOrderItemByOid(order.getOid());
		//将订单项放入值栈中
	    ActionContext.getContext().getValueStack().set("list", list);
	    return "findOrderItem";
	}
	//修改订单状态
    public String updateState(){
    	//根据订单id查询订单
    	Order currentOrder = orderService.findByOid(order.getOid());
    	//改变订单状态
    	currentOrder.setState(3);
    	orderService.update(currentOrder);
    	//页面跳转
    	return "updateStateSuccess";
    }
}
