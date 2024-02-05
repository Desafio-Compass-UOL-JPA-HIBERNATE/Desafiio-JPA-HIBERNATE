package br.com.ecommerce.dto;

import br.com.ecommerce.domain.Product;
import br.com.ecommerce.dto.interfaces.IProductDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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


    //Testing Privilege
    @Override
    public Product create(Product product) throws Exception {
        // Opening Connection to the DB
        em.getTransaction().begin();
        em.merge(product);  // poderia ser em.persist(cliente);
        em.getTransaction().commit();
        emf.close();
        return  product;
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
