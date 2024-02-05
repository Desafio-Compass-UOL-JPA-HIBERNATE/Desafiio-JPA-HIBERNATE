package br.com.ecommerce.dto.interfaces;

import br.com.ecommerce.domain.Product;

import java.util.List;

public interface IProductDAO {
    public Product create(Product product) throws Exception;
    public Product update(Product p, Integer id) throws Exception;
    public List<Product> listAll() throws Exception;
    public Product findById(Integer id) throws Exception;
    public void delete(Integer id) throws Exception;
}

