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
    public ArrayList<Lista_contenido.Lista_entrada> animales;

    public ArrayList<Lista_contenido.Lista_entrada> getAnimales() {
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
        Connectar a la base de dades d'usuaris per saber si ha posat bé el nom i la contrasenya
        */

            // String nomEntrada = entraNom.getText().toString();
            // String contraEntrada = entraContra.getText().toString();
            String imagen = "tarja";

            String consulta = "select * from tarja where nom like ('Gato%');";
            Connection conn = null;

            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://192.168.0.21:5432/clickadopta", "bernerslee", "tim");
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(consulta);

                File outputFile = null;
                FileOutputStream fos = null;
                //Bitmap myBitmap = null;
                byte[] dibuix = null;


                System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
                animales = new ArrayList<>();
                while (rs.next()) {
                    outputFile = new File("output_" + rs.getString("nom"));
                    //fos = new FileOutputStream(new File(getExternalFilesDir("/storage/carpeta_Nueva"), String.valueOf(outputFile)));
                    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    if (rs.getBytes("foto") != null) {

                        //fos.write(rs.getBytes("foto"));
                        dibuix = rs.getBytes("foto");
                    }
                    animales.add(new Lista_contenido.Lista_entrada(rs.getString("id"), BitmapFactory.decodeByteArray(dibuix, 0, dibuix.length), rs.getString("nom"), rs.getString("descrip")));
                    //myBitmap[contador] = BitmapFactory.decodeByteArray(dibuix, 0, dibuix.length);


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