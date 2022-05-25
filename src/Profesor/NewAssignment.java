package Profesor;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Data.Assignments;

public class NewAssignment extends Profesor{
    Scanner scanning = new Scanner(System.in);
    Assignments assignment = new Assignments();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    public void createAssignment() {
        char opt; int cont=0, aux=0;
        System.out.println(" --- ASIGNAR NUEVA ASIGNACION ---");
        System.out.println("\nTitulo de la nueva Asignacion: ");
        assignment.setName(scanning.nextLine());
        do {
            System.out.println("Que tipo de Asignacion es: ");
            System.out.println("\t1. Trabajo");
            System.out.println("\t2. Proyecto");
            System.out.println("\t3. Tesis");
            System.out.println("\t0. Cancelar **");
            System.out.print("Digita la opcion: ");
            opt = scanning.next().charAt(0);
            if (opt < 48 || opt > 51) 
                System.out.println("Digito incorrecto, intentelo de nuevo...");
        } while (opt < 48 || opt > 51);
        switch (opt) {
            case '1': assignment.setType("Trabajo"); break;
            case '2': assignment.setType("Proyecto"); break;
            case '3': assignment.setType("Tesis"); break;
            default: System.out.println("Operacion cancelada"); break;
        }
        System.out.println("Introduzca la descripcion de la Asignacion '" + assignment.getName() + "' de tipo '" + assignment.getType() + "': ");
        assignment.setDescription(scanning.next());
        assignment.setState("Por entregar");
        assignment.setProfessor(getUsername());
        System.out.println("Seleccione la materia a la cual corresponde esta asignacion (Recuerde que solo se despliegan las materias que dictas): ");
        for (String x : subjects) {
            cont++;
            System.out.println("\t"+cont+". "+x);
        }
        do {
            try {
                System.out.print("Esta asignacion corresponde a la materia #");
                aux = scanning.nextInt();
            } catch (Exception e) {
                System.out.println("Digito Incorrecto.");
            }
        } while (aux > cont || aux <= 0);
        assignment.setSubject(getSubjects().get(aux));
        assignment.setAssignmentDate(dtf.format(LocalDateTime.now()));
        System.out.println("Introduzca la Fecha y Hora en la que se debe entregar esta asignacion, recuerde que para tener un mejor orden debe ingresarlo de la manera (yyyy/MM/dd HH:mm)...: ");
        assignment.setTosubmitDate(scanning.next());

        // Data que esta llenada como borrador hasta que se complete la asignacion:
        assignment.setCommentE("...");
        assignment.setCommentP("..."); // Comentarios del profesor despues de calificar
        assignment.setSubmittedDate("No entregada aun.");
        assignment.setScore(0.0);

        // GUARDANDO LA ASIGNACION EN LA ASIGNACION DE TODOS LOS ARCHIVOS
        File FAssignments = new File(".//Files//Assignments.obj");
        try {
            FileOutputStream fos = new FileOutputStream(FAssignments, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(assignment);
            oos.close();
        } catch (Exception e) {
            System.out.println("Lo sentimos, ocurrio un fallo al guardar la nueva asignacion.");
        }
    }
}