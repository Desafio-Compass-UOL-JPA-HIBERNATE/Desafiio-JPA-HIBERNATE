package br.com.ecommerce.dto;

import br.com.ecommerce.domain.Product;
import br.com.ecommerce.dto.interfaces.IProductDAO;
import br.com.ecommerce.exception.*;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * The ProductDAO class is located in the
 */

public class ProductDAO implements IProductDAO {

	// EntityManagerFactory Manages Communication with DB
	EntityManagerFactory emf;
	// EntityManager does the transactions
	EntityManager em;

	/**
	 * Constructor
	 */
	public ProductDAO() {
		try {
			emf = Persistence.createEntityManagerFactory("persiste-ecommerce"); // Aqui diz a unidade de persistência
																				// especificada no arquivo
																				// persistance.xml
			em = emf.createEntityManager();
		} catch (Exception e) {
			System.out.println("Error creating database connection");
		}
	}

	/**
	 * Method that terminates the EntityManager connection
	 */
	public void close() { // Closes database connection
		em.close();
		emf.close();
	}

	/**
	 * This method adds the product
	 * 
	 * @param product Object product
	 * @return Returns the product when it is able to perform the survey
	 * @throws Exception
	 */
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

	/**
	 * Method of updating product
	 * 
	 * @param p  Object product
	 * @param id Product ID
	 * @return Retorna o produto ao realizar o update
	 * @throws Exception
	 */
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

	/**
	 * Method that lists products
	 * 
	 * @return Returns the list of products that are saved in the database
	 * @throws Exception
	 */
	@Override
	public List<Product> listAll() throws Exception {
		try {
			TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
			return query.getResultList();
		} catch (Exception ex) {
			throw new ProductNotFoundException("Error listing products", ex);
		}
	}

	/**
	 * Method that searches by ID
	 * 
	 * @param id Product ID
	 * @return It will return the product found with the ID entered in the parameter
	 * @throws Exception
	 */

	@Override
	public Product findById(Integer id) throws Exception {
		try {
			return em.find(Product.class, id);
		} catch (Exception ex) {
			throw new ProductNotFoundException("Error finding product", ex);
		}
	}

	/**
	 *
	 * @param name Product name
	 * @return It will return the product found with the name entered in the parameter
	 * @throws Exception
	 */
	public Product findByName(String name) throws ProductNotFoundException {
		try {
			TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.name = :name", Product.class);
			query.setParameter("name", name);
			return query.getSingleResult();
		} catch (NoResultException e) {
			throw new ProductNotFoundException("Product with name '" + name + "' not found", e);
		} catch (Exception e) {
			throw new ProductNotFoundException("Error finding product by name", e);
		}
	}

	/**
	 * Method for deleting the product
	 * 
	 * @param id Product ID that will be deleted
	 * @throws Exception
	 */
	@Override
	public void delete(Integer id) throws Exception {
		try {
			em.getTransaction().begin();
			Product product = findById(id);
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

	/**
	 * Checks if a product with the given  exists in the database.
	 * @param productName
	 * @throws ProductAlreadyExistsException
	 */
	public void checkIfProductExists(String productName) throws ProductAlreadyExistsException {
		TypedQuery<Long> query = em.createQuery("SELECT COUNT(p) FROM Product p WHERE p.name = :name", Long.class);
		query.setParameter("name", productName);// compara no banco de dados se o produto com mesmo nome já existe
		Long count = query.getSingleResult();
		if (count > 0) {
			throw new ProductAlreadyExistsException(productName);
		}
	}

	/**
	 * Checks if a product with the given ID exists in the database.
	 * @param id The ID of the product to check.
	 * @throws ProductNoExistIdException If no product exists with the specified ID.
	 */
	public void checkIfProductExistsId(Integer id) throws ProductAlreadyExistsException {
		TypedQuery<Long> query = em.createQuery("SELECT COUNT(p) FROM Product p WHERE p.id = :id", Long.class);
		query.setParameter("id", id);
		Long count = query.getSingleResult();
		if (!(count > 0)) {
			throw new ProductNoExistIdException(id);
		}
	}

}
