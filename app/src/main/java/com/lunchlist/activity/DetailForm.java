package com.lunchlist.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lunchlist.Items;
import com.lunchlist.R;
import com.lunchlist.RestaurantHelper;

public class DetailForm extends AppCompatActivity {

    EditText name=null;
    EditText address=null;
    EditText notes=null;
    RadioGroup types=null;
    RestaurantHelper helper=null;
    Button btn_save;
    String restaurantId;
    LinearLayout linear_combo;
    TextView tv_scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        connectView();
        restaurantId=getIntent().getStringExtra(MainActivity.ID_EXTRA);
        if (restaurantId!=null) {
            load();
            linear_combo.setVisibility(View.VISIBLE);
        }else {
            linear_combo.setVisibility(View.GONE);
            btn_save.setVisibility(View.VISIBLE);
        }
    }

    private void load() {
        Cursor cursor = helper.getRestaurentbyID(restaurantId);
        name.setText(cursor.getString(1));
        address.setText(cursor.getString(2));
        notes.setText(cursor.getString(4));
        /*if (cursor.getString(3).equals("Take out")){
            types.check(R.id.rd_takeout);
        }else if (cursor.getString(3).equals("Sit down")){
            types.check(R.id.rd_sitdown);
        }else if (cursor.getString(3).equals("Delivery")){
            types.check(R.id.rd_delivery);
        }*/
    }

    private void connectView() {
        helper = new RestaurantHelper(this);
        name = (EditText) findViewById(R.id.edt_name);
        address = (EditText) findViewById(R.id.edt_address);
        notes = (EditText) findViewById(R.id.edt_notes);
        //types = (RadioGroup) findViewById(R.id.rd_typeres);
        linear_combo = (LinearLayout) findViewById(R.id.linear_combo);
        btn_save = (Button) findViewById(R.id.btn_submit);
        tv_scan = (TextView) findViewById(R.id.tv_scan);
    }

    public Items getValuesForm(){
        String type = "null";
        /*if (types.getCheckedRadioButtonId() == R.id.rd_takeout){
            type = "Take out";
        }else if (types.getCheckedRadioButtonId() == R.id.rd_sitdown){
            type = "Sit down";
        }else {
            type = "Delivery";
        }*/
        Items res = new Items(name.getText().toString(), address.getText().toString(),type, notes.getText().toString());
        return res;
    }

    public void update(View view) {

            helper.updateById(getValuesForm(), Long.parseLong(restaurantId));
            Toast.makeText(this, "Đã update", Toast.LENGTH_SHORT).show();
            finish();

    }

    public void add(View view) {
        helper.insertRestaurent(getValuesForm());
        Toast.makeText(this, "Đã thêm mới", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        helper.close();
    }

    public void delete(View view) {
        helper.delete(Long.parseLong(restaurantId));
        Toast.makeText(this, "Đã xóa mới", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void scan(View view) {
        Intent intent=new Intent(DetailForm.this, QrCodeScannerActivity.class);
        startActivityForResult(intent,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2){
            if (resultCode == 8888){
                String text = data.getStringExtra("key");
                tv_scan.setText(text);
            }
        }
    }
}
