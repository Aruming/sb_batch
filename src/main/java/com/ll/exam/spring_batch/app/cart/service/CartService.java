package com.ll.exam.spring_batch.app.cart.service;

import com.ll.exam.spring_batch.app.cart.entity.CartItem;
import com.ll.exam.spring_batch.app.cart.repository.CartItemRepository;
import com.ll.exam.spring_batch.app.member.entity.Member;
import com.ll.exam.spring_batch.app.product.entity.ProductOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartItemRepository cartItemRepository;

    public void addItem(Member member, ProductOption productOption, int quantity) {
        Optional<CartItem> item = cartItemRepository.findCartItemByMemberAndProductOption(member, productOption);

        if (item.isPresent()){
            CartItem cartItem = item.get();
            cartItem.setQuantity(cartItem.getQuantity()+quantity);
            cartItemRepository.save(cartItem);
        }
        else{
            CartItem cartItem = CartItem.builder()
                    .member(member)
                    .productOption(productOption)
                    .quantity(quantity)
                    .build();

            cartItemRepository.save(cartItem);
        }
    }

    public List<CartItem> getItemsByMember(Member member) {
        return cartItemRepository.findAllByMemberId(member.getId());
    }

    public void deleteItem(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }
}