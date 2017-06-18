package com.amaker.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.amaker.shop.cart.bean.Cart;
import com.amaker.shop.cart.bean.CartItem;
import com.amaker.shop.product.bean.Product;
import com.amaker.shop.product.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 购物车Action
 * @author ChangchunLong
 *
 */
public class CartAction extends ActionSupport{
    //接收参数pid
	private Integer pid;
    //注入ProductService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	//接收count
	private Integer count;

	public void setCount(Integer count) {
		this.count = count;
	}

	//加入购物车
	public String addCart(){
		//构建一个CartItem的对象
		CartItem cartItem = new CartItem();
		//设置count
		cartItem.setCount(count);
		//根据pid获取商品
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		//获取cart
		Cart cart = getCart();
		//添加cartItem到购物车
		cart.addCart(cartItem);
		return "addCart";
	}
	//清空购物车
	public String clearCart(){
		//获取购物车
		Cart cart = getCart();
		//清空购物车
		cart.clearCart();
		return "clearCart";
	}
	//移除购物车
	public String removeCart(){
		//获取购物车
		Cart cart = getCart();
		//清空购物车
		cart.removeCart(pid);
		return "removeCart";
	}
	//获得购物车
	public String myCart(){
		return "myCart";
	}
	/**
	 * 从session中获取cart购物车
	 * @return
	 */
	public Cart getCart(){
		//从session中获取Cart,如果为空新建一个Cart,不为空则获取
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null){
			cart = new Cart();
			 ServletActionContext.getRequest().getSession().setAttribute("cart",cart);
		}
		return cart;
	}
     
}
