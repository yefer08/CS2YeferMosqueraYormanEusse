/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package veterinarianapp.port;

import Models.Order;
import java.util.List;

/**
 *
 * @author Yorman
 */
public interface PetsPort {
    
        void createOrder(Order order);
    void cancelOrder(String IdOrder);
    List<Order> listOrder();

    
}
