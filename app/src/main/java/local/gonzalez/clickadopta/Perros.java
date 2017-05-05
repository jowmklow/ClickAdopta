package local.gonzalez.clickadopta;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by joel.gonzalez on 05/05/17.
 */

public class Perros extends Activity {
    ImageView imgShowOk;

    Thread sqlThread1 = new Thread() {

        public void run() {

        /*
        Connectar a la base de dades d'usuaris per saber si ha posat bé el nom i la contrasenya
        */

            // String nomEntrada = entraNom.getText().toString();
            // String contraEntrada = entraContra.getText().toString();
            String imagen = "tarja";

            String consulta = "SELECT * FROM tarja;";
            java.sql.Connection conn = null;

            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://192.168.0.21:5432/clickadopta", "bernerslee", "tim");
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(consulta);

                File outputFile = null;
                FileOutputStream fos = null;
                Bitmap myBitmap = null;
                byte[] dibuix = null;

                while (rs.next()) {
                    outputFile = new File("output_" + rs.getString("nom"));
                    fos = new FileOutputStream(new File(getExternalFilesDir(null), String.valueOf(outputFile)));

                    if (rs.getBytes("foto") != null) {
                        fos.write(rs.getBytes("foto"));
                        dibuix = rs.getBytes("foto");
                    }
                    myBitmap = BitmapFactory.decodeByteArray(dibuix, 0, dibuix.length);
                    imgShowOk = (ImageView) findViewById(R.id.imageView);
                    imgShowOk.setImageBitmap(myBitmap);
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
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
