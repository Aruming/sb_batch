package com.ll.exam.spring_batch.app.cart.service;

import com.ll.exam.spring_batch.app.cart.entity.CartItem;
import com.ll.exam.spring_batch.app.cart.repository.CartItemRepository;
import com.ll.exam.spring_batch.app.member.entity.Member;
import com.ll.exam.spring_batch.app.product.entity.ProductOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartItemRepository cartItemRepository;

    public void addItem(Member member, ProductOption productOption, int quantity) {
        CartItem cartItem = CartItem.builder()
                .member(member)
                .productOption(productOption)
                .quantity(quantity)
                .build();

        cartItemRepository.save(cartItem);
    }
}