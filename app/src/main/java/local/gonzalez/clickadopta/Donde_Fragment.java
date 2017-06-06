package local.gonzalez.clickadopta;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Donde_Fragment extends Fragment implements View.OnClickListener {
    View view;
    ImageView mapa;
    TextView texto;

    public Donde_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // rellena para este fragmen el layout correspondiente
        view = inflater.inflate(R.layout.fragment_donde_, container, false);
        mapa = (ImageView) view.findViewById(R.id.mapa_perrera);
        mapa.setOnClickListener(this);
        //haz que mapa sea clickable
        texto = (TextView) view.findViewById(R.id.textView);
        texto.setOnClickListener(this);
        //devuelve la vista
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mapa_perrera:
                //cuando des click al mapa, especifica la ubicacion en la que estas y haz el recorrido mas eficiente con la aplicacion maps, si no esta
                //lo abrira con internet
                Uri uri1 = Uri.parse("https://www.google.com/maps/dir//Carrer+d'Eduard+Fontser%C3%A8,+08035+Barcelona,+Espanya/@41.4167862,2.127654,17z/data=!4m16!1m7!3m6!1s0x12a4981eacba5a1b:0xb60ccc78f493e8fa!2sCarrer+d'Eduard+Fontser%C3%A8,+08035+Barcelona,+Espanya!3b1!8m2!3d41.4167862!4d2.1298427!4m7!1m0!1m5!1m1!1s0x12a4981eacba5a1b:0xb60ccc78f493e8fa!2m2!1d2.1298427!2d41.4167862?hl=ca");
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(intent1);
                break;
            case R.id.textView:
                Fragment cont = new Contacto_Fragment();
                ActionsActivity ac = (ActionsActivity) getActivity();
                ac.setFragment(cont, "Contacto");
                break;
        }
    }
}
