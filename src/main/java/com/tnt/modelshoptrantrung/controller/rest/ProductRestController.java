package com.tnt.modelshoptrantrung.controller.rest;

import com.tnt.modelshoptrantrung.exception.AttributesExistsException;
import com.tnt.modelshoptrantrung.exception.DataInputException;
import com.tnt.modelshoptrantrung.exception.DataOutputException;
import com.tnt.modelshoptrantrung.model.Category;
import com.tnt.modelshoptrantrung.model.Product;
import com.tnt.modelshoptrantrung.model.dto.CategoryDTO;
import com.tnt.modelshoptrantrung.model.dto.ProductDTO;
import com.tnt.modelshoptrantrung.model.dto.ProductListDTO;
import com.tnt.modelshoptrantrung.model.dto.UserDTO;
import com.tnt.modelshoptrantrung.service.category.CategoryService;
import com.tnt.modelshoptrantrung.service.product.ProductService;
import com.tnt.modelshoptrantrung.util.AppUtil;
import com.tnt.modelshoptrantrung.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    @Autowired
    private AppUtil appUtil;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> getAllProducts() {
        List<ProductDTO> productDTOList = productService.findAllProductsDTO ();

        if (productDTOList.isEmpty ()) {
            throw new DataOutputException ( "No data" );
        }

        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doAddProduct(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult  ) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }

        String slug = Validator.makeSlug ( productDTO.getTitle () );

        productDTO.getCategory ().setId ( 0L );
        Category category = categoryService.save ( productDTO.getCategory ().toCategory () );
        productDTO.setCategory ( category.toCategoryDTO () );

        try {
            Product product = productDTO.toProduct ();
            product.setSlug ( slug );
            product.setCreatedBy ( productDTO.getCreatedBy () );
            Product newProduct = productService.save(product);

            return new ResponseEntity<>( newProduct.toProductDTO (), HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            throw new DataInputException ("Product information is not valid, please check the information again");
        }
    }
}
