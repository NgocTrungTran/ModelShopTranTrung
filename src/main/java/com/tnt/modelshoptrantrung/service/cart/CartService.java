package com.tnt.modelshoptrantrung.service.cart;


import com.tnt.modelshoptrantrung.model.Cart;
import com.tnt.modelshoptrantrung.service.IGeneralService;

import java.util.Optional;

public interface CartService extends IGeneralService<Cart> {

    Optional<Cart> findByCreatedBy(String createdBy);

    Boolean existsByCreatedBy(String createdBy);

    void delete(Cart cart);
}
