import Login.Login;
// import Login.User;

// import java.io.*;

// import Administrador.Administrador;
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");

        // File file = new File("Files/Admins.obj");
        // User firstUser = new Administrador("Daniel8", "12345678", "Daniel", "Orjuela", "daniel.orju@gmail.com", 31123316, 18);
        // try {
        //     FileOutputStream fos = new FileOutputStream(file);
        //     ObjectOutputStream oos = new ObjectOutputStream(fos);
        //     System.out.println("Llego 0");
        //     oos.writeObject(firstUser);
        //     System.out.println("Llego 1");
        //     oos.close();
        //     System.out.println("Llego 2");
        // } catch (Exception e) {
        //     System.out.println("Error al guardar"+e);
        // }

        // System.out.println("Done");

        
        Login login = new Login();
        login.asking_data();
    }
}