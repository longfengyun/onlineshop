package com.amaker.shop.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.amaker.shop.product.dao.ProductDao;

public class ProductDaoTest {
    ProductDao pd;
	@Before
	public void setUp() throws Exception {
		pd = new ProductDao();
	}

	@After
	public void tearDown() throws Exception {
	}

	/*@Test
	public void testFindHotProduct() {
		System.out.println(pd.findHotProduct().size());
	}*/

	@Test
	public void testFindNewProduct() {
		System.out.println(pd.findHotProduct().size());
	}

	/*@Test
	public void testFindByPid() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByPageCid() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountCid() {
		fail("Not yet implemented");
	}*/

}
