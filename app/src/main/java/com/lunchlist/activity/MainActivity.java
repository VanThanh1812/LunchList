package com.lunchlist.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.Toast;

import com.lunchlist.R;
import com.lunchlist.Restaurent;
import com.lunchlist.RestaurentAdapter;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    EditText edt_name;
    EditText edt_address;
    EditText edt_note;
    Restaurent newRestaurent;
    RadioGroup rd_gr;
    RadioGroup rd_type;
    ListView  lst_res;
    ArrayList<Restaurent> arr_res;
    RestaurentAdapter adapter;
    String listname = "";
    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        /*
        *
        * */
        findID();

        /*
        * tab host
        * */
        tabHost = (TabHost) findViewById(R.id.tabhost_main);

        tabHost.setup();

        TabHost.TabSpec specList = tabHost.newTabSpec("tab_list");
        specList.getTag();
        specList.setContent(R.id.lst_restaurent);
        specList.setIndicator("List Restaurent");
        tabHost.addTab(specList);

        TabHost.TabSpec specDetail = tabHost.newTabSpec("tag2");
        specDetail.setIndicator("Detail");
        specDetail.setContent(R.id.tab_detail);

        tabHost.addTab(specDetail);
        tabHost.setCurrentTab(0);

        lst_res.setOnItemClickListener(onListClick);
    }

    private void findID() {
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_address = (EditText) findViewById(R.id.edt_address);
        edt_note = (EditText) findViewById(R.id.edt_notes);
        rd_gr = (RadioGroup) findViewById(R.id.rd_gr);
        rd_type = (RadioGroup) findViewById(R.id.rd_typeres);
        lst_res = (ListView) findViewById(R.id.lst_restaurent);
        arr_res = new ArrayList<>();
        adapter = new RestaurentAdapter(this,arr_res);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            String message = "No select";
            if (newRestaurent != null){
                message = newRestaurent.getNotes();
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            return true;
        }else if (item.getItemId() == R.id.run){
            new Thread(longTassk).start();
        }

        return super.onOptionsItemSelected(item);
    }

    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Restaurent res = arr_res.get(i);
            edt_name.setText(res.getName());
            edt_address.setText(res.getAddress());
            if (res.getType().equals("Take-out")){
                rd_gr.check(R.id.rd_takeout);
            }else if (res.getType().equals("Sit-down")){
                rd_gr.check(R.id.rd_sitdown);
            }else {
                rd_gr.check(R.id.rd_delivery);
            }
            tabHost.setCurrentTab(1);
        }
    };

    public void submit(View view) {
        newRestaurent = new Restaurent(edt_name.getText().toString()
                , edt_address.getText().toString()
                , getTextButton(rd_type.getCheckedRadioButtonId())
                , edt_note.getText().toString());
        Toast.makeText(this, newRestaurent.toString()+"\n "+
                getTextButton(rd_gr.getCheckedRadioButtonId())+"\n"+
                getTextButton(rd_type.getCheckedRadioButtonId()), Toast.LENGTH_SHORT).show();
        arr_res.add(newRestaurent);
        listname = listname + newRestaurent.toString()+"\n";
        adapter.notifyDataSetChanged();
        lst_res.setAdapter(adapter);
        tabHost.setCurrentTab(0);

    }
   private String getTextButton(int id) {
       RadioButton rd_btn = (RadioButton) findViewById(id);
        return rd_btn.getText().toString();
    }

   private Runnable longTassk = new Runnable() {
       @Override
       public void run() {
            for (int i=0; i<20;i++){
                doSomeLongWork(500);
            }
       }
   };

    private void doSomeLongWork(int i) {
        runOnUiThread(new Runnable() {
            public void run() {

            }
        });
        SystemClock.sleep(250);
    }

    public void viewwlist(View view) {
        Toast.makeText(this, listname, Toast.LENGTH_SHORT).show();
    }
}
