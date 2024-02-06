package br.com.ecommerce.exception;

public class ProductNotFoundException extends EcommerceException { 
	
	private static final long serialVersionUID = 1L;

	//primeiro construtor aceita um id
    public ProductNotFoundException(Integer productId) {
        super("Product not found with ID: " + productId);
    }

    //segundo construtor aceita mensagem e causa
    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}


