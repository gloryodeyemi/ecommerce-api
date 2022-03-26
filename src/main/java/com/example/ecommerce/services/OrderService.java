package com.example.ecommerce.services;

import com.example.ecommerce.dtos.AddToCartDto;
import com.example.ecommerce.dtos.CartDto;
import com.example.ecommerce.dtos.NewOrderDto;
import com.example.ecommerce.dtos.OrderDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.*;
import com.example.ecommerce.repositories.OrderRepository;
import com.example.ecommerce.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<OrderDetails> newOrder(NewOrderDto newOrderDto, Long userId) throws UserAlreadyExistException {
        // checking to see if user is registered
        Optional<UserAccount> user = userRepository.findById(userId);
        if (user.isPresent()) {
            // checking to see if user is a customer
            if (user.get().getRole().getName().equals("Customer")) {
                // create a new OrderDetails object
                OrderDetails orderDetails = new OrderDetails();
                BeanUtils.copyProperties(newOrderDto, orderDetails);
                orderDetails.setStatus(OrderStatus.ON_HOLD);
                return ResponseEntity.ok(orderRepository.save(orderDetails));
            }
            throw new UserAlreadyExistException("Action not allowed!");
        }
        throw new UserAlreadyExistException("Unauthorized!");
    }

    public ResponseEntity<Order> getOrderDetails(Long userId) throws UserAlreadyExistException {
        Optional<UserAccount> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserAlreadyExistException("Unauthorized!");
        }
        List<OrderDetails> orderDetails = orderRepository.findAllByUserId(userId);
        List<OrderDto> orderItems = new ArrayList<>();
        for (OrderDetails order: orderDetails){
            OrderDto orderDto = getDtoFromOrder(order);
            orderItems.add(orderDto);
        }
        Double totalCost = 0D;
        for (OrderDto orderDto:orderItems){
            totalCost += (orderDto.getProduct().getPrice() * orderDto.getQuantity());
        }
//        CartCost cartCost = new CartCost();
        Order order = new Order();
        order.setOrderDetails(orderItems);
        order.setTotalPrice(totalCost);
        return ResponseEntity.ok(order);
    }

    public static OrderDto getDtoFromOrder(OrderDetails orderDetails) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderDetails.getId());
        orderDto.setCartId(orderDetails.getCartId());
        orderDto.setProduct(orderDetails.getProduct());
        orderDto.setQuantity(orderDetails.getQuantity());
        return orderDto;
    }
}
