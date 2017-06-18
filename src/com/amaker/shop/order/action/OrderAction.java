package com.amaker.shop.order.action;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.amaker.shop.cart.bean.Cart;
import com.amaker.shop.cart.bean.CartItem;
import com.amaker.shop.order.bean.Order;
import com.amaker.shop.order.bean.OrderItem;
import com.amaker.shop.order.service.OrderService;
import com.amaker.shop.user.bean.User;
import com.amaker.shop.util.PageBean;
import com.amaker.shop.util.PaymentUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * ����ģ���Action
 * @author ChangchunLong
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	// ģ������ʹ�õĶ���
	private Order order = new Order();

	public Order getModel() {
		return order;
	}
	//ע��orderService
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	// ����page
	private Integer page;
	
    public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	//����֧��ͨ������
	private String pd_FrpId;
	
	public String getPd_FrpId() {
		return pd_FrpId;
	}
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	// ���ո���ɹ���Ĳ���:
	private String r3_Amt;
	private String r6_Order;
	
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	//���涩��order
	public String saveOrder() throws ParseException{
		// ����Service������ݿ����Ĳ���:
		// Order order = new Order();
		// ���ö������ܽ��:�������ܽ��Ӧ���ǹ��ﳵ���ܽ��:
		// ���ﳵ��session��,��session�ܻ�ù��ﳵ��Ϣ.
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			this.addActionMessage("��!����û�й���!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// ���ö�����״̬
		order.setState(1); // 1:δ����. 2.�Ѹ����δ����  3. �Ѹ���ѷ���  4.�Ѿ��ջ�
		// ���ö���ʱ��
		Date date=new Date();                             
        SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date2=temp.format(date);  
        Date date3=temp.parse(date2);  
		order.setOrdertime(date3);
		// ���ö��������Ŀͻ�:
		User existUser = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("existUser");
		if (existUser == null) {
			this.addActionMessage("��!����û�е�¼!");
			return "msg";
		}
		order.setUser(existUser);
		// ���ö������:
		for (CartItem cartItem : cart.getCartItems()) {
			// ���������Ϣ�ӹ������õ�.
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubTotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		orderService.save(order);
		// ��չ��ﳵ:
		cart.clearCart();

		// ҳ����Ҫ���Զ�����Ϣ:
		// ʹ��ģ�������� ���п��Բ�ʹ��ֵջ������
		// ActionContext.getContext().getValueStack().set("order", order);

		return "saveOrder";
	}
	//�����û�id��ѯ����
	public String findByUid(){
		//��ȡ�û�id,��session��
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		Integer uid = existUser.getUid();
		//�����û�id��service�в�ѯ����
		PageBean<Order> pageBean = orderService.findByUid(uid,page);
		// ��PageBean���ݴ���ҳ����(ֵջ).
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}
	//���ݶ���id��ѯ����
	public String findByOid(){
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
	//����֧������oid
	public String payOrder() throws IOException{
		// 1.�޸����ݿ��ж�������:
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		// �޸Ķ���
		orderService.update(currOrder);
		//Ϊ��������
		// ������Ҫ�Ĳ���:
		String p0_Cmd = "Buy"; // ҵ������:
		String p1_MerId = "10001126856";// �̻����:
		String p2_Order = order.getOid().toString();// �������:
		String p3_Amt = "0.01"; // ������:
		String p4_Cur = "CNY"; // ���ױ���:
		String p5_Pid = ""; // ��Ʒ����:
		String p6_Pcat = ""; // ��Ʒ����:
		String p7_Pdesc = ""; // ��Ʒ����:
		String p8_Url = "http://115.156.186.193:8080/shop/order_callBack.action"; // �̻�����֧���ɹ����ݵĵ�ַ:
		String p9_SAF = ""; // �ͻ���ַ:
		String pa_MP = ""; // �̻���չ��Ϣ:
		String pd_FrpId = this.pd_FrpId;// ֧��ͨ������:
		String pr_NeedResponse = "1"; // Ӧ�����:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // ��Կ
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// ���ױ���������:
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		// �ض���:���ױ�����:
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}
	// ����ɹ�����ת������·��:
	public String callBack(){
		// �޸Ķ�����״̬:
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		// �޸Ķ���״̬Ϊ2:�Ѿ�����:
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("֧���ɹ�!�������Ϊ: "+r6_Order +" ������Ϊ: "+r3_Amt);
		return "msg";
	}
	//�޸�ǰ̨����״̬
    public String updateState(){
    	//���ݶ���id��ѯ����
    	Order currentOrder = orderService.findByOid(order.getOid());
    	//�ı䶩��״̬
    	currentOrder.setState(4);
    	orderService.update(currentOrder);
    	//ҳ����ת
    	return "updateStateSuccess";
    }
}
