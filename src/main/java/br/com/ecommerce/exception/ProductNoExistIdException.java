package br.com.ecommerce.exception;

/**
 * This exception is thrown when there is no product with the specified ID.
 */
public class ProductNoExistIdException extends EcommerceException {

	private static final long serialVersionUID = 1L;

	/**
     * Constructor that accepts the ID of the non-existent product and creates a corresponding error message.
     * @param id The ID of the product that was not found.
     */
	public ProductNoExistIdException(Integer id) {
		super("There is no product with this ID " + id);
		System.out.println(new StatusMessage(Status.NOT_FOUND, "Product with ID " + id + " not found").toString());
	}

	 /**
     * Constructor that accepts a custom message and the cause of the exception.
     * @param message The custom error message.
     * @param cause The cause of the exception.
     */
	public ProductNoExistIdException(String message, Throwable cause) {
		super(message, cause);
		System.out.println(new StatusMessage(Status.NOT_FOUND, message).toString());
	}
}
