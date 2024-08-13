package com.fisa.land.fisaland.lending.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.lending.dto.ProductDTO;
import com.fisa.land.fisaland.lending.dto.ProductDTO.CreateProduct;
import com.fisa.land.fisaland.lending.dto.ProductDTO.getProduct;
import com.fisa.land.fisaland.lending.entity.Product;
import com.fisa.land.fisaland.lending.repository.ProductRepository;
import com.fisa.land.fisaland.market.dto.MarketDTO;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private HttpSession httpSession;

    @Transactional
	@Override
	public Long saveProduct(ProductDTO.CreateProduct createProduct) {
		Long userId = (Long) httpSession.getAttribute("userId");
		
		        Product product = new Product();
		        product.setProduct_name(createProduct.getProductName());
		        product.setDescription(createProduct.getDescription());
		        product.setPrice(createProduct.getPrice());
		        product.setCategory(createProduct.getCategory());
		        product.setStatus(Product.Status.AVAILABLE);
		        product.setUser_id(userId);
		
		        Product savedProduct =productRepository.save(product);
		        
		        return savedProduct.getUser_id();
		    }

	@Override
	public List<getProduct> getProductList() {
		List<ProductDTO.getProduct> products =  productRepository.findAll().stream()
				.map(product -> modelMapper.map(product, ProductDTO.getProduct.class))
				.collect(Collectors.toList());
		
		return products;

	}

  
}