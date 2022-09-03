package com.tnt.modelshoptrantrung.repository;

import com.tnt.modelshoptrantrung.model.Cart;
import com.tnt.modelshoptrantrung.model.CartItem;
import com.tnt.modelshoptrantrung.model.dto.CartItemListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByProductId(Long productId);

    List<CartItem> findAllByCart(Cart cart);


    @Query("SELECT SUM(ci.amount) FROM CartItem AS ci WHERE ci.cart.id = :cartId")
    BigDecimal getSumAmountByCartId(@Param("cartId") Long cartId);


    @Query("SELECT NEW com.tnt.modelshoptrantrung.model.dto.CartItemListDTO(" +
            "ci.id, " +
            "ci.productId, " +
            "ci.title, " +
            "p.image, " +
            "ci.price, " +
            "ci.quantity, " +
            "ci.amount" +
            ") FROM CartItem AS ci " +
            "JOIN Product AS p " +
            "ON p.id = ci.productId " +
            "WHERE ci.cart.id = :cartId")
    List<CartItemListDTO> findAllCartItemsDTO(@Param("cartId") Long cartId);
}
