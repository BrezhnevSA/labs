package ifmo.ws.service;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebMethod;
import java.util.List;

@WebService(serviceName = "BookWebService")
public class BookWebService {

    private boolean basicAuth() {
        MessageContext mctx = wsctx.getMessageContext();
        Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
        List userList = (List) http_headers.get("Username");
        List passList = (List) http_headers.get("Password");

        String username = "";
        String password = "";
        
        if(userList!=null){
        	username = userList.get(0).toString();
        }
        	
        if(passList!=null){
        	password = passList.get(0).toString();
        }

        if (username.equals("user") && password.equals("password")){
        	return true;
        }else{
        	return false;
        }
    }
    
    @WebMethod(operationName = "getBooks")
    public List<Book> getBooks(){
        BookDAO dao = new BookDAO();
        List<Book> books = dao.getBooks();
        return books;
    }

    @WebMethod(operationName = "get2018Books")
    public List<Book> get2018BooksList(){
        BookDAO dao = new BookDAO();
        List<Book> books = dao.get2018BooksList();
        return books;
    }

    @WebMethod(operationName = "getLongDescriptionBooks")
    public List<Book> getLongDescriptionBooks(){
        BookDAO dao = new BookDAO();
        List<Book> books = dao.getLongDescriptionBooks();
        return books;
    }

    @WebMethod(operationName = "insertNewEntry")
    public int insertNewEntry(@WebParam(name = "author") String author,
                              @WebParam(name = "name") String name,
                              @WebParam(name = "year") int year,
                              @WebParam(name = "description") int description) {
        if (basicAuth()){
            BookDAO dao = new BookDAO();
            int result = dao.insertNewEntry(author, name, year, description);
            return result;
        } else {
            return -1;
        }
    }

    @WebMethod(operationName = "updateBookEntry")
    public int updateBookEntry(@WebParam(name = "id") int id,
                              @WebParam(name = "author") String author,
                              @WebParam(name = "name") String name,
                              @WebParam(name = "year") int year,
                              @WebParam(name = "description") int description) {
        if (basicAuth()){
            BookDAO dao = new BookDAO();
            int result = dao.updateBookEntry(id, author, name, year, description);
            return result;
        } else {
            return -1;
        }
    }

    @WebMethod(operationName = "removeBookEntry")
    public int removeBookEntry(@WebParam(name = "id") int id) {
        if (basicAuth()){
            BookDAO dao = new BookDAO();
            int result = dao.removeBookEntry(id);
            return result;
        } else {
            return -1;
        }
    }

}
