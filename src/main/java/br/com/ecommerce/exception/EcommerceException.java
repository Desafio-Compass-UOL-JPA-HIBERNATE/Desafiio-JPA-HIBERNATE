package br.com.ecommerce.exception;

public class EcommerceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EcommerceException(String message) {
		super(message);
	}

	public EcommerceException(String message, Throwable cause) {
		super(message, cause);
	}

	public void logError() {
		System.err.println("Error in the e-commerce system: " + getMessage());
	}
}
