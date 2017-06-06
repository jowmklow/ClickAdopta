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


public class Fragment_Lista extends Fragment implements AdapterView.OnItemClickListener {

    /**
     * El callback del fragmento que notificará de pulsaciones en la lista
     */
    private Callbacks mCallbacks;

    /**
     * Todas las actividades que contengan este fragmento deben implementar la interfaz del este
     * callback. Permite notificar a las actividades de los elementos seleccionados
     */
    public interface Callbacks {
        /**
         * Método Callback que escucha cuando un elemento de la lista ha sido pulsado, entonces
         * entra aquí. Devuelve el ID del elemento de la lista que fue seleccionado
         */
        public void onEntradaSelecionada(Lista_contenido.Lista_entrada perro);
    }

    ArrayList<Lista_contenido.Lista_entrada> an;
    ListView lista;

    /**
     * Es obligatorio un contructor vacío para instanciar el fragmento
     */
    public Fragment_Lista() {
    }

    //instancia la accion de callbacks y el fragment
    public static Fragment_Lista newInstance(Callbacks act) {
        Fragment_Lista f = new Fragment_Lista();
        f.setmCallbacks(act);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //instancia perros y an sera la recogida de todos los perros
        Perros p = new Perros();
        an = p.getAnimales(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_ediago, container, false);
        lista = (ListView) rootView.findViewById(R.id.lista);
        lista.setAdapter(new Lista_Adaptador(getActivity(), R.layout.layout_elemento_listado, an)
                //rellena el contenido de la lista con el adaptador y toda informacion que contenga ListaAdaptador
        {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    /*
                    * si la entrada de datos tiene contenido entonces establece el texto te titulo
                    * el texto de descripcion y rellena la imagen Bitmap dentro del ImageView
                     */
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
        //permite que la lista sea onclick
        return rootView;
        //muestralo con los requisitos que se necesite
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mCallbacks.onEntradaSelecionada(an.get(position));
        //es clickable tod@ contenido de la lista, recoge el aptador con el numero x del Array
    }

    public Callbacks getmCallbacks() {
        return mCallbacks; //devuelve los array
    }

    public void setmCallbacks(Callbacks mCallbacks) {
        this.mCallbacks = mCallbacks; //devuelve los array
    }
}