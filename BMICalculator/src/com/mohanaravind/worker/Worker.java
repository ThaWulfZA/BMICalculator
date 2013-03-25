package com.mohanaravind.worker;

import com.mohanaravind.worker.AnalyzedData.PersonCategory;
import com.mohanaravind.worker.AnalyzedData.Sex;
import com.mohanaravind.worker.AnalyzedData.HeightUnit;
import com.mohanaravind.worker.AnalyzedData.WeightUnit;

public class Worker {
		
	///Calculates the BMI from height and weight of a particular person
	public AnalyzedData calculateBMI(Sex enSex, String strHeight,HeightUnit enHeightUnit, String strWeight, WeightUnit enWeightUnit){
		//Declarations
		AnalyzedData objAnalyzedData = new AnalyzedData();
		Integer intBMI = 1;			
		Integer intDifferentialWeight = 0;
		Double dblConvertedHeight = 1.0;		
		Double dblConvertedWeight = 1.0;
		Double dblWeightConversionFactor = 1.0;
		Double dblHeightConversionFactor = 1.0;

		try{				
			//Convert the height based on the type of measure
			if(enHeightUnit == HeightUnit.cm){
				dblHeightConversionFactor = 0.01;			
			}
			else if(enHeightUnit == HeightUnit.in){
				dblHeightConversionFactor = 0.0254;	
			}
			else if(enHeightUnit == HeightUnit.ft){
				dblHeightConversionFactor = 0.3048;				
			}
						
			if(enWeightUnit == WeightUnit.lbs){
				dblWeightConversionFactor = 0.45359237;
			}
			else if(enWeightUnit == WeightUnit.kg){
				dblWeightConversionFactor = 1.0;
			}
			
			//Convert it into metric unit
			dblConvertedHeight = dblHeightConversionFactor * Double.parseDouble(strHeight);	
			dblConvertedWeight = dblWeightConversionFactor * Double.parseDouble(strWeight);
				
			intBMI = (int) ( dblConvertedWeight / (dblConvertedHeight * dblConvertedHeight));
			
			//Check whether the person is overweight
			if(intBMI > 25){
				intDifferentialWeight = (int) (dblConvertedWeight - (24 * dblConvertedHeight * dblConvertedHeight));
			}
			//If its under weight
			else if(intBMI < 20){
				intDifferentialWeight = (int) ((20 * dblConvertedHeight * dblConvertedHeight) - dblConvertedWeight);				
			}
							
		}
		catch(Exception e){
			intBMI = 0;
		}
		
		//Construct the analyzed data
		objAnalyzedData.setBMI(intBMI);
		objAnalyzedData.setDifferentialWeight(intDifferentialWeight);
		objAnalyzedData.setSex(enSex);
		objAnalyzedData.setPersonCategory(getPersonCategory(intBMI));
		objAnalyzedData.setHeightUnit(enHeightUnit);
		objAnalyzedData.setWeightUnit(enWeightUnit);
				
		return objAnalyzedData;
	}

	private PersonCategory getPersonCategory(int intBMIValue){
		//Declarations
		PersonCategory enPersonCategory = null;
		
		if(intBMIValue < 20){
			enPersonCategory = PersonCategory.Underweight;
		}
		else if(intBMIValue >= 20 && intBMIValue <= 25){
			enPersonCategory = PersonCategory.NormalWeight;
		}
		else if(intBMIValue >= 26 && intBMIValue <= 29){
			enPersonCategory = PersonCategory.SlightlyOverWeight;
		}
		else if(intBMIValue >= 30 && intBMIValue <= 37){
			enPersonCategory = PersonCategory.OverWeight;
		}
		else if(intBMIValue > 37){
			enPersonCategory = PersonCategory.ExtremelyOverweight;
		}
			
		return enPersonCategory;
	}
	
	public String getStatistics(AnalyzedData objAnalyzedData){		
		//Declarations
		StringBuilder sbStatistics = new StringBuilder();
		PersonCategory enPersonCategory = objAnalyzedData.getPersonCategory();
		Sex enSex  = objAnalyzedData.getSex(); 
		String strPercentage = "";
				
		if(enPersonCategory == PersonCategory.NormalWeight){
			if(enSex == Sex.Male)
				strPercentage = "67.1";
			else
				strPercentage = "14.1";
		}
		if(enPersonCategory == PersonCategory.Underweight){
			if(enSex == Sex.Male)
				strPercentage = "16.2";
			else
				strPercentage = "14.1";
		}
		if(enPersonCategory == PersonCategory.SlightlyOverWeight){
			if(enSex == Sex.Male)
				strPercentage = "14";
			else
				strPercentage = "21.5";
		}
		if(enPersonCategory == PersonCategory.OverWeight || 
		   enPersonCategory == PersonCategory.ExtremelyOverweight){
			if(enSex == Sex.Male)
				strPercentage = "2.8";
			else
				strPercentage = "6.8";
		}

		//Build the string
		sbStatistics.append("About ");
		sbStatistics.append(strPercentage);
		sbStatistics.append("% of ");
		sbStatistics.append(enSex.toString());
		sbStatistics.append(" population in the world have your BMI");
		
		return sbStatistics.toString();		
	}
	
	public String getTips(AnalyzedData objAnalyzedData){
		//Declarations
		PersonCategory enPersonCategory = objAnalyzedData.getPersonCategory();
		String strTip = "";
		int intDifferentialWeight = objAnalyzedData.getDifferentialWeight();
		String strWeightUnit = " " + objAnalyzedData.getWeightUnit().toString();
		
		if(objAnalyzedData.getWeightUnit() == WeightUnit.lbs){
			intDifferentialWeight = (int) (intDifferentialWeight * 2.20462262);
		}
		
		if(enPersonCategory == PersonCategory.NormalWeight){
			strTip = "You seem to have a proper physique. Try maintaining your existing diet.";
		}
		else if(enPersonCategory == PersonCategory.Underweight){
			strTip =  "You are having a week body. Try increasing carbohydrates in your diet.";
			strTip = strTip + "Try to put on some " + String.valueOf(intDifferentialWeight) + strWeightUnit + " to your weight.";
		}
		else if(enPersonCategory == PersonCategory.SlightlyOverWeight){
			strTip = "Try some exercise and reduce junk foods. Plan to have a balanced diet.";
			strTip = strTip + "Try shedding some " + String.valueOf(intDifferentialWeight)  + strWeightUnit + " of your weight.";
		}
		else if(enPersonCategory == PersonCategory.OverWeight){
			strTip = "Join a gym immediately and change your diet. Try to walk whenever you get a chance.";
			strTip = strTip + "Try to loose atleast " + String.valueOf(intDifferentialWeight)  + strWeightUnit + " of your weight.";
		}
		else if(enPersonCategory == PersonCategory.ExtremelyOverweight){
			strTip = "Consult a doctor before its too late.";
			strTip = strTip +  String.valueOf(intDifferentialWeight) + strWeightUnit + " of your weight is exposing you to risk.";
		}
			
		return strTip;
	}
	

	


}


