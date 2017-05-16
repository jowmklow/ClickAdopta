package local.gonzalez.clickadopta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class Fragment_Detalle extends Fragment {
    /**
     * El argumento que representa el ID del elemento selecionado en la lista y que a este fragmento le llega para cargar el contenido apropiado
     */

    public Lista_contenido.Lista_entrada ARG_ENTRADA_SELECIONADA;



    /**
     * Es obligatorio un contructor vacío para instanciar el fragmento
     */
    public Fragment_Detalle() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_fragment_detalle, container, false);

        //Mostramos el contenido al usuario
        if (ARG_ENTRADA_SELECIONADA != null) {
            ((TextView) rootView.findViewById(R.id.textView_superior)).setText(ARG_ENTRADA_SELECIONADA.textoEncima);
            ((TextView) rootView.findViewById(R.id.textView_inferior)).setText(ARG_ENTRADA_SELECIONADA.textoDebajo);
            ((ImageView) rootView.findViewById(R.id.imageView_imagen)).setImageBitmap(ARG_ENTRADA_SELECIONADA.idImagen);
        }

        return rootView;
    }

    public Lista_contenido.Lista_entrada getARG_ENTRADA_SELECIONADA() {
        return ARG_ENTRADA_SELECIONADA;
    }

    public void setARG_ENTRADA_SELECIONADA(Lista_contenido.Lista_entrada ARG_ENTRADA_SELECIONADA) {
        this.ARG_ENTRADA_SELECIONADA = ARG_ENTRADA_SELECIONADA;
    }
}