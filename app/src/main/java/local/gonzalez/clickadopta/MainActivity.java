package local.gonzalez.clickadopta;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button entra;
    private Button registra;

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
        entra = (Button) findViewById(R.id.entra);
        entra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EntraActivity.class);
                startActivity(i);
                finish(); //boton de entrar
            }
        });
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
    }
}

