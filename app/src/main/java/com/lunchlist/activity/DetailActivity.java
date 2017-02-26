package com.lunchlist.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.lunchlist.R;
import com.lunchlist.Restaurent;

public class DetailActivity extends AppCompatActivity {
    EditText edt_name;
    EditText edt_address;
    EditText edt_note;
    Restaurent newRestaurent;
    RadioGroup rd_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findId();

    }

    private void findId() {
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_address = (EditText) findViewById(R.id.edt_address);
        edt_note = (EditText) findViewById(R.id.edt_notes);
        rd_type = (RadioGroup) findViewById(R.id.rd_typeres);
    }

    public void submit(View view) {

    }

}
