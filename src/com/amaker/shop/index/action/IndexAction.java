package com.amaker.shop.index.action;

import java.util.List;

import com.amaker.shop.category.bean.Category;
import com.amaker.shop.category.service.CategoryService;
import com.amaker.shop.product.bean.Product;
import com.amaker.shop.product.service.ProductService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ��ҳ���ʵ�Action
 * @author ChangchunLong
 *
 */
@SuppressWarnings("serial")
public class IndexAction extends ActionSupport {
    //ע��һ�������Service;
	private CategoryService categoryService;
	//ע����Ʒ��Service;
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public String execute() throws Exception {
		//��ȡ���е�һ�������list
		List<Category> cList=categoryService.findAll();
		//���뵽Session�У���Ϊÿ��ҳ�涼��Ҫ
		ActionContext.getContext().getSession().put("cList", cList);
		
		//��ѯ������Ʒ��
		List<Product> hList = productService.findHotProduct();
		//���浽ֵջ
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		//��ѯ������Ʒ��
		List<Product> nList = productService.findNewProduct();
		//���浽ֵջ
		ActionContext.getContext().getValueStack().set("nList", nList);
		
		//ת����ҳ
		return "index";
	}
     
}
