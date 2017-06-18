package com.amaker.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.amaker.shop.cart.bean.Cart;
import com.amaker.shop.cart.bean.CartItem;
import com.amaker.shop.product.bean.Product;
import com.amaker.shop.product.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
/**
 * ���ﳵAction
 * @author ChangchunLong
 *
 */
public class CartAction extends ActionSupport{
    //���ղ���pid
	private Integer pid;
    //ע��ProductService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	//����count
	private Integer count;

	public void setCount(Integer count) {
		this.count = count;
	}

	//���빺�ﳵ
	public String addCart(){
		//����һ��CartItem�Ķ���
		CartItem cartItem = new CartItem();
		//����count
		cartItem.setCount(count);
		//����pid��ȡ��Ʒ
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		//��ȡcart
		Cart cart = getCart();
		//���cartItem�����ﳵ
		cart.addCart(cartItem);
		return "addCart";
	}
	//��չ��ﳵ
	public String clearCart(){
		//��ȡ���ﳵ
		Cart cart = getCart();
		//��չ��ﳵ
		cart.clearCart();
		return "clearCart";
	}
	//�Ƴ����ﳵ
	public String removeCart(){
		//��ȡ���ﳵ
		Cart cart = getCart();
		//��չ��ﳵ
		cart.removeCart(pid);
		return "removeCart";
	}
	//��ù��ﳵ
	public String myCart(){
		return "myCart";
	}
	/**
	 * ��session�л�ȡcart���ﳵ
	 * @return
	 */
	public Cart getCart(){
		//��session�л�ȡCart,���Ϊ���½�һ��Cart,��Ϊ�����ȡ
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null){
			cart = new Cart();
			 ServletActionContext.getRequest().getSession().setAttribute("cart",cart);
		}
		return cart;
	}
     
}
