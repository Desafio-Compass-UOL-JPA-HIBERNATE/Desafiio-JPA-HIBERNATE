package br.com.ecommerce.exception;

/**
 * This exception is thrown when an error occurs while creating a product.
 */
public class ProductNotCreatedException extends EcommerceException {

	private static final long serialVersionUID = 1L;

	/**
     * Constructor that accepts the name of the product that could not be created and generates an appropriate error message.
     * @param productName The name of the product that could not be created.
     */
	public ProductNotCreatedException(String productName) {
		super("Error creating product with name '" + productName);
		System.out.println(new StatusMessage(Status.INTERNAL_SERVER_ERROR, "Error creating product with name " + productName).toString());
	}

	/**
     * Constructor that accepts a custom message and the cause of the exception.
     * @param message The custom error message.
     * @param cause The cause of the exception.
     */
	public ProductNotCreatedException(String message, Throwable cause) {
		super(message, cause);
		System.out.println(new StatusMessage(Status.INTERNAL_SERVER_ERROR, message).toString());
	}
}
