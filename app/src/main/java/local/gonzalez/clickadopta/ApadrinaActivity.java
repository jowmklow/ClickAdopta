package local.gonzalez.clickadopta;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ApadrinaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayout linearLayout;
    Toolbar toolbar;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apadrina);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.bringToFront();
                Toast.makeText(v.getContext(), "haha", Toast.LENGTH_LONG).show();
            }
        });
        View headerView = navigationView.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              Intent i = new Intent(ApadrinaActivity.this, ActionsActivity.class);
                                              startActivity(i);
                                              finish();
                                          }
                                      }
        );
        linearLayout.bringToFront();

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer;
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.bringToFront();
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_perros) {

            Intent i = new Intent(ApadrinaActivity.this, PerrosActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_gatos) {
            Intent i = new Intent(ApadrinaActivity.this, GatosActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_apadrinar) {
            Intent i = new Intent(ApadrinaActivity.this, ApadrinaActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_voluntariado) {
            Intent i = new Intent(ApadrinaActivity.this, VoluntariadoActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_quienessomos) {
            Intent i = new Intent(ApadrinaActivity.this, QuienesActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_contacto) {
            Intent i = new Intent(ApadrinaActivity.this, DondeActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_dondeestamos) {
            Intent i = new Intent(ApadrinaActivity.this, DondeActivity.class);
            startActivity(i);
            // } else if (id == R.id.nav_share) {

            // } else if (id == R.id.nav_send) {
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
