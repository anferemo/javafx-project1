/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.controller;

import com.andrusoft.planning.product.app.dao.ProductDAO;
import com.andrusoft.planning.product.app.model.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andrusoft
 */
public class ProductController {
    
    private List<Product> productList;
    private ProductDAO productDAO;

    public ProductController() {
        productList = new ArrayList<>();
        productDAO = new ProductDAO();
    }

    public List<Product> getProductList() {
        return productDAO.queryAllProducts();       
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
    
    public void addProduct(Product product) {
        productDAO.saveProduct(product);
    }

    
}
