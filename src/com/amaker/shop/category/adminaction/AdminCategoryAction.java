package com.amaker.shop.category.adminaction;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.amaker.shop.category.bean.Category;
import com.amaker.shop.category.service.CategoryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
    //����ģ������
	private Category category = new Category();
	
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}
	//ע��categoryService
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//��ѯ���е�һ������
	public String findAll(){
		//ͨ��categoryservice��ȡ���е�һ������
		List<Category> cList = categoryService.findAll();
		//��һ������ļ��ϴ浽ֵջ��
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	//���һ������
	public String save(){
		// ����Service��ɱ���һ������
		categoryService.save(category);
		return "saveSuccess";
	}
	//ɾ��һ������ķ���
	public String delete(){
		//ʹ��ģ����������cid��ͬʱɾ����������
		category = categoryService.findByCid(category.getCid());
		//ɾ��һ������
		categoryService.delete(category);
		return "deleteSuccess";
	}
	//����cid����һ������
	public String edit(){
		//����cid��ѯһ������
		category = categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
    //����cid����һ������
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
}
