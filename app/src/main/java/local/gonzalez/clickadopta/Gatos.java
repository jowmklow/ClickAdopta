package local.gonzalez.clickadopta;

import android.app.Activity;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Gatos extends Activity {
    // ImageView imgShowOk;
    private boolean limite;
    public ArrayList<Lista_contenido.Lista_entrada> animales;

    //instanciamos array list de los animales
    public ArrayList<Lista_contenido.Lista_entrada> getAnimales(final boolean limit) {
        //iniciamos un hilo, y que este muestre el contenido de la base de datos
        limite = limit;
        //boleano de limite
        sqlThread1.start();
        try {
            sqlThread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return animales;
    }

    Thread sqlThread1 = new Thread() {

        public void run() {


        /*
        Conectar a la base de dades d'usuaris per saber si ha posat bé el nom i la contrasenya
        */

            //consulta ternaria
            String consulta = (limite) ? "select * from tarja where animal = FALSE order by fecha asc limit 5;" : "select * from tarja where animal = FALSE;";
            //limite contendra 2 consultas, 1 para noticias, otra para gatos, si fuera null seria porque no ha podido realizar la consulta
            Connection conn = null;
            //instanciamos la conexion

            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://192.168.0.21:5432/clickadopta", "bernerslee", "tim");
                //conecta con la base de datos (se coloca la base de datos, el usuario y  la contraseña)
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(consulta);
                //ejecutamos la query
                File outputFile = null;
                FileOutputStream fos = null;
                //Bitmap myBitmap = null;
                byte[] dibuix = null;
                //instanciamos dibuix como un recorrido de bytes nulo
                animales = new ArrayList<>();
                //instanciamos el arraylist de animales para rellenar estos de una sentada y no volverlo a hacer luego
                while (rs.next()) {
                    outputFile = new File("output_" + rs.getString("nom"));
                    /*en caso de que se quisiera guardar las imagenes en una carpeta
                    fos = new FileOutputStream(new File(getExternalFilesDir("/storage/carpeta_Nueva"), String.valueOf(outputFile)));*/
                    //si el contenido es diferente de nullo
                    if (rs.getBytes("foto") != null) {

                        //fos.write(rs.getBytes("foto"));
                        //todo contenido de foto se pondra la variable de bytes dibujo
                        dibuix = rs.getBytes("foto");
                    }
                    //al arraylist de animales se añadira: un id, que sera el que luego pongamos en el coverflow,
                    animales.add(new Lista_contenido.Lista_entrada(rs.getString("id"), BitmapFactory.decodeByteArray(dibuix, 0, dibuix.length), rs.getString("nom"), rs.getString("descrip")));
                    //myBitmap[contador] = BitmapFactory.decodeByteArray(dibuix, 0, dibuix.length);
                    //recoge desde el punto 0 del array de bytes de dibujo hasta el ultimo, byte, aparte recoge tambien todo contenido de la consutla que se llame nom y descrip


                    /*imgShowOk = (ImageView) findViewById(R.id.imageView);
                    imgShowOk.setImageBitmap(myBitmap);*/
                }


            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    };
}
