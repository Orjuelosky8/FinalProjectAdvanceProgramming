package Login;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {
    protected String username, password, name, lastName, email;
    protected long num;
    protected int age;
    protected ArrayList<String>subjects;

    public User(String username, String password, String name, String lastName, String email, long num, int age, ArrayList<String> subjects) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.num = num;
        this.age = age;
        this.subjects = subjects;
    }

    public User(String username, String password, String name, String lastName, String email, long num, int age) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.num = num;
        this.age = age;
    }

    public User(){ }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<String> getSubjects(){
        return subjects;
    }

    public void setSubjects(ArrayList<String> subjects) {
        this.subjects = subjects;
    }

    public abstract void editProfile();
    public abstract void logout();

}
