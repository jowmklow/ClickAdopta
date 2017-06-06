package local.gonzalez.clickadopta;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class CoverFlowAdapter extends BaseAdapter {
    //clase coverflow
    private ArrayList<Lista_contenido.Lista_entrada> data;
    private ActionsActivity activity;
    private Fragment_Lista.Callbacks listener;
    //instanciamos unas varables con los arrayliist donde estableceremos el coverflow
    //informacion extraida de: https://www.youtube.com/watch?v=pzD5dQWsTSs
    //tutorial donde explica la utilidad de esta opcion

    public interface EntradaListener {
        void change(Lista_contenido.Lista_entrada animal);
    }

    public CoverFlowAdapter(ActionsActivity context, ArrayList<Lista_contenido.Lista_entrada> objects) {
        this.activity = context;
        this.data = objects;
        listener = (Fragment_Lista.Callbacks) context;
    }
    //instanciamos el adaptador del coverflow, donde le instanciaremos los objectos y el contenido de
    //la app

    @Override
    public int getCount() {
        return data.size();
    }
    //le permitiremos contar la cantidad de datos que contendra

    @Override
    public Lista_contenido.Lista_entrada getItem(int position) {
        return data.get(position);
    }

    //ponemos a la lista de entrada la posicion de cada objeto que recoja
    @Override
    public long getItemId(int position) {
        return position;
    }

    //por cada id de la lista le devolvemos al usuario una ubicacion
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        //recogeremos la vista que recoge view
        //si lo que recoge en esos momentos es null
        if (convertView == null) {
            //rellena el contenido con lo que tengas
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_noticias, null, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            //sino muestra lo que tenga el viewHolder (null), asi que siempre mostrara un contenido
            //cuando recoja los datos del servidor
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.animalImage.setImageBitmap(data.get(position).idImagen);
        convertView.setOnClickListener(onClickListener(position));
        //introducimos en el viewholder todas las imagenes,1 por 1, se le aplicara una posicion
        //esta posicion se colocara junto con el Bitmap(imagen)
        return convertView;
        //muestralo dentro del coverflow
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onEntradaSelecionada(data.get(position));
            }
        };
    }
    //en caso de hacer click en alguna posicion, se abrira lista callbacks


    private static class ViewHolder {

        private ImageView animalImage;

        //muestra la imagen del animal, dentro del coverflow
        public ViewHolder(View v) {
            animalImage = (ImageView) v.findViewById(R.id.image);
        }
    }
}