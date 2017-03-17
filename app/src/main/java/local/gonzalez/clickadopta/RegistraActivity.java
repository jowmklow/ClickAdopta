package local.gonzalez.clickadopta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegistraActivity extends AppCompatActivity {

        private EditText id_user;
        private EditText pass;
        private EditText telf;
        private EditText city;
        private EditText Cp;
        private Button registra; //valores de pantalla registra

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registra);

            id_user = (EditText) findViewById(R.id.name);
            pass= (EditText) findViewById(R.id.pass);
            telf = (EditText) findViewById(R.id.phone);
            city = (EditText) findViewById(R.id.ciudad);
            Cp = (EditText) findViewById(R.id.cp);
            registra = (Button) findViewById(R.id.registra); //strings values R activitys

//introducimos el xml a nuestros datos para que almacene estos dentro de el.


            registra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sqlThread.start();

                    if (sqlThread.isAlive()) {
                        System.out.print("Correcte");
                    } else {
                        System.out.print("Error");
                    }
                }
            });
        }
/*
TABLE user_id
id_user
pass
id_number
telf
city
cp
date
 */
        Thread sqlThread = new Thread() {
            public void run() {

                String name = id_user.getText().toString();
                String password = pass.getText().toString();
                String phone = telf.getText().toString();
                String ciudad = city.getText().toString();
                String cp = Cp.getText().toString();//strings

                //String insert = "insert into user_id values('" + name + "', '" + password + "', '" + "DEFAULT" + "', '"  + phone + "', " + ciudad + ", " + cp + "14/03/2017"+");";
                Connection conn = null;
                //insert into user_id values ('Joel','1234',DEFAULT,658980270,'Av Gran Via','08020','13/03/2017');
                //st.executeUpdate("INSERT INTO contacts(nombre, direccion, cp, telefono, password, usuario) values('" + nombre + "', '" + direccion + "', '" + cp + "', '" + telefono + "', '" + passord + "', '" + usuario + "');");
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dateFormat.setTimeZone(cal.getTimeZone());
                //jaja salu2

                try {

                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://192.168.0.21:5432/clickadopta", "bernerslee", "tim");


                    Statement st = conn.createStatement();
                    st.executeUpdate("insert into user_id(id_user, pass, telf, city, cp, tiempo) values ('" + name + "', '" + password + "', '" + phone + "', '" + ciudad + "', '" + cp +"', '"+ dateFormat.format(cal.getTime())+"');");

                    Intent i = new Intent(RegistraActivity.this, EntraActivity.class);

                    startActivity(i);//pantalla entra si conecta


                } catch (SQLException e) {
                    System.out.println("Error: " + e);
                } catch (ClassNotFoundException e) {
                    System.out.println("Error: " + e);
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        System.out.println("Error: " + e);
                    }//errores SQL
                }
            }
        };
    }
