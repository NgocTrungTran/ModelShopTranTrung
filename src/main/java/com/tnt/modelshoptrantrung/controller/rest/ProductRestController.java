package com.tnt.modelshoptrantrung.controller.rest;

import com.tnt.modelshoptrantrung.exception.AttributesExistsException;
import com.tnt.modelshoptrantrung.exception.DataInputException;
import com.tnt.modelshoptrantrung.model.Category;
import com.tnt.modelshoptrantrung.model.Product;
import com.tnt.modelshoptrantrung.model.dto.CategoryDTO;
import com.tnt.modelshoptrantrung.model.dto.ProductDTO;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> doAddProduct(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult  ) {
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }

        String slug = Validator.makeSlug ( productDTO.getTitle () );
        CategoryDTO categoryDTO = productDTO.getCategory ();
        categoryDTO.setId ( 0L );
        Category category = categoryDTO.toCategory ();

        try {
            categoryService.save ( category );
            Product product = productDTO.toProduct ();
            product.setId ( 0L );
            product.setCategory ( category );
            product.setSlug ( slug );
            productService.save(product);

            return new ResponseEntity<>( HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            throw new DataInputException ("Product information is not valid, please check the information again");
        }
    }
}
