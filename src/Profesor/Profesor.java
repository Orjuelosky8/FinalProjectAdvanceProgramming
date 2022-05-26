package Profesor;

import java.util.ArrayList;

import Login.User;

public class Profesor extends User{
    
    public Profesor(String username, String password, String name, String lastName, String email, long num, int age, ArrayList<String> subjects){
        super(username, password, name, lastName, email, num, age, subjects);
    }
    public Profesor(){

    }

    @Override
    public void editProfile(){
        new Edit_and_LogOut().editProfile();
    }
    
    @Override
    public void logout(){
        new Edit_and_LogOut().logout();
    }
}
