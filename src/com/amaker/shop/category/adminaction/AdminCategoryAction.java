package com.amaker.shop.category.adminaction;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.amaker.shop.category.bean.Category;
import com.amaker.shop.category.service.CategoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
    //接收模型驱动
	private Category category = new Category();
	
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}
	//注入categoryService
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//查询所有的一级分类
	public String findAll(){
		//通过categoryservice获取所有的一级分类
		List<Category> cList = categoryService.findAll();
		//将一级分类的集合存到值栈中
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	//添加一级分类
	public String save(){
		// 调用Service完成保存一级分类
		categoryService.save(category);
		return "saveSuccess";
	}
	//删除一级分类的方法
	public String delete(){
		//使用模型驱动接收cid，同时删除二级分类
		category = categoryService.findByCid(category.getCid());
		//删除一级分类
		categoryService.delete(category);
		return "deleteSuccess";
	}
	//根据cid接收一级分类
	public String edit(){
		//根据cid查询一级分类
		category = categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
    //根据cid更新一级分类
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
}
