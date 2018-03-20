package ifmo.ws.service;

public class BookServiceFault {
    private static final String DEFAULT_MESSAGE = "bookName cannot be null or empty";
    protected String message;

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public static BookServiceFault defaultInstance() {
        BookServiceFault fault = new BookServiceFault();
        fault.message = DEFAULT_MESSAGE;
        return fault;
    }
}
