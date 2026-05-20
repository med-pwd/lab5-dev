package com.example.lab5convertisseurtempraturedistance;



import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.OnBackPressedCallback;   // ✅ nouveau import
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ✅ Interception moderne du bouton Retour
        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        showQuitDialog();
                    }
                }
        );

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Converter");
        }

        // ViewPager2 + Adapter
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ConverterPagerAdapter(this));

        // TabLayout ↔ ViewPager2
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) tab.setText("C <-> F");
            else               tab.setText("KM <-> MILES");
        }).attach();
    }

    // Menu Quitter
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_quit) {
            showQuitDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Dialogue de confirmation
    private void showQuitDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Quitter")
                .setMessage("Voulez-vous vraiment quitter l'application ?")
                .setPositiveButton("Oui", (dialog, which) -> finish())
                .setNegativeButton("Non", null)
                .show();
    }

    // Adaptateur de pages
    private static class ConverterPagerAdapter extends FragmentStateAdapter {
        ConverterPagerAdapter(FragmentActivity fa) { super(fa); }

        @Override public int getItemCount() { return 2; }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return position == 0 ? new TemperatureFragment() : new DistanceFragment();
        }
    }
}