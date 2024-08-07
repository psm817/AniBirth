package com.cod.AniBirth.cart.service;

import com.cod.AniBirth.cart.entity.CartItem;
import com.cod.AniBirth.cart.repository.CartRepository;
import com.cod.AniBirth.member.entity.Member;
import com.cod.AniBirth.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public void add(Product product, Member member) {
        CartItem existingCartItem = cartRepository.findByMemberAndProduct(member, product);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
            cartRepository.save(existingCartItem);
        } else {
            CartItem c = CartItem.builder()
                    .product(product)
                    .member(member)
                    .thumbnailImg(product.getThumbnailImg())
                    .quantity(1L) // 초기 수량 설정
                    .build();

            cartRepository.save(c);
        }
    }

    public List<CartItem> getList(Member member) {
        return cartRepository.findByMember(member);
    }

    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    public int getTotalPrice(Member member) {
        List<CartItem> cartItems = getList(member);
        return cartItems.stream().mapToInt(item -> (int) (item.getProduct().getPrice() * item.getQuantity())).sum();
    }

    public void updateQuantity(Long id, Long quantity) {
        CartItem cartItem = cartRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid cart item id"));
        cartItem.setQuantity(quantity);
        cartRepository.save(cartItem);
    }

    public long getQuantity(Long id) {
        CartItem cartItem = cartRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid cart item id"));
        return cartItem.getQuantity();
    }

    public void clearCart(Member member) {
        List<CartItem> cartItems = cartRepository.findByMember(member);
        cartRepository.deleteAll(cartItems);
    }
}
