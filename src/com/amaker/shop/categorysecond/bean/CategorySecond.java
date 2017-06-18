package com.amaker.shop.categorysecond.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.amaker.shop.category.bean.Category;
import com.amaker.shop.product.bean.Product;

/**
 * 二级分类的实体
 * @author 传智.郭嘉
 *
 */
public class CategorySecond  implements Serializable{
	private Integer csid;
	private String csname;
	// 所属一级分类.存的是一级分类的对象.
	private Category category;
	//包含商品的集合
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
