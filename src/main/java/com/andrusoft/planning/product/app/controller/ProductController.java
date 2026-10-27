/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.andrusoft.planning.product.app.controller;

import com.andrusoft.planning.product.app.model.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andrusoft
 */
public class ProductController {
    
    private List<Product> productList;

    public ProductController() {
        productList = new ArrayList<>();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
    
    public void addProduct(Product product) {
        this.productList.add(product);
    }

    
}
