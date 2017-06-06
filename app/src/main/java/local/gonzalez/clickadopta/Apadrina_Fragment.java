package local.gonzalez.clickadopta;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Apadrina_Fragment extends Fragment implements View.OnClickListener {
    View view;
    Button apadrina;

    public Apadrina_Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_apadrina_, container, false);
        apadrina = (Button) view.findViewById(R.id.apadrina);
        apadrina.setOnClickListener(this);
        //rellena el fragment con su contenido correspondiente
        return view;
    }
    public void onClick(View v) {
        //metodo onclick, busca el id y dale uso
        switch (v.getId()) {
            case R.id.apadrina:
                //en caso de +id:apadrina entra en la siguiente direccion URL y llama un actionview que pueda mostrar este contenido
                Uri uri1 = Uri.parse("https://docs.google.com/a/iesjoandaustria.org/forms/d/e/1FAIpQLSfK_hqre6kJmjAe3kwbS6s-ZYEYHXJBE6D9KePT8tigussj8A/viewform");
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(intent1);
                break;
        }
    }
}
