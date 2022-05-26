package Administrador;

import java.io.*;
import java.util.Scanner;

import Data.Courses;

public class ManageCourses {
    Scanner sc = new Scanner(System.in);
    File FCourses = new File("./Data/Courses.java");
    public void showMenu(){
        char option;
        do {
            System.out.print("\033[H\033[2J"); 
            System.out.flush(); 
            System.out.println("\n\n-------- GESTIONAR CURSOS --------");
            System.out.println("\t1. Ver lista actual de Cursos");
            System.out.println("\t2. Agregar Curso");
            System.out.println("\t3. Editar Curso");
            System.out.println("\t4. Eliminar Curso");
            System.out.print("Digita tu opcion: ");
            option = sc.next().charAt(0);
        } while (option < 48 || option > 52);
        redirect(option);
    }
    
    private void redirect(char opt){
        switch (opt) {
            case '1':
                courses_list();
                showMenu();
            break;
            case '2':
                add_course();
                showMenu();
                break;
            case '3':
                edit_course();
                showMenu();
                break;
            case '4':
                delete_course();
                showMenu();
                break;
            case '0': System.out.println("Vale, sera redirigido al menu principal de Admins"); break;
        
            default: break;
        }
    }

    private void courses_list(){
        int cont = 0;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Courses course;
        try {
            fis = new FileInputStream(FCourses);
            while(true){ //holi xd, helloooo no. NO. somos muy lindos con ojeras, TOTAAALLL, siempre somos re lindos, SI GREO
                ois = new ObjectInputStream(fis);
                cont += 1;
                course = (Courses) ois.readObject();
                System.out.println("  ***** DATOS DE LA MATERIA #"+cont+" *****");
                System.out.println("\tNombre de la materia: "+course.getName()); 
                System.out.println("\tNumero de clase: "+course.getN_class()); 
                System.out.println("\tDescripcion: "+course.getDescription()); 
                System.out.println("\tNumero de Creditos: "+course.getN_credits()); 
                System.out.println("\tCupos Totales: "+course.getN_cupos());
                System.out.println("----------------------------------------");
                ois.close();
            }
        } catch (Exception e) {
            System.out.println("(ESTA FUE LA LISTA DE TODOS LOS CURSOS DISPONIBLES)");
            System.out.print("Press Any Key To Continue...");
            sc.nextLine();
        }
        try {
            ois.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void add_course(){
        boolean valid=true; int aux;
        Courses newcourse = new Courses();
        System.out.print("Digite el nombre del nuevo curso: ");
        newcourse.setName(sc.nextLine());
        do {
            try {
                System.out.print("Digite el numero de clase: ");
                aux = sc.nextInt();
                FileInputStream fis = null;
                ObjectInputStream ois = null;
                Courses course;
                try {
                    fis = new FileInputStream(FCourses);
                    while (true){
                        ois = new ObjectInputStream(fis);
                        course = (Courses) ois.readObject();
                        if (course.getN_class() == aux) {
                            System.out.println("Este nombre de curso ya esta registrado en el sistema, recuerde que cada numero de clase es unico, intentelo de nuevo.");
                            valid = false;
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println();
                }
                if (valid) {
                    System.out.println("Numero de clase Disponible ;D");
                    newcourse.setN_class(aux);
                }
                try {
                    ois.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println("ERROR... "+e+"\nIntentelo de nuevo.");
            }
        } while (!valid);
        System.out.print("Digite la descripcion del nuevo curso: ");
        newcourse.setDescription(sc.nextLine());
        do {
            try{
                System.out.print("Digite el numero de creditos de la materia: ");
                newcourse.setN_credits(sc.nextInt());
                System.out.print("Digite el numero de cupos totales del nuevo curso: ");
                newcourse.setN_cupos(sc.nextInt());
                valid = true;
            } catch (Exception e) {
                System.out.println("ERROR... "+e+"\nRecuerde que debe digitar un numero.");
                valid = false;
            }
        } while (!valid);
        try {
            FileOutputStream fos = new FileOutputStream(FCourses, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(newcourse);
            oos.close();
        } catch (Exception e) {
            System.out.println("Lo sentimos, ocurrio un fallo al guardar la nuevo materia.");
        }
    }

    private void edit_course(){
        int cont=0, aux; char opt;
        System.out.println("A continuacion podra apreciar una lista ordenada de el nombre de curso de todas las materias para que puedas saber la informacion de cual deseas editar");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Courses course;
        Courses editted = new Courses();
        try {
            fis = new FileInputStream(FCourses);
            while(true){
                ois = new ObjectInputStream(fis);
                cont += 1;
                course = (Courses) ois.readObject();
                System.out.println(" "+cont+". "+course.getName());
            }
        } catch (Exception e) {
            try {
                ois.close();
                fis.close();
            } catch (Exception e1) {
                e1.getStackTrace();
            }
            do {
                System.out.print("Seleccione el numero de clase del que desea editar su informacion (0 para salir):");
                aux = sc.nextInt();
            } while (aux > cont || aux < 0);
        }

        if (aux != 0) {
            try {
                fis = new FileInputStream(FCourses);
                while(true){
                    ois = new ObjectInputStream(fis);
                    cont += 1;
                    course = (Courses) ois.readObject();
                    editted.setName(course.getName());
                    editted.setDescription(course.getDescription());
                    editted.setN_class(course.getN_class());
                    editted.setN_credits(course.getN_credits());
                    editted.setN_cupos(course.getN_cupos());
                    if (cont == aux) {
                        System.out.println("Clase encontrada, que desea editar?");
                        System.out.println("1. Nombre actual - "+course.getName());
                        System.out.println("2. Descripcion actual - "+course.getDescription());
                        System.out.println("3. Numero de creditos actual - "+course.getN_credits());
                        System.out.println("4. Numero de cupos totales actual - "+course.getN_cupos());
                        System.out.println("0. Cancelar");
                        do {
                            System.out.print("Digite su opcion: ");
                            opt = sc.nextLine().charAt(0);
                        } while (opt < 48 || opt > 53);
                        switch (opt) {
                            case '1':
                                System.out.print("Digite el nuevo nombre: ");
                                editted.setName(sc.nextLine());
                                break;
                            case '2':
                                System.out.print("Digite la nueva descripcion: ");
                                editted.setDescription(sc.nextLine());
                                break;
                            case '3':
                                System.out.print("Digite el nuevo numero de creditos: ");
                                editted.setN_credits(sc.nextInt());
                                break;
                            case '4':
                                System.out.print("Digite el nuevo numero de cupos totales: ");
                                editted.setN_cupos(sc.nextInt());
                                break;
                        
                            default:
                                System.out.println("Operacion cancelada de manera exitosa.");
                                break;
                        }
                    }
                    try {
                        FileOutputStream fos = new FileOutputStream(FCourses, true);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(editted);
                        oos.close();
                    } catch (Exception e) {
                        System.out.println("Ocurrio un fallo al guardar.");
                    }
                }
            } catch (Exception e) {
                System.out.println();
            }
            try {
                fis.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            /* Aqui va todo lo de cambios de archivos..., lo hago en 10 minutos mañana, que ya van a ser las 4 am xd */
        } else
            System.out.println("Operacion cancelada con exito.");
    }
    
    private void delete_course(){
        int aux; boolean found = false;
        System.out.print("Digite el numero de clase del curso que desea Eliminar: ");
        aux = sc.nextInt();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Courses course;
        try {
            fis = new FileInputStream(FCourses);
            while(true){ 
                ois = new ObjectInputStream(fis);
                course = (Courses) ois.readObject();
                if (course.getN_class() == aux) {
                    System.out.println("Clase encontrada");
                    found = true;
                }
            }
        } catch (Exception e) {
            try {
                fis.close();
                ois.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        try {
            fis.close();
        } catch (IOException e3) {
            e3.printStackTrace();
        }

        if(found){
            FileInputStream fis2 = null;
            ObjectInputStream ois2 = null;
            Courses course2;
            Courses savecourse = new Courses();
            try {
                fis2 = new FileInputStream(FCourses);
                while(true){ 
                    ois2 = new ObjectInputStream(fis2);
                    course2 = (Courses) ois2.readObject();
                    if (course2.getN_class() != aux) {
                        savecourse.setName(course2.getName());
                        savecourse.setDescription(course2.getDescription());
                        savecourse.setN_class(course2.getN_class());
                        savecourse.setN_credits(course2.getN_credits());
                        savecourse.setN_cupos(course2.getN_cupos());
                    }
                    try {
                        FileOutputStream fos = new FileOutputStream(FCourses, true);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(savecourse);
                        oos.close();
                    } catch (Exception e) {
                        System.out.println("Ocurrio algun error.");
                    }
                }
            } catch (Exception e) {
            }
            try {
                fis2.close();
                ois2.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            /* espacio para rotacion de archivos */

            System.out.println("Usuario Eliminado de Manera SatisFCoursesctoria.");
            System.out.print("Press Any Key To Continue...");
            sc.nextLine();
        }
    }
}
