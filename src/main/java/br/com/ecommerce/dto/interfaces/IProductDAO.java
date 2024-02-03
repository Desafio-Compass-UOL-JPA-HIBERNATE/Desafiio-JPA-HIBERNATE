package br.com.ecommerce.dto.interfaces;

import br.com.ecommerce.domain.Product;

import java.util.List;

public interface IProductDAO {

    public Product create() throws Exception;
    public Product update() throws Exception;
    public Product read() throws Exception;
    public Product getProduct(Integer id) throws Exception;
    public void delete() throws Exception;
}
