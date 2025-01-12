package com.fz.dolgozat2;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        BottomNavigationView nav = findViewById(R.id.bottom_navigation);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_films){
                    fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, FilmsFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("films")
                            .commit();
                } else if (item.getItemId() ==  R.id.menu_gallery){
                    fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, ImagesFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("gallery")
                            .commit();
                }

                return true;
            }
        });
    }
}