package Estudiante;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Data.Assignments;

public class CheckAssignments extends Estudiante{
    private int cont;
    File FAsignments = new File("./Assignments.obj"); 
    FileInputStream fis ;
    ObjectInputStream ois;
    Scanner scanning = new Scanner(System.in);
    Assignments assignment;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    
    public void showMenu(){
        char opt;
        do {
            System.out.println(" --- MENU ASIGNACIONES ESTUDIANTE ---");
            System.out.println("Que desea hacer? ");
            System.out.println("\t1. Revisar Asignaciones Pendientes");
            System.out.println("\t2. Revisar Asignaciones Calificadas");
            System.out.println("\t3. Revisar Asignaciones Enviadas");
            System.out.println("\t4. Entregar Asignacion Pendiente");
            System.out.println("\t0. Cancelar");
            opt = scanning.nextLine().charAt(0);
            if (opt < 48 || opt > 52)
                System.out.println("Digito ingresado incorrecto, intentelo de nuevo a continuacion: ");
        } while (opt < 48 || opt > 52);

        switch (opt) {
            case '1': CheckAssignmentsP(); break;
            case '2': CheckAssignmentsQ(); break;
            case '3': CheckAssignmentsS(); break;
            case '4':submitAssignment(); break;
            default: System.out.println("Vale, ninguna opcion digitada, redirigiendo al menu anterior"); break;
        }
    }

    public void CheckAssignmentsP(){ // Pending
        cont=0;
        try {
            fis = new FileInputStream(FAsignments);
            while (true) {
                ois = new ObjectInputStream(fis);
                assignment = (Assignments) ois.readObject();
                if (assignment.getState().equals("Por entregar")) {
                    cont++;
                    System.out.println("\tASIGNACION PENDIENTE #"+cont);
                    System.out.println("Nombre de la Asignacion: "+assignment.getName());
                    System.out.println("Tipo de Asignacion: "+assignment.getType());
                    System.out.println("Descripcion de la Asignacion: "+assignment.getDescription());
                    System.out.println("Materia de la Asignacion: "+assignment.getSubject());
                    System.out.println("Profesor de la Asignacion: "+assignment.getProfessor());
                    System.out.println("Comentario dejado por ti en la Asignacion: "+assignment.getCommentE());
                    System.out.println("Fecha en la que se asigno la Asignacion: "+assignment.getAssignmentDate());
                    System.out.println("Fecha oportuna de entrega de la Asignacion: "+assignment.getTosubmitDate());
                }
            }
        } catch (Exception e) {
            System.out.println("\tFIN LISTA DE ASIGNACIONES PENDIENTES...");
        }
    }
    
    public void CheckAssignmentsQ(){ // Qualified
        cont=0;
        try {
            fis = new FileInputStream(FAsignments);
            while (true) {
                ois = new ObjectInputStream(fis);
                assignment = (Assignments) ois.readObject();
                if (assignment.getState().equals("Entregada")) {
                    cont++;
                    System.out.println("\tASIGNACION ENTREGADA #"+cont);
                    System.out.println("Nombre de la Asignacion: "+assignment.getName());
                    System.out.println("Tipo de Asignacion: "+assignment.getType());
                    System.out.println("Descripcion de la Asignacion: "+assignment.getDescription());
                    System.out.println("Materia de la Asignacion: "+assignment.getSubject());
                    System.out.println("Profesor de la Asignacion: "+assignment.getProfessor());
                    System.out.println("Comentario dejado por ti en la Asignacion: "+assignment.getCommentE());
                    System.out.println("Fecha en la que se asigno la Asignacion: "+assignment.getAssignmentDate());
                    System.out.println("Fecha oportuna de entrega de la Asignacion: "+assignment.getTosubmitDate());
                    System.out.println("Fecha de entrega de la Asignacion: "+assignment.getSubmittedDate());
                }
            }
        } catch (Exception e) {
            System.out.println("\tFIN LISTA DE ASIGNACIONES ENTREGADAS...");
        }
    }
    
    public void CheckAssignmentsS(){ // Sent/Submitted
        cont=0;
        try {
            fis = new FileInputStream(FAsignments);
            while (true) {
                ois = new ObjectInputStream(fis);
                assignment = (Assignments) ois.readObject();
                if (assignment.getState().equals("Calificada")) {
                    cont++;
                    System.out.println("\tASIGNACION CALIFICADA #"+cont);
                    System.out.println("Nombre de la Asignacion: "+assignment.getName());
                    System.out.println("Tipo de Asignacion: "+assignment.getType());
                    System.out.println("Descripcion de la Asignacion: "+assignment.getDescription());
                    System.out.println("Materia de la Asignacion: "+assignment.getSubject());
                    System.out.println("Profesor de la Asignacion: "+assignment.getProfessor());
                    System.out.println("Comentario dejado por ti de la Asignacion: "+assignment.getCommentE());
                    System.out.println("Fecha en la que se asigno la Asignacion: "+assignment.getAssignmentDate());
                    System.out.println("Fecha oportuna de entrega de la Asignacion: "+assignment.getTosubmitDate());
                    System.out.println("Fecha de entrega de la Asignacion: "+assignment.getSubmittedDate());
                    System.out.println("\tCALIFICACION: "+assignment.getScore());
                    System.out.println("Comentario dejado por el profesor al calificar: "+assignment.getCommentP());
                }
            }
        } catch (Exception e) {
            System.out.println("\tFIN LISTA DE ASIGNACIONES CALIFICADAS...");
        }
    }
    
    public void submitAssignment(){
        int aux=0; boolean valid= false; String subject="";
        char auxc;
        System.out.println(" *** SUBIR ASIGNACION ***");
        System.out.println("A continuacion aparecera una lista de las materias que actualmente estas cursando, seleccione la materia de la cual es la asignacion que va a entregar: ");
        
        File FStudents = new File("./Files/Students.obj");
        Estudiante student;
        try {
            fis = new FileInputStream(FStudents);
            while (true) {
                ois = new ObjectInputStream(fis);
                student = (Estudiante) ois.readObject();
                if (student.getUsername().equals(getUsername())) {
                    int i;
                    for (i = 0; i < student.getSubjects().size(); i++) {
                        System.out.println("MATERIA QUE CURSAS #"+(i+1)+": "+student.getSubjects().get(i));
                    }
                    do {
                        try {
                            do {
                                System.out.print("Digite el # del orden que aparece en la lista de la materia a la que corresponde la asignacion: ");
                                aux = scanning.nextInt();
                            } while (aux > i || aux <= 0);
                            subject=student.getSubjects().get(aux-1);
                            valid = true;
                        } catch (Exception e) {
                            System.out.println("Error, recuerde que debe digitar un numero...");
                            valid = false;
                        }
                    } while(!valid);
                }
            }
        } catch (Exception e) {
            System.out.println(" -- Fin lista Materias que estas cursando --");
        }
        System.out.println("A continuacion seran desplegadas la lista de incidencias qde asignaciones que se encontraron en la materia, para que las entregues por primera vez o vuelvas a entregar...");
        cont =0; valid = false;
        try {
            fis = new FileInputStream(FAsignments);
            System.out.println("Buscando coincidencias...");
            while (true) {
                ois = new ObjectInputStream(fis);
                assignment = (Assignments) ois.readObject();
                if (assignment.getSubject().equals(subject)) {
                    if (assignment.getState().equals("Calificada")==false) {
                        cont++;
                        System.out.println("COINCIDENCIA #"+cont+" ENCONTRADA");
                        System.out.println("Nombre de la Asignacion: "+assignment.getName());
                        System.out.println("Tipo de Asignacion: "+assignment.getType());
                        System.out.println("Descripcion de la Asignacion: "+assignment.getDescription());
                        System.out.println("Materia de la Asignacion: "+assignment.getSubject());
                        System.out.println("Profesor de la Asignacion: "+assignment.getProfessor());
                        System.out.println("Comentario dejado por ti en la Asignacion: "+assignment.getCommentE());
                        System.out.println("Fecha en la que se asigno la Asignacion: "+assignment.getAssignmentDate());
                        System.out.println("Fecha oportuna de entrega de la Asignacion: "+assignment.getTosubmitDate());
                        valid = true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("--- Fin Incidencias ---");
        }
        if (valid) {
            if (cont > 1) {
                do {
                    try {
                        System.out.print("Digite el # de la asignacion que desea entregar: ");
                        aux = scanning.nextInt();
                    } catch (Exception e) {
                        System.out.println("ERROR!!, recuerda que aqui debes digitar un numero que este dentro del rango de incidencias.");
                    }
                } while (aux < 0 || aux > cont);
            } else{
                aux = 1; //Posicion 1 de las asignaciones entregadas o por entregar
            }
            try {
                fis = new FileInputStream(FAsignments);
                while (true) {
                    ois = new ObjectInputStream(fis);
                    assignment = (Assignments) ois.readObject();
                    if (assignment.getSubject().equals(subject)) {
                        if (assignment.getState().equals("Calificada")==false) {
                            cont++;
                            if (cont == aux) {
                                System.out.println("Asignacion encontrada...");
                                System.out.println("Nombre de la Asignacion: "+assignment.getName());
                                System.out.print("Es esta la asignacion que desea? (S/N): ");
                                switch (scanning.nextLine().charAt(0)) {
                                    case 'S':
                                        System.out.println("Digite el comentario que desea dejar en la asignacion: ");
                                        assignment.setCommentE(scanning.nextLine());
                                        assignment.setState("Entregada");
                                        assignment.setSubmittedDate(dtf.format(LocalDateTime.now()));
                                        do {
                                            System.out.print("Todo Listo!, fecha de entrega establecida como ("+assignment.getSubmittedDate()+").\nDesea confirmar la entrega? (S/N):");
                                            auxc = scanning.nextLine().charAt(0);
                                        } while (auxc != 'S' || auxc != 'N');
                                        switch (auxc) {
                                            case 'S':
                                                System.out.println("Perfecto, asignacion entregada exitosamente");
                                                break;
                                            case 'N':
                                                System.out.println("Okay, entrega cancelada.");
                                                assignment.setCommentE("...");
                                                assignment.setState("Por Entregar");
                                                assignment.setSubmittedDate("Aun no entregada");
                                                break;
                                        
                                            default: System.out.println("Impossible hehe");
                                                break;
                                        }
                                        break;
                                    case 'N':
                                        System.out.println("Vale, redirigiendo al menu anterior, intentelo de nuevo si lo desea...");
                                        break;
                                    default:
                                        System.out.println("Digito incorrecto, redirigiendo al menu anterior.");
                                        break;
                                }
                            }
                        }
                    }
                    try {
                        FileOutputStream fos = new FileOutputStream(FAsignments);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(assignment);
                        oos.close();
                    } catch (Exception e) {
                        System.out.println("Error al guardar asignaciones, estamos trabajando para ver que paso");
                    }
                }
            } catch (Exception e) {
                System.out.println("Redirigiendo al Menu Anterior...");
            }
        } else
            System.out.println("No encontramos asignaciones que tenga pendientes de esta materia...");
    }
}
