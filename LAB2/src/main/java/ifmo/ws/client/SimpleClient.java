package ifmo.ws.client;

import ifmo.ws.generated.Book;
import ifmo.ws.generated.BookWebServicePortBindingStub;
import ifmo.ws.generated.BookWebService_ServiceLocator;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.List;
import java.net.MalformedURLException;
import javax.xml.rpc.ServiceException;
import java.io.Console;
import java.util.ArrayList;
import java.util.logging.Logger;

public class SimpleClient {
    private static Logger logger = Logger.getLogger(SimpleClient.class.getName());
    private static final String SUCCESSFUL_OP = "Result of operation is successful";
    private static final String UNSUCCESSFUL_OP = "Result of operation is unsuccessful";

    public static void main(String[] args) throws MalformedURLException{
        URL url = new URL("http://localhost:8081/lab2/BookWebService?wsdl");
        try {
            BookWebService_ServiceLocator locator = new BookWebService_ServiceLocator();
            BookWebServicePortBindingStub bookService = (BookWebServicePortBindingStub) locator.getBookWebServicePort(url);
            initCli(bookService);
        } catch (RemoteException | ServiceException e) {
            logger.log(Level.SEVERE, "An exception is occured: ", e);
        }
    }

    private static void initCli(BookWebServicePortBindingStub bookService) throws RemoteException{
        Scanner in;
        in = new Scanner(System.in);
        List<Book> bookList = new ArrayList<>();
        String command = "";
        try {
            while (!(command = in.nextLine()).equals("quit")) {
                if (command.equals("all")) {
                    bookList = Arrays.asList(bookService.getBooks());
                    writeListOfBooks(bookList);
                } else if (command.equals("russian")) {
                    bookList = Arrays.asList(bookService.getToyotas());
                    writeListOfBooks(bookList);
                } else if (command.equals("longbook")) {
                    bookList = Arrays.asList(bookService.getLongDistanceBooks());
                    writeListOfBooks(bookList);
                } else if (command.equals("insert")) {
                    String author = in.nextLine();
                    logger.info("Author = " + author);
                    String name = in.nextLine();
                    logger.info("Name = " + name);
                    int year = Integer.parseInt(in.nextLine());
                    logger.info("Year = " + year);
                    int description = Integer.parseInt(in.nextLine());
                    logger.info("Description = " + description);
                    handleInsertion(bookService, author, name, year, description);
                } else if (command.equals("update")) {
                    int id = Integer.parseInt(in.nextLine());
                    logger.info("ID = " + id);
                    String author = in.nextLine();
                    logger.info("Author = " + author);
                    String name = in.nextLine();
                    logger.info("Name = " + name);
                    int year = Integer.parseInt(in.nextLine());
                    logger.info("Year = " + year);
                    int description = Integer.parseInt(in.nextLine());
                    logger.info("Description = " + description);
                    handleUpdate(bookService, id, author, name, year, description);
                } else if (command.equals("delete")) {
                    int id = Integer.parseInt(in.nextLine());
                    handleDelete(bookService, id);
                }
            }
        }catch (Exception e) {
            logger.log(Level.SEVERE, "An exception is occured: ", e);
        }
    }

    private static void writeListOfBooks(List<Book> bookList) {
        for (Book book : bookList) {
            System.out.println(
                    "Book{" +
                            "id=" + book.getId() +
                            ", athor='" + book.getAuthor() + '\'' +
                            ", name='" + book.getName() + '\'' +
                            ", year=" + book.getYear() +
                            ", description=" + book.getDescription() +
                            '}'
            );
        }
    }

    private static void handleInsertion(BookWebServicePortBindingStub bookService, String author, String name, int year, int description) {
        try {
            int result = bookService.insertNewEntry(id, author, name, year, description);
            if (result == 1) {
                logger.info(SUCCESSFUL_OP);
            } else {
                logger.info(UNSUCCESSFUL_OP);
            }
        } catch (RemoteException e) {
            logger.log(Level.SEVERE, "Exception occured during inserting entry:",e );
        }
    }

    private static void handleUpdate(BookWebServicePortBindingStub bookService, int id, String author, String name, int year, int description) {
        try {
            int result = bookService.updateBookEntry(id, author, name, year, description);
            if (result == 1) {
                logger.info(SUCCESSFUL_OP);
            } else {
                logger.info(UNSUCCESSFUL_OP);
            }
        } catch (RemoteException e) {
            logger.log(Level.SEVERE, "Exception occured during inserting entry:",e );
        }
    }

    private static void handleDelete(BookWebServicePortBindingStub bookService, int id) {
        try {
            int result = bookService.removeBookEntry(id);
            if (result == 1) {
                logger.info(SUCCESSFUL_OP);
            } else {
                logger.info(UNSUCCESSFUL_OP);
            }
        } catch (RemoteException e) {
            logger.log(Level.SEVERE, "Exception occured during inserting entry:",e );
        }
    }

}
