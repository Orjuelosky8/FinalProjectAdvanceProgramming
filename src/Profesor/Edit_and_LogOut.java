package Profesor;

import java.util.Scanner;

public class Edit_and_LogOut extends Profesor{
    Scanner scanner = new Scanner(System.in);

    public void editProfile(){
        String aux; boolean flag = false;
        short opt=0; long auxl=0;
        System.out.println(" *** EDITAR PERFIL PROFESOR ***");
        System.out.println("Bienvenido a la seccion donde podras editar tu informacion personal; debido a que tu '@" + username + "' eres un profesor, puedes editar todos tus datos personales exceptuando tu username y las materias que dictas..., al igual que tampoco puedes eliminar tu cuenta a menos de hablar con un administrador; a continuacion encuentras el menu: ");
        System.out.println("\t1. Editar mi nombre");
        System.out.println("\t2. Editar mi apellido");
        System.out.println("\t3. Editar mi correo de contacto");
        System.out.println("\t4. Editar mi numero de contacto");
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
                System.out.print("Digite su Nombre: ");
                aux = scanner.nextLine();
                setName(aux);
                break;
            case 2:
                System.out.print("Digite su Apellido: ");
                aux = scanner.nextLine();
                setLastName(aux);
                break;
            case 3:
                System.out.print("Digite su nuevo Correo Electronico de Contacto: ");
                aux = scanner.nextLine();
                setEmail(aux);
            break;
            case 4:
                System.out.print("Digite su nuevo Numero Telefonico de Contacto: ");
                auxl = scanner.nextLong();
                setNum(auxl);
                break;

            case 0: System.out.println("Vale, operacion cancelada oportunamente."); break;
            default: System.out.println("Digito incorrecto ;("); break;
        }
    }
    
    public void logout(){
        System.out.println("Hasta Luego Profesor @"+super.name+" "+super.lastName+"!");
        System.out.println("Sesion Cerrada de Manera Exitosa ;D");
    }
}
