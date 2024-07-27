/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package controller;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Product;

@ManagedBean
@SessionScoped
public class CartBean implements Serializable {
    private List<Product> cartItems = new ArrayList<>();
    private Map<Product, Integer> productQuantities = new HashMap<>();

    public List<Product> getCartItems() {
        return cartItems;
    }

    public Map<Product, Integer> getProductQuantities() {
        return productQuantities;
    }

    public void addToCart(Product product) {
        if (product != null) {
            cartItems.add(product);
            productQuantities.put(product, 1); // Inicializa la cantidad con 1
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto añadido al carrito: " + product.getNombre(), null));
            System.out.println("Producto añadido al carrito: " + product.getNombre());
            System.out.println("Items en el carrito ahora: " + cartItems.size());
        } else {
            System.out.println("Producto es null, no se puede añadir al carrito.");
        }
    }

    public void removeFromCart(Product product) {
        if (product != null) {
            cartItems.remove(product);
            productQuantities.remove(product);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto eliminado del carrito: " + product.getNombre(), null));
            System.out.println("Producto eliminado del carrito: " + product.getNombre());
            System.out.println("Items en el carrito ahora: " + cartItems.size());
        } else {
            System.out.println("Producto es null, no se puede eliminar del carrito.");
        }
    }

    public void increaseQuantity(Product product) {
        if (productQuantities.containsKey(product)) {
            int quantity = productQuantities.get(product);
            productQuantities.put(product, quantity + 1);
        }
    }

    public void decreaseQuantity(Product product) {
        if (productQuantities.containsKey(product) && productQuantities.get(product) > 1) {
            int quantity = productQuantities.get(product);
            productQuantities.put(product, quantity - 1);
        }
    }

    public void updateQuantity(Product product, int quantity) {
        productQuantities.put(product, quantity);
    }

    public double totalPrice() {
        return cartItems.stream()
                .mapToDouble(Product::getPrecio)
                .sum();
    }

    public double totalRentalPrice() {
        return cartItems.stream()
                .mapToDouble(item -> item.getPrecio() * productQuantities.get(item))
                .sum();
    }

    public double getGrandTotal() {
        return totalPrice() + totalRentalPrice();
    }

    public void clearCart() {
        cartItems.clear();
        productQuantities.clear();
    }

    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }
}