package com.mohanaravind.BMICalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class About extends Activity {
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);        
    }
	
    public void btnBack_clicked(View view){
    	this.finish();    	
    }
	
}
