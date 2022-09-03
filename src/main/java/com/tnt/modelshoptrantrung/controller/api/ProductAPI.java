//package com.tnt.modelshoptrantrung.controller.api;
//
//
//import com.tnt.modelshoptrantrung.exception.DataInputException;
//import com.tnt.modelshoptrantrung.model.Product;
//import com.tnt.modelshoptrantrung.model.dto.IProductDTO;
//import com.tnt.modelshoptrantrung.model.dto.ProductDTO;
//import com.tnt.modelshoptrantrung.service.product.ProductService;
//import com.tnt.modelshoptrantrung.util.AppUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//
//@RestController
//@RequestMapping("/api/products")
//public class ProductAPI {
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private AppUtil appUtils;
//
//
//    @GetMapping
//    public ResponseEntity<Iterable<?>> findAll() {
//        try {
//            Iterable<IProductDTO> iProductDTOS = productService.findAllIProductDTO();
//
//            if (((List<?>) iProductDTOS).isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(iProductDTOS, HttpStatus.OK);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<?> create(@Validated ProductDTO productDTO, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors())
//            return appUtils.mapErrorToResponse(bindingResult);
//
//        try {
//            Product createdProduct = productService.create(productDTO);
//
//            IProductDTO iProductDTO =  productService.findIProductDTOById(createdProduct.getId());
//
//            return new ResponseEntity<>(iProductDTO, HttpStatus.CREATED);
//
//        } catch (DataIntegrityViolationException e) {
//            throw new DataInputException ("Product creation information is not valid, please check the information again");
//        }
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> delete(@PathVariable String id) throws IOException {
//
//        Optional<Product> product = productService.findById(id);
//
//        if (product.isPresent()) {
//            productService.delete(product.get());
//
//            return new ResponseEntity<>(HttpStatus.ACCEPTED);
//        } else {
//            throw new DataInputException("Invalid product information");
//        }
//    }
//
//}