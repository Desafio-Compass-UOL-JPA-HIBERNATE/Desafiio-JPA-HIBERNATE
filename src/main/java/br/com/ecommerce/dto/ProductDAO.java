package br.com.ecommerce.dto;

import br.com.ecommerce.domain.Product;
import br.com.ecommerce.dto.interfaces.IProductDAO;
import br.com.ecommerce.exception.*;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ProductDAO implements IProductDAO {

	// EntityManagerFactory Manages Communication with DB
	EntityManagerFactory emf;
	// EntityManager does the transactions
	EntityManager em;

	// Adicionar validação
	public ProductDAO() {
		emf = Persistence.createEntityManagerFactory("persiste-ecommerce"); // Aqui diz a unidade de persistência
																			// especificada no arquivo persistance.xml
		em = emf.createEntityManager();
	}

	public void close() {
		em.close();
		emf.close();
	}

	// Testing Privilege
	@Override
	public Product create(Product product) throws ProductAlreadyExistsException, ProductNotCreatedException {
		try {
			em.getTransaction().begin();
			checkIfProductExists(product.getName());
			em.persist(product);
			em.getTransaction().commit();
			return product;
		} catch (ProductAlreadyExistsException ex) {
			em.getTransaction().rollback();
			throw ex;
		} catch (Exception ex) {
			em.getTransaction().rollback();
			throw new ProductNotCreatedException(product.getName(), ex);
		}
	}

	@Override
	public Product update(Product p, Integer id) throws Exception {
		em.getTransaction().begin();
		Product product = findById(id);
		try {
			if (product == null) {
				throw new ProductNotFoundException(id);
			}
			product.setName(p.getName());
			product.setValue(p.getValue());
			product.setDescription(p.getDescription());
			em.merge(product);
			em.getTransaction().commit();
			return product;
		} catch (ProductNotFoundException ex) {
			em.getTransaction().rollback();
			throw ex;
		} catch (Exception ex) {
			em.getTransaction().rollback();
			throw new ProductNotFoundException("Error updating product", ex);
		}
	}

	@Override
	public List<Product> listAll() throws Exception {
		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
		return query.getResultList();

	}

	@Override
	public Product findById(Integer id) throws Exception {
		return em.find(Product.class, id);

	}

	@Override
	public void delete(Integer id) throws Exception {
		em.getTransaction().begin();
		Product product = findById(id);
		try {
			if (product == null) {
				throw new ProductNotFoundException(id);
			}
			em.remove(product);
			em.getTransaction().commit();
		} catch (ProductNotFoundException ex) {
			em.getTransaction().rollback();
			throw ex;
		} catch (Exception ex) {
			em.getTransaction().rollback();
			throw new ProductNotDeletedException(id);
		}
	}

	private void checkIfProductExists(String productName) throws ProductAlreadyExistsException {
		TypedQuery<Long> query = em.createQuery("SELECT COUNT(p) FROM Product p WHERE p.name = :name", Long.class);
		query.setParameter("name", productName);//compara no banco de dados se o produto com mesmo nome já existe
		Long count = query.getSingleResult();

		if (count > 0) {
			throw new ProductAlreadyExistsException(productName);
		}
	}
}
