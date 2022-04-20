package com.example.ecommerce.services;

import com.example.ecommerce.dtos.CheckoutDto;
import com.example.ecommerce.dtos.ViewOrderDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.*;
import com.example.ecommerce.repositories.CartItemRepository;
import com.example.ecommerce.repositories.CartRepository;
import com.example.ecommerce.repositories.OrderRepository;
import com.example.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Order> userCheckout(CheckoutDto checkoutDto) throws UserAlreadyExistException {
        UserAccount user =  validateUser(checkoutDto.getUserId()).getBody();
        // checking to see if user is a customer
        if (user.getRole().getName().equals("Customer")) {
            // checking to see if cart exists
            Optional<Cart> cart = cartRepository.findCartById(checkoutDto.getCartId());
            if (cart.isPresent()) {
                // check if user cart id matches cart id
                if (cart.get().getId().equals(user.getCart().getId())){
                    // checkout items in cart
                    Order newOrder = new Order();
                    newOrder.setCart(cart.get().getUser().getCart());
                    newOrder.setOrderStatus(OrderStatus.ON_HOLD);
                    Double sum = 0D;
                    List<CartItems> cartItemsList = cart.get().getUser().getCart().getCartItems();
                    if (!(cartItemsList == null)){
                        for (CartItems cartIt: cartItemsList){
                            sum += (cartIt.getProduct().getPrice() * cartIt.getQuantity());
                        }
                    }
                    newOrder.getCart().setTotalCost(sum);
                    Order orderNew = orderRepository.save(newOrder);
                    for (CartItems cartIt: cartItemsList){
                        cartIt.setItemStatus(ItemStatus.CHECKED_OUT);
                        cartItemRepository.save(cartIt);
                    }
//                        user.get().getCart().getCartItems().clear();
                    return ResponseEntity.ok(orderNew);
                }
                throw new UserAlreadyExistException("Action not allowed");
            }
            throw new UserAlreadyExistException("Cart not found");
        }
        throw new UserAlreadyExistException("Action not allowed!");
    }

    public ResponseEntity<Order> viewOrderByUserId(ViewOrderDto viewOrderDto, Long merchantId) throws UserAlreadyExistException{
        // checking to see if user is registered
        UserAccount merchant = validateUser(merchantId).getBody();
        if (merchant.getRole().getName().equals("Merchant")) {
            Optional<UserAccount> customer = userRepository.findById(viewOrderDto.getCustomerId());
            if (customer.isPresent()){
                Optional<Order> gOrder = orderRepository.findById(customer.get().getCart().getOrder().getId());
                return gOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
            }
            throw new UserAlreadyExistException("Customer not found");
        }
        throw new UserAlreadyExistException("Action not allowed!");
    }

    public ResponseEntity<Order> viewOrderById(Long orderId){
        Optional<Order> gOrder = orderRepository.findById(orderId);
        return gOrder.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Order> changeOrderStatus(ViewOrderDto viewOrderDto, Long merchantId) throws UserAlreadyExistException{
        Order theOrder = viewOrderByUserId(viewOrderDto, merchantId).getBody();
        theOrder.setOrderStatus(OrderStatus.valueOf(viewOrderDto.getOrderStatus()));
        orderRepository.save(theOrder);
        return ResponseEntity.ok(theOrder);
    }

    public ResponseEntity<UserAccount> validateUser(Long userId) throws UserAlreadyExistException{
        // checking to see if user is registered
        Optional<UserAccount> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        throw new UserAlreadyExistException("Unauthorized!");
    }
}