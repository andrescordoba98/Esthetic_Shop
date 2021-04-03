package edu.unicauca.myapplication.ui.Usuario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import edu.unicauca.myapplication.Adaptadores.AdapterCategoria;
import edu.unicauca.myapplication.Adaptadores.AdapterImagenSlider;
import edu.unicauca.myapplication.Modelos.Categoria;
import edu.unicauca.myapplication.Modelos.Producto;
import edu.unicauca.myapplication.R;

public class MenuFragment extends Fragment {

    //Atributos
    private ViewPager slider;
    private LinearLayout sliderDots;
    private int contDots;
    private ImageView[] dots;
    private ArrayList<Producto> productos = new ArrayList<>();//Lista de productos a mostrar
    private ArrayList<Categoria> categorias = new ArrayList<>();
    private RecyclerView recyclerCategoria;

    //Instancias de firebase
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    
    
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_menu_usuario, container, false);

        recyclerCategoria = root.findViewById(R.id.recyclerCategorias);



        slider = root.findViewById(R.id.viewPagerMenuUsuario);
        sliderDots = root.findViewById(R.id.sliderDots);

        llenarCategorias();

        //Llenar el array de productos
        Producto p = new Producto();
        p.setNombre("Producto 1");
        p.setImagen("https://firebasestorage.googleapis.com/v0/b/freshpop-27c12.appspot.com/o/prueba1.jpg?alt=media&token=aebecb51-1951-4aac-9bf5-8b5857aaa00c");
        productos.add(p);

        p = new Producto();
        p.setNombre("Producto 2");
        p.setImagen("https://firebasestorage.googleapis.com/v0/b/freshpop-27c12.appspot.com/o/prueba2.jpg?alt=media&token=a203d1de-3c56-4743-996d-e2d7d4a4243e");
        productos.add(p);

        p = new Producto();
        p.setNombre("Producto 3");
        p.setImagen("https://firebasestorage.googleapis.com/v0/b/freshpop-27c12.appspot.com/o/prueba3.jpg?alt=media&token=e230de5b-3861-41f2-b71d-b5211c190fab");
        productos.add(p);

        p = new Producto();
        p.setNombre("Producto 4");
        p.setImagen("https://firebasestorage.googleapis.com/v0/b/freshpop-27c12.appspot.com/o/prueba4.jpg?alt=media&token=d2d1aa16-58a9-43b6-af1e-e72ed8d8c670");
        productos.add(p);


        AdapterImagenSlider adapterImagenSlider = new AdapterImagenSlider(getContext(),getActivity().getSupportFragmentManager().beginTransaction());
        adapterImagenSlider.setProductos(productos);
        slider.setAdapter(adapterImagenSlider);


        contDots = adapterImagenSlider.getCount();
        dots = new ImageView[contDots];

        for (int i = 0; i < contDots; i++) {
            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(),R.drawable.noactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);

            sliderDots.addView(dots[i],params);

            dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(),R.drawable.active_dot));

            slider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    for (int i = 0; i < contDots; i++) {
                        dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(),R.drawable.noactive_dot));
                    }

                    dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(),R.drawable.active_dot));

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        }


        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

        // Add a new document with a generated ID
        /*db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getContext(), "Se agrego correctamente con el id: "+documentReference.getId(), Toast.LENGTH_SHORT).show();
                        //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error adding document", e);
                        Toast.makeText(getContext(), "Error al agregar el documento", Toast.LENGTH_SHORT).show();
                    }
                });

        // Create a new user with a first, middle, and last name
         user = new HashMap<>();
        user.put("first", "Alan");
        user.put("middle", "Mathison");
        user.put("last", "Turing");
        user.put("born", 1912);*/

// Add a new document with a generated ID
        /*db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getContext(), "Se agrego correctamente con el id: "+documentReference.getId(), Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Error al agregar el documento", Toast.LENGTH_SHORT).show();

                    }
                });*/

        return root;
    }

    private void llenarCategorias(){
        db.collection("Categoria")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Categoria categoria;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                categoria = new Categoria();

                                categoria.setNombre(document.getString("nombre"));
                                categoria.setImage(document.getString("imagen"));

                                categorias.add(categoria);
                            }

                            Toast.makeText(getContext(), "---> Categorias: "+categorias.size(), Toast.LENGTH_SHORT).show();

                            AdapterCategoria adapterCategoria = new AdapterCategoria();
                            adapterCategoria.setCategorias(categorias);

                            adapterCategoria.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //seleccionarCategoria(view);
                                }
                            });

                            recyclerCategoria.setAdapter(adapterCategoria);
                            recyclerCategoria.setLayoutManager(new GridLayoutManager(getContext(), 2));


                        } else {
                            //Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }


}