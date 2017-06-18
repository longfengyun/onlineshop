package com.amaker.shop.categorysecond.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.amaker.shop.category.bean.Category;
import com.amaker.shop.product.bean.Product;

/**
 * ���������ʵ��
 * @author ����.����
 *
 */
public class CategorySecond  implements Serializable{
	private Integer csid;
	private String csname;
	// ����һ������.�����һ������Ķ���.
	private Category category;
	//������Ʒ�ļ���
	Set<Product> products = new HashSet<Product>();
	
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}	
}
