package Administrador;

import Login.User;

public class Administrador extends User{
    public Administrador(String username, String password, String name, String lastName, String email, long num, int edad){
        super(username, password, name, lastName, email, num, edad);
    }
    public Administrador(){ }

    @Override
    public void editProfile(){
        new Edit_and_LogOut().editData();
    }
    
    @Override
    public void logout(){
        new Edit_and_LogOut().logOut();
    }
}
