package com.tnt.modelshoptrantrung.service.product;


import com.tnt.modelshoptrantrung.model.Product;
import com.tnt.modelshoptrantrung.model.dto.ProductDTO;
import com.tnt.modelshoptrantrung.model.dto.ProductListDTO;
import com.tnt.modelshoptrantrung.service.IGeneralService;

import java.util.List;

public interface ProductService extends IGeneralService<Product> {

    List<ProductListDTO> findAllProductListDTO();

    List<ProductDTO> findAllProductsDTO();

}
