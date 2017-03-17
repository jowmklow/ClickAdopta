package local.gonzalez.clickadopta;
import android.content.Intent;
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

public class EntraActivity extends AppCompatActivity {

    private EditText entraNom;
    private EditText entraContra;
    private Button inicia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entra);

        entraNom = (EditText) findViewById(R.id.entra_nom_inici);
        entraContra = (EditText) findViewById(R.id.entra_contrasenya_inici);
        inicia = (Button) findViewById(R.id.inicia_sessio);

        inicia.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sqlThread.start();

                if(sqlThread.isAlive()){
                    System.out.print("thread correcte");
                }
                else{
                    System.out.print("thread error");
                }
            }
        });
    }

    Thread sqlThread = new Thread() {

        public void run(){

        /*
        Connectar a la base de dades d'usuaris per saber si ha posat bé el nom i la contrasenya
        */

            String nomEntrada = entraNom.getText().toString();
            String contraEntrada = entraContra.getText().toString();
            String nombre_usuario = "user_id";
            String contrasenya = "user_pass";

            String consulta = "select id_pass, pass from user_pass where id_pass='"+nomEntrada+"';";
            Connection conn = null;

            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://192.168.0.21:5432/clickadopta", "bernerslee", "tim");
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(consulta);
                while (rs.next()) {
                    nombre_usuario = rs.getString("id_pass");
                    contrasenya = rs.getString("pass");
                }
                if (nomEntrada.equals(nombre_usuario) && contraEntrada.equals(contrasenya)) {
                    Intent i = new Intent(EntraActivity.this, ActionsActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(EntraActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
            catch (SQLException e) {
                System.out.println("Conection is failed! Check outout console\n" + e.toString().trim());
            }
            catch (ClassNotFoundException e) {
                System.out.println("Where is your postgreSQL JDBC Driver" + "\nInclude in your library path!");
            }
            finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    };
   }