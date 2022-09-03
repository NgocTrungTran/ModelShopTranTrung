package com.tnt.modelshoptrantrung.service.orderitem;


import com.tnt.modelshoptrantrung.model.OrderItem;
import com.tnt.modelshoptrantrung.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> findAll() {
        return null;
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public OrderItem getById(Long id) {
        return null;
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void delete(Long id) {

    }
}
