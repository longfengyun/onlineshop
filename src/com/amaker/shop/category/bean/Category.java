package com.amaker.shop.category.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.amaker.shop.categorysecond.bean.CategorySecond;

/**
 * category的实体类
 * @author ChangchunLong
 *
 */
public class Category implements Serializable{
	  //`cid` int(11) NOT NULL AUTO_INCREMENT,
	 // `cname` varchar(255) DEFAULT NULL,
	 // PRIMARY KEY (`cid`)
	private Integer cid;
	private String cname;
	//包含二级分类的集合
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
}
