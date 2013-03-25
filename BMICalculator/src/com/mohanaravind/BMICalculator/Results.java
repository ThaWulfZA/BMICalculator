package com.mohanaravind.BMICalculator;

import com.mohanaravind.worker.AnalyzedData;
import com.mohanaravind.worker.Worker;
import com.mohanaravind.worker.AnalyzedData.PersonCategory;
import com.mohanaravind.worker.AnalyzedData.Sex;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Results extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);        
        
        //Display the required informations
        displayInformations();        
    }
        
    private void displayInformations(){

        //Declarations
    	AnalyzedData objAnalyzedData = null;
    	TextView lblBMI = (TextView)findViewById(R.id.lblBMI);
    	TextView lblBodyType = (TextView)findViewById(R.id.lblBodyType);
    	ImageView imgBodyType = (ImageView)findViewById(R.id.imgBodyType);
    	TextView lblFact = (TextView)findViewById(R.id.lblFact);
    	TextView lblTips = (TextView)findViewById(R.id.lblTips);
    	PersonCategory enPersonCategory = null;    	    
    	Worker objWorker = new Worker();
    	int intTextColor = 0;
    	int intImageBody = 0;
    	       	
    	//Get the data        
        objAnalyzedData = (AnalyzedData)this.getIntent().getExtras().get("AnalyzedData"); 
        
        //Get the type of person
        enPersonCategory = objAnalyzedData.getPersonCategory();
        
        //Set the BMI value        
        lblBMI.setText(String.valueOf(objAnalyzedData.getBMI()));
        lblBodyType.setText(enPersonCategory.toString());
   
        //Set the color based on discrepancy from normal      
        if(enPersonCategory == PersonCategory.NormalWeight){
        	intTextColor  = Color.GREEN;        	
        	if(objAnalyzedData.getSex() == Sex.Male){
        		intImageBody = R.drawable.male_normalweight;
        	}
        	else{
        		intImageBody = R.drawable.female_normalweight;
        	}
        }
        else if(enPersonCategory == PersonCategory.SlightlyOverWeight){
        	intTextColor = Color.YELLOW;        	
        	if(objAnalyzedData.getSex() == Sex.Male){
        		intImageBody = R.drawable.male_slightlyoverweight;
        	}
        	else{
        		intImageBody = R.drawable.female_slightlyoverweight;
        	}
        }
        else if(enPersonCategory == PersonCategory.Underweight){
        	intTextColor = Color.YELLOW;       	
        	if(objAnalyzedData.getSex() == Sex.Male){
        		intImageBody = R.drawable.male_underweight;
        	}
        	else{
        		intImageBody = R.drawable.female_underweight;
        	}
        }
        else if(enPersonCategory == PersonCategory.OverWeight){
        	intTextColor = Color.RED; 
        	if(objAnalyzedData.getSex() == Sex.Male){
        		intImageBody = R.drawable.male_overweight;
        	}
        	else{
        		intImageBody = R.drawable.female_overweight;
        	}
        }
        else if(enPersonCategory == PersonCategory.ExtremelyOverweight){
        	intTextColor = Color.RED;    
        	if(objAnalyzedData.getSex() == Sex.Male){
        		intImageBody = R.drawable.male_extremelyoverweight;
        	}
        	else{
        		intImageBody = R.drawable.female_extremelyoverweight;
        	}
        }
        
        //Set the colors
        lblBMI.setTextColor(intTextColor);      
        lblBodyType.setTextColor(intTextColor);          
        imgBodyType.setImageResource(intImageBody);
        
        lblFact.setText(objWorker.getStatistics(objAnalyzedData));
        lblTips.setText(objWorker.getTips(objAnalyzedData));
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
    
    public void btnBack_clicked(View view){
    	this.finish();    	
    }
}
