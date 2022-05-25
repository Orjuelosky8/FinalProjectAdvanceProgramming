package Profesor;

import java.io.*;
import java.util.Scanner;

import Data.Comments;
import Estudiante.Estudiante;

public class CommunicateStudent extends Profesor {
    Comments newComment = new Comments();
    Estudiante estudiante;
    File FStudents = new File(".//Files//Students.obj");
    Scanner scanning = new Scanner(System.in);

    public void newMessage(){
        int n = 0, aux=0;
        System.out.println("A que estudiante le desea enviar este mensaje?");
        System.out.println(" * * Lista Estudiantes * * ");
        FileInputStream fis=null;
        ObjectInputStream ois=null;
        try {
            fis = new FileInputStream(FStudents);
            while (true) {
                n++;
                ois = new ObjectInputStream(fis);
                estudiante = (Estudiante)ois.readObject();
                System.out.println(n+" -> @"+estudiante.getUsername() + " => " + estudiante.getName()+" "+estudiante.getLastName());
            }
        } catch (Exception e) {
            System.out.println(" Fin lista de estudiantes.");
        }
        do {
            try {
                System.out.print("Digite el # del estudiante al cual ira dirigido el mensaje (1/"+n+"): ");
                aux = scanning.nextInt();
            } catch (Exception e) {
                System.out.println("Recuerde que debe digitar un numero del 1 al "+n+".");
            }
        } while (aux > n || aux <= 0);
        n=0;
        try {
            fis = new FileInputStream(FStudents);
            while (true) {
                n++;
                ois = new ObjectInputStream(fis);
                estudiante = (Estudiante)ois.readObject();
                if (n == aux) {
                    newComment.setReciever((estudiante.getUsername() + ": "+ estudiante.getName()+" "+estudiante.getLastName()));
                    System.out.println("Establecido correctamente como destinatario: @"+estudiante.getUsername() + " => " + estudiante.getName()+" "+estudiante.getLastName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(" Fin lista de estudiantes.");
        }
        System.out.print("Introduzca el titulo del mensaje: ");
        newComment.setTitle(scanning.nextLine());
        System.out.println("A continuacion, redacte el mensaje que desea dejar: ");
        newComment.setContent(scanning.nextLine());
        newComment.setSender((getUsername() + ": "+getName()+" "+getLastName()));

        try {
            FileOutputStream fos = new FileOutputStream(FStudents, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(newComment);
            oos.close();
        } catch (Exception e) {
            System.out.println("Fallo al guardar el mensaje.");
        }
    }
}