package br.com.ecommerce.dto.interfaces;

import br.com.ecommerce.domain.Product;

import java.util.List;

public interface IProductDAO {

	Product create(Product product) throws Exception;

	Product update(Product product) throws Exception;

	Product read(Integer id) throws Exception;

	void delete(Integer id) throws Exception;

	List<Product> getAllProducts() throws Exception;
}