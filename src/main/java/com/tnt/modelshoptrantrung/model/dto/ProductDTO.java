package com.tnt.modelshoptrantrung.model.dto;

import com.tnt.modelshoptrantrung.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductDTO {
    private Long id;

    private String title;

    private String slug;

    private String image;

    private BigDecimal price;

    private CategoryDTO category;

    public Product toProduct() {
        return new Product ()
                .setId ( id )
                .setTitle ( title )
                .setSlug ( slug )
                .setPrice ( price )
                .setImage ( image )
                .setCategory ( category.toCategory () );

    }
}
