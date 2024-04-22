package com.app.Dao;


import com.app.Model.Product;
import com.app.Model.ProductDetails;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PorductDAoImpl implements  ProductDAO {
    @Autowired
    public SessionFactory sessionFactory;


    @Override
    public Product addProduct(ProductDetails productDetails) {
        try {
            Session session = sessionFactory.getCurrentSession();
            productDetails.setExpirationDate(productDetails.getExpirationDate());

            Product product = new Product(productDetails.getName());
            product.setProductDetails(productDetails);
            session.persist(product);
            return product;
        } catch (Exception exception) {
            exception.printStackTrace();

        }
        return null;

    }

    @Override
    public Product findById(int id) {
        try{
             Session session= sessionFactory.getCurrentSession();
             return session.get(Product.class, id);
        } catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Product findByProductDetails(ProductDetails productDetails) {
         try {
             Session session = sessionFactory.getCurrentSession();
             ProductDetails productDetails1 = session.get(ProductDetails.class, productDetails.getId());
             return productDetails1.getProduct();
         }catch(Exception exception){
             exception.printStackTrace();
         }
        return null;
    }

    @Override
    public void deleteById(int theId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Product product = session.get(Product.class, theId);
            Query query = session.createQuery("delete FROM Product where id =:productId ");
            query.setParameter("productId", theId);
            query.executeUpdate();

            query = session.createQuery("delete  FROM   ProductDetails where id=:productDetailsId");
            query.setParameter("productDetailsId",  product.getProductDetails().getId());
            query.executeUpdate();
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void update(ProductDetails newProduct) {
         try{
             Session session= sessionFactory.getCurrentSession();
              ProductDetails oldProductDetails= session.get(ProductDetails.class,newProduct.getId());
              Product oldProduct= oldProductDetails.getProduct();
               oldProduct.setProductName(newProduct.getName());
               oldProductDetails.setName(newProduct.getName());
               oldProductDetails.setExpirationDate(newProduct.getExpirationDate());
               oldProductDetails.setManufacturer(newProduct.getManufacturer());
               oldProductDetails.setPrice(newProduct.getPrice());
               oldProductDetails.setAvailable(newProduct.getAvailable());
               oldProductDetails.setProduct(newProduct.getProduct());
                oldProduct.setProductDetails(oldProductDetails);
                 session.update(oldProduct);
         } catch(Exception exception) {
              exception.printStackTrace();
         }
    }

    @Override
    public List<Product> getAllProduct() {

        // List<Product> products= null;
        try {
           Session session = sessionFactory.getCurrentSession();
           Query query = session.createQuery("FROM Product");
           List<Product> products = query.list();
           return products;


        } catch (Exception exception) {
            exception.printStackTrace();
        }
         return null;

    }

    @Override
    public List<Product> findByName(String searchName) {
        Session session=sessionFactory.getCurrentSession();
        Query query= session.createQuery("From  Product  where productName=:productName");
        query.setParameter("productName",searchName);
        return query.list();
    }
}
