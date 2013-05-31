package com.example.Myproject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class suggestion extends Activity {
	String[] suggestionsare;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestion);
       
        suggestionsare =getResources().getStringArray(R.array.suggested);
        Spinner s1 = (Spinner) findViewById(R.id.suggese);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item, suggestionsare);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new OnItemSelectedListener()
        {
        
        public void onItemSelected(AdapterView<?> arg0,View arg1, int arg2, long arg3)
        {
        int index = arg0.getSelectedItemPosition();
        Toast.makeText(getBaseContext(),"You have selected item : " +suggestionsare[index],
    			Toast.LENGTH_SHORT).show();
       }
      
        public void onNothingSelected(AdapterView<?> arg0) { }
        });
           
        
    }

  
}
