package local.gonzalez.clickadopta;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class CoverFlowAdapter extends BaseAdapter {

    private ArrayList<Lista_contenido.Lista_entrada> data;
    private ActionsActivity activity;
    private Fragment_Lista.Callbacks listener;

    public interface EntradaListener {
        void change(Lista_contenido.Lista_entrada animal);
    }

    public CoverFlowAdapter(ActionsActivity context, ArrayList<Lista_contenido.Lista_entrada> objects) {
        this.activity = context;
        this.data = objects;
        listener = (Fragment_Lista.Callbacks) context;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Lista_contenido.Lista_entrada getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_noticias, null, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.animalImage.setImageBitmap(data.get(position).idImagen);


        convertView.setOnClickListener(onClickListener(position));

        return convertView;
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onEntradaSelecionada(data.get(position));
            }
        };
    }


    private static class ViewHolder {

        private ImageView animalImage;

        public ViewHolder(View v) {
            animalImage = (ImageView) v.findViewById(R.id.image);
        }
    }
}