/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.bean.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import model.Product;
import service.ProductService;

@Named
@ViewScoped
public class ProductDetailsBean implements Serializable {
    private int productId;
    private Product product;

    @Inject
    private ProductService productService;

    @Inject
    private CartBean cartBean;

    @PostConstruct
    public void init() {
        String idParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("productId");
        if (idParam != null) {
            productId = Integer.parseInt(idParam);
            product = productService.findProductById(productId);
            System.out.println("Producto cargado: " + product.getNombre());
        }
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void addToCart() {
        if (product != null) {
            cartBean.addToCart(product);
            System.out.println("Producto añadido al carrito: " + product.getNombre());
        } else {
            System.out.println("Producto es null, no se puede añadir al carrito.");
        }
    }
}