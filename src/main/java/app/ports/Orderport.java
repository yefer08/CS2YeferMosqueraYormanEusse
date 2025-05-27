/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.ports;

import app.Entities.OrderEntity;
import app.domain.models.Order;
import java.util.List;
import java.util.Optional;


public interface Orderport {

    Order save(Order order);

    Optional<Order> findById(String id);

    List<Order> findAll();
}
