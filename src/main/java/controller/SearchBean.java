package controller;

import model.Product;
import service.ProductService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class SearchBean implements Serializable {
    private String searchTerm;
    private List<Product> filteredProducts;
    private List<Product> allProducts;

    private Map<String, Boolean> selectedBrands = new HashMap<>();
    private Map<String, Boolean> selectedPrices = new HashMap<>();
    private Map<String, Boolean> selectedRentalTypes = new HashMap<>();
    private Map<String, Boolean> selectedStates = new HashMap<>();

    @Inject
    private ProductService productService;

    @PostConstruct
    public void init() {
        loadProducts();
        initializeMaps();
    }

    public void loadProducts() {
        allProducts = productService.findAll();
        filteredProducts = new ArrayList<>(allProducts); // Inicializa la lista filtrada con todos los productos
    }

    private void initializeMaps() {
        // Inicializar todos los mapas con valores posibles y establecerlos en falso
        selectedBrands.put("Werken", false);
        selectedBrands.put("Bosch", false);
        selectedBrands.put("Stanley", false);
        selectedBrands.put("Truper", false);
        selectedBrands.put("Pretul", false);
        selectedBrands.put("Kamasa", false);

        selectedPrices.put("Bajo", false);
        selectedPrices.put("Intermedio", false);
        selectedPrices.put("Alto", false);

        selectedRentalTypes.put("Horas", false);
        selectedRentalTypes.put("Diario", false);
        selectedRentalTypes.put("Semanal", false);
        selectedRentalTypes.put("Mensual", false);

        selectedStates.put("Nuevo", false);
        selectedStates.put("Semi-Nuevo", false);
        selectedStates.put("Usado", false);
    }

    public void search() {
        filteredProducts = allProducts.stream()
            .filter(this::matchesSearchTerm)
            .filter(this::matchesFilters)
            .collect(Collectors.toList());
    }

    private boolean matchesSearchTerm(Product product) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return true;
        }
        return product.getNombre().toLowerCase().contains(searchTerm.toLowerCase());
    }

    private boolean matchesFilters(Product product) {
        return matchesBrand(product) && matchesPrice(product) && matchesRentalType(product) && matchesState(product);
    }

    private boolean matchesBrand(Product product) {
        return selectedBrands.values().stream().noneMatch(Boolean::booleanValue) ||
               selectedBrands.getOrDefault(product.getMarca(), false);
    }

    private boolean matchesPrice(Product product) {
        if (selectedPrices.values().stream().noneMatch(Boolean::booleanValue)) {
            return true;
        }
        double price = product.getPrecio();
        if (selectedPrices.getOrDefault("Bajo", false) && price >= 10 && price <= 50) {
            return true;
        }
        if (selectedPrices.getOrDefault("Intermedio", false) && price > 50 && price <= 100) {
            return true;
        }
        return selectedPrices.getOrDefault("Alto", false) && price > 100;
    }

    private boolean matchesRentalType(Product product) {
        if (selectedRentalTypes.values().stream().noneMatch(Boolean::booleanValue)) {
            return true;
        }
        String rentalType = product.getTipoAlquiler().toLowerCase();
        boolean matches = selectedRentalTypes.entrySet().stream()
            .filter(Map.Entry::getValue)
            .map(Map.Entry::getKey)
            .map(String::toLowerCase)
            .anyMatch(rentalType::equals);
        System.out.println("Filtrando por tipo de alquiler: " + product.getTipoAlquiler() + " - Coincide: " + matches);
        return matches;
    }

    private boolean matchesState(Product product) {
        if (selectedStates.values().stream().noneMatch(Boolean::booleanValue)) {
            return true;
        }
        String state = product.getEstado().toLowerCase();
        boolean matches = selectedStates.entrySet().stream()
            .filter(Map.Entry::getValue)
            .map(Map.Entry::getKey)
            .map(String::toLowerCase)
            .anyMatch(state::equals);
        System.out.println("Filtrando por estado: " + product.getEstado() + " - Coincide: " + matches);
        return matches;
    }

    // Getters y Setters
    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public List<Product> getFilteredProducts() {
        return filteredProducts;
    }

    public void setFilteredProducts(List<Product> filteredProducts) {
        this.filteredProducts = filteredProducts;
    }

    public Map<String, Boolean> getSelectedBrands() {
        return selectedBrands;
    }

    public void setSelectedBrands(Map<String, Boolean> selectedBrands) {
        this.selectedBrands = selectedBrands;
    }

    public Map<String, Boolean> getSelectedPrices() {
        return selectedPrices;
    }

    public void setSelectedPrices(Map<String, Boolean> selectedPrices) {
        this.selectedPrices = selectedPrices;
    }

    public Map<String, Boolean> getSelectedRentalTypes() {
        return selectedRentalTypes;
    }

    public void setSelectedRentalTypes(Map<String, Boolean> selectedRentalTypes) {
        this.selectedRentalTypes = selectedRentalTypes;
    }

    public Map<String, Boolean> getSelectedStates() {
        return selectedStates;
    }

    public void setSelectedStates(Map<String, Boolean> selectedStates) {
        this.selectedStates = selectedStates;
    }
}