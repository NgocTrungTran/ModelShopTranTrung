package com.tnt.modelshoptrantrung.service.product;


import com.tnt.modelshoptrantrung.model.Product;
import com.tnt.modelshoptrantrung.model.dto.ProductDTO;
import com.tnt.modelshoptrantrung.model.dto.ProductListDTO;
import com.tnt.modelshoptrantrung.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductListDTO> findAllProductListDTO() {
        return productRepository.findAllProductListDTO();
    }

    @Override
    public List<ProductDTO> findAllProductsDTO() {
        return productRepository.findAllProductsDTO ();
    }

    @Override
    public List<ProductDTO> findAllProductsDTOTrash() {
        return productRepository.findAllProductsDTOTrash ();
    }

    @Override
    public Boolean existsByTitle(String title) {
        return productRepository.existsByTitle ( title );
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product getById(Long id) {
        return null;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save ( product );
    }

    @Override
    public void delete(Long id) {

    }
}
