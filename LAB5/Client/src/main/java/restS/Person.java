package restS;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Human")
public class Person
{
        private String name;
        private String surname;
        private int age;
        private String username;
        private String position;
        private int status;

    public Human()
    { }

	public Person(String name, String surname, int age, String position, String username, int status) {
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.username = username;
		this.position = position;
		this.status = status;
	}
}