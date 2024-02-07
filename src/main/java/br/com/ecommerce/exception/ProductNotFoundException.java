package br.com.ecommerce.exception;

/**
 * This exception is thrown when a product with a specific ID is not found.
 */
public class ProductNotFoundException extends EcommerceException { 
	
	private static final long serialVersionUID = 1L;

	 /**
     * Constructor that accepts the ID of the product that was not found and generates an appropriate error message.
     * @param productId The ID of the product that was not found.
     */
    public ProductNotFoundException(Integer productId) {
        super("Product not found with ID: " + productId);
    }

    /**
     * Constructor that accepts a custom message and the cause of the exception.
     * @param message The custom error message.
     * @param cause The cause of the exception.
     */
    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}


