package Administrador;

import java.util.Scanner;

public class Edit_and_LogOut extends Administrador{
    Scanner scanner = new Scanner(System.in);

    public void editData(){
        String aux;
        short opt=0; boolean flag = false;
        System.out.println(" *** EDITAR PERFIL ADMINISTRADOR ***");
        System.out.println("Bienvenido a la seccion donde podras editar tu informacion personal; debido a tu '@" + username + "' que eres un administrador, puedes editar todos tus datos personales y hasta eliminar tu cuenta, a continuacion encuentras el menu: ");
        System.out.println("\t1. Editar mi Username");
        System.out.println("\t2. Editar mi Nombre");
        System.out.println("\t3. Editar mi Apellido");
        System.out.println("\t4. Editar mi Correo de Contacto");
        System.out.println("\t5. Eliminar mi cuenta");
        System.out.println("\t0. Cancelar");
        do {
            try {
                System.out.print("Digita la opcion que vas a realizar: ");
                opt = scanner.nextShort();
                flag = true;
            } catch (Exception e) {
                System.out.println("\tDigito incorrecto ;(\nIntente de nuevo...");
                flag = false;
            }
        } while(!flag);

        switch (opt) {
            case 1:
                System.out.print("Digite su nuevo Username: ");
                aux = scanner.nextLine();
                setUsername(aux);
                break;
            case 2:
                System.out.print("Digite su Nombre: ");
                aux = scanner.nextLine();
                setName(aux);
                break;
            case 3:
                System.out.print("Digite su Apellido: ");
                aux = scanner.nextLine();
                setLastName(aux);
                break;
            case 4:
                System.out.print("Digite su Correo Electronico de Contacto: ");
                aux = scanner.nextLine();
                setEmail(aux);
                break;
            case 5:
                do {
                    System.out.print("Esta seguro de eliminar de manera definitva su cuenta? (S/N): ");
                    aux = scanner.nextLine();
                } while (aux != "S" && aux != "N");
                if (aux == "S"){
                    System.out.println("***  CUENTA ELIMINADA DE MANERA SATISFACTORIA  ***");
                } else
                    System.out.println("Eliminacion de cuenta cancelada oportunamente.");
                break;

            case 0: System.out.println("Vale, operacion cancelada oportunamente."); break;
            default: System.out.println("Digito incorrecto ;("); break;
        }
    }

    public void logOut(){
        System.out.println("Hasta Luego Admin!");
        System.out.println("Sesion Cerrada de Manera Exitosa ;D");
    }
}
