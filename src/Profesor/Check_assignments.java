package Profesor;

import java.io.*;
import java.util.Scanner;

import Data.Assignments;

public class Check_assignments {
    private int cont;
    Scanner scanning = new Scanner(System.in);
    Assignments assignment;
    File FAssignments = new File(".//Files//Assignments.obj");
    FileInputStream fis = null;
    ObjectInputStream ois = null;

    public void showMenu(){
        char opt;
        do {
            System.out.println(" --- REVISAR ASIGNACIONES ---");
            System.out.println("Seleccione la opcion que desea (recuerde que solo aparecen las asignaciones que ustged ha asignado).");
            System.out.println("\n1. Revisar todas mis Asignaciones");
            System.out.println("\n2. Revisar Asignaciones pendientes");
            System.out.println("\n3. Revisar Asignaciones ya entregadas");
            System.out.println("\n0. Cancelar");
            System.out.print("Digite la opcion que desea revisar: ");
            opt = scanning.next().charAt(0);
            if (opt < 48 || opt > 51)
                System.out.println("Digito incorrecto, intentelo de nuevo (0/4)...");
        } while (opt < 48 || opt > 51);

        switch (opt) {
            case '1': checkAll(); break;
            case '2': checkPending(); break;
            case '3': checkSubmitted(); break;
            default:System.out.println("Vale, no revises ninguna asignacion..."); break;
        }
    }

    private void checkAll(){
        cont = 0;
        try {
            fis = new FileInputStream(FAssignments);
            while (true) {
                ois = new ObjectInputStream(fis);
                cont++;
                assignment = (Assignments) ois.readObject();
                System.out.println(" ***** ASIGNACION #"+cont+" *****");
                System.out.println("Nombre de la Asignacion: "+assignment.getName());
                System.out.println("Tipo de Asignacion: "+assignment.getType());
                System.out.println("Descripcion de la Asignacion: "+assignment.getDescription());
                System.out.println("Estado de la Asignacion: "+assignment.getState());
                System.out.println("Materia de la Asignacion: "+assignment.getSubject());
                System.out.println("Fecha en que se asigno: "+assignment.getAssignmentDate());
                System.out.println("Fecha oportuna de entrega: "+assignment.getTosubmitDate());
                if (assignment.getState().equals("Por entregar") == false) {
                    System.out.println("Fecha entregada: "+assignment.getSubmittedDate());
                    System.out.println("Comentarios del estudiante: "+assignment.getCommentE());
                    System.out.println("Comentarios que dejaste al calificar: "+assignment.getCommentP());
                    System.out.println("Calificacion recibida: "+assignment.getScore());
                }
                System.out.println("\n -------------------------------------------------------- \n");
            }
        } catch (Exception e) {
            System.out.println("__ Esta fue la lista de todas las asignaciones a su cargo.");
        }
        System.out.print("Desea calificar o corregir la nota de alguna de las anteriores asignaciones? (S/N): ");
        switch (scanning.nextLine().charAt(0)) {
            case 'S':
                System.out.println("Digite el numero de la asignacion que deseas (numero que que aparece en la lista ordenada (1/" + cont + ")): ");
                editScore(scanning.nextInt(), 'A'); // All
                break;
            case 'N': System.out.println("Vale, ninguna calificacion sera modificada ni asignada"); break;
            default: System.out.println("Digito incorrecto, ninguna calificacion sera modificada..."); break;
        }
    }
    
    private void checkPending(){
        cont = 0;
        try {
            fis = new FileInputStream(FAssignments);
            while (true) {
                ois = new ObjectInputStream(fis);
                cont++;
                assignment = (Assignments) ois.readObject();

                if (assignment.getState().equals("Por entregar")) {
                    System.out.println(" ***** ASIGNACION PENDIENTE #"+cont+" *****");
                    System.out.println("Nombre de la Asignacion: "+assignment.getName());
                    System.out.println("Tipo de Asignacion: "+assignment.getType());
                    System.out.println("Descripcion de la Asignacion: "+assignment.getDescription());
                    System.out.println("Estado de la Asignacion: "+assignment.getState());
                    System.out.println("Materia de la Asignacion: "+assignment.getSubject());
                    System.out.println("Fecha en que se asigno: "+assignment.getAssignmentDate());
                    System.out.println("Fecha oportuna de entrega: "+assignment.getTosubmitDate());
                    System.out.println("\n -------------------------------------------------------- \n");
                }
            }
        } catch (Exception e) {
            System.out.println("__ Esta fue la lista de todas las asignaciones pendientes a su cargo.");
        }
        System.out.print("Desea calificar la nota de alguna de las anteriores asignaciones? (S/N): ");
        switch (scanning.nextLine().charAt(0)) {
            case 'S':
                System.out.println("Digite el numero de la asignacion que deseas (numero que que aparece en la lista ordenada (1/" + cont + ")): ");
                editScore(scanning.nextInt(), 'P'); // Pending
                break;
            case 'N': System.out.println("Vale, ninguna calificacion sera modificada ni asignada"); break;
            default: System.out.println("Digito incorrecto, ninguna calificacion sera modificada..."); break;
        }
    }
    
    private void checkSubmitted(){
        cont = 0;
        try {
            fis = new FileInputStream(FAssignments);
            while (true) {
                ois = new ObjectInputStream(fis);
                cont++;
                assignment = (Assignments) ois.readObject();
    
                if (assignment.getState().equals("Por entregar") == false) {
                    System.out.println(" ***** ASIGNACION ENTREGADA/CALIFICADA #"+cont+" *****");
                    System.out.println("Nombre de la Asignacion: "+assignment.getName());
                    System.out.println("Tipo de Asignacion: "+assignment.getType());
                    System.out.println("Descripcion de la Asignacion: "+assignment.getDescription());
                    System.out.println("Estado de la Asignacion: "+assignment.getState());
                    System.out.println("Materia de la Asignacion: "+assignment.getSubject());
                    System.out.println("Fecha en que se asigno: "+assignment.getAssignmentDate());
                    System.out.println("Fecha oportuna de entrega: "+assignment.getTosubmitDate());
                    System.out.println("Fecha entregada: "+assignment.getSubmittedDate());
                    System.out.println("Comentarios del estudiante: "+assignment.getCommentE());
                    System.out.println("Comentarios que dejaste al calificar: "+assignment.getCommentP());
                    System.out.println("Calificacion recibida: "+assignment.getScore());
                    System.out.println("\n -------------------------------------------------------- \n");
                }
            }
        } catch (Exception e) {
            System.out.println("__ Esta fue la lista de todas las asignaciones que ya fueron entregadas por los estudiantes.");
        }
        System.out.print("Desea calificar o corregir la nota de alguna de las anteriores asignaciones? (S/N): ");
        switch (scanning.nextLine().charAt(0)) {
            case 'S':
                System.out.println("Digite el numero de la asignacion que deseas (numero que que aparece en la lista ordenada (1/" + cont + ")): ");
                editScore(scanning.nextInt(), 'S'); // Submitted
                break;
            case 'N': System.out.println("Vale, ninguna calificacion sera modificada ni asignada"); break;
            default: System.out.println("Digito incorrecto, ninguna calificacion sera modificada..."); break;
        }
    }

    private void editScore(int n, char identifier){
        cont = 0;
        try {
            fis = new FileInputStream(FAssignments);
            while (true) {
                ois = new ObjectInputStream(fis);
                assignment = (Assignments) ois.readObject();
                if (identifier == 'A') 
                    cont++;
                else if(identifier == 'P')
                    if (assignment.getState().equals("Por entregar"))
                        cont++;
                else if(identifier == 'S')
                    if (assignment.getState().equals("Por entregar") == false)
                        cont++;
    
                if (cont == n) {
                    System.out.println("Asignacion encontrada, que desea editar de la misma?");
                    System.out.println("\t1. Calificacion");
                    System.out.println("\t2. Comentario");
                    System.out.println("\t3. Calificacion y Comentario");
                    System.out.print("Digite su opcion: ");
                    switch (scanning.next().charAt(0)) {
                        case '1':
                            System.out.print("Digite la nueva calificacion para esta asignacion: ");
                            assignment.setScore(scanning.nextDouble());
                            break;
                        case '2':
                            System.out.print("Introduzca el nuevo comentario para esta asignacion: ");
                            assignment.setCommentP(scanning.nextLine());
                            break;
                        case '3':
                            System.out.print("Digite la nueva calificacion para esta asignacion: ");
                            assignment.setScore(scanning.nextDouble());
                            System.out.print("Introduzca el nuevo comentario para esta asignacion: ");
                            assignment.setCommentP(scanning.nextLine());
                            break;
                    
                        default: System.out.println("Digito incorrecto, redirigiendo al menu anterior..."); break;
                    }
                }
                try {
                    FileOutputStream fos = new FileOutputStream(FAssignments, true);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(assignment);
                    oos.close();
                } catch (Exception e) {
                    System.out.println("Ocurrio un error al guardar los ajustes");
                }
            }
        } catch (Exception e) {
            System.out.println("Finalizado.");
        }
    }
}
