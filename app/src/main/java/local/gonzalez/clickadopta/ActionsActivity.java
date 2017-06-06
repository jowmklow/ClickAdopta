package local.gonzalez.clickadopta;

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
import android.view.View;


public class ActionsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Fragment_Lista.Callbacks {
    //implementamos en esta clase lo necesario para que los datos de las noticias se
    private FragmentManager manager;
    DrawerLayout drawer;
    //instanciamos los drawer layout para insertar vistas dentro de estas
    ActionBarDrawerToggle toggle;
    //toggle es el icono de opciones
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //colocamos la toolbar como toolbar y la instanciamos como actionbar
        setSupportActionBar(toolbar);
        //instanciamos la toolbar y la ponemos en el activity con vista
        manager = getSupportFragmentManager();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        /*le introducimos a esta actividad el navegador que le permitira al usuario viajar por
        la app*/

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //colocamos setNavigationItemSelectedListener para permitir al navegador ser clickeado


        View headerView = navigationView.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              Actions_Fragment fragment = new Actions_Fragment();
                                              setFragment(fragment, "main");
                                              drawer.closeDrawer(navigationView);
                                              //cuando se abra si se hace click fuera de este
                                              //automaticamente se cerrara

                                          }
                                      }
        );
        Actions_Fragment fragment = new Actions_Fragment();
        setFragment(fragment, "main");
        //aplicamos el fragment Actions_Fragment sobre este
    }

    public void setFragment(Fragment fragment, String etiqueta) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.contenedor1, fragment, etiqueta);
        transaction.addToBackStack(null);
        transaction.commit();
        //hacemos una transaccion del fragment
        //pondremos el contenido de contenedor1 y la etiqueta de cada animal
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Fragment f = getSupportFragmentManager().findFragmentByTag("main");
            if (f == null) {
                super.onBackPressed();
            } else {
                Actions_Fragment fragment = new Actions_Fragment();
                setFragment(fragment, "main");
            }
        }
    }
    /*
    metodo, si se tiene abierto el DrawerLayout y se le da al boton atras del telefono (fuera de
    la app ), este se cerrara y se mostrara la actividad que este en un nivel inferior
    */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        /*
        metodo que permite automaticamente
        */
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actions, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        drawer.closeDrawer(navigationView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_perros) {
            //Intent i = new Intent(ActionsActivity.this, Activity_Principal.class);
            //startActivity(i);
            Fragment_Lista fragment = new Fragment_Lista();
            fragment.setmCallbacks(this);
            setFragment(fragment, "perro");
        } else if (id == R.id.nav_gatos) {
            // Intent i = new Intent(ActionsActivity.this, Activity_Secundario.class);
            // startActivity(i);
            Gatos_Fragment fragment = new Gatos_Fragment();
            fragment.setmCallbacks(this);
            setFragment(fragment, "gato");
        } else if (id == R.id.nav_apadrinar) {
            Apadrina_Fragment fragment = new Apadrina_Fragment();
            setFragment(fragment, "Apadrina");
        } else if (id == R.id.nav_voluntariado) {
            Voluntariado_Fragment fragment = new Voluntariado_Fragment();
            setFragment(fragment, "Voluntariado");
        } else if (id == R.id.nav_quienessomos) {
            Quienes_Fragment fragment = new Quienes_Fragment();
            setFragment(fragment, "Quienes");
        } else if (id == R.id.nav_contacto) {
            Contacto_Fragment fragment = new Contacto_Fragment();
            setFragment(fragment, "Contacto");
        } else if (id == R.id.nav_dondeestamos) {
            Donde_Fragment fragment = new Donde_Fragment();
            setFragment(fragment, "Donde");
            // } else if (id == R.id.nav_share) {

            // } else if (id == R.id.nav_send) {
        }
        return true;

    }

    @Override
    public void onEntradaSelecionada(Lista_contenido.Lista_entrada animales) {
        //if (dosFragmentos) {
        // Si estamos en pantallas grandes, se mostrará el detalle seleccionado en esta misma actividad remplazando el fragmento del detalle por el nuevo
        Bundle arguments = new Bundle();
        Fragment_Detalle fragment = new Fragment_Detalle();
        fragment.setArguments(arguments);
        fragment.setARG_ENTRADA_SELECIONADA(animales);
        setFragment(fragment, "animal");

    }
}
