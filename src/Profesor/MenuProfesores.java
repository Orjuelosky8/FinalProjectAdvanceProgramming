package Profesor;

import java.io.IOException;
import java.util.Scanner;

import Interfaces.Menus;
import Login.Login;

public class MenuProfesores implements Menus{
    Scanner reader = new Scanner(System.in);

    @Override
    public void showMenu() {
        char option;
        do {
            System.out.println("\t =======  Menu Profesores =====");
            System.out.println("\nQue desea hacer?");
            System.out.println("\t1. Asignar nueva entrega");
            System.out.println("\t2. Revisar asignaciones enviadas");
            System.out.println("\t3. Comunicarse con Estudiante");
            System.out.println("\t4. Editar mi cuenta");
            System.out.println("\t0. Salir");
            System.out.println("\tDigita tu opcion: ");
            option = reader.next().charAt(0);
            if (option < 48 || option > 52) {
                System.out.println("\n\tDigito incorrecto, intentelo de nuevo a continuacion: \n");
            }
        } while (option < 48 || option > 52); // Se verifican los numeros de acuerdo al codigo ascii jsjs
        redirecting(option);
    }

    @Override 
    public void redirecting(char opt){
        switch (opt) {
            case '1': new NewAssignment().createAssignment();  break;
            case '2': new Check_assignments().showMenu(); break;
            case '3': new CommunicateStudent().newMessage(); break;
            case '4': new Profesor().editProfile(); break;
            default:
                new Profesor().logout();
                try {
                    new Login().asking_data();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch(Exception e){
                    System.out.println("Error al finalizar el cierre de Sesion.");
                }
                break;
        }
    }
}