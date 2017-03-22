package local.gonzalez.clickadopta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActionsActivity extends AppCompatActivity {

    private Button image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);

        image1 = (Button) findViewById(R.id.image1);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }

        });
    }

}
