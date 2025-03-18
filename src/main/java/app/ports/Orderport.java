/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.ports;

import app.domain.models.Order;

/**
 *
 * @author User
 */
public interface Orderport {

    public void save(Order order);

    public Order findByorderId(String idOrder);

   
    
}
