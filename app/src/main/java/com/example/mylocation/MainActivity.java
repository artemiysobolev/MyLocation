package com.example.mylocation;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int id;
    // Создадим новый фрагмент
    private Fragment fragment = null;
    private Class fragmentClass = null;
    private String name;
    private ArrayList<String> contacts = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        fragmentClass = DeviceInfoFragment.class;
        tryInstance();
        Intent intent = new Intent(this, DeviceInfoActivity.class);
        startActivityForResult(intent, 1);

        Bundle bundle = new Bundle();
        bundle.putString("info", name);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        id = item.getItemId();

        if (id == R.id.nav_deviceinfo) {
            // Handle the camera action
            fragmentClass = DeviceInfoFragment.class;
            tryInstance();
            Intent intent = new Intent(this, DeviceInfoActivity.class);
            startActivityForResult(intent, 1);

            Bundle bundle = new Bundle();
            bundle.putString("info", name);
            fragment.setArguments(bundle);
        } else if (id == R.id.nav_messages) {
            fragmentClass = MessagesFragment.class;
            tryInstance();
        } else if (id == R.id.nav_photos) {
            fragmentClass = PhotosFragment.class;
            tryInstance();

        } else if (id == R.id.nav_contacts) {
            fragmentClass = ContactsFragment.class;
            tryInstance();
            Intent intent = new Intent(this, ContactsActivity.class);
            startActivityForResult(intent, 1);

            Bundle bundle = new Bundle();
            bundle.putStringArrayList("contacts", contacts);
            fragment.setArguments(bundle);
//            Bundle bundle = new Bundle();
//            bundle.putString("info", name);
//            fragment.setArguments(bundle);
        }

        // Вставляем фрагмент, заменяя текущий фрагмент
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
        // Выделяем выбранный пункт меню в шторке
        item.setChecked(true);
        // Выводим выбранный пункт в заголовке
        setTitle(item.getTitle());



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (id == R.id.nav_deviceinfo) {
            name = data.getStringExtra("info");
        } else if (id == R.id.nav_messages) {
        } else if (id == R.id.nav_photos) {
        } else if (id == R.id.nav_contacts) {
            contacts = data.getStringArrayListExtra("contacts");
        }
    }

    protected void tryInstance() {
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
