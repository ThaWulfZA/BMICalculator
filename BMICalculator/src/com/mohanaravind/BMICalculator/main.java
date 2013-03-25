package com.mohanaravind.BMICalculator;



import com.mohanaravind.worker.AnalyzedData;
import com.mohanaravind.worker.Worker;
import com.mohanaravind.worker.AnalyzedData.HeightUnit;
import com.mohanaravind.worker.AnalyzedData.Sex;
import com.mohanaravind.worker.AnalyzedData.WeightUnit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsSpinner;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;


public class main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Spinner spnSex = (Spinner)findViewById(R.id.spnsex);
        final ImageView imgSex = (ImageView)findViewById(R.id.imgSex);
        

        
        //Set the change event handler
        spnSex.setOnItemSelectedListener (new OnItemSelectedListener() {
            //@Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
            	if(parentView.getItemAtPosition(position).toString().equals("Male")) 
            		imgSex.setImageResource(R.drawable.male);
            	else
            		imgSex.setImageResource(R.drawable.female);            	
            }

            //@Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
    	if(item.getItemId() == R.id.about){
    		//Display the about page
    		startActivity(new Intent(this, About.class));
    	}    	
    	
    	return true;
	}

	//Gets triggered when calculate button is clicked
    public void btnCalculate_clicked(View view){
    	//Declarations    	
    	AnalyzedData objAnalyzedData = null;
    	EditText txtHeight = (EditText) findViewById(R.id.txtHeight);
    	EditText txtWeight = (EditText) findViewById(R.id.txtweight);
    	AbsSpinner spnWeight = (AbsSpinner) findViewById(R.id.spnweight); 
    	AbsSpinner spnHeight = (AbsSpinner) findViewById(R.id.spnheight); 
    	AbsSpinner spnSex = (AbsSpinner) findViewById(R.id.spnsex);
    	Worker objWorker = null;
    	
    	try{
    		
    		//Check whether the inputs are valid
    		if(IsInputsValid(txtHeight, txtWeight)){
    		
	    		//Construct the worker object
		    	objWorker = new Worker();
		   		  	   
		    	HeightUnit enHeightUnit = HeightUnit.valueOf(spnHeight.getSelectedItem().toString());
		    	WeightUnit enWeightUnit =  WeightUnit.valueOf(spnWeight.getSelectedItem().toString());
		    	Sex enSex = Sex.valueOf(spnSex.getSelectedItem().toString());
		    	
		    	
		    	String strHeight = txtHeight.getText().toString();  
		    	String strWeight = txtWeight.getText().toString();  
		    		    		    		    	    
		    	objAnalyzedData = objWorker.calculateBMI(enSex, strHeight, enHeightUnit, strWeight, enWeightUnit);
	    	
		    	//Display the results
		    	Intent inResults = new Intent(this, Results.class);
		    	
		    	//Pass the data
		    	inResults.putExtra("AnalyzedData", objAnalyzedData);
		    			  		    
		    	objWorker = null;
		    	objAnalyzedData = null;
		    	
		    	main.this.startActivity(inResults);
    		}	    	
    	}
    	catch(Exception ex){
    		objAnalyzedData = null;
    	}
    	finally{
    		objWorker = null;
    		objAnalyzedData = null;
    	}
    	   	    	
    	//Toast.makeText(this, strBMI, Toast.LENGTH_SHORT).show();
    	
    }
    
    private boolean IsInputsValid(final EditText txtHeight, final EditText txtWeight){
    	//Declarations
    	boolean blnIsValid = true;
    	String strMessage = "";
    	    	
    	
    	if(txtHeight.getText().toString().length() == 0){
    		strMessage = "Please enter your height";
    		txtHeight.requestFocus();  
    		blnIsValid = false;
    	}
    	else if(txtWeight.getText().length() == 0){
    		strMessage = "Please enter your weight";
    		txtWeight.requestFocus();  
    		blnIsValid = false;
    	}
    	else if(txtHeight.getText().toString().equals("0") || txtWeight.getText().toString().equals("0") ||
    			txtHeight.getText().toString().equals(".")){
    		strMessage = "This means you dont exist. Stop playing around!";
    		blnIsValid = false;
    	}
    	    	   
    	if(!blnIsValid){
	    	//Display the error message
	    	AlertDialog ad = new AlertDialog.Builder(this).create();
	    	ad.setCancelable(false); // This blocks the 'BACK' button
	    	ad.setMessage(strMessage);
	    	ad.setButton3("OK", new DialogInterface.OnClickListener() {
	    	    public void onClick(DialogInterface dialog, int which) {
	    	        dialog.dismiss();                    
	    	    }
	    	});
	    	ad.show(); 
    	}
    	
    	return blnIsValid;
    }
        
    
}