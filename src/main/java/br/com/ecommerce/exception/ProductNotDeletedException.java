package br.com.ecommerce.exception;

public class ProductNotDeletedException extends EcommerceException {

	private static final long serialVersionUID = 1L;

	//primeiro construtor aceita um id
	public ProductNotDeletedException(Integer productId) {
		super("Error deleting product with ID '" + productId + "'.");
	}

	//segundo construtor aceita menssagem e causa
	public ProductNotDeletedException(String message, Throwable cause) {
		super(message, cause);
	}
}
