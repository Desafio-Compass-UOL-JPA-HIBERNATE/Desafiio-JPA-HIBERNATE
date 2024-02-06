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

  
    //  EntityManagerFactory Manages Communication with DB
    EntityManagerFactory emf;
    // EntityManager does the transactions
    EntityManager em;


    //Adicionar validação
    public ProductDAO() {
        try {
            emf = Persistence.createEntityManagerFactory("persiste-ecommerce"); // Aqui diz a unidade de persistência especificada no arquivo persistance.xml
            em = emf.createEntityManager();
        }catch (Exception e) {
            System.out.println("Error creating database connection");
        }
    }

    public void close() { //Closes database connection
        em.close();
        emf.close();
    }

    //Testing Privilege
    @Override
    public Product create(Product product) throws Exception {
        try {
            // Opening Connection to the DB
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
            return product;
        }catch (Exception e){   //TODO: Tratar exceção
            throw new Exception("Error creating product");
        }
    }

    @Override
    public Product update(Product p, Integer id) throws Exception {
        try {
            em.getTransaction().begin();
            Product product = findById(id);
            if (product == null) {
                throw new Exception("Product not found");
            }
            product.setName(p.getName());
            product.setValue(p.getValue());
            product.setDescription(p.getDescription());

            em.merge(product);
            em.getTransaction().commit();
            return product;
        }catch (Exception e){   //TODO: Tratar exceção
            throw new Exception("Error updating product");
        }
    }

    @Override
    public List<Product> listAll() throws Exception {
        try{
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
        return query.getResultList();
        }catch (Exception e){   //TODO: Tratar exceção
            throw new Exception("Error listing products");
        }

    }

    @Override
    public Product findById(Integer id) throws Exception {
        try {
            return em.find(Product.class, id);
        }catch (Exception e){ //TODO: Tratar exceção
            throw new Exception("Error finding product");
        }
    }

    public Product findByName(String name) throws Exception {
        try {
            TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.name = :name", Product.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        }catch (Exception e){ //TODO: Tratar exceção
            throw new Exception("Error finding product");
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try {
            em.getTransaction().begin();
            Product product = findById(id);
            if (product == null) {
                throw new Exception("Product not found");
            }
            em.remove(product);
            em.getTransaction().commit();
        }catch (Exception e){ //TODO: Tratar exceção
            throw new Exception("Error deleting product");
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
