package Administrador;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.security.auth.Subject;

import Estudiante.Estudiante;
import Login.User;

public class ManageStudents {
    Scanner sc = new Scanner(System.in);
    File FS = new File("./Files/Students.obj");

    public void showMenu(){
        char option;
        do {
            //System.out.print("\033[H\033[2J"); 
            //System.out.flush(); 
            System.out.println("\n\n-------- GESTIONAR ESTUDIANTES --------");
            System.out.println("\t1. Ver lista actual de Estudiantes");
            System.out.println("\t2. Agregar Estudiante");
            System.out.println("\t3. Editar Estudiante");
            System.out.println("\t4. Eliminar Estudiante");
            System.out.print("Digita tu opcion: ");
            option = sc.next().charAt(0);
        } while (option < 48 || option > 52);
        redirect(option);
    }

    private void redirect(char opt){
        switch (opt) {
            case '1':
                students_list();
                showMenu();
            break;
            case '2':
                add_student();
                showMenu();
                break;
            case '3':
                edit_student();
                showMenu();
                break;
            case '4':
                delete_student();
                showMenu();
                break;
            case '0': System.out.println("Vale, sera redirigido al menu principal de Admins"); break;
        
            default: break;
        }
    }

    private void students_list(){
        int cont = 0;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        User user;
        try {
            fis = new FileInputStream("./Files/Students.obj");
            do{ //holi xd, helloooo no. NO. somos muy lindos con ojeras, TOTAAALLL, siempre somos re lindos, SI GREO
                ois = new ObjectInputStream(fis);
                cont += 1;
                user = (User) ois.readObject();
                System.out.println("  ***** DATOS DEL ESTUDIANTE #"+cont+" *****");
                System.out.println("\tUsuario: "+user.getUsername()); 
                System.out.println("\tNombre Completo: "+user.getName()+" "+user.getLastName()); 
                System.out.println("\tCorreo Electronico: "+user.getEmail()); 
                System.out.println("\tNumero Telefonico: "+user.getNum()); 
                System.out.println("\tEdad: "+user.getAge());
                System.out.println("\tMaterias que estas cursando: ");
                for (String x : user.getSubjects()) {
                    System.out.println("\t"+x);
                }
                // for (int i = 0; i <= user.getSubjects().size(); i++)
                //     System.out.println("\t"+(i+1) + ". " + user.getSubjects().get(i));
                System.out.println("----------------------------------------");
            }while(user != null);
        } catch (Exception e) {
            System.out.println("(ESTA FUE LA LISTA DE TODOS LOS ESTUDIANTES)");
            System.out.print("Press Any Key To Continue...");
            sc.nextLine();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void add_student(){
        boolean valid=true; String aux; int n=0;
        ArrayList<String> subjects = new ArrayList<String>();
        User newUser = new Estudiante();
        sc.nextLine();
        do {
            System.out.print("Digite el nuevo usuario del nuevo estudiante: ");
            aux = sc.nextLine();
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            User user;
            try {
                fis = new FileInputStream(FS);
                while (true){
                    ois = new ObjectInputStream(fis);
                    user = (Estudiante) ois.readObject();
                    if (user.getUsername().equals(aux)) {
                        System.out.println("Este nombre de usuario ya esta registrado en el sistema, recuerde que cada username es unico, intentelo de nuevo.");
                        valid = false;
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println();
            }
            if (valid) {
                System.out.println("Nombre de Usuario Disponible ;D");
                newUser.setUsername(aux);
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!valid);
        System.out.print("Digite la nueva contraseña del nuevo estudiante: ");
        newUser.setPassword(sc.nextLine());
        System.out.print("Digite el nombre del nuevo estudiante: ");
        newUser.setName(sc.nextLine());
        System.out.print("Digite el apellido del nuevo estudiante: ");
        newUser.setLastName(sc.nextLine());
        System.out.print("Digite el correo electronico del nuevo estudiante: ");
        newUser.setEmail(sc.nextLine());
        do {
            try{
                System.out.print("Digite el numero telefonico del nuevo estudiante: ");
                newUser.setNum(sc.nextLong());
                System.out.print("Digite la edad del nuevo estudiante: ");
                newUser.setAge(sc.nextShort());
                System.out.print("Digite el numero de materias que cursa el nuevo estudiante: ");
                n = sc.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("ERROR... "+e+"\nRecuerde que debe digitar un numero.");
                valid = false;
            }
        } while (!valid);
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Digite el nombre de la materia #"+(i+1)+" que cursa: ");
            subjects.add(sc.nextLine());
        }
        newUser.setSubjects(subjects);
        try {
            FileOutputStream fos = new FileOutputStream(FS, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(newUser);
            oos.close();
        } catch (Exception e) {
            System.out.println("Lo sentimos, ocurrio un fallo al guardar el nuevo estudiante.");
        }
    }

    private void edit_student(){
        File FS2 = new File("./Files/Students2.obj");
        int cont=0, aux; char opt;
        System.out.println("A continuacion podra apreciar una lista ordenada de el username de todos los estudiantes para que puedas saber la informacion de cual deseas editar");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        User user;
        User editted = new Estudiante();
        try {
            fis = new FileInputStream(FS);
            while(true){
                ois = new ObjectInputStream(fis);
                cont += 1;
                user = (Estudiante) ois.readObject();
                System.out.println(" "+cont+". "+user.getUsername());
            }
        } catch (Exception e) {
            try {
                ois.close();
                fis.close();
            } catch (Exception e1) {
                e1.getStackTrace();
            }
            do {
                System.out.print("Seleccione el numero del estudiante del que desea editar su informacion (0 para salir):");
                aux = sc.nextInt();
            } while (aux > cont || aux < 0);
        }
        cont=0;
        sc.nextLine();
        if (aux != 0) {
            try {
                fis = new FileInputStream(FS);
                while(true){
                    ois = new ObjectInputStream(fis);
                    cont += 1;
                    user = (Estudiante) ois.readObject();
                    editted.setUsername(user.getUsername());
                    editted.setPassword(user.getPassword());
                    editted.setName(user.getName());
                    editted.setLastName(user.getLastName());
                    editted.setEmail(user.getEmail());
                    editted.setNum(user.getNum());
                    editted.setAge(user.getAge());
                    editted.setSubjects(user.getSubjects());
                    if (cont == aux) {
                        System.out.println("Usuario encontrado, que desea editar?");
                        System.out.println("1. Nombre actual - "+user.getName());
                        System.out.println("2. Apellido actual - "+user.getLastName());
                        System.out.println("3. Correo actual - "+user.getEmail());
                        System.out.println("4. Numero actual - "+user.getNum());
                        System.out.println("0. Cancelar");
                        do {
                            System.out.print("Digite su opcion: ");
                            opt = sc.nextLine().charAt(0);
                        } while (opt < 48 || opt > 52);
                        switch (opt) {
                            case '1':
                                System.out.print("Digite el nuevo nombre: ");
                                editted.setName(sc.nextLine());
                                break;
                            case '2':
                                System.out.print("Digite el nuevo apellido: ");
                                editted.setLastName(sc.nextLine());
                                break;
                            case '3':
                                System.out.print("Digite el nuevo correo: ");
                                editted.setEmail(sc.nextLine());
                                break;
                            case '4':
                                System.out.print("Digite el nuevo numero: ");
                                editted.setNum(sc.nextLong());
                                break;
                        
                            default:
                                System.out.println("Operacion cancelada de manera exitosa.");
                                break;
                        }
                        try {
                            FileOutputStream fos = new FileOutputStream(FS2, true);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(editted);
                            oos.close();
                        } catch (Exception e) {
                            System.out.println("Ocurrio un fallo al guardar.");
                        }
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
            FS.delete();
            //sc.nextLine();
            boolean success = FS2.renameTo(FS);
            System.out.println(success);
            //sc.nextLine();
            FS2.delete();
        } else
            System.out.println("Operacion cancelada con exito.");
    }
    
    private void delete_student(){
        File FS2 = new File("./Files/Students2.obj");
        String aux; boolean found = false;
        sc.nextLine();
        System.out.print("Digite el Username de el Estudiante que desea Eliminar: ");
        aux = sc.nextLine();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        User user;
        try {
            fis = new FileInputStream(FS);
            while(true){ 
                ois = new ObjectInputStream(fis);
                user = (Estudiante) ois.readObject();
                if (user.getUsername().equals(aux)) {
                    System.out.println("Usuario encontrado");
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
            User user2;
            User saveUser = new Estudiante();
            try {
                fis2 = new FileInputStream(FS);
                while(true){ 
                    ois2 = new ObjectInputStream(fis2);
                    user2 = (Estudiante) ois2.readObject();
                    if (user2.getUsername().equals(aux) == false) {
                        saveUser.setUsername(user2.getUsername()); 
                        saveUser.setPassword(user2.getPassword());
                        saveUser.setName(user2.getName());
                        saveUser.setLastName(user2.getLastName());
                        saveUser.setEmail(user2.getEmail());
                        saveUser.setNum(user2.getNum());
                        saveUser.setAge(user2.getAge());
                        saveUser.setSubjects(user2.getSubjects());
                    }
                    try {
                        FileOutputStream fos = new FileOutputStream(FS2, true);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(saveUser);
                        oos.close();
                    } catch (Exception e) {
                        System.out.println("Ocurrio algun error.");
                    }
                    ois.close();
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
            new File("./Files/Students.obj").delete();
            //sc.nextLine();
            boolean success = new File("./Files/Students2.obj").renameTo(new File("./Files/Students.obj"));
            System.out.println(success);
            //sc.nextLine();
            new File("./Files/Students2.obj").delete();
            
            System.out.println("Estudiante Eliminado de Manera Satisfactoria.");
            System.out.print("Press Any Key To Continue...");
            sc.nextLine();
        }
    }
}
