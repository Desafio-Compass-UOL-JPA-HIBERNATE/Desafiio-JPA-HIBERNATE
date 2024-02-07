package br.com.ecommerce;

import br.com.ecommerce.domain.Product;
import br.com.ecommerce.dto.ProductDAO;
import br.com.ecommerce.exception.Status;
import br.com.ecommerce.exception.StatusMessage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Main class that is located in the main class that runs the program, and the same class that displays the main menu
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ProductDAO productDAO = new ProductDAO(); //Begins the connection with the database
        Scanner scanner = new Scanner(System.in);
        int choice;

        int id;
        String name;
        double value;
        String description;

            do{
                System.out.println("1 - List All Products");
                System.out.println("2 - Search Product by ID");
                System.out.println("3 - Register New Product");
                System.out.println("4 - Update Product");
                System.out.println("5 - Remove Product");
                System.out.println("0 - Exit\n");
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice){
                    case 0:
                        //Exit
                        break;
                    case 1:
                        //List All Products
                        List<Product> products = productDAO.listAll();
                        //Reads the list of products and prints them
                        System.out.println(new StatusMessage(Status.OK, "GET Successful").toString());
                        for (Product p : products) {
                            System.out.println(p.toString());
                        }
                        break;
                    case 2:
                        //Search Product by ID
                        System.out.println("Enter the ID of the product: ");
                        id = Integer.parseInt(scanner.nextLine());

                        Product product = productDAO.findById(id);
                        if(product != null){
                            System.out.println(new StatusMessage(Status.OK, "GET Successful").toString());
                            System.out.println(product.toString());
                        }else{
                            System.out.println(new StatusMessage(Status.NOT_FOUND, "Product not found").toString());
                        }

                        break;
                    case 3:
                        //Register New Product
                        do {
                            System.out.println("Enter the name of the product: ");
                            name = scanner.nextLine().toUpperCase();
                            productDAO.checkIfProductExists(name);
                            if (name.isEmpty()) {
                                System.out.println("The name cannot be empty");
                            }
                        } while (name.isEmpty());

                        do {
                            System.out.println("Enter the value of the product: ");
                            value = Double.parseDouble(scanner.nextLine());
                            if (value <= 0) {
                                System.out.println("The value must be greater than 0");
                            }
                        } while (value <= 0);

                        do {
                            System.out.println("Enter the description of the product: ");
                            description = scanner.nextLine();

                            if (description.length() < 10) { //se achar melhor pode tratar isso como exceção
                                System.out.println("The description must have at least 10 characters");
                            }
                        } while (description.length() < 10);

                        Product newProduct = new Product(name, value, description);
                        productDAO.create(newProduct);
                        System.out.println(new StatusMessage(Status.CREATED, "Product created").toString());
                        System.out.println(productDAO.findByName(newProduct.getName()).toString());
                        //as asked in the exercise, the product is shown after being registered
                        break;
                    case 4:
                        //Update Product
                        System.out.println("Enter the ID of the product: ");
                        id = Integer.parseInt(scanner.nextLine());

                        if (productDAO.findById(id) == null) {
                            System.out.println(new StatusMessage(Status.NOT_FOUND, "Product not found").toString());
                            break;
                        }else{
                            System.out.println("Enter the new name of the product: ");
                            String newName = scanner.nextLine().toUpperCase();

                            System.out.println("Enter the new value of the product: ");
                            Double newValue = Double.parseDouble(scanner.nextLine());

                            System.out.println("Enter the new description of the product: ");
                            String newDescription = scanner.nextLine().toUpperCase();

                            Product updatedProduct = new Product(newName, newValue, newDescription);
                            productDAO.update(updatedProduct, id);
                            System.out.println(new StatusMessage(Status.OK, "Product updated").toString());
                            System.out.println(productDAO.findById(id).toString());
                        }

                        break;
                    case 5:
                        //Remove Product
                        System.out.println("Enter the ID of the product: ");
                        id = Integer.parseInt(scanner.nextLine());
                        productDAO.checkIfProductExistsId(id);
                        productDAO.delete(id);
                        System.out.println(new StatusMessage(Status.NO_CONTENT, "Product deleted").toString());
                        break;
                    default:
                        System.out.println("Invalid Option");
                }
            }while (choice != 0);

        productDAO.close(); //Closes the connection with the database
    }

}