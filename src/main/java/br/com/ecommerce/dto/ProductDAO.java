package br.com.ecommerce.dto;

import br.com.ecommerce.domain.Product;
import br.com.ecommerce.dto.interfaces.IProductDAO;

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
        emf = Persistence.createEntityManagerFactory("persiste-ecommerce"); // Aqui diz a unidade de persistência especificada no arquivo persistance.xml
        em = emf.createEntityManager();
    }

    public void close() {
        em.close();
        emf.close();
    }
    //Testing Privilege
    @Override
    public Product create(Product product) throws Exception {
        // Opening Connection to the DB
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        return  product;
    }

    @Override
    public Product update() throws Exception {
        return null;
    }

    @Override
    public List<Product> listAll() throws Exception {
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
        return query.getResultList();


    }

    @Override
    public Product findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public void delete() throws Exception {

    }
}
