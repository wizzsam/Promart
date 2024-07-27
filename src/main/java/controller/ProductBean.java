package controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.bean.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import model.Product;
import service.ProductService;

@Named
@ViewScoped
public class ProductBean implements Serializable {

    private Product product = new Product();
    private List<Product> products;
    private ProductService productService;
    private Product selectedProduct;

    @Inject
    private UserSessionBean userSessionBean;

    @PostConstruct
    public void init() {
        productService = new ProductService();
        loadProducts();
    }

    public void loadProducts() {
        products = productService.findAll();
        System.out.println("Productos encontrados: " + products.size());
        for (Product p : products) {
            System.out.println("Producto: " + p.getNombre() + ", Marca: " + p.getMarca() + ", ID: " + p.getId());
        }
    }

    public void loadProductForEdit(Product product) {
        selectedProduct = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public void saveProduct() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (!userSessionBean.isLoggedIn()) {
            String script = "alert('Para subir un producto necesitas estar logueado'); window.location.href = 'login.xhtml';";
            facesContext.getPartialViewContext().getRenderIds().add("alertScript");
            facesContext.getExternalContext().getFlash().setKeepMessages(true);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Para subir un producto necesitas estar logueado", null));
            facesContext.getExternalContext().getFlash().put("alertScript", script);
            return;
        }

        try {
            product.setUploadedBy(userSessionBean.getUsername());
            System.out.println("Guardando producto: " + product.getNombre());
            productService.saveProduct(product);
            System.out.println("Producto guardado exitosamente");
            loadProducts();
            System.out.println("Productos después de guardar: " + products.size());
            String successScript = "alert('Producto añadido con éxito'); window.location.href = 'product.xhtml';";
            facesContext.getPartialViewContext().getRenderIds().add("successScript");
            facesContext.getExternalContext().getFlash().setKeepMessages(true);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto añadido con éxito", null));
            facesContext.getExternalContext().getFlash().put("successScript", successScript);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLoggedInUsername() {
        return userSessionBean.getUsername();
    }

    public void updateProduct() {
        productService.updateProduct(selectedProduct);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto actualizado con éxito"));
        loadProducts();
    }

    public void deleteProduct(Product product) {
        productService.deleteProduct(product.getId());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto eliminado con éxito"));
        loadProducts();
    }

    public String addToCart(Product product) {
        FacesContext context = FacesContext.getCurrentInstance();
        CartBean cartBean = context.getApplication().evaluateExpressionGet(context, "#{cartBean}", CartBean.class);
        cartBean.addToCart(product);
        return "carrito.xhtml?faces-redirect=true";
    }
}
