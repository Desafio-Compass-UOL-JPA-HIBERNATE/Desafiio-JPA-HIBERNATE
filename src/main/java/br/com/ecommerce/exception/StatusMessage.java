package br.com.ecommerce.exception;

public class StatusMessage {

    private Status status;
    private String message;


    public StatusMessage(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return status.getCode();
    }

    public String getInternalStatus() {
        return status.getStatus();
    }

    public String toString() {
        //return as a json string with code status and message
        return "{\"code\": " + status.getCode() + ", \"status\": \"" + status.getStatus() + "\", \"message\": \"" + message + "\"}\n\n";

    }

}
