import com.app.Dao.ProductDAO;
import com.app.Model.Product;
import com.app.Model.ProductDetails;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProductTest {

    @Test
    public void testFindById(){
        RestTemplate restTemplate= new RestTemplate();
        String url="http://localhost:8082/SpringREST_war/Products/?productId=1";
      ResponseEntity<Product> response=restTemplate.getForEntity(url,Product.class);
       assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
    @Test
    public void testGetAllProduct(){
         RestTemplate restTemplate=new RestTemplate();
         String baseUrle="http://Localhost:8082/api/getProducts";
        ResponseEntity<List<Product>> responseEntity = restTemplate.exchange(
                baseUrle,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() {});
         assertEquals(HttpStatus.OK.value(),responseEntity.getStatusCode());
          List<Product>products=responseEntity.getBody();
           System.out.println(products);

    }
     @Test
     public void testFindByName(){
         final String BaseUrl="http://Localhost:8082/api/ProductByName";
        RestTemplate restTemplate= new RestTemplate();
         String productName="milk";
        String Url=String.format("%s?name=%s",BaseUrl,productName);
        ResponseEntity<Product> productResponce= restTemplate.getForEntity(Url,Product.class);
        assertEquals(HttpStatus.OK.value(),productResponce.getStatusCode());
        Product product=productResponce.getBody();
        assertNotNull(product);
        assertEquals(productName,product.getProductName());
     }
      @Test
    public void testGetProductDetailsBProductId(){
        RestTemplate restTemplate= new RestTemplate();
        String Url="http://Localhost:8082/api/products/productDetails";
        ResponseEntity<ProductDetails> response= restTemplate.getForEntity(Url,ProductDetails.class);
        assertEquals(HttpStatus.OK.value(),response.getStatusCode());
      }
      @Test
    public void testAddProduct() throws ParseException {
          SimpleDateFormat dateFormat=new SimpleDateFormat("yyy-mm-dd");
        String url="http://localhost:8082/SpringREST_war/products";
        RestTemplate restTemplate= new RestTemplate();
        ProductDetails productDetails = new ProductDetails("tea",dateFormat.parse("2022-01-16"),"leptone",12.0,true);
         ProductDetails response= restTemplate.postForObject(url,productDetails,ProductDetails.class);
          System.out.print(response);
         assertEquals(response.getProduct(),productDetails.getProduct());

      }
      @Test
     public void testUpdateProduct() throws ParseException {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyy-mm-dd");
        String url="http://localhost:8082/SpringREST_war/products";
        RestTemplate restTemplate= new RestTemplate();
         ProductDetails productDetails= new ProductDetails("greentea",dateFormat.parse("2023-10-3"),"leptone",10.9,true);
         productDetails.setId(16);
        restTemplate.put(url,productDetails);

      }
       @Test
    public void testDeleteProduct(){
        String url="http://localhost:8082/SpringREST_war/products?productId=9";
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.delete(url);
       }

}
