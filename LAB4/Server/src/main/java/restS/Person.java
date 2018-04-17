package restS;
@XmlRootElement
public class Person {
        private String name;
        private String surname;
        private int age;
        private String username;
        private String position;
        private int status;
        public Person() {
        }
        public Person(String name, String surname, int age, String position, String username, int status) {
            this.name = name;
            this.surname = surname;
            this.age = age;
            this.username = username;
            this.position = position;
            this.status = status;
        }
        public String getName() {
            return name;
        }
        public String getSurname() {
            return surname;
        }
        public int getAge() {
            return age;
        }
        public String getUsername() {
            return username;
        }
        public int getStatus() {
            return status;
        }
        public String getPosition() {
            return position;
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setSurname(String surname) {
            this.surname = surname;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public void setPosition(String position) {
            this.position = position;
        }
        public void setStatus(int status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "Person{" + "name=" + name +
                             ", surname=" + surname +
                             ", age=" + age +
                             ", username=" + username +
                             ", position=" + position +
                             ", status=" + status +
                          '}';
        }
}
