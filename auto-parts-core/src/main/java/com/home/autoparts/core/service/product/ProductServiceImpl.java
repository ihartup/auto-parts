package com.home.autoparts.core.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.autoparts.api.repository.ProductRepository;
import com.home.autoparts.api.service.ProductService;
import com.home.autoparts.domain.GenericProduct;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void createProduct(GenericProduct product) {
		GenericProduct insertedProduct = productRepository.insert(product);
	}

}
