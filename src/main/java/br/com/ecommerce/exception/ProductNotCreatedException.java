package br.com.ecommerce.exception;

public class ProductNotCreatedException extends EcommerceException {

	private static final long serialVersionUID = 1L;

	public ProductNotCreatedException(String productName) {
		super("Error creating product with name '" + productName + "'.");
	}

	public ProductNotCreatedException(String message, Throwable cause) {
		super(message, cause);
	}
}
