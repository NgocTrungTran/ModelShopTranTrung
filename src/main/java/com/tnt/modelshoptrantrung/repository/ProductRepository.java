package com.tnt.modelshoptrantrung.repository;


import com.tnt.modelshoptrantrung.model.Product;
import com.tnt.modelshoptrantrung.model.dto.ProductListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query("SELECT NEW com.tnt.modelshoptrantrung.model.dto.ProductListDTO (" +
                "p.id, " +
                "p.title, " +
                "p.slug, " +
                "p.image, " +
                "p.price " +
            ") " +
            "FROM Product AS p")
    List<ProductListDTO> findAllProductListDTO();
}
