package local.gonzalez.clickadopta;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class ActionsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager manager;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        manager = getSupportFragmentManager();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

/*
        View headerView = navigationView.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              Perros_Fragment fragment = new Perros_Fragment();
                                              setFragment(fragment);
                                              drawer.closeDrawer(navigationView);

                                          }
                                      }
        );*/
        Actions_Fragment fragment = new Actions_Fragment();
        setFragment(fragment);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.contenedor1, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actions, menu);
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


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_perros) {
            Intent i = new Intent(ActionsActivity.this, Activity_Principal.class);
            startActivity(i);
            // Perros_Fragment fragment = new Perros_Fragment();
            //  setFragment(fragment);
        } else if (id == R.id.nav_gatos) {
            Intent i = new Intent(ActionsActivity.this, Activity_Principal.class);
            startActivity(i);
            Gatos_Fragment fragment = new Gatos_Fragment();
            setFragment(fragment);
        } else if (id == R.id.nav_apadrinar) {
            Apadrina_Fragment fragment = new Apadrina_Fragment();
            setFragment(fragment);
        } else if (id == R.id.nav_voluntariado) {
            Voluntariado_Fragment fragment = new Voluntariado_Fragment();
            setFragment(fragment);
        } else if (id == R.id.nav_quienessomos) {
            Quienes_Fragment fragment = new Quienes_Fragment();
            setFragment(fragment);
        } else if (id == R.id.nav_contacto) {
            Contacto_Fragment fragment = new Contacto_Fragment();
            setFragment(fragment);
        } else if (id == R.id.nav_dondeestamos) {
            Donde_Fragment fragment = new Donde_Fragment();
            setFragment(fragment);
            // } else if (id == R.id.nav_share) {

            // } else if (id == R.id.nav_send) {
        }
        drawer.closeDrawer(navigationView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

}
