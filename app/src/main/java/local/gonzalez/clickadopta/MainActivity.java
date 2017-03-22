package local.gonzalez.clickadopta;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    private Button registra;
    private EditText entraNom;
    private EditText entraContra;
    private Button inicia;
    boolean error = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cManager.getActiveNetworkInfo();
        if (nInfo != null && nInfo.isConnected()) {
            Toast.makeText(this, "Internet conectado", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Internet desconectado", Toast.LENGTH_LONG).show();
            Intent offline = new Intent(this, ActionsActivity.class);
            startActivity(offline);
            finish();
        }
        //servidor test
        entraNom = (EditText) findViewById(R.id.entra_nom_inici);
        entraContra = (EditText) findViewById(R.id.entra_contrasenya_inici);
        inicia = (Button) findViewById(R.id.inicia_sessio);


        registra = (Button) findViewById(R.id.registra);
        registra.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent i = new Intent(MainActivity.this, RegistraActivity.class);
                                            startActivity(i);
                                            finish();
                                        }
                                    } //boton de registrar
        );
        inicia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlThread.start();

                while (sqlThread.isAlive()) {

                }
                if (error) {
                    Toast.makeText(MainActivity.this, "Combinación Errónea", Toast.LENGTH_LONG).show();
                }
                /*if(sqlThread.isAlive()){
                    System.out.print("thread correcte");
                }
                else{
                    System.out.print("thread error");
                }*/
            }
        });


    }

    Thread sqlThread = new Thread() {

        public void run() {

        /*
        Connectar a la base de dades d'usuaris per saber si ha posat bé el nom i la contrasenya
        */

            String nomEntrada = entraNom.getText().toString();
            String contraEntrada = entraContra.getText().toString();
            String nombre_usuario = "user_id";
            String contrasenya = "user_pass";

            String consulta = "select id_user, pass from user_id where id_user='" + nomEntrada + "';";
            Connection conn = null;

            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://192.168.0.21:5432/clickadopta", "bernerslee", "tim");
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(consulta);
                while (rs.next()) {
                    nombre_usuario = rs.getString("id_user");
                    contrasenya = rs.getString("pass");
                }
                if (nomEntrada.equals(nombre_usuario) && contraEntrada.equals(contrasenya)) {
                    Intent i = new Intent(MainActivity.this, ActionsActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    error = true;
                    Intent i = new Intent(MainActivity.this, MainActivity.class);

                    startActivity(i);

                    finish();
                }
            } catch (SQLException e) {
                System.out.println("Conection is failed! Check outout console\n" + e.toString().trim());
            } catch (ClassNotFoundException e) {
                System.out.println("Where is your postgreSQL JDBC Driver" + "\nInclude in your library path!");
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

