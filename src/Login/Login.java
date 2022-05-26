package Login;

import java.util.Scanner;

import Administrador.Administrador;
import Administrador.MenuAdmins;
import Estudiante.Estudiante;
import Estudiante.MenuEstudiantes;
import Profesor.MenuProfesores;
import Profesor.Profesor;

import java.io.*;

public class Login {
    Scanner scanning = new Scanner(System.in);

    public void asking_data() throws IOException {
        String username, password;
        char rol;
        System.out.println("* < A-bcd-E-fgh-I-jklmn-O-pqrst-U-vwxyZ > *");
        System.out.println("\t--o--o-- LOGIN --o--o-- ");
        System.out.println(" ** Iniciar Sesion en Javestion ** ");
        System.out.print("\nDigita tu username: ");
        username = scanning.next();
        System.out.print("\nDigita tu contraseña: ");
        password = scanning.next();
        rol = validating(username, password);
        redirectTo(rol);
    }

    private char validating(String username, String password) throws IOException{
        File fileA = new File(".//Files//Admins.obj");
        File fileE = new File(".//Files//Students.obj");
        File fileP = new File(".//Files//Teachers.obj");
        ObjectInputStream ois = null;
        try {
            FileInputStream fisA = new FileInputStream(fileA);
            while (true) {
                ois = new ObjectInputStream(fisA);
                User user = (User)ois.readObject();
                if (user.getUsername().equals(username)) {
                    if (user.getPassword().equals(password)) {
                        /*User userLogged */user = new Administrador(user.username, user.password, user.name, user.lastName, user.email, user.num, user.age);
                        System.out.print("\033[H\033[2J");  
                        System.out.flush();
                        System.out.println(" ___ Bienvenido Sr. Admin "+user.getName()+" "+user.getLastName()+" ___");
                        ois.close(); 
                        return 'A';
                    }
                }
            }
        } catch (Exception e) {
            
        }
        try {
            FileInputStream fisE = new FileInputStream(fileE);
            while (true) {
                ois = new ObjectInputStream(fisE);
                User user = (User)ois.readObject();
                if (user.getUsername().equals(username)) {
                    if (user.getPassword().equals(password)) {
                        user = new Estudiante(user.username, user.password, user.name, user.lastName, user.email, user.num, user.age, user.subjects);
                        System.out.print("\033[H\033[2J");  
                        System.out.flush();
                        System.out.println(" ___ Bienvenido Sr. Estudiante "+user.getName()+" "+user.getLastName()+" ___");
                        ois.close();
                        return 'E';
                    }
                }
            }
        } catch (Exception e) {
            
        }
        try {
            FileInputStream fisP = new FileInputStream(fileP);
            while (true) {
                ois = new ObjectInputStream(fisP);
                User user = (User)ois.readObject();
                if (user.getUsername().equals(username)) {
                    if (user.getPassword().equals(password)) {
                        user = new Profesor(user.username, user.password, user.name, user.lastName, user.email, user.num, user.age, user.subjects);
                        System.out.print("\033[H\033[2J");  
                        System.out.flush();
                        System.out.println(" ___ Bienvenido Sr. Profesor "+user.getName()+" "+user.getLastName()+" ___");
                        ois.close();
                        return 'P';
                    }
                }
            }
        } catch (Exception e) {
            
        }
        return 'N';
    }

    private void redirectTo(char rol){
        if (rol != 'N') {
            switch (rol) {
                case 'A':
                    MenuAdmins ma = new MenuAdmins();
                    ma.showMenu();
                    break;
                case 'E':
                    MenuEstudiantes me = new MenuEstudiantes();
                    me.showMenu();
                    
                    break;
                case 'P':
                    MenuProfesores mp = new MenuProfesores();
                    mp.showMenu();
                    break;
            }
        } else{
            System.out.println("USER NOT FOUND!");
            System.out.println("Verifique usuario y contraseña e intentelo mas tarde.");
            System.out.println("Si cree que se trata de un error, contactese con un administrador.");
        }
    }
}
