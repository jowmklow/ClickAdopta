package local.gonzalez.clickadopta;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Apadrina_Fragment extends Fragment {


    public Apadrina_Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_apadrina_, container, false);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.apadrina:
                Uri uri1 = Uri.parse("https://docs.google.com/a/iesjoandaustria.org/forms/d/e/1FAIpQLSfK_hqre6kJmjAe3kwbS6s-ZYEYHXJBE6D9KePT8tigussj8A/viewform");
                Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(intent1);
                break;
        }

    }
}
