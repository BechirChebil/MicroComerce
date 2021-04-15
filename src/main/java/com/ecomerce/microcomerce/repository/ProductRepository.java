package com.ecomerce.microcomerce.repository;

import com.ecomerce.microcomerce.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
// hibernate ORM
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    //List<Product> search(List<Product> productList);

    List<Product> findByPrixGreaterThan(int prixLimit);


    @Query(value = "SELECT id, nome, prix  FROM Product p WHERE p.prix > :prixLimit")
    List<Product>  chercherUnProduitCher(@Param("prixLimit") Integer prix);

//    @Query("SELECT id, nome, prix  FROM Product p WHERE p.prix > %?1")
//    List<Product>  chercherUnProduitCher(Integer prix);




}
