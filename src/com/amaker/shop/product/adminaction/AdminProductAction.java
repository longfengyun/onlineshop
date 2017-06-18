package com.amaker.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.amaker.shop.categorysecond.bean.CategorySecond;
import com.amaker.shop.categorysecond.service.CategorySecondService;
import com.amaker.shop.product.bean.Product;
import com.amaker.shop.product.service.ProductService;
import com.amaker.shop.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台商品管理的Action
 * @author ChangchunLong
 *
 */
public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
    //模型驱动接收的对象
	private Product product = new Product();
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	//注入productService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//接收page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	//注入二级分类categoryService
	private CategorySecondService categorySecondService;
	
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	//文件上传需要的三个参数；
	private File upload;//上传的文件
	private String uploadFileName;//接收文件上传的文件名
	private String uploadContextType;//文件上传的MIME类型。即文件后缀
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}
	//按照分页查询商品
	public String findAll(){
		//返回pageBean
		PageBean<Product> pageBean = productService.findAll(page);
		//将pageBean放入值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	//跳转到添加商品的页面
	public String addPage(){
		//1.先查询所有的二级分类的名称
		List<CategorySecond>  csList = categorySecondService.findAll();
		//将csList放置到值栈中
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
	}
	//保存商品
	public String save() throws IOException, ParseException{
		// 将提交的数据添加到数据库中.
		Date date=new Date();                             
        SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date2=temp.format(date);  
        Date date3=temp.parse(date2);  
		product.setPdate(date3);
		// product.setImage(image);
		if(upload != null){
			// 将商品图片上传到服务器上.
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products/1");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);
	
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}
	//商品删除
	public String delete(){
		//先查询商品后删除
        product = productService.findByPid(product.getPid());
		//删除上传的商品图片
        String path = product.getImage();
        if(path!=null){
        	String realPath = ServletActionContext.getServletContext().getRealPath("/"+path);
        	File file =new File(realPath);
        	file.delete();
        }
		//删除商品
		productService.delete(product);
		return "deleteSuccess";
	}
	//转到商品修改页面
	public String edit(){
		//先根据商品id查询商品
		product = productService.findByPid(product.getPid());
		//查询所有的二级分类
		List<CategorySecond> csList = categorySecondService.findAll();
	    //将list集合放到值栈中
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}
	//修改商品
	public String update() throws Exception{
		Date date=new Date();                             
        SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date2=temp.format(date);  
        Date date3=temp.parse(date2);  
		product.setPdate(date3);
		// product.setImage(image);
		if(upload != null){
			//将原来商品的图片删除
			String path1 = product.getImage();
	        if(path1!=null){
	        	String realPath = ServletActionContext.getServletContext().getRealPath("/"+path1);
	        	File file =new File(realPath);
	        	file.delete();
	        }
			// 将商品图片上传到服务器上.
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products/1");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);
			//删除上传的商品图片
			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		return "updateSuccess";
	}
}
