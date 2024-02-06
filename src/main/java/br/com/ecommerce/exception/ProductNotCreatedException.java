package br.com.ecommerce.exception;

public class ProductNotCreatedException extends EcommerceException {

	private static final long serialVersionUID = 1L;

	//primeiro construtor aceita um id
	public ProductNotCreatedException(String productName) {
		super("Error creating product with name '" + productName + "'.");
	}

	//segundo construtor aceita menssagem e causa
	public ProductNotCreatedException(String message, Throwable cause) {
		super(message, cause);
	}
}
