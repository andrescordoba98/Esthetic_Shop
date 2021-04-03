package edu.unicauca.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private Handler handler;//Variable para manejar el tiempo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Intent intentUsuario = new Intent(this,MenuActivity.class);
        handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                //Codigo de inicio

                Toast.makeText(SplashActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                startActivity(intentUsuario);
                finish();
            }
        }, 2000);

    }
}