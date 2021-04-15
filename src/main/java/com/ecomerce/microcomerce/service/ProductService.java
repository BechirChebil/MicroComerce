package com.ecomerce.microcomerce.service;

import com.ecomerce.microcomerce.model.Product;
import com.ecomerce.microcomerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "*")
@Service
public class ProductService {
    @Autowired
    ProductRepository pr;

    public Product findID(int x) {
        return pr.findById(x).get();
    }

    public List<Product> findAll() {
        return (List<Product>) pr.findAll();
    } // ctrl + alt + shift + L

    public Product addProduct(Product product) {
        return pr.save(product);
    }

    public Product updateProduct(Product product) {
        Optional<Product> product1 = pr.findById(product.getId());
        if (product1.isEmpty()) {
            return null;
        }
        Product productToUpdate = updateNullableFields(product, product1.get());
        return pr.save(productToUpdate);
    }

    public Boolean deleteProduct(int id){
        Optional<Product> product1 =pr.findById(id);
        if (product1.isEmpty()) {
            return false;
        }
        pr.deleteById(id);
        return true;
    }

    private Product updateNullableFields(Product newProduct, Product oldProduct)
    {
        if (newProduct.getNome() != null) {
            oldProduct.setNome(newProduct.getNome());
        }
        if (newProduct.getPrix() != 0) {
            oldProduct.setPrix(newProduct.getPrix());
        }
        if (newProduct.getPrixAchat() != 0) {
            oldProduct.setPrixAchat(newProduct.getPrixAchat());
        }
        return oldProduct;
    }

    public List<Product> findByPrixGreaterThan(int prixLimit) {
        return pr.findByPrixGreaterThan(prixLimit);
    }

    public List<Product> chercherUnProduitCher(int prixLimit) {
        return pr.chercherUnProduitCher(prixLimit);
    }
}
