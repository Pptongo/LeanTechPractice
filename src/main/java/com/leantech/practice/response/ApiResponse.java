package com.leantech.practice.response;

/**
 * A generic API response object for all web services.
 * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
 * @version 1.0
 * @since 1.0
 */
public class ApiResponse {
    
    /**
     * The error message in case of exists.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private String message;

    /**
     * Any object to be passed as a response for the web services.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private Object body;

    /**
     * Inidicates if the web services run succesfully or not. In case of erro @see {@link #message}
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    private Boolean status;

    /**
     * Create an empty ApiResponse object.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     */
    public ApiResponse() { }

    /**
     * Create an ApiResponse object with defined status.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param status
     */
    public ApiResponse(Boolean status) {
        this.status = status;
    }

    /**
     * Create an ApiResponse object with status = true and message = null and with the passed body as body.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param body The object to be added into the response.
     */
    public ApiResponse(Object body) {
        this.status = true;
        this.body = body;
        this.message = null;
    }

    /**
     * Create an ApiResponse with defined body and message and status = true.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param body The object to be added into the response.
     * @param message The message to be added into the response.
     */
    public ApiResponse(Object body, String message) {
        this.status = true;
        this.body = body;
        this.message = message;
    }

    /**
     * Create an ApiResponse with status = false and a defined message.
     * @author Jose Luis Perez Olvera <sistem_pp@hotmail.com>
     * @version 1.0
     * @since 1.0
     * @param message The error message to be used for the response.
     */
    public ApiResponse(String message) {
        this.status = false;
        this.message = message;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public Object getBody() { return body; }

    public void setBody(Object body) { this.body = body; }

    public Boolean getStatus() { return status; }

    public void setStatus(Boolean status) { this.status = status; }

}
