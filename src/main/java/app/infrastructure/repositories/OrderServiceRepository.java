/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.infrastructure.repositories;

import app.domain.models.Order;
import java.util.HashMap;
import java.util.Map;
public class OrderServiceRepository {
    
    private final Map<String, Order> orders;

    public OrderServiceRepository(Map<String, Order> orders) {
        this.orders = orders; // Usa el mismo mapa de OrderService
    }
    
    public void save(Order order) {
        orders.put(order.getOrderId(), order);  // Usa el OrderId como clave
    }

    // Buscar una orden por ID
    public Order findById(String orderId) {
        return orders.get(orderId); // Devuelve la orden o null si no existe
    }

    // Actualizar una orden existente
    public void update(Order order) {
        if (orders.containsKey(order.getOrderId())) {
            orders.put(order.getOrderId(), order);
        } else {
            throw new IllegalArgumentException("Orden no encontrada para actualizar.");
        }
    }

    // Listar todas las órdenes
    public Map<String, Order> findAll() {
        return new HashMap<>(orders); // Retorna una copia para evitar modificaciones externas
    }
}

