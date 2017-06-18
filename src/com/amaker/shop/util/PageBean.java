package com.amaker.shop.util;

import java.util.List;

/**
 * 设置分页的信息
 * @author ChangchunLong
 *
 */
public class PageBean<T> {
   private int page;//第几页
   private int totalPage;//总页数
   private int totalCount;//总记录数量
   private int limit;//每页记录限制
   private List<T> list;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
   
}
