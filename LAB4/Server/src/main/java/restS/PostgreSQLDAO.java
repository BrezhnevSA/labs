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
									 rs.getInt("status")));
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