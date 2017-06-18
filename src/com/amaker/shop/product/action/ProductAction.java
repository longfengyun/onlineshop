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
	//用于接收模型驱动的product
    private Product product = new Product();
    //注入productService
    private ProductService productService;
    //注入categoryService
    private CategoryService categoryService;
    //用于接收商品一级分类id
    private Integer cid;
  //用于接收商品二级分类id
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
	//用于接收页面（第几页）
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
    
	//根据商品pid查询商品
	public String findByPid(){
		// 调用Service的方法完成查询.
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	//根据商品一级分类查询所有的商品
	public String findByCid(){
		//根据一级分类查询到所有的二级分类（关联查询）
		List<Category> cList = categoryService.findAll();
		//存入值栈中
		ActionContext.getContext().getValueStack().set("cList", cList);
		
		//根据一级分类查询带分页的商品,并存入值栈中
		PageBean<Product> pageBean = productService.findByCid(cid,page);
		// 将PageBean存入到值栈中:
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	//根据商品二级分类查询所有的商品
	public String findByCsid(){
		//根据二级分类查询带分页的商品,并存入值栈中
		PageBean<Product> pageBean = productService.findByCsid(csid,page);
		// 将PageBean存入到值栈中:
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
