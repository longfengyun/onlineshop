package com.amaker.shop.cart.bean;

import java.io.Serializable;

import com.amaker.shop.product.bean.Product;

/**
 * 购物项对象
 * @author ChangchunLong
 *
 */
public class CartItem implements Serializable{
    private Product product;//商品对象
    private Integer count;//商品数量
    private double subTotal;//商品小计
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	//小计自动计算
	public double getSubTotal() {
		return count*product.getShop_price();
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
}
