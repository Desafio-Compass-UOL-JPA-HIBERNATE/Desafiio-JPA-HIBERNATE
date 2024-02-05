package br.com.ecommerce;

import br.com.ecommerce.domain.Product;
import br.com.ecommerce.dto.ProductDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ProductDAO productDAO = new ProductDAO();

        Product prod = new Product("TV", 1500.75,"TV Samsung");
        Product teste = new Product("Celular", 735.90,"Redmi Note 8");
        Product a = new Product("Geladeira", 2000.0,"Brastemp");


//        System.out.println(productDAO.create(prod).toString());
//        System.out.println(productDAO.create(teste).toString());
//        System.out.println(productDAO.create(a).toString());
//
//
//        List<Product> products = new ProductDAO().listAll();
//        for (Product product : products) {
//            System.out.println(product.toString());
//        }
        a.setValue(2500.0);
        a.setName("Geladeira Brastemp 2 portas frost free");
        System.out.println(productDAO.update(a, 3).toString());
//        System.out.println(productDAO.findById(2).toString());

        productDAO.close();
    }
}