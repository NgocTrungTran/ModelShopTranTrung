package com.tnt.modelshoptrantrung.service.cartItem;

import com.tnt.modelshoptrantrung.model.Cart;
import com.tnt.modelshoptrantrung.model.CartItem;
import com.tnt.modelshoptrantrung.model.dto.CartItemListDTO;
import com.tnt.modelshoptrantrung.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CartItemService extends IGeneralService<CartItem> {

    Optional<CartItem> findByProductId(Long productId);

    List<CartItem> findAllByCart(Cart cart);

    BigDecimal getSumAmountByCartId(Long cartId);

    List<CartItemListDTO> findAllCartItemsDTO(@Param("cartId") Long cartId);

    void delete(CartItem cartItem);
}
