package com.epam.exceptions_logging;
import java.util.HashMap;

public class House {
	
	private float totalArea;
	private String materialType;
	private boolean fullyAutomated;
	HashMap<String, Integer> costPerSquareFoot = new HashMap<>();
	
	public House() {
		costPerSquareFoot.put("standard", 1200);
		costPerSquareFoot.put("aboveStandard", 1500);
		costPerSquareFoot.put("highStandard", 1800);
	}
	
	public House(float totalArea, String materialType, boolean fullyAutomated) {
		this();
		this.totalArea = totalArea;
		this.materialType = materialType;
		this.fullyAutomated = fullyAutomated;
	}
	
	public float getHousePrice() {
		float housePrice;
		housePrice = this.totalArea * this.costPerSquareFoot.get(materialType) + (this.materialType.equals("highStandard") && this.fullyAutomated ? 700 : 0);
		return housePrice;
	}
	
}
