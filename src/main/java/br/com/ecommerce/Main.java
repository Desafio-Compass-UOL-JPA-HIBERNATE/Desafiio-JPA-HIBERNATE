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
                        productDAO.close(); //Closes the connection with the database
                        System.exit(0);
                        break;
                    case 1:
                        //List All Products
                        List<Product> products = productDAO.listAll();
                        //Reads the list of products and prints them
                        System.out.println(new StatusMessage(Status.OK, "GET Successful").toString());
                        for (Product p : products) {
                            //For each product in the list, it prints the product
                            System.out.println(p.toString());
                        }
                        break;
                    case 2:
                        //Search Product by ID
                        System.out.println("Search a Product");
                        System.out.println("Enter the ID or Name of the product: ");
                        if(scanner.hasNextInt()){ //If the input is an integer, it reads the ID
                            id = Integer.parseInt(scanner.nextLine());
                        }else{ //If the input is not an integer, it reads as the name
                            name = scanner.nextLine().toUpperCase();
                            //Searches for the product by name and gets the ID
                            id = productDAO.findByName(name).getId();
                        }

                        //Searches for the product by ID
                        Product product = productDAO.findById(id);
                        if(product != null){    //If the product is found, it prints the product
                            System.out.println(new StatusMessage(Status.OK, "GET Successful").toString());
                            System.out.println(product.toString());
                        }else{ //If the product is not found, it prints an error message
                            System.out.println(new StatusMessage(Status.NOT_FOUND, "Product not found").toString());
                        }

                        break;
                    case 3:
                        //Register New Product
                        System.out.println("Register a New Product");
                        do {
                            System.out.println("Enter the name of the product: ");
                            name = scanner.nextLine().toUpperCase();
                            //Checks if the product already exists
                            productDAO.checkIfProductExists(name);
                            if (name.isEmpty()) {
                                System.out.println("The name cannot be empty");
                            }
                        } while (name.isEmpty()); //If the name is empty, it asks for the name again

                        do {
                            System.out.println("Enter the value of the product: ");
                            value = Double.parseDouble(scanner.nextLine());
                            if (value <= 0) {
                                System.out.println("The value must be greater than 0");
                            }
                        } while (value <= 0);//If the value is less than or equal to 0, it asks for the value again

                        do {
                            System.out.println("Enter the description of the product: ");
                            description = scanner.nextLine();

                            if (description.length() < 10) {
                                System.out.println("The description must have at least 10 characters");
                            } //If the description has less than 10 characters, it asks for the description again
                        } while (description.length() < 10);

                        Product newProduct = new Product(name, value, description);
                        productDAO.create(newProduct);
                        //creates the product and persists it in the database
                        //then it prints the status message and the product that was created
                        System.out.println(new StatusMessage(Status.CREATED, "Product created").toString());
                        System.out.println(productDAO.findByName(newProduct.getName()).toString());
                        //as asked in the exercise, the product is shown after being registered
                        break;
                    case 4:
                        //Update Product
                        System.out.println("Update a Product");
                        System.out.println("Enter the ID or Name of the product: ");
                        if(scanner.hasNextInt()){
                            id = Integer.parseInt(scanner.nextLine());
                        }else{
                            name = scanner.nextLine().toUpperCase();
                            id = productDAO.findByName(name).getId();
                        }

                        if (productDAO.findById(id) == null) { //If the product is not found, it prints an error message
                            System.out.println(new StatusMessage(Status.NOT_FOUND, "Product not found").toString());
                        }else{
                            //If the product is found, it asks for the new name, value and description of the product
                            System.out.println("Enter the new name of the product: ");
                            String newName = scanner.nextLine().toUpperCase();

                            System.out.println("Enter the new value of the product: ");
                            Double newValue = Double.parseDouble(scanner.nextLine());

                            System.out.println("Enter the new description of the product: ");
                            String newDescription = scanner.nextLine().toUpperCase();

                            //Creates a new product with the new name, value and description
                            Product updatedProduct = new Product(newName, newValue, newDescription);
                            //Updates the product in the database
                            productDAO.update(updatedProduct, id);
                            //Prints the status message and the product that was updated
                            System.out.println(new StatusMessage(Status.OK, "Product updated").toString());
                            System.out.println(productDAO.findById(id).toString());
                        }
                        break;
                    case 5:
                        //Remove Product
                        System.out.println("Remove a Product");
                        System.out.println("Enter the ID or Name of the product: ");
                        if(scanner.hasNextInt()){ //If the input is an integer, it reads the ID
                            id = Integer.parseInt(scanner.nextLine());
                            Product p = productDAO.findById(id); //Searches for the product by ID
                            productDAO.delete(p.getId()); //Deletes the product and prints the status message
                            System.out.println(new StatusMessage(Status.NO_CONTENT, "Product deleted").toString());
                        }else{
                            name = scanner.nextLine().toUpperCase();
                            //if the input is not an integer, it reads as the name
                            //Searches for the product by name
                            Product p = productDAO.findByName(name);
                            productDAO.delete(p.getId()); //Deletes the product and prints the status message
                            System.out.println(new StatusMessage(Status.NO_CONTENT, "Product deleted").toString());
                        }
                        break;
                    default:
                        System.out.println("Invalid Option");
                        //If the input is not 0, 1, 2, 3, 4 or 5, it prints an error message
                }
            }while (true);

    }
}