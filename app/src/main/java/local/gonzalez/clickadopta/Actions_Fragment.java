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


    private FeatureCoverFlow coverFlow;
    private FeatureCoverFlow coversinFlow;
    private CoverFlowAdapter adapter;
    private CoverFlowAdapter sinadapter;
    private ArrayList<Lista_contenido.Lista_entrada> noticia;
    private ArrayList<Lista_contenido.Lista_entrada> sinnoticia;

    public Actions_Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

        return root;
    }

    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                Log.v("MainActiivty", "position: " + position);
            }

            @Override
            public void onScrolling() {
                Log.i("MainActivity", "scrolling");
            }
        };
    }

    private void settingDummyData() {

        noticia = new ArrayList<>();
        sinnoticia = new ArrayList<>();

        Perros p = new Perros();
        Gatos g = new Gatos();

        noticia.addAll(p.getAnimales(true));
        sinnoticia.addAll(g.getAnimales(true));

    }

}
