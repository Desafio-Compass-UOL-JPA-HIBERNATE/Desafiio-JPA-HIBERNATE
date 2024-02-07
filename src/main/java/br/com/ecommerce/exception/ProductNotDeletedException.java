package br.com.ecommerce.exception;

/**
 * This exception is thrown when an error occurs while deleting a product.
 */
public class ProductNotDeletedException extends EcommerceException {

	private static final long serialVersionUID = 1L;

	/**
     * Constructor that accepts the ID of the product that could not be deleted and generates an appropriate error message.
     * @param productId The ID of the product that could not be deleted.
     */
	public ProductNotDeletedException(Integer productId) {
		super("Error deleting product with ID '" + productId + "'.");
	}

	/**
     * Constructor that accepts a custom message and the cause of the exception.
     * @param message The custom error message.
     * @param cause The cause of the exception.
     */
	public ProductNotDeletedException(String message, Throwable cause) {
		super(message, cause);
	}
}
