package br.com.ecommerce.exception;

public class ProductNotDeletedException extends EcommerceException {

	private static final long serialVersionUID = 1L;

	public ProductNotDeletedException(Integer productId) {
		super("Error deleting product with ID '" + productId + "'.");
	}

	public ProductNotDeletedException(String message, Throwable cause) {
		super(message, cause);
	}
}
