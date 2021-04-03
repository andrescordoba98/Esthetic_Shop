package edu.unicauca.myapplication.Adaptadores;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.unicauca.myapplication.Modelos.Categoria;
import edu.unicauca.myapplication.R;

//Adaptador para colocar la lista de las categorias
public class AdapterCategoria extends RecyclerView.Adapter<AdapterCategoria.ViewHolderDatos> implements View.OnClickListener{

    ArrayList<Categoria> categorias = new ArrayList<>();//Lista de las categorias a mostrar
    private View.OnClickListener listener;//Evento de clic en las categorias

    public void setCategorias(ArrayList<Categoria> list){ this.categorias = list; }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    //Inflate del objeto del recycler de categoria
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_categoria,parent,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    //Metodo para setear los datos en cada cardview
    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.nombre.setText(categorias.get(position).getNombre());

        if(categorias.get(position).getImage() != null){
            Uri uri = (Uri.parse(categorias.get(position).getImage()));
            Glide.with(holder.context)
                    .load(uri)
                    .fitCenter()
                    .into(holder.img);
        }

    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    //Clase estatica con los objetos del recycler
    public static class ViewHolderDatos extends RecyclerView.ViewHolder {

        ImageView img;
        TextView nombre;
        Context context;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();
            img = itemView.findViewById(R.id.imagenCategoria);
            nombre = itemView.findViewById(R.id.nombreCategoria);
        }

    }

}
