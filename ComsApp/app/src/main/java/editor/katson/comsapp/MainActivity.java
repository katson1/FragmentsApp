package editor.katson.comsapp;

        import android.net.Uri;
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
        import android.widget.TextView;

        import com.google.android.gms.appindexing.Action;
        import com.google.android.gms.appindexing.AppIndex;
        import com.google.android.gms.appindexing.Thing;
        import com.google.android.gms.common.api.GoogleApiClient;


        import java.io.File;
        import java.io.FileInputStream;
        import java.io.IOException;
        import java.io.ObjectInputStream;

        import static editor.katson.comsapp.R.id.nav_inicio;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private TextView textNameNavHeader;
    private TextView textEmailNavHeader;
    private UserObject user;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /** FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
         fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show();
        }
        }); */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        changeNameInNavHeader();
        changeEmailInNavHeader();
        navigationView.setNavigationItemSelectedListener(this);



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame
                        , new MenuFragment())
                .commit();


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
        int id = item.getItemId();

        android.app.FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_meus_pedidos) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new MeusPedidosFragment())
                    .commit();


        } else if (id == R.id.nav_lista_desejos) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new ListaDeDesejosFragment())
                    .commit();

        } else if (id == R.id.nav_carrinho) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new CarrinhoFragment())
                    .commit();
        } else if (id == nav_inicio) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new MenuFragment())
                    .commit();

        } else if (id == R.id.nav_my_acc) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new MyAccFragment())
                    .commit();

        } else if (id == R.id.nav_comunicat) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new TalkFragment())
                    .commit();

        } else if (id == R.id.nav_about) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new AboutFragment())
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {

        super.onStart();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public UserObject lerDados() throws ClassNotFoundException, IOException {
        String FILENAME = "user.txt";
        File file = getApplicationContext().getFileStreamPath(FILENAME);
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        UserObject user = (UserObject) ois.readObject();
        fis.close();

        ois.close();
        return user;
    }

    public boolean fileExists() throws ClassNotFoundException, IOException{
        String FILENAME = "user.txt";
        File file = getApplicationContext().getFileStreamPath(FILENAME);
        return file.exists();
    }

    public void changeNameInNavHeader() {
        View headerView = navigationView.getHeaderView(0);
        textNameNavHeader = (TextView) headerView.findViewById(R.id.txt_barra_nome);

        try {
            user = lerDados();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (fileExists()) {
                textNameNavHeader.setText(user.getNome());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeEmailInNavHeader() {
        View headerView = navigationView.getHeaderView(0);
        textEmailNavHeader = (TextView) headerView.findViewById(R.id.txt_barra_email);

        try {
            user = lerDados();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (fileExists()) {
                textEmailNavHeader.setText(user.getEmail());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
