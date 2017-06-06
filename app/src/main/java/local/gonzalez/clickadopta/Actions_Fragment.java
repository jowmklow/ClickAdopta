package local.gonzalez.clickadopta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;


public class Actions_Fragment extends Fragment {

    //instanciamos las variables
    private FeatureCoverFlow coverFlow;
    private FeatureCoverFlow coversinFlow;
    //featurecoverflow, sirve para poner una lista visual tanto horizontal como vertical
    private CoverFlowAdapter adapter;
    private CoverFlowAdapter sinadapter;
    //creamos el cover para perro y para gato
    private ArrayList<Lista_contenido.Lista_entrada> noticia;
    private ArrayList<Lista_contenido.Lista_entrada> sinnoticia;

    public Actions_Fragment() {
        //instanciamos el fragment
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //rellenamos el fragment con el coverflow del gato y del eprro, le insertamos los datos de Actionsactivity
        View root = inflater.inflate(R.layout.fragment_actions_, container, false);
        coverFlow = (FeatureCoverFlow) root.findViewById(R.id.coverflow);
        coversinFlow = (FeatureCoverFlow) root.findViewById(R.id.coversinflow);
        settingDummyData();
        adapter = new CoverFlowAdapter((ActionsActivity) getActivity(), noticia);
        sinadapter = new CoverFlowAdapter((ActionsActivity) getActivity(), sinnoticia);
        coverFlow.setAdapter(adapter);
        coversinFlow.setAdapter(sinadapter);

        coversinFlow.setOnScrollPositionListener(onScrollListener());
        coverFlow.setOnScrollPositionListener(onScrollListener());
        //devolvemos root dado que necesita permisos como tal para poder mostrar el contenido
        return root;
    }

    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            //mirar la posicion actual de la lista, donde se puede situar la foto actual como primera
            public void onScrolledToPosition(int position) {
                Log.v("MainActivity", "position: " + position);
            }

            @Override
            //se puede mover tanto a la izq como para la derecha
            public void onScrolling() {
                Log.i("MainActivity", "scrolling");
            }
        };
    }

    private void settingDummyData() {
        //actualizamos el array list
        noticia = new ArrayList<>();
        sinnoticia = new ArrayList<>();
        //instanciamos las calses de ambos
        Perros p = new Perros();
        Gatos g = new Gatos();
        //insertamos dentro del array todos los valores que recoja el hilo de estas clases
        noticia.addAll(p.getAnimales(true));
        sinnoticia.addAll(g.getAnimales(true));

    }

}
