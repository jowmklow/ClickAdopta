package local.gonzalez.clickadopta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class Gatos_Fragment extends Fragment implements AdapterView.OnItemClickListener {
    private Fragment_Lista.Callbacks mCallbacks;
    ArrayList<Lista_contenido.Lista_entrada> an;
    ListView lista;

    public Gatos_Fragment() {

    }

    public static Fragment_Lista newInstance(Fragment_Lista.Callbacks act) {
        Fragment_Lista f = new Fragment_Lista();
        f.setmCallbacks(act);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Gatos g = new Gatos();
        an = g.getAnimales(false);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_ediago, container, false);
        lista = (ListView) rootView.findViewById(R.id.lista);
        lista.setAdapter(new Lista_Adaptador(getActivity(), R.layout.layout_elemento_listado, an) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_titulo);
                    if (texto_superior_entrada != null)
                        texto_superior_entrada.setText(((Lista_contenido.Lista_entrada) entrada).textoEncima);

                    ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen_miniatura);
                    if (imagen_entrada != null)
                        imagen_entrada.setImageBitmap(((Lista_contenido.Lista_entrada) entrada).idImagen);
                }
            }
        });
        lista.setOnItemClickListener(this);
        return rootView;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mCallbacks.onEntradaSelecionada(an.get(position));
    }

    public Fragment_Lista.Callbacks getmCallbacks() {
        return mCallbacks;
    }

    public void setmCallbacks(Fragment_Lista.Callbacks mCallbacks) {
        this.mCallbacks = mCallbacks;
    }
}