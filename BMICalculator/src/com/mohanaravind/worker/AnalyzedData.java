/**
 * 
 */
package com.mohanaravind.worker;

import java.io.Serializable;

/**
 * 
 * @author Aravind
 *
 */

public class AnalyzedData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int _intBMI = 0;
	private int _intDifferentialWeight = 0;
	private PersonCategory _enPersonCategory = PersonCategory.NormalWeight;
	private Sex _enSex = Sex.Male;
	private HeightUnit _enHeightUnit = HeightUnit.cm;
	private WeightUnit _enWeightUnit = WeightUnit.kg;
	
	/**
	 * @return the _intBMI
	 */
	public int getBMI() {
		return _intBMI;
	}
	/**
	 * @param _intBMI the _intBMI to set
	 */
	public void setBMI(int intBMI) {
		this._intBMI = intBMI;
	}
	/**
	 * @return the _intDifferentialWeight
	 */
	public int getDifferentialWeight() {
		return _intDifferentialWeight;
	}
	/**
	 * @param _intDifferentialWeight the _intDifferentialWeight to set
	 */
	public void setDifferentialWeight(int intDifferentialWeight) {
		this._intDifferentialWeight = intDifferentialWeight;
	}
	
	
	/**
	 * @return the _enPersonCategory
	 */
	public PersonCategory getPersonCategory() {
		return _enPersonCategory;
	}
	/**
	 * @param _enPersonCategory the _enPersonCategory to set
	 */
	public void setPersonCategory(PersonCategory enPersonCategory) {
		this._enPersonCategory = enPersonCategory;
	}


	/**
	 * @return the _enSex
	 */
	public Sex getSex() {
		return _enSex;
	}
	/**
	 * @param _enSex the _enSex to set
	 */
	public void setSex(Sex enSex) {
		this._enSex = enSex;
	}


	/**
	 * @return the _enHeightUnit
	 */
	public HeightUnit getHeightUnit() {
		return _enHeightUnit;
	}
	/**
	 * @param _enHeightUnit the _enHeightUnit to set
	 */
	public void setHeightUnit(HeightUnit enHeightUnit) {
		this._enHeightUnit = enHeightUnit;
	}


	/**
	 * @return the _enWeightUnit
	 */
	public WeightUnit getWeightUnit() {
		return _enWeightUnit;
	}
	/**
	 * @param _enWeightUnit the _enWeightUnit to set
	 */
	public void setWeightUnit(WeightUnit enWeightUnit) {
		this._enWeightUnit = enWeightUnit;
	}


	public enum PersonCategory{
		Underweight, NormalWeight, SlightlyOverWeight, OverWeight, ExtremelyOverweight 
	}
	
	public enum Sex{
		Male, Female		
	}
	
	public enum HeightUnit{
		cm, in, ft		
	}
	
	public enum WeightUnit{
		kg, lbs		
	}
	
	
}
