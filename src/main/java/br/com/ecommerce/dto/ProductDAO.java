package br.com.ecommerce.dto;

import br.com.ecommerce.domain.Product;
import br.com.ecommerce.dto.interfaces.IProductDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ProductDAO implements IProductDAO {

    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Product create() throws Exception {
        return null;
    }

    @Override
    public Product update() throws Exception {
        return null;
    }

    @Override
    public Product read() throws Exception {
        return null;
    }

    @Override
    public Product getProduct(Integer id) throws Exception {
        return null;
    }

    @Override
    public void delete() throws Exception {

    }
}
