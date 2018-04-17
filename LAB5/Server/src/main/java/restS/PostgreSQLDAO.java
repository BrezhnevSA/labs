package restS;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSQLDAO {
    public static final int STATUS_GOOD = 0, STATUS_ERROR = 1, STATUS_BAD_REQUEST = 2;

    public int addPerson(@Nullable String name, @Nullable String surname, @Nullable Integer age, @Nullable String username, @Nullable String position, @Nullable Integer status) {
        try {
            final String query = String.format("INSERT INTO persons VALUES('%s', '%s', '%d', '%s', '%s', '%d');", name, surname, age, username, position, status);
            System.out.print("Execute: " + query);
            Connection conn = ConnectionUtil.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            System.out.println("execute success");
            return STATUS_GOOD;
        } catch (SQLException e) { 
			e.printStackTrace(); 
		}
        return STATUS_ERROR;
    }

    public int removePerson(String username) {
        try {
            final String query = "DELETE FROM persons WHERE username = " + username;
            System.out.print("Execute: " + query);
            Connection conn = ConnectionUtil.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            System.out.println("execute success");
            return STATUS_GOOD;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }
        return STATUS_ERROR;
    }

    public int changePerson(@Nullable String name, @Nullable String surname, @Nullable Integer age, @Nullable String username, @Nullable String position, @Nullable Integer status) {
        try {
            final String query = String.format(
                "UPDATE humans" +
                        " SET name = '%s'," +
                            " surname = '%s'," +
                            " age = '%d'," +
                            " username = '%s'" +
							" position = '%s'" +
							" status = '%s'" +
                        " WHERE id = '%d';",
                    name, surname, age, username, position, status);
            System.out.print("Execute: " + query);
            Connection conn = ConnectionUtil.getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            System.out.println("execute success");
            return STATUS_GOOD;
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
        }
        return STATUS_ERROR;
    }

    public List<Person> getPerson() {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from persons");
            while (rs.next()) {
                persons.add(new Person(rs.getString("name"),
                                       rs.getString("surname"),
                                       rs.getInt("age"),
									   rs.getString("username"),
                                       rs.getString("position"),
									   rs.getInt("status"));
            }
        }
        catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        return persons;
    }

    public List<Persons> getPersons(@Nullable String name, @Nullable String surname, @Nullable Integer age, @Nullable String username, @Nullable String position, @Nullable Integer status) {
        List<Person> gettedPersons;
        gettedPersons = new ArrayList<>();
        for (Person person : getPersons()) {
            if ((name == null || person.name.equals(name))
            && (username == null || person.username.equals(username))
            && (surname == null || person.surname.equals(surname))
            && (age == null || person.age != 0 || person.age == age)
            && (position == null || person.position.equals(position))
			&& (status == null || person.status != 0 || person.status == status))	
                selectedPersons.add(person);
        }
        return gettedPersons;
    }
}