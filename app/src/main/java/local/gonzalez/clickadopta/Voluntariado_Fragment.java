package local.gonzalez.clickadopta;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Voluntariado_Fragment extends Fragment implements View.OnClickListener {
    View view;
    Button BotonPerro;
    Button BotonGato;
    public Voluntariado_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_voluntariado_, container, false);
        view = inflater.inflate(R.layout.fragment_voluntariado_, container, false);
        BotonPerro = (Button) view.findViewById(R.id.BPerrosVolunt);
        BotonPerro.setOnClickListener(this);

        BotonGato = (Button) view.findViewById(R.id.BGatosVolunt);
        BotonGato.setOnClickListener(this);
        return view;
    }//instanciamos las variables necesarias, 2 botones y 1 view

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //si se selecciona uno de los 2 ids, uri a url, se abre con action view
            case R.id.BPerrosVolunt:
                Uri uri1 = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSd6X9f91C-QpOKaLVKhvq9wrJRHd8cGdtSEEDwLjt-G1EGFWQ/viewform");
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(intent1);
                break;
            case R.id.BGatosVolunt:
                Uri uri2 = Uri.parse("http://docs.google.com/forms/d/e/1FAIpQLSdgUtXMu6fO8DZVSEFY6AWkInNS8vm3_AKQvdhtqAAU-Q9UVQ/viewform");
                Intent intent2 = new Intent(Intent.ACTION_VIEW, uri2);
                startActivity(intent2);
                break;
        }

    }
}
