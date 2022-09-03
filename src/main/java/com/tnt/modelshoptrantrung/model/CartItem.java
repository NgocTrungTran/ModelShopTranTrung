package com.tnt.modelshoptrantrung.model;

import com.tnt.modelshoptrantrung.model.dto.CartItemListDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart_items")
@Accessors(chain = true)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    private String title;

    @Column(precision = 9, scale = 0, nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantity;

    @Column(precision = 9, scale = 0, nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    private Cart cart;

    public CartItemListDTO toCartItemListDTO() {
        return new CartItemListDTO()
                .setId(id)
                .setProductId(productId)
                .setTitle(title)
                .setPrice(price)
                .setQuantity(quantity)
                .setAmount(amount);
    }
}
