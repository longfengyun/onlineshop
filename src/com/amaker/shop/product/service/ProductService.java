package com.amaker.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.amaker.shop.product.bean.Product;
import com.amaker.shop.product.dao.ProductDao;
import com.amaker.shop.util.PageBean;

/**
 * 商品的业务层代码
 * @author ChangchunLong
 *
 */
@Transactional
public class ProductService {
    //注入productDao
	private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
    //业务层查询热门商品
	public List<Product> findHotProduct() {
		return productDao.findHotProduct();
	}
	//业务层查询最新商品
	public List<Product> findNewProduct() {
		return productDao.findNewProduct();
	}
	//业务层根据商品id查询商品
	public Product findByPid(Integer pid) {
		System.out.println("findByPidService");
		return productDao.findByPid(pid);
	}
	//业务层根据一级分类查询带分页的商品
	public PageBean<Product> findByCid(int cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		//设置分页大小
		int limit=8;
		pageBean.setLimit(limit);
		//获取相应一级分类的总记录
		int totalCount = productDao.countCid(cid);
		
		pageBean.setTotalCount(totalCount);
		//获取相应一级分类的总页数
		int totalPage =0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		int begin =(page-1)*limit;
		//获取相应分页的商品集合
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//业务层根据二级分类查询分页的商品
	public PageBean<Product> findByCsid(int csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		//设置分页大小
		int limit=8;
		pageBean.setLimit(limit);
		//获取相应二级分类的总记录
		int totalCount = productDao.countCsid(csid);
		pageBean.setTotalCount(totalCount);
		//获取相应二级分类的总页数
		int totalPage =0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		int begin =(page-1)*limit;
		//获取相应分页的商品集合
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	//业务层查询商品（按分页）
	public PageBean<Product> findAll(Integer page) {
		PageBean<Product> pageBean = new PageBean();
		//设置page
		pageBean.setPage(page);
		//设置limit
		int limit=10;
		pageBean.setLimit(limit);
		//获取总商品记录数并设置
		int totalCount = productDao.findCountProduct();
		pageBean.setTotalCount(totalCount);
		//设置商品总页数
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//获取商品集合
		int begin = (page-1)*limit;
		List<Product> list = productDao.findByPageCid(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	//业务层保存商品
	public void save(Product product) {
		productDao.save(product);
	}
	//业务层删除商品
	public void delete(Product product) {
         productDao.delete(product);	
	}
	//业务层修改商品
	public void update(Product product) {
		productDao.update(product);
	}
}
