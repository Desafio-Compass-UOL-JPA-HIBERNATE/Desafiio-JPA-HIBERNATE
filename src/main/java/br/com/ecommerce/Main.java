package br.com.ecommerce;

import br.com.ecommerce.domain.Product;
import br.com.ecommerce.dto.ProductDAO;
import br.com.ecommerce.service.UtilConnectionHibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {

        Product prod = new Product("Th", 2.0,"brasul", null);

        ProductDAO productDAO = new ProductDAO();

        productDAO.create(prod);

    }
}