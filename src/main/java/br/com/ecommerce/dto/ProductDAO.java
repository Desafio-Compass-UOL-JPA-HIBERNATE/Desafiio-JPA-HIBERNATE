package br.com.ecommerce.dto;

import br.com.ecommerce.domain.Product;
import br.com.ecommerce.dto.interfaces.IProductDAO;
import br.com.ecommerce.exception.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class ProductDAO implements IProductDAO {

	private final EntityManager entityManager;

	public ProductDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public Product create(Product product) {
		try {
			checkIfProductExists(product.getName());
			entityManager.persist(product);
			return product;
		} catch (ProductAlreadyExistsException ex) {
			handleException(ex);
			return null;
		} catch (Exception ex) {
			handleException(new ProductNotCreatedException(product.getName(), ex));
			return null;
		}
	}

	@Override
	@Transactional
	public Product update(Product product) {
		try {
			checkIfProductExists(product.getName());
			entityManager.merge(product);
			return product;
		} catch (ProductAlreadyExistsException ex) {
			handleException(ex);
			return null;
		} catch (Exception ex) {
			handleException(new ProductNotFoundException("Error updating product", ex));
			return null;
		}
	}

	@Override
	@Transactional
	public Product read(Integer id) {
		try {
			Product product = entityManager.find(Product.class, id);
			if (product == null) {
				throw new ProductNotFoundException(id);
			}
			return product;
		} catch (ProductNotFoundException ex) {
			handleException(ex);
			return null;
		} catch (Exception ex) {
			handleException(new ProductNotFoundException("Error reading product", ex));
			return null;
		}
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		try {
			Product product = entityManager.find(Product.class, id);
			if (product == null) {
				throw new ProductNotFoundException(id);
			}
			entityManager.remove(product);
		} catch (ProductNotFoundException ex) {
			handleException(ex);
		} catch (Exception ex) {
			handleException(new ProductNotDeletedException(id));
		}
	}

	private void checkIfProductExists(String productName) {
		try {
			TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(p) FROM Product p WHERE p.name = :name",
					Long.class);
			query.setParameter("name", productName);
			Long count = query.getSingleResult();

			if (count > 0) {
				throw new ProductAlreadyExistsException(productName);
			}
		} catch (Exception ex) {
			handleException(ex);
		}
	}

	private void handleException(Exception ex) {
		ex.printStackTrace();
	}

	@Override
	@Transactional
	public List<Product> getAllProducts() {
		try {
			String jpql = "SELECT p FROM Product p";
			TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
			return query.getResultList();
		} catch (Exception ex) {
			handleException(ex);
			return null;
		}
	}
}
