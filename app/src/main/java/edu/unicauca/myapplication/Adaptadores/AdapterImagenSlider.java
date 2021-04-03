package edu.unicauca.myapplication.Adaptadores;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import edu.unicauca.myapplication.Modelos.Producto;

//Adaptador para mostrar la lista de las imagenes
public class AdapterImagenSlider extends PagerAdapter {

    private Context context;//Contexto de la actividad
    private ArrayList<Producto> productos = new ArrayList<>();//Lista de productos a mostrar
    private FragmentTransaction transaction;//Fragment para ir a la categoria

    public AdapterImagenSlider(Context context, FragmentTransaction transaction){
        this.context = context;
        this.transaction = transaction;
    }

    public void setProductos(ArrayList<Producto> productos){
        this.productos = productos;
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    //Metodo para colocar los imagenes en el slider
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        ImageView imageView = new ImageView(context);

        if(productos.get(position).getImagen() != null){
            Uri uri = (Uri.parse(productos.get(position).getImagen()));
            Glide.with(context)
                    .load(uri)
                    .fitCenter()
                    .into(imageView);
        }

        container.addView(imageView,0);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ir al fragment segun el item seleccionado
                String categoria = productos.get(position).getCategoria();

                Bundle bn = new Bundle();
                bn.putString("categoria",categoria);

                /*ProductosFragment fr = new ProductosFragment();
                fr.setArguments(bn);
                transaction.replace(R.id.nav_host_fragment,fr);
                transaction.addToBackStack(null);
                transaction.commit();*/
            }
        });

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
