package com.amaker.shop.index.action;

import java.util.List;

import com.amaker.shop.category.bean.Category;
import com.amaker.shop.category.service.CategoryService;
import com.amaker.shop.product.bean.Product;
import com.amaker.shop.product.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 首页访问的Action
 * @author ChangchunLong
 *
 */
@SuppressWarnings("serial")
public class IndexAction extends ActionSupport {
    //注入一级分类的Service;
	private CategoryService categoryService;
	//注入商品的Service;
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public String execute() throws Exception {
		//获取所有的一级分类的list
		List<Category> cList=categoryService.findAll();
		//存入到Session中，因为每个页面都需要
		ActionContext.getContext().getSession().put("cList", cList);
		
		//查询热门商品、
		List<Product> hList = productService.findHotProduct();
		//保存到值栈
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		//查询最新商品、
		List<Product> nList = productService.findNewProduct();
		//保存到值栈
		ActionContext.getContext().getValueStack().set("nList", nList);
		
		//转向首页
		return "index";
	}
     
}
