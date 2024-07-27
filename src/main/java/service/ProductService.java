package service;

import conexion.dao.dao;
import model.Product;
import java.util.List;

public class ProductService {
    private dao dao = new dao();

    public void saveProduct(Product product) {
        dao.addProduct(product);
    }

    public ProductService() {
        this.dao = new dao();
    }
    public List<Product> findAll() {
        return dao.findAllProducts();
    }

    public Product findProductById(int id) {
        return dao.findProductById(id);
    }
      public void updateProduct(Product product) {
        dao.updateProduct(product);
    }

    public void deleteProduct(int id) {
        dao.deleteProduct(id);
    }
}