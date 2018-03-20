package ifmo.ws.service;

public class IllegalNameException extends Exception {

    private static final long serialVersionUID = -6647544772732631047L;
    private final BookServiceFault fault;
    public IllegalNameException(String message, BookServiceFault fault) {
        super(message);
        this.fault = fault;
    }
    public IllegalNameException(String message, BookServiceFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }
    public BookServiceFault getFaultInfo() {
        return fault;
    }
}