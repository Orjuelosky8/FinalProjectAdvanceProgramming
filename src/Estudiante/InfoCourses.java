package Estudiante;

import java.io.*;
import java.util.Scanner;

import Data.Courses;

public class InfoCourses extends Estudiante{
    File FCourses = new File("./Files/Courses.obj");
    Scanner scanning = new Scanner(System.in);

    public void checkCourses(){
        System.out.println("\t--- MIS CURSOS ---");
        System.out.println("A continuacion se desplegara la lista de materias que tienes inscritas actualmente: ");
        for (int i = 0; i < subjects.size(); i++) 
            System.out.println("\t"+(i+1)+". "+ subjects.get(i));
    }
    
    public void addCourse(){
        int cont=0, n=0; boolean valid=false;
        System.out.println("\t--- AÑADIR MATERIA ---");
        System.out.println("En primer lugar se desplegara la lista de todas las materias que tenemos disponiblres para ti, para que de esta forma puedas saber cual quieres inscribir.");
        System.out.println(" ** LISTA DE MATERIAS **");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Courses course;
        try {
            fis = new FileInputStream(FCourses);
            while (true) {
                ois = new ObjectInputStream(fis);
                course = (Courses)ois.readObject();
                cont++;
                System.out.println(" *** MATERIA #"+cont+" ***");
                System.out.println("Nombre: "+course.getName());
                System.out.println("Numero de Clase: "+course.getN_class());
                System.out.println("Descripcion: "+course.getDescription());
                System.out.println("Numero de creditos: "+course.getN_credits());
                System.out.println("Numero de cupos totales: "+course.getN_cupos());
            }
        } catch (Exception e) {
            System.out.println("FIN MATERIAS DISPONIBLES");
        }
        do {
            try {
                System.out.print("Digite el numero de clase de la materia que desea inscribir: ");
                n = scanning.nextInt();
                // Busqueda de la clase en el archivo, aca voy xd
                valid = true;
            } catch (Exception e) {
                System.out.println("Solo digitar numeros.");
                valid = false;
            }
        } while (!valid);
        System.out.println(n);;
    }
    
    public void removeCourse(){
        String subject;
        System.out.println(" --- RETIRAR MATERIA ---");
        System.out.println("Digite el nobre de la materia que quiere retirar de su horario actual: ");
        subject = scanning.nextLine();
        for (int i = 0; i < subjects.size(); i++) 
            if (subjects.get(i).equals(subject)) {
                System.out.println("Materia encontrada en su lista actual, eliminandola...");
                subjects.remove(i);
                System.out.println("Materia eliminada de manera exitosa.");
            }
    }
}
