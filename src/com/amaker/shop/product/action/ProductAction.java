package com.amaker.shop.product.action;

import java.util.List;

import com.amaker.shop.category.bean.Category;
import com.amaker.shop.category.service.CategoryService;
import com.amaker.shop.product.bean.Product;
import com.amaker.shop.product.service.ProductService;
import com.amaker.shop.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	//���ڽ���ģ��������product
    private Product product = new Product();
    //ע��productService
    private ProductService productService;
    //ע��categoryService
    private CategoryService categoryService;
    //���ڽ�����Ʒһ������id
    private Integer cid;
  //���ڽ�����Ʒ��������id
    private Integer csid;
    
    public int getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	//���ڽ���ҳ�棨�ڼ�ҳ��
    private int page;
    
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
    
	//������Ʒpid��ѯ��Ʒ
	public String findByPid(){
		// ����Service�ķ�����ɲ�ѯ.
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	//������Ʒһ�������ѯ���е���Ʒ
	public String findByCid(){
		//����һ�������ѯ�����еĶ������ࣨ������ѯ��
		List<Category> cList = categoryService.findAll();
		//����ֵջ��
		ActionContext.getContext().getValueStack().set("cList", cList);
		
		//����һ�������ѯ����ҳ����Ʒ,������ֵջ��
		PageBean<Product> pageBean = productService.findByCid(cid,page);
		// ��PageBean���뵽ֵջ��:
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	//������Ʒ���������ѯ���е���Ʒ
	public String findByCsid(){
		//���ݶ��������ѯ����ҳ����Ʒ,������ֵջ��
		PageBean<Product> pageBean = productService.findByCsid(csid,page);
		// ��PageBean���뵽ֵջ��:
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
