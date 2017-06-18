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
 * 后台二级分类的action
 * @author ChangchunLong
 *
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	//模型驱动用于接收的对象
    private CategorySecond categorySecond = new CategorySecond();
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}
	//注入service
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	//注入接收page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	//注入categoryService
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//按页查询二级分类
	public String findAll(){
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		//将pageBean存到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//转到添加二级分类的页面（将所有一级分类带上）
	public String addPage(){
		List<Category> cList = categoryService.findAll();
		//放到值栈中
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}
	//保存二级分类
	public String save(){
		System.out.println(categorySecond.getCsname());
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	//根据二级分类id删除二级分类
	public String delete(){
		//如果级联删除，则先查询再 删除，配置casecade
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	//编辑二级分类
	public String edit(){
		//根据二级分类的id查询二级分类的对象
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		//查询所有的一级分类
		List<Category> cList = categoryService.findAll();
		//将一级分类放到值栈中
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	//更新二级分类
	public String update(){
		categorySecondService.update(categorySecond);
	    return "updateSuccess";
	}
}
