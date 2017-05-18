package com.lunchlist.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.Toast;

import com.lunchlist.CursorAdapter;
import com.lunchlist.Items;
import com.lunchlist.R;
import com.lunchlist.RestaurantAdapter;
import com.lunchlist.RestaurantHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edt_name;
    EditText edt_address;
    EditText edt_note;
    Items newItems;
    RadioGroup rd_gr;
    RadioGroup rd_type;
    ListView  lst_res;
    ListView lst_restaurent_max;
    ArrayList<Items> arr_res;
    RestaurantAdapter adapter;
    String listname = "";
    TabHost tabHost;
    ProgressBar progressBar;
    Cursor cursor;
    public final static String ID_EXTRA="apt.tutorial._ID";
    private SharedPreferences.OnSharedPreferenceChangeListener prefListener;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Danh sách nhà hàng");
        /*
        *
        * */


        /*
        * tab host
        * */

        // 12

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        findID();
        setValueListView();
        lst_res.setOnItemClickListener(onListClick);

    }

    private void setValueListView() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {

            public void onSharedPreferenceChanged(SharedPreferences sharedPrefs, String

                    key) {

                if (key.equals("sort_order")) {
                    initList();
                    Toast.makeText(MainActivity.this, sharedPrefs.getString("sort_order","default"), Toast.LENGTH_SHORT).show();
                }

            }

        };

        initList();
        
        prefs.registerOnSharedPreferenceChangeListener(prefListener);
    }

    private void initList(){
        if (cursor != null){
            stopManagingCursor(cursor);
            cursor.close();
        }
        RestaurantHelper helper = new RestaurantHelper(this);
        cursor = helper.getAllRestaurent(prefs.getString("sort_order", "name"));
        startManagingCursor(cursor);
        CursorAdapter adapter = new CursorAdapter(this,cursor , true);
        lst_res.setAdapter(adapter);
        registerForContextMenu(lst_res);
    }

    private void findID() {

        lst_res = (ListView) findViewById(R.id.lst_restaurent);
        //arr_res = new ArrayList<>();
        /*adapter = new RestaurantAdapter(this,arr_res);
        lst_res.setAdapter(adapter);*/
        // max
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_add){
            Intent intent=new Intent(MainActivity.this, DetailForm.class);

            startActivity(intent);
            return true;
        }else if (id == R.id.menu_thongke){
            // TODO: thống kê
        }else if (id == R.id.menu_runlong){
            progressBar.setVisibility(View.VISIBLE);
            runLongTask();
            return true;
        }else if (id == R.id.menu_preference){
            startActivity(new Intent(this, EditPreference.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void runLongTask() {
        new Thread(longTask).start();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.lst_restaurent) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_listview, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // 2. lunch list click
            Intent intent=new Intent(MainActivity.this, DetailForm.class);
            intent.putExtra(ID_EXTRA, String.valueOf(l));
            startActivity(intent);

        }
    };

   @NonNull
   public String getTextButton(int id) {
       RadioButton rd_btn = (RadioButton) findViewById(id);
        return rd_btn.getText().toString();
    }

   private Runnable longTask = new Runnable() {
       @Override
       public void run() {
            for (int i=0; i<20;i++){
                doSomeLongWork(5);
            }

           runOnUiThread(new Runnable() {
               @Override
               public void run() {
                   progressBar.setProgress(0);
                   progressBar.setVisibility(View.GONE);
                   Toast.makeText(MainActivity.this, "End progress", Toast.LENGTH_SHORT).show();
               }
           });
       }
   };

    private void doSomeLongWork(final int i) {

        runOnUiThread(new Runnable() {
            public void run() {
                int now = progressBar.getProgress()+i;
                progressBar.setProgress(now);
            }
        });
        SystemClock.sleep(250);
    }

    /*public void viewwlist(View view) {
        Button btn_Save = (Button) findViewById(R.id.btn_submit);
        btn_Save.setText("Lưu");
        edt_name.setText("");
        edt_address.setText("");
        edt_note.setText("");
        rd_gr.check(R.id.rd_takeout);
        rd_type.check(R.id.rd_25);

    }*/

    @Override
    protected void onResume() {
        super.onResume();
        cursor.requery();
    }
}
