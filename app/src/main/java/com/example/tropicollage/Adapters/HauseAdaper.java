package com.example.tropicollage.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tropicollage.R;

import java.util.List;

public class HauseAdaper extends ArrayAdapter {

    Activity context;
    List<Hause> listaHause;

    public HauseAdaper(Activity context,List<Hause> lista) {
        super(context, R.layout.item_hause,lista);
        this.context = context;
        this.listaHause = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // rellenar los campos del layaut item_hause con los valores de la lista cargada
        View view = context.getLayoutInflater().inflate(R.layout.item_hause,null);
        //TextView txt_id = (TextView) view.findViewById(R.id.txt_id_hause);
        TextView txt_descrip = (TextView) view.findViewById(R.id.txt_descrip);
        //TextView txt_precio = (TextView) view.findViewById(R.id.txt_precio);

        //txt_id.setText(listaHause.get(position).getId()+"");
        txt_descrip.setText(listaHause.get(position).getDescrip());
        //txt_precio.setText(listaHause.get(position).getPrecio()+"");

        return view;
    }
/**
 * Hause  - define las valores de la clase hause
 * id - identificador de la casa
 * descrip - desciption de la casa
 * precio - el precio de alquiler de la casa*/

    public static class Hause{

        private int id;
        private String descrip;
        private int precio;

        public Hause(int id, String descrip, int precio){
            this.setId(id);
            this.setDescrip(descrip);
            this.setPrecio(precio);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescrip() {
            return descrip;
        }

        public void setDescrip(String descrip) {
            this.descrip = descrip;
        }

        public int getPrecio() {
            return precio;
        }

        public void setPrecio(int precio) {
            this.precio = precio;
        }
    }
}
