package Test;

import java.io.File;
import java.io.PrintWriter;

public class Probando {
    // ESTO ES PARA PROBAR LA RUTA DE LOS ARCHIVOS, lo demas si con el main y eso xd
    File file1 = new File(".//Files//Prueba.txt"); // De esta menra funciona
    File file2 = new File("./Files/Prueba2.txt"); // De esta manera funciona
    File file3 = new File("../Files/Prueba3.txt"); /* ASI NO */
    
    public void probando(){
        try {
            PrintWriter pw = new PrintWriter(file1);
            pw.println("1Probando linea");
            pw.println("1Probando linea 2");
            System.out.println("Done1!");
            pw.close();
        } catch (Exception e) {
            System.out.println("Error1"+e);
        }
        try {
            PrintWriter pw = new PrintWriter(file2);
            pw.println("2Probando linea");
            pw.println("2Probando linea 2");
            System.out.println("Done2!");
            pw.close();
        } catch (Exception e) {
            System.out.println("Error2"+e);
        }
        try {
            PrintWriter pw = new PrintWriter(file3);
            pw.println("3Probando linea");
            pw.println("3Probando linea 2");
            System.out.println("Done3!");
            pw.close();
        } catch (Exception e) {
            System.out.println("Error3"+e);
        }
    }
}