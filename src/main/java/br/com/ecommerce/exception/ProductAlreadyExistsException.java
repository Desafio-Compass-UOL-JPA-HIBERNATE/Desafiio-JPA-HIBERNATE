package br.com.ecommerce.exception;

public class ProductAlreadyExistsException extends EcommerceException {

	private static final long serialVersionUID = 1L;

	//primeiro construtor aceita um id
	public ProductAlreadyExistsException(String productName) {
		super("Product with name '" + productName + "' already exists");
	}

	//segundo construtor aceita menssagem e causa
	public ProductAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
