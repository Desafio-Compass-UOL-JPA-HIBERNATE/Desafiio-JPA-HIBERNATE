package br.com.ecommerce.exception;

public class ProductAlreadyExistsException extends EcommerceException {

	private static final long serialVersionUID = 1L;

	public ProductAlreadyExistsException(String productName) {
		super("Product with name '" + productName + "' already exists");
	}

	public ProductAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
