package com.tnt.modelshoptrantrung.model.dto;

import com.tnt.modelshoptrantrung.model.Category;
import com.tnt.modelshoptrantrung.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductDTO implements Validator {
    private Long id;

//    @NotBlank(message = "Title not null")
    private String title;

    private String slug;

//    @NotBlank(message = "Image not null")
    private String image;

//    @NotBlank(message = "Price not null")
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

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductDTO.class.isAssignableFrom ( clazz );
    }


    @Override
    public void validate(Object o, Errors errors) {
        ProductDTO productDTO = (ProductDTO) o;
        String price = productDTO.getPrice().toString();


        if (!com.tnt.modelshoptrantrung.util.Validator.isNumberValid(price)) {

            if (price == null || price.equals("")) {
                errors.rejectValue("price", "400", "Price not null!");
            } else {
                errors.rejectValue("price", "400", "Price invalid!");
            }

        } else {
            if (price.length() > 9) {
                errors.rejectValue("price", "400", "Max price is 100.000.000đ!");
            } else {

                long validPrice = Long.parseLong(price);
                if (validPrice < 99999) {
                    errors.rejectValue("price", "400", "Min price is 100.000đ!");
                }

                if (validPrice > 100000000) {
                    errors.rejectValue("price", "400", "Max price is 100.000.000đ!");
                }
            }
        }
    }
}
