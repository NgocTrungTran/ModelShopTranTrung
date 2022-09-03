package com.tnt.modelshoptrantrung.service.cartItem;


import com.tnt.modelshoptrantrung.model.Cart;
import com.tnt.modelshoptrantrung.model.CartItem;
import com.tnt.modelshoptrantrung.model.dto.CartItemListDTO;
import com.tnt.modelshoptrantrung.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> findAll() {
        return null;
    }

    @Override
    public Optional<CartItem> findById(Long id) {
        return cartItemRepository.findById(id);
    }

    @Override
    public Optional<CartItem> findByProductId(Long productId) {
        return cartItemRepository.findByProductId(productId);
    }

    @Override
    public List<CartItem> findAllByCart(Cart cart) {
        return cartItemRepository.findAllByCart(cart);
    }

    @Override
    public BigDecimal getSumAmountByCartId(Long cartId) {
        return cartItemRepository.getSumAmountByCartId(cartId);
    }

    @Override
    public List<CartItemListDTO> findAllCartItemsDTO(Long cartId) {
        return cartItemRepository.findAllCartItemsDTO(cartId);
    }

    @Override
    public CartItem getById(Long id) {
        return null;
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }
}
