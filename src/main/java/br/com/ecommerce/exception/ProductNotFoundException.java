package br.com.ecommerce.exception;

public class ProductNotFoundException extends EcommerceException { 
	
	private static final long serialVersionUID = 1L;

    public ProductNotFoundException(Integer productId) {
        super("Product not found with ID: " + productId);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}


