package br.com.ecommerce.exception;

/**
 * Exception thrown when a product already exists in the system.
 */
public class ProductAlreadyExistsException extends EcommerceException {

	private static final long serialVersionUID = 1L;

	/**
     * Constructs a ProductAlreadyExistsException with the specified product name.
     * @param productName The name of the product that already exists.
     */
	public ProductAlreadyExistsException(String productName) {
		super("Product with name '" + productName + "' already exists");
	}
	
	/**
     * Constructs a ProductAlreadyExistsException with the specified product ID.
     * @param id The ID of the product that already exists.
     */
	public ProductAlreadyExistsException(Integer id) {
		super("Product with ID '" + id + "' already exists");
	}

	/**
     * Constructs a ProductAlreadyExistsException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause of the exception.
     */
	public ProductAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
