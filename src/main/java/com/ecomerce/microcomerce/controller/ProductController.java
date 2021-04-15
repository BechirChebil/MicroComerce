package com.ecomerce.microcomerce.controller;


import com.ecomerce.microcomerce.exceptions.ProduitIntrouvableException;
import com.ecomerce.microcomerce.model.Product;
import com.ecomerce.microcomerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "https://*/")
@RestController
public class ProductController {
    @Autowired
    ProductService ps;
    @CrossOrigin(origins = "http://localhost:3000/")
    // ajouter
    @PostMapping(value = "/Produits")
    public ResponseEntity<Product> ajouterProduit(@RequestBody Product product) {
        product = ps.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    //modifier
    @PutMapping(value = "/Produits/{id}")
    public ResponseEntity<Object> modifierProduit(@RequestBody Product product, @PathVariable int id) {
       product.setId(id);
       product = ps.updateProduct(product);
       HttpStatus status = HttpStatus.OK;
       if (product == null) {
           status = HttpStatus.NOT_FOUND;
       }

       return new ResponseEntity<>(product, status);
    }

    //delete product
    @DeleteMapping("/Produits/{id}")
    public ResponseEntity<Object> supprimerProduit(@PathVariable int id){
        Boolean deleteSuccess = ps.deleteProduct(id);
        if(deleteSuccess){
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Product does not exist", HttpStatus.BAD_REQUEST);
    }

    //Produits/{id}
    @GetMapping(value = "/Produits/{id}")
    public Product afficherUnProduit(@PathVariable int id) throws ProduitIntrouvableException {
        Product product = ps.findID(id);
       if (product==null) throw new ProduitIntrouvableException("le produit avec l'id "+id+" n'existe pas");
        return product;
    }

    //Produits
    @GetMapping(value = "/Produits")
    public List<Product> listeProduits() {
        return ps.findAll();
    }


    //Produits dont le prix superiere a
    @GetMapping(value = "Produits/Prix/{prixLimit}")
    public List<Product> testeDeRequetes(@PathVariable int prixLimit) {
        return ps.chercherUnProduitCher(prixLimit);
    }


}
