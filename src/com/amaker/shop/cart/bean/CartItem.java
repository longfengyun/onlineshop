package com.amaker.shop.cart.bean;

import java.io.Serializable;

import com.amaker.shop.product.bean.Product;

/**
 * ���������
 * @author ChangchunLong
 *
 */
public class CartItem implements Serializable{
    private Product product;//��Ʒ����
    private Integer count;//��Ʒ����
    private double subTotal;//��ƷС��
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
	//С���Զ�����
	public double getSubTotal() {
		return count*product.getShop_price();
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
}
