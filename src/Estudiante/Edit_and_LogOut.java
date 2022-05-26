package Estudiante;

import java.util.Scanner;

public class Edit_and_LogOut extends Estudiante{
    Scanner scanner = new Scanner(System.in);

    public void editProfile(){
        String aux; boolean flag = false;
        short opt=0; long auxl=0;
        System.out.println(" *** EDITAR PERFIL ESTUDIANTE ***");
        System.out.println("Bienvenido a la seccion donde podras editar tu informacion personal");
        System.out.println("debido a que tu '@" + username + "' eres un estudiante, solo podras editar manualmente tu numero y correo de contacto, en caso de necesitar editar otro dato, deberas hablar con un administrador; a continuacion encuentras el menu: ");
        System.out.println("\t1. Editar mi correo de contacto");
        System.out.println("\t2. Editar mi numero de contacto");
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
                System.out.print("Digite su nuevo Correo Electronico de Contacto: ");
                aux = scanner.nextLine();
                setEmail(aux);
            break;
            case 2:
                System.out.print("Digite su nuevo Numero Telefonico de Contacto: ");
                auxl = scanner.nextLong();
                setNum(auxl);
                break;

            case 0: System.out.println("Vale, operacion cancelada oportunamente."); break;
            default: System.out.println("Digito incorrecto ;("); break;
        }
    }

    public void logout(){
        System.out.println("Hasta Luego Estudiante!");
        System.out.println("Sesion Cerrada de Manera Exitosa ;D");
    }
}
