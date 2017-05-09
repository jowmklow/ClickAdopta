package local.gonzalez.clickadopta;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class Fragment_Lista extends ListFragment {

    /**
     * El callback del fragmento que notificará de pulsaciones en la lista
     */
    private Callbacks mCallbacks = CallbacksVacios;

    /**
     * Todas las actividades que contengan este fragmento deben implementar la interfaz del este callback. Permite notificar a las actividades de los elementos seleccionados
     */
    public interface Callbacks {
        /**
         * Método Callback que escucha cuando un elemento de la lista ha sido pulsado, entonces entra aquí. Devuelve el ID del elemento de la lista que fue seleccionado
         */
        public void onEntradaSelecionada(String id);
    }

    /**
     * Una implementacón de la interface cuando la interfaz no hace nada. Se usuará cuando el fragmento no esté ligado a la actividad
     */
    private static Callbacks CallbacksVacios = new Callbacks() {
        @Override
        public void onEntradaSelecionada(String id) {
        }
    };

    /**
     * Es obligatorio un contructor vacío para instanciar el fragmento
     */
    public Fragment_Lista() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Perros p = new Perros();
        ArrayList<Lista_contenido.Lista_entrada> an = p.getAnimales();

        setListAdapter(new Lista_Adaptador(getActivity(), R.layout.layout_elemento_listado, an) {
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

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Las actividades que contengan este fragmento deben implementar este callback
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Error: La actividad debe implementar el callback del fragmento");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Resetear los callbacks activos a los vacíos.
        mCallbacks = CallbacksVacios;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int posicion, long id) {
        super.onListItemClick(listView, view, posicion, id);

        // Notificar a la actividad, por medio de la interfaz del callback, que un elemento ha sido seleccionado
        mCallbacks.onEntradaSelecionada(Lista_contenido.ENTRADAS_LISTA.get(posicion).id);
    }


}