package com.app.Service;

import com.app.Dao.ProductDAO;
import com.app.Exception.ProductBadRequest;
import com.app.Exception.ProductNotFoundException;
import com.app.Model.Product;

import com.app.Model.ProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;
    @Override
    public ProductDetails Add(ProductDetails productDetails) throws ProductBadRequest {
        if(!productDetails.getName().isEmpty()){
            productDAO.addProduct(productDetails);
            return productDetails;
        }
        else
         throw  new ProductBadRequest("there is no data");
    }

    @Override
    public Product findById(int Id) throws ProductNotFoundException {
        if (Id<= 0 || Id > 1000)
            throw new ProductNotFoundException("this  id invalid ,must  be  bigger than 0,and smaller than1000 ");
        else {
            Product product = productDAO.findById(Id);
            if (product == null)
                throw new ProductNotFoundException("there is no product  with  this id");
            else
                return product;

        }

    }

    @Override
    public void deleteById(int id) {productDAO.deleteById(id);
    }

    @Override
    public ProductDetails Update(ProductDetails productDetails) {
         Product product = productDAO.findByProductDetails(productDetails);
          if(product!=null)
          {
              productDAO.update(productDetails);
          }
          else {
              throw new NullPointerException();
          }
        return productDetails;
    }

    @Override
    @Transactional
    public List<Product> getAllProduct() {
         try{
             return productDAO.getAllProduct();
         }catch(Exception exception)
         {
             exception.printStackTrace();
         }
        return null;
    }

    @Override
    @Transactional
    public List<Product> findByName(String name) {return productDAO.findByName(name);}
}
