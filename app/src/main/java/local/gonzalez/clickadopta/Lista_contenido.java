package local.gonzalez.clickadopta;

import android.app.Activity;
import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lista_contenido extends Activity {
    /**
     * Donde se guardan las entradas de la lista.
     */
    public static ArrayList<Lista_entrada> ENTRADAS_LISTA = new ArrayList<Lista_entrada>();

    /**
     * Donde se asigna el identificador a cada entrada de la lista
     */
    //private static Bitmap myBitmap = Perros.get_perro1();


    public static Map<String, Lista_entrada> ENTRADAS_LISTA_HASHMAP = new HashMap<String, Lista_entrada>();

    /**
     Creamos estáticamente las entradas

     public Bitmap[] myBitmap = Perros.

     Añade una entrada a la lista

     entrada Elemento que añadimos a la lista
     */
    private static void aniadirEntrada(Lista_entrada entrada) {
        ENTRADAS_LISTA.add(entrada);
        ENTRADAS_LISTA_HASHMAP.put(entrada.id, entrada);
    }

    /**
     * Representa una entrada del contenido de la lista
     */
    public static class Lista_entrada implements Serializable {
        public String id;
        public Bitmap idImagen;
        public String textoEncima;
        public String textoDebajo;

        public Lista_entrada(String id, Bitmap idImagen, String textoEncima, String textoDebajo) {
            this.id = id;
            this.idImagen = idImagen;
            this.textoEncima = textoEncima;
            this.textoDebajo = textoDebajo;
        }
    }

}