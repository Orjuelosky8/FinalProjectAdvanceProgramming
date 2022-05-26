package Administrador;

import java.io.IOException;
import java.util.Scanner;

import Interfaces.Menus;
import Login.Login;

public class MenuAdmins implements Menus{
    Scanner reader = new Scanner(System.in);

    @Override
    public void showMenu() {
        char option;
        do {
            System.out.println("* < A-bcd-E-fgh-I-jklmn-O-pqrst-U-vwxyZ > *");
            System.out.println("\t =======  Menu Administradores =====");
            System.out.println("\nQue desea hacer?");
            System.out.println("\t1. Gestionar Administradores");
            System.out.println("\t2. Gestionar Profesores");
            System.out.println("\t3. Gestionar Estudiantes");
            System.out.println("\t4. Gestionar Cursos");
            System.out.println("\t5. Editar mi cuenta");
            System.out.println("\t0. Cerrar Sesion");
            System.out.print("\tDigita tu opcion: ");
            option = reader.next().charAt(0);
            if (option < 48 || option > 53) {
                System.out.println("\n\tDigito incorrecto, intentelo de nuevo a continuacion: \n");
            }
        } while (option < 48 || option > 52);
        redirecting(option);
    }

    @Override
    public void redirecting(char opt) {
        switch (opt) {
            case '1':
                new ManageAdmins().showMenu();
                break;
            case '2':
                new ManageStudents().showMenu();
                break;
            case '3':
                new ManageProfessors().showMenu();
                break;
            case '4':
                new ManageCourses().showMenu();
                break;
            case '5':
                new Administrador().editProfile(); 
                break;
        
            default: 
                new Administrador().logout(); 
                try {
                    new Login().asking_data();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
