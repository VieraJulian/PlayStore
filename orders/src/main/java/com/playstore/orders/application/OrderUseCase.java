package com.playstore.orders.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playstore.orders.application.exception.GameNotFoundException;
import com.playstore.orders.application.exception.UserNotFoundException;
import com.playstore.orders.domain.Order;
import com.playstore.orders.domain.OrderItem;
import com.playstore.orders.infrastructure.dto.GameDTO;
import com.playstore.orders.infrastructure.dto.GameRequestDTO;
import com.playstore.orders.infrastructure.dto.OrderDTO;
import com.playstore.orders.infrastructure.dto.OrderItemDTO;
import com.playstore.orders.infrastructure.dto.OrderRequestDTO;
import com.playstore.orders.infrastructure.dto.UserDTO;
import com.playstore.orders.infrastructure.inputPort.IOrderInputPort;
import com.playstore.orders.infrastructure.outputPort.IGameServicePort;
import com.playstore.orders.infrastructure.outputPort.IOrderItemMethod;
import com.playstore.orders.infrastructure.outputPort.IOrderMethod;
import com.playstore.orders.infrastructure.outputPort.IUserServicePort;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;
import java.math.BigDecimal;

@Service
public class OrderUseCase implements IOrderInputPort {

    @Autowired
    private IOrderMethod orderMethod;

    @Autowired
    private IOrderItemMethod orderItemMethod;

    @Autowired
    private IUserServicePort userServ;

    @Autowired
    private IGameServicePort gameServ;

    @Override
    public OrderDTO findOrderById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOrderById'");
    }

    @Override
    public OrderDTO findOrderByCode(String code) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOrderByCode'");
    }

    @Override
    public OrderDTO createOrder(OrderRequestDTO orderReq) throws UserNotFoundException, GameNotFoundException {
        Long user_id = orderReq.getUser_id();
        List<OrderItemDTO> ordersItems = new ArrayList<>();
        BigDecimal priceFinal = new BigDecimal(0);

        UserDTO user = userServ.getUser(user_id);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        for (GameRequestDTO g : orderReq.getListGames()) {
            GameDTO game = gameServ.getGame(g.getProduct_id());

            if (game == null) {
                throw new GameNotFoundException("Game not found");
            }

            priceFinal = priceFinal.add(game.getFinal_price().multiply(BigDecimal.valueOf(g.getQuantity())));
            System.out.println(game.getGameImage().getImage_url());
            ordersItems.add(OrderItemDTO.builder()
                    .id(game.getId())
                    .title(game.getTitle())
                    .original_price(game.getOriginal_price())
                    .final_price(game.getFinal_price())
                    .discount(game.getDiscount())
                    .quantity(g.getQuantity())
                    .gameImage(game.getGameImage())
                    .build());
        }

        Order order = Order.builder()
                .user_id(user.getId())
                .final_price(priceFinal)
                .date_purchase(LocalDate.now())
                .code_operation(UUID.randomUUID().toString())
                .enabled(true)
                .build();

        Order orderNew = orderMethod.save(order);

        ordersItems.forEach(oi -> {
            orderItemMethod.save(OrderItem.builder()
                    .order(orderNew)
                    .game_id(oi.getId())
                    .quantiy(oi.getQuantity())
                    .build());
        });

        return OrderDTO.builder()
                .id(orderNew.getId())
                .user_id(orderNew.getUser_id())
                .final_price(orderNew.getFinal_price())
                .date_purchase(orderNew.getDate_purchase())
                .code_operation(orderNew.getCode_operation())
                .enabled(orderNew.isEnabled())
                .ordersItems(ordersItems)
                .build();

    }

    @Override
    public String deleteOrderById(Long id) {
        orderMethod.deleteById(id);

        return "Order deleted successfully";
    }

}
