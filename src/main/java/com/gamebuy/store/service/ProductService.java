package com.gamebuy.store.service;

import com.gamebuy.store.dao.ProductDAO;
import com.gamebuy.store.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private static ProductService instance;
    private final ProductDAO productDAO;

    private ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public static ProductService getInstance() {
        ProductDAO productDAO = new ProductDAO();

        if (instance == null) {
            instance = new ProductService(productDAO);
        }
        return instance;
    }

    public List<String> getAllProductTypes() {
        return productDAO.getAllProducts()
                .stream()
                .map(Product::getCategory)
                .distinct()
                .toList();
    }

    public ArrayList<Product> filterProductsByType(ArrayList<Product> allProducts, String productType) {
        if (productType.equals("All")) {
            return allProducts;
        }
        List<Product> filteredProducts = allProducts
                .stream()
                .filter(product -> product.getCategory().equals(productType))
                .toList();

        return new ArrayList<>(filteredProducts);
    }
}