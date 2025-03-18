/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.infrastructure.repositories;

import app.Entity.OrderEntity;
import app.domain.models.Order;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderServiceRepository extends JpaRepository<Order, String>  {

    public void save(OrderEntity orderEntity);
  
}