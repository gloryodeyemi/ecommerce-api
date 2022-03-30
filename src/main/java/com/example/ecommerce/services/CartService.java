package com.example.ecommerce.services;

import com.example.ecommerce.dtos.AddToCartDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.Cart;
import com.example.ecommerce.models.CartItems;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.UserAccount;
import com.example.ecommerce.repositories.CartItemRepository;
import com.example.ecommerce.repositories.CartRepository;
import com.example.ecommerce.repositories.ProductRepository;
import com.example.ecommerce.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductRepository productRepository;

//    public ResponseEntity<Cart> createCart(CartDto cartDto, Long userId) throws UserAlreadyExistException {
//        // checking to see if user is registered
//        Optional<UserAccount> user = userRepository.findById(userId);
//        if (user.isPresent()) {
//            // checking to see if user is a customer
//            if (user.get().getRole().getName().equals("Customer")) {
//                // add item to cart
//                Cart cart = new Cart();
//                cart.setUserId(userId);
//                return ResponseEntity.ok(cartRepository.save(cart));
//            }
//            throw new UserAlreadyExistException("Action not allowed!");
//        }
//        throw new UserAlreadyExistException("Unauthorized!");
//    }

    public ResponseEntity<CartItems> addToCart(AddToCartDto addToCartDto, Long userId) throws UserAlreadyExistException {
        // checking to see if user is registered
        Optional<UserAccount> user = userRepository.findById(userId);
        if (user.isPresent()) {
            // checking to see if user is a customer
            if (user.get().getRole().getName().equals("Customer")) {
                // checking to see if cart exists
                Optional<Cart> cart = cartRepository.findCartById(addToCartDto.getCartId());
                if (cart.isPresent()) {
                    // add item to cart
                    CartItems cartItems = new CartItems();
                    BeanUtils.copyProperties(addToCartDto, cartItems);
                    cartItems.setCart(cart.get());
                    Optional<Product> product = productRepository.findProductById(addToCartDto.getProductId());
                    if (product.isPresent()){
                        cartItems.setProduct(product.get());
                        cartItemRepository.save(cartItems);
//                        cart.get().getCartItems().add(cartItems);
                        return ResponseEntity.ok(cartItems);
                    }
                    return ResponseEntity.notFound().build();
                }
                throw new UserAlreadyExistException("Cart not found");
            }
            throw new UserAlreadyExistException("Action not allowed!");
        }
        throw new UserAlreadyExistException("Unauthorized!");
    }

    public ResponseEntity<Cart> getCartDetails(Long userId) {
        Optional<Cart> cart = cartRepository.findCartByUserId(userId);
        if (cart.isPresent()){
            Double sum = 0D;
            List<CartItems> cartItemsList = cart.get().getCartItems();
            if (!(cartItemsList == null)){
                for (CartItems cartIt: cartItemsList){
                    sum += (cartIt.getProduct().getPrice() * cartIt.getQuantity());
                }
            }
            cart.get().setTotalCost(sum);
            return ResponseEntity.ok(cart.get());
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Cart> getCart(Long id) {
        Optional<Cart> cart = cartRepository.findCartById(id);
        if (cart.isPresent()){
            Double sum = 0D;
            List<CartItems> cartItemsList = cart.get().getCartItems();
            if (!(cartItemsList == null)){
                for (CartItems cartIt: cartItemsList){
                    sum += (cartIt.getProduct().getPrice() * cartIt.getQuantity());
                }
            }
            cart.get().setTotalCost(sum);
            return ResponseEntity.ok(cart.get());
        }
        return ResponseEntity.notFound().build();
    }

//    public ResponseEntity<Cart> addToCart(AddToCartDto addToCartDto, Long userId) throws UserAlreadyExistException {
//        // checking to see if user is registered
//        Optional<UserAccount> user = userRepository.findById(userId);
//        if (user.isPresent()) {
//            // checking to see if user is a customer
//            if (user.get().getRole().getName().equals("Customer")) {
//                // create a new cart object
//                Cart cart = new Cart();
//                BeanUtils.copyProperties(addToCartDto, cart);
//                cart.setUserId(userId);
//                return ResponseEntity.ok(cartRepository.save(cart));
//            }
//            throw new UserAlreadyExistException("Action not allowed!");
//        }
//        throw new UserAlreadyExistException("Unauthorized!");
//    }
//
//    public ResponseEntity<CartCost> listCartItems(Long userId) throws UserAlreadyExistException {
//        Optional<UserAccount> user = userRepository.findById(userId);
//        if (!user.isPresent()) {
//            throw new UserAlreadyExistException("Unauthorized!");
//        }
//        // checking to see if user is a customer
//        if (!(user.get().getRole().getName().equals("Customer"))) {
//            throw new UserAlreadyExistException("Action not allowed!");
//        }
//        List<Cart> cartList = cartRepository.findAllByUserId(userId);
//        List<CartDto> cartItems = new ArrayList<>();
//        for (Cart cart: cartList){
//            CartDto cartDto = getDtoFromCart(cart);
//            cartItems.add(cartDto);
//        }
//        Double totalCost = 0D;
//        for (CartDto cartDto:cartItems){
//            totalCost += (cartDto.getProduct().getPrice() * cartDto.getQuantity());
//        }
//        CartCost cartCost = new CartCost();
//        cartCost.setCartItems(cartItems);
//        cartCost.setTotalCost(totalCost);
//        return ResponseEntity.ok(cartCost);
//    }
//
//    public static CartDto getDtoFromCart(Cart cart) {
//        CartDto cartDto = new CartDto();
//        cartDto.setId(cart.getId());
//        cartDto.setUserId(cart.getUserId());
//        cartDto.setProduct(cart.getProduct());
//        cartDto.setQuantity(cart.getQuantity());
//        return cartDto;
//    }
}
