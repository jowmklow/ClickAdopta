package local.gonzalez.clickadopta;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Perros_Fragment extends Fragment {

    public Perros_Fragment() {
    }

    //rellena el contenido de fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perros_, container, false);
    }

}
