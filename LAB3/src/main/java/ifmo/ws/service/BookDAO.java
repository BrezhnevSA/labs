package ifmo.ws.service;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.sql.*;

public class BookDAO {
    private Logger logger = Logger.getLogger(BookDAO.class.getName());
    private static String INSERT_BOOK_SQL = "INSERT INTO IFMO_SCHEMA.BOOKS (author, name, year, description) VALUES (?, ?, ?, ?)";
    private static String DELETE_BOOK_SQL = "DELETE FROM IFMO_SCHEMA.BOOKS WHERE id = ?";
    private static String UPDATE_BOOK_SQL = "UPDATE IFMO_SCHEMA.BOOKS SET author = ?, name = ?, year = ?, description = ? where id = ?";

    public List<Book> getBooks(){
        List<Book> bookArrayList = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from ifmo_schema.books");
            packToObjectAddToList(bookArrayList, rs);
        } catch(SQLException ex) {
            logger.log(Level.SEVERE, "An exception caused: ", ex);
        }
        return bookArrayList;
    }

    public List<Book> get2018BooksList(){
        List<Book> bookArrayList = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from ifmo_schema.books where year = 2018");
            packToObjectAddToList(bookArrayList, rs);
        } catch(SQLException ex) {
            logger.log(Level.SEVERE, "An exception caused: ", ex);
        }
        return bookArrayList;
    }

    public List<Book> getLongDescriptionBooks(){
        List<Book> bookArrayList = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from ifmo_schema.books where char_length(description) > 2000");
            packToObjectAddToList(bookArrayList, rs);
        } catch(SQLException ex) {
            logger.log(Level.SEVERE, "An exception caused: ", ex);
        }
        return bookArrayList;
    }

    public int insertNewEntry(String author, String name, int year, int description) throws IllegalNameException {
        int result = -1;
        if (name == null || name.trim().isEmpty()) {
            BookServiceFault fault = BookServiceFault.defaultInstance();
            throw new IllegalNameException("bookName is not specified", fault);
        }
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(INSERT_BOOK_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, author);
            statement.setString(2, name);
            statement.setInt(3, year);
            statement.setInt(4, description);
            result = statement.executeUpdate();
            if (result == 0) {
                logger.log(Level.SEVERE, "Adding entry failed, no rows affected");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idResult = generatedKeys.getInt(1);
                logger.info("Inserted entry with id: " + idResult);
            } else {
                logger.log(Level.SEVERE, "Adding entry failed, no ID obtained");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An exception caused: ", e);
        }
        return result;
    }

    public int updateBookEntry(int id, String author, String name, int year, int description) {
        int result = 0;
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_BOOK_SQL);
            statement.setString(1, author);
            statement.setString(2, name);
            statement.setInt(3, year);
            statement.setInt(4, description);
            statement.setInt(5, id);
            result = statement.executeUpdate();
            if (result == 0) {
                logger.info("No entries updated");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An exception caused: ", e);
        }
        return result;
    }

    public int removeBookEntry(int id) {
        int result = 0;
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_BOOK_SQL);
            statement.setInt(1, id);
            statement.execute();
            result = 1;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "An exception caused: ", e);
        }
        return result;
    }

    private void packToObjectAddToList(List<Book> bookArrayList, ResultSet rs) throws SQLException {
        while (rs.next()){
            int id = rs.getInt("id");
            String author = rs.getString("author");
            String name = rs.getString("name");
            int year = rs.getInt("year");
            int description = rs.getInt("description");
            Book book = new Book(id, author, name, year, description);
            bookArrayList.add(book);
        }
    }

}