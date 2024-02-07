package br.com.ecommerce.exception;

/**
 * Base exception class for e-commerce related exceptions.
 */
public class EcommerceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
     * Constructs an EcommerceException with the specified detail message.
     * @param message The detail message.
     */
	public EcommerceException(String message) {
		super(message);
	}

	/**
     * Constructs an EcommerceException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause of the exception.
     */
	public EcommerceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
     * Logs the error message to the standard error stream.
     */
	public void logError() {
		System.err.println("Error in the e-commerce system: " + getMessage());
	}
}
