package Administrador;

import java.io.*;
import java.nio.file.*;
import java.nio.file.Paths;
import java.util.Scanner;

import Login.User;

public class ManageAdmins extends Administrador{
    Scanner sc = new Scanner(System.in);

    public void showMenu(){
        char option;
        //System.out.print("\033[H\033[2J"); //XD
        //System.out.flush(); //https://www.delftstack.com/es/howto/java/java-clear-console/#:~:text=Para%20limpiar%20la%20consola%20en%20Java%2C%20usaremos%20el%20c%C3%B3digo%20de,comando%20para%20limpiar%20la%20consola.
        do {
            System.out.println("\n\n-------- GESTIONAR ADMINISTRADORES --------");
            System.out.println("\t1. Ver lista actual de Administradores");
            System.out.println("\t2. Agregar Administrador");
            System.out.println("\t3. Editar Administrador");
            System.out.println("\t4. Eliminar Administrador");
            System.out.println("\t0. Cancelar");
            System.out.print("Digita tu opcion: ");
            option = sc.next().charAt(0);
            if (option < 48 || option > 52) {
                System.out.println("Digito incorrecto, intentelo de nuevo: ");
            }
        } while (option < 48 || option > 52);
        redirect(option);
    }

    private void redirect(char opt){
        switch (opt) {
            case '1':
                admins_list();
                showMenu();
            break;
            case '2':
                add_admin();
                showMenu();
                break;
            case '3':
                edit_admin();
                showMenu();
                break;
            case '4':
                delete_admin();
                showMenu();
                break;
            case '0': 
                System.out.println("Vale, sera redirigido al menu principal de Admins"); 
                new MenuAdmins().showMenu();
            break;
        
            default: break;
        }
    }

    private void admins_list(){
        int cont = 0;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        User user;
        try {
            fis = new FileInputStream("./Files/Admins.obj");
            do{ //holi xd, helloooo no. NO. somos muy lindos con ojeras, TOTAAALLL, siempre somos re lindos, SI GREO
                ois = new ObjectInputStream(fis);
                cont += 1;
                user = (Administrador) ois.readObject();
                System.out.println("  ***** DATOS DEL ADMIN #"+cont+" *****");
                System.out.println("\tUsuario: "+user.getUsername()); 
                System.out.println("\tNombre Completo: "+user.getName()+" "+user.getLastName()); 
                System.out.println("\tCorreo Electronico: "+user.getEmail()); 
                System.out.println("\tNumero Telefonico: "+user.getNum()); 
                System.out.println("\tEdad: "+user.getAge());
                System.out.println("----------------------------------------");
            }while(user != null);
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("(ESTA FUE LA LISTA DE TODOS LOS ADMINS)");
            System.out.print("Press Any Key To Continue...");
            sc.nextLine();
            sc.nextLine();
        }
        try {
            ois.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void add_admin(){
        File FA = new File("./Files/Admins.obj");
        boolean valid=true; String aux;
        User newUser = new Administrador();
        sc.nextLine(); //LIMPIA BUFFER 
        do {
            valid = true;
            System.out.print("Digite el nuevo usuario del nuevo administrador: ");
            aux = sc.nextLine();
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            User user;
            try {
                fis = new FileInputStream(FA);
                do{
                    ois = new ObjectInputStream(fis);
                    user = (Administrador) ois.readObject();
                    if (user.getUsername().equals(aux)) {
                        System.out.println("Este nombre de usuario ya esta registrado en el sistema, recuerde que cada username es unico, intentelo de nuevo.");
                        valid = false;
                        break;
                    }
                }while (user != null);
                ois.close();
            } catch (Exception e) {
                System.out.println();
            }
            if (valid) {
                System.out.println("Nombre de Usuario Disponible ;D");
                newUser.setUsername(aux);
            }
            try {
                ois.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!valid);
        System.out.print("Digite la nueva contraseña del nuevo administrador: ");
        newUser.setPassword(sc.nextLine());
        System.out.print("Digite el nombre del nuevo administrador: ");
        newUser.setName(sc.nextLine());
        System.out.print("Digite el apellido del nuevo administrador: ");
        newUser.setLastName(sc.nextLine());
        System.out.print("Digite el correo electronico del nuevo administrador: ");
        newUser.setEmail(sc.nextLine());
        do {
            try{
                System.out.print("Digite el numero telefonico del nuevo administrador: ");
                newUser.setNum(sc.nextLong());
                System.out.print("Digite la edad del nuevo administrador: ");
                newUser.setAge(sc.nextShort());
                valid = true;
            } catch (Exception e) {
                System.out.println("ERROR... "+e+"\nRecuerde que debe digitar un numero.");
                valid = false;
            }
        } while (!valid);
        try {
            FileOutputStream fos = new FileOutputStream(FA, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(newUser);
            System.out.println("Datos con los que se guardo - "+newUser.getUsername());
            oos.close();
        } catch (Exception e) {
            System.out.println("Lo sentimos, ocurrio un fallo al guardar el nuevo Administrador.");
        }
    }

    private void edit_admin(){
        File FA = new File("./Files/Admins.obj");
        File FA2 = new File("./Files/Admins2.obj");
        int cont=0, aux=-1; char opt;
        System.out.println("A continuacion podra apreciar una lista ordenada de el username de todos los admins para que puedas saber la informacion de cual deseas editar");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        User user;
        User editted = new Administrador();
        try {
            fis = new FileInputStream(FA);
            ois = new ObjectInputStream(fis);
            user = (Administrador) ois.readObject();
            while(user != null){
                cont += 1;
                System.out.println(" "+cont+". "+user.getUsername());
                ois = new ObjectInputStream(fis);
                user = (Administrador) ois.readObject();
            }
            ois.close();
        } catch (Exception e) {
            try {
                ois.close();
                fis.close();
            } catch (Exception e1) {
                e1.getStackTrace();
            }
            do {
                System.out.print("Seleccione el numero del administrador del que desea editar su informacion (0 para salir):");
                aux = sc.nextInt();
            } while (aux > cont || aux < 0);
        }
        cont = 0;
        fis = null;
        ois = null;
        if (aux != 0) {
            try {
                fis = new FileInputStream(FA);
                do{
                    ois = new ObjectInputStream(fis);
                    cont += 1;
                    user = (Administrador) ois.readObject();
                    editted.setUsername(user.getUsername());
                    editted.setPassword(user.getPassword());
                    editted.setName(user.getName());
                    editted.setLastName(user.getLastName());
                    editted.setEmail(user.getEmail());
                    editted.setNum(user.getNum());
                    editted.setAge(user.getAge());
                    if (cont == aux) {
                        if (user.getUsername().equals(getUsername())) {
                            System.out.println("RECUERDE QUE NO PUEDE EDITAR SU PROPIA INFORMACION DESDE ESTA PESTAÑA; SI DESEA HACERLO DIRIGASE A LA SECCION 'EDITAR MI PERFIL'.");
                        } else{
                            System.out.println("Usuario encontrado, que desea editar?");
                            System.out.println("1. Nombre actual - "+user.getName());
                            System.out.println("2. Apellido actual - "+user.getLastName());
                            System.out.println("3. Correo actual - "+user.getEmail());
                            System.out.println("4. Numero actual - "+user.getNum());
                            System.out.println("0. Cancelar");
                            do {
                                System.out.print("Digite su opcion: ");
                                sc.nextLine();
                                opt = sc.nextLine().charAt(0);
                            } while (opt < 48 || opt > 52);
                            switch (opt) {
                                case '1':
                                    System.out.print("Digite el nuevo nombre: ");
                                    editted.setName(sc.nextLine());
                                    System.out.println("Nombre editado con exito.");
                                    break;
                                case '2':
                                    System.out.print("Digite el nuevo apellido: ");
                                    editted.setLastName(sc.nextLine());
                                    System.out.println("Apellido editado con exito.");
                                    break;
                                case '3':
                                    System.out.print("Digite el nuevo correo: ");
                                    editted.setEmail(sc.nextLine());
                                    System.out.println("Correo editado con exito.");
                                    break;
                                case '4':
                                    System.out.print("Digite el nuevo numero: ");
                                    editted.setNum(sc.nextLong());
                                    System.out.println("Numero editado con exito.");
                                    break;
                            
                                default:
                                    System.out.println("Operacion cancelada de manera exitosa.");
                                    break;
                            }
                        }
                    }
                    try {
                        FileOutputStream fos = new FileOutputStream(FA2, true);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(editted);
                        oos.close();
                    } catch (Exception e) {
                        System.out.println("Ocurrio un fallo al guardar.");
                    }
                }while(user != null);
                ois.close();
            } catch (Exception e) {
                
            }
            try {
                fis.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            Path path = Paths.get("./Files/Admins.obj");
            try {
                Files.delete(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
            FA.delete();
            //sc.nextLine();
            boolean success = FA2.renameTo(FA);
            System.out.println(success);
            //sc.nextLine();
            FA2.delete();
            //sc.nextLine();
        } else
            System.out.println("Operacion cancelada con exito.");
    }
    
    private void delete_admin(){
        File FA2 = new File("./Files/Admins2.obj");
        String aux; boolean found = false;
        sc.nextLine();
        System.out.print("Digite el Username de el Admin que desea Eliminar: ");
        aux = sc.nextLine();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        User user;
        try {
            fis = new FileInputStream("./Files/Admins.obj");
            do{ 
                ois = new ObjectInputStream(fis);
                user = (Administrador) ois.readObject();
                if (user.getUsername().equals(getUsername())) {
                    System.out.println("Recuerde que no puede eliminarse a si mismo desde este menu, si desea hacerlo vaya a la seccion editar su perfil.");
                } else if (user.getUsername().equals(aux)) {
                    System.out.println("Usuario encontrado");
                    found = true;
                }
            }while(user != null);
            ois.close();
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
            User user2;
            User saveUser = new Administrador();
            FileInputStream fis2=null;
            ObjectInputStream ois2=null;
            try {
                fis2 = new FileInputStream("./Files/Admins.obj");
                ois2 = new ObjectInputStream(fis2);
                user2 = (Administrador) ois2.readObject();
                do{
                    if (user2.getUsername().equals(aux) == false) {
                        saveUser.setUsername(user2.getUsername()); 
                        saveUser.setPassword(user2.getPassword());
                        saveUser.setName(user2.getName());
                        saveUser.setLastName(user2.getLastName());
                        saveUser.setEmail(user2.getEmail());
                        saveUser.setNum(user2.getNum());
                        saveUser.setAge(user2.getAge());
                        try {
                            FileOutputStream fos = new FileOutputStream(FA2, true);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(saveUser);
                            oos.close();
                        } catch (Exception e) {
                            System.out.println("Ocurrio algun error.");
                        }
                    }
                    ois2 = new ObjectInputStream(fis2);
                    user2 = (Administrador) ois2.readObject();
                }while(user2 != null);
                ois2.close();
                fis2.close();
            } catch (Exception e) {
            }
            try {
                fis2.close();
                ois2.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Path path = Paths.get("./Files/Admins.obj");
            try {
                Files.delete(path);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            new File("./Files/Admins.obj").delete();
            //sc.nextLine();
            boolean success = new File("./Files/Admins2.obj").renameTo(new File("./Files/Admins.obj"));
            System.out.println(success);
            //sc.nextLine();
            new File("./Files/Admins2.obj").delete();
            System.out.println("Usuario Eliminado de Manera Satisfactoria.");
            System.out.print("Press Any Key To Continue...");
            sc.nextLine();
        } else{
            System.out.println("Usuario no encontrado.");
        }
    }
}
