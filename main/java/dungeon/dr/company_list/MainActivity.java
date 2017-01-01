package dungeon.dr.company_list;

import android.app.SearchManager;
import android.support.v7.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    final String[] tests={"test1", "test2", "test3", "Test4","hema","rekha", "Jaya", "Aur", "Sushma","Sabki","pasand","Nirma"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       //toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);//other than app name string goes the activity name
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));




        //custom adapter
        ListAdapter ayu_adapter = new CustomAdapter(this, tests);
        Intent i = new Intent(this,INfo_Activity.class);

        final ListView ayu_listview = (ListView)findViewById(R.id.listView);

        ayu_listview.setAdapter(ayu_adapter);

        ayu_listview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String Test = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this, Test, Toast.LENGTH_LONG).show();
/*
                        ImageView img = (ImagenView)findViewbyid(R.id.myimageview);
                        String url = "http://..."; //Firebase URL to the picture

                        Glide.with(yourActivity).load(url).into(img);
                        i.putExtra("car_image", ImageView.(parent.getItemAtPosition(position)));

                          Glide
                            .with(myFragment)
                            .load(url)
                            .centerCrop()
                            .placeholder(R.drawable.loading_spinner)
                            .crossFade()
                            .into(myImageView);
                        
                        repositories {
                                mavenCentral()
                            }

                            dependencies {
                                compile 'com.github.bumptech.glide:glide:3.7.0'
                                compile 'com.android.support:support-v4:19.1.0'
                            }
                        */


                        Intent car_info = new Intent(MainActivity.this, INfo_Activity.class);
                        car_info.putExtra("car_name", Test);
                        startActivity(car_info);
                    }


                }
        );





    }


    @Override//for adding search menu
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> result = new ArrayList<String>();
                if (newText != null && !newText.isEmpty()) {
                    for (String search : tests) {
                        if (search.toLowerCase().contains(newText.toLowerCase())) {
                            result.add(search);
                        }
                    }
                    String[] result_array = new String[result.size()];
                    result_array = result.toArray(new String[0]);
                    final ListView ayu_listview = (ListView) findViewById(R.id.listView);
                    ListAdapter ayu_adapter = new CustomAdapter(MainActivity.this, result_array);
                    ayu_listview.setAdapter(ayu_adapter);
                } else {
                    final ListView ayu_listview = (ListView) findViewById(R.id.listView);
                    ListAdapter ayu_adapter = new CustomAdapter(MainActivity.this, tests);
                    ayu_listview.setAdapter(ayu_adapter);
                }
                return true;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (menuItem.getItemId()) {
            case R.id.home_button:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_LONG).show();
                startActivity(homeIntent);
        }
        return super.onOptionsItemSelected(menuItem);
    }
    @Override
    public boolean onSearchRequested() {
        Bundle appData = new Bundle();
        startSearch(null, false, appData, false);
        return true;
    }
}