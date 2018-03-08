package com.example.jponferrada.cuatroenraya;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class AjustesFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.ajustes);

    }
}
