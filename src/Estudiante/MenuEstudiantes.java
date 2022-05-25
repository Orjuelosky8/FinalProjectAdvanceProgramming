package Estudiante;

import java.util.Scanner;

import Interfaces.Menus;
import Login.Login;

public class MenuEstudiantes implements Menus{
    Scanner reader = new Scanner(System.in);
    
    @Override
    public void showMenu() {
        char option;
        do {
            System.out.println("\t =======  Menu Estudiantes =====");
            System.out.println("\nQue desea hacer?");
            System.out.println("\t1. Revisar Asignaciones");
            System.out.println("\t2. Ver mis cursos");
            System.out.println("\t3. Inscribir cursos");
            System.out.println("\t4. Retirar cursos");
            System.out.println("\t5. Editar mi informacion personal");
            System.out.println("\t0. Salir");
            System.out.println("\tDigita tu opcion: ");
            option = reader.nextLine().charAt(0);
            if (option < 48 || option > 53) {
                System.out.println("\n\tDigito incorrecto, intentelo de nuevo a continuacion: \n");
            }
        } while (option < 48 || option > 53);
        redirecting(option);
    }

    @Override
    public void redirecting(char opt) {
        switch (opt) {
            case '1':
                new CheckAssignments().showMenu();
                break;
            case '2':
                new InfoCourses().checkCourses();
                break;
            case '3':
                new InfoCourses().addCourse();
                break;
            case '4':
                new InfoCourses().removeCourse();
                break;
            case '5':
                new Estudiante().editProfile();
                break;
        
            default:
                new Estudiante().logout();
                try {
                    new Login().asking_data();
                } catch (Exception e) {
                    System.out.println("Jmm, ocurrio un fallo en el ultimo paso de cierre de sesion..., intentaremos averiguar que paso para solucionarlo lo antes posible.");
                }
                break;
        }
    }
}
