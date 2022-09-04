package com.tnt.modelshoptrantrung.model.dto;

import com.tnt.modelshoptrantrung.model.Category;
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

    private Long sold;

    private Long viewed;
    private String createdBy;

    private CategoryDTO category;

    public ProductDTO(Long id, String title, String slug, String image, BigDecimal price, Long sold, Long viewed, String createdBy, Category category) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.image = image;
        this.price = price;
        this.sold = sold;
        this.viewed = viewed;
        this.createdBy = createdBy;
        this.category = category.toCategoryDTO ();
    }

    public Product toProduct() {
        return (Product) new Product ()
                .setId ( id )
                .setTitle ( title )
                .setSlug ( slug )
                .setPrice ( price )
                .setImage ( image )
                .setCategory ( category.toCategory () )
                .setCreatedBy ( createdBy )
                ;

    }
}
