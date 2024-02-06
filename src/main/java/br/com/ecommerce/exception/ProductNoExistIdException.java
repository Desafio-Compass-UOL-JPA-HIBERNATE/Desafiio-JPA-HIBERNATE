package br.com.ecommerce.exception;

public class ProductNoExistIdException extends EcommerceException {

	private static final long serialVersionUID = 1L;

	public ProductNoExistIdException(Integer id) {
		super("There is no product with this ID " + id);
	}

	//segundo construtor aceita menssagem e causa
	public ProductNoExistIdException(String message, Throwable cause) {
		super(message, cause);
	}
}
