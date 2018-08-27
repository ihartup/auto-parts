package com.home.autoparts.storage.datasource.impl;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Ignored;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.home.autoparts.api.repository.ProductRepository;
import com.home.autoparts.domain.GenericProduct;
import com.home.autoparts.storage.datasource.domain.MongoProduct;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/app-storage-test.xml"})
@Ignored
public class ProductRepositoryImplTest {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void testInsertAndRetrieveOneProduct()  throws Exception {
		MongoProduct gp = new MongoProduct.MongoProductBuilder()
									.manufacturer("Bosch")
									.name("SpringMX-01")
									.offerDate(new Date())
									.price(10.05)
									.stock(20L)
									.build();
		GenericProduct loadedGp = productRepository.insert(gp);
		Assert.assertNotNull(loadedGp);
		gp.setId(loadedGp.getId());
		Assert.assertEquals(gp, loadedGp);
		
	}
	
	@Test
	public void testInsertOneProductAndRetrieveByList()  throws Exception {
		MongoProduct gp = new MongoProduct.MongoProductBuilder()
									.manufacturer("Bosch")
									.name("SpringMX-01")
									.offerDate(new Date())
									.price(10.05)
									.stock(20L)
									.build();
		productRepository.insert(gp);
		
		List<GenericProduct> gpList = productRepository.findAll();
		Assert.assertTrue("There should be just one product inserted", gpList.size() == 1);
		GenericProduct loadedGp = gpList.get(0);
		gp.setId(loadedGp.getId());
		Assert.assertEquals(gp, loadedGp);
	}
	
	@Test
	public void testInsertOneProductAndRetrieveByName()  throws Exception {
		GenericProduct gp = new MongoProduct.MongoProductBuilder()
									.manufacturer("Bosch")
									.name("SpringMX-01")
									.offerDate(new Date())
									.price(10.05)
									.stock(20L)
									.build();
		productRepository.insert(gp);
		
		GenericProduct loadedGp = productRepository.getByName("SpringMX-01");
		Assert.assertNotNull(loadedGp);
		Assert.assertEquals(gp, loadedGp);
	}
	
	@Test
	public void testUpdateOneProduct()  throws Exception {
		GenericProduct gp = new MongoProduct.MongoProductBuilder()
									.manufacturer("Bosch")
									.name("SpringMX-01")
									.offerDate(new Date())
									.price(10.05)
									.stock(20L)
									.build();
		MongoProduct loadedGp = (MongoProduct)productRepository.insert(gp);
		loadedGp.setManufacturer("Valeo");
		GenericProduct updatedGp = productRepository.save(loadedGp);
		Assert.assertNotNull(updatedGp);
		Assert.assertEquals(loadedGp, updatedGp);
		updatedGp = productRepository.getByName("SpringMX-01");
		Assert.assertNotNull(updatedGp);
		Assert.assertEquals(loadedGp, updatedGp);
	}
	
	
	@Test
	public void testDeleteProduct()  throws Exception {
		GenericProduct gp = new MongoProduct.MongoProductBuilder()
									.manufacturer("Bosch")
									.name("SpringMX-01")
									.offerDate(new Date())
									.price(10.05)
									.stock(20L)
									.build();
		GenericProduct loadedGp = productRepository.insert(gp);
		Assert.assertNotNull(loadedGp);
		boolean exists = productRepository.exists(loadedGp);
		Assert.assertTrue("The product exists", exists);
		productRepository.delete(loadedGp);
		GenericProduct deletedGp = productRepository.getByName("SpringMX-01");
		Assert.assertNull(deletedGp);
		exists = productRepository.exists(loadedGp);
		Assert.assertFalse("The product does not exist", exists);
	}
	
	@Test
	public void testExistsProduct()  throws Exception {
		GenericProduct gp = new MongoProduct.MongoProductBuilder()
									.manufacturer("Bosch")
									.name("SpringMX-01")
									.offerDate(new Date())
									.price(10.05)
									.stock(20L)
									.build();
		GenericProduct loadedGp = productRepository.insert(gp);
		Assert.assertNotNull(loadedGp);
		boolean exists = productRepository.exists(loadedGp);
		Assert.assertTrue("The product exists", exists);
	}
	
	@Test
	public void testNotExistProduct()  throws Exception {
		MongoProduct gp = new MongoProduct.MongoProductBuilder()
									.manufacturer("Bosch")
									.name("SpringMX-01")
									.offerDate(new Date())
									.price(10.05)
									.stock(20L)
									.build();
		gp.setId("x");
		boolean exists = productRepository.exists(gp);
		Assert.assertFalse("The product does not exist", exists);
	}
	
	@After
	public void after() {
		productRepository.deleteAll();
	}
	
}
