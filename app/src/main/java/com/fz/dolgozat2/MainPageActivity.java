package com.fz.dolgozat2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import com.fz.dolgozat2.database.AppDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class MainPageActivity extends AppCompatActivity {
    AppDatabase db;

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
                if (item.getItemId() == R.id.menu_home){
                    fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, HomeFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("home")
                            .commit();
                }else if (item.getItemId() == R.id.menu_films){
                    fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, FilmsFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("films")
                            .commit();
                } else if (item.getItemId() ==  R.id.menu_gallery){
                    fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, IMDbFragment.class, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("imdb")
                            .commit();
                }

                return true;
            }
        });

        Intent addFilmIntent = new Intent(this, AddFilmActivity.class);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFilmIntent.putExtra("name", getIntent().getStringExtra("name"));
                startActivity(addFilmIntent);

                /*
                String url = "https://www.youtube.com/watch?v=MAlSjtxy5ak";
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setPackage("com.google.android.youtube");
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
                 */
            }
        });
    }

}