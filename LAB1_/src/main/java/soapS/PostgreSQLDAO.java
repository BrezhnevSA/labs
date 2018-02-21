package soapS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class PostgreSQLDAO {
    private Connection connection;

    public PostgreSQLDAO(Connection connection){
        this.connection = connection;
    }

    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<Person>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from persons");

            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                String username = rs.getString("username");
                String position = rs.getString("position");
                int status = rs.getInt("status");

                Person person = new Person(name, surname, age, position, username, status);
                persons.add(person);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return persons;
    }

    public List<Person> searchPersons(String name, String surname, int age, String username, String position, int status) {
        List<Person> persons = new ArrayList<Person>();
        try {
            Statement stmt = connection.createStatement();
            String condition = "";
            boolean moreThanOneCondition = false;
            if (name != "") {
                condition += " p.name = '" + name + "' ";
                moreThanOneCondition = true;
            }
            if (surname != "") {
                if (moreThanOneCondition) condition += " OR p.surname = '" + surname + "' ";
                else condition += " p.surname = '" + surname + "' ";
                moreThanOneCondition = true;
            }
            if (age != -1) {
                if (moreThanOneCondition) condition += " OR p.age = " + age + " ";
                else condition += " p.age = " + age + " ";
                moreThanOneCondition = true;
            }
            if (username != "") {
                if (moreThanOneCondition) condition += " OR p.username = '" + username + "' ";
                else condition += " p.username = '" + username + "' ";
                moreThanOneCondition = true;
            }
            if (position != "") {
                if (moreThanOneCondition) condition += " OR p.position == '" + position + "' ";
                else condition += " p.position = '" + position + "' ";
                moreThanOneCondition = true;
            }
            if (status != -1) {
                if (moreThanOneCondition) condition += " OR p.status = " + status + " ";
                else condition += " p.status = " + status + " ";
            }
            System.out.println("select * from persons p where" + condition);
            ResultSet rs = stmt.executeQuery("select * from persons p where" + condition );

            while (rs.next()) {
                String name_ = rs.getString("name");
                String surname_ = rs.getString("surname");
                int age_ = rs.getInt("age");
                String username_ = rs.getString("username");
                String position_ = rs.getString("position");
                int status_ = rs.getInt("status");

                Person person = new Person(name_, surname_, age_, position_, username_, status_);
                persons.add(person);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persons;
    }
}