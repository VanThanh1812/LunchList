package com.lunchlist.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.lunchlist.R;

public class EditPreference extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencescreen);
    }
}
