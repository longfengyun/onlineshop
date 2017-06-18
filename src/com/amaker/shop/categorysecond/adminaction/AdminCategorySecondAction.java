package com.amaker.shop.categorysecond.adminaction;

import java.util.List;

import com.amaker.shop.category.bean.Category;
import com.amaker.shop.category.service.CategoryService;
import com.amaker.shop.categorysecond.bean.CategorySecond;
import com.amaker.shop.categorysecond.service.CategorySecondService;
import com.amaker.shop.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ��̨���������action
 * @author ChangchunLong
 *
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	//ģ���������ڽ��յĶ���
    private CategorySecond categorySecond = new CategorySecond();
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}
	//ע��service
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	//ע�����page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	//ע��categoryService
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//��ҳ��ѯ��������
	public String findAll(){
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		//��pageBean�浽ֵջ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//ת����Ӷ��������ҳ�棨������һ��������ϣ�
	public String addPage(){
		List<Category> cList = categoryService.findAll();
		//�ŵ�ֵջ��
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}
	//�����������
	public String save(){
		System.out.println(categorySecond.getCsname());
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	//���ݶ�������idɾ����������
	public String delete(){
		//�������ɾ�������Ȳ�ѯ�� ɾ��������casecade
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	//�༭��������
	public String edit(){
		//���ݶ��������id��ѯ��������Ķ���
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		//��ѯ���е�һ������
		List<Category> cList = categoryService.findAll();
		//��һ������ŵ�ֵջ��
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	//���¶�������
	public String update(){
		categorySecondService.update(categorySecond);
	    return "updateSuccess";
	}
}
