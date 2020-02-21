package com.epam.exceptions_logging;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class App 
{	
	private static final Logger LOGGER = LogManager.getLogger(App.class);
    public static void main( String[] args )
    {
    	Scanner scanner = new Scanner(System.in);
        LOGGER.info(String.format("Calculate: %n\t1.Simple Interest or Compound Interest%n\t2.House Construction Cost Estimation"));
        LOGGER.info("Choose an option (1/2)");
        int operationChoice = scanner.nextInt();
        if(operationChoice == 1) {
        	getInterestParameters(scanner);
        } else if(operationChoice == 2) {
        	getHouseDetails(scanner);
        } else {
        	scanner.close();
        	return;
        }   
        scanner.close();
    }
    private static void getHouseDetails(Scanner scanner) {
    	try {
    		String materialType;
    		boolean fullyAutomated = false;
    		LOGGER.info("Total Area for the house");
        	float totalArea = scanner.nextFloat();
        	LOGGER.info("Select material type : \n\t1.standard\n\t2.above standard\n\t3.high standard\n\t4.high standard and fully automated)");
        	int materialChoice = scanner.nextInt();
        	if(materialChoice == 1)
        		materialType = "standard";
        	else if(materialChoice == 2)
        		materialType = "aboveStandard";
        	else if(materialChoice == 3) {
        		materialType = "highStandard";
        		LOGGER.info("Do you want a fully automated house (y/n)");
        		char automationChoice = scanner.next().charAt(0);
        		if(automationChoice == 'Y' || automationChoice == 'y') {
        			fullyAutomated = true;
        		}
        	}
        	else
        		return;
        	calculateHouseConstructionCost( totalArea,  materialType, fullyAutomated);
    	} catch(Exception exception) {
    		LOGGER.error(exception);
    	} finally {
    		scanner.close();
    	}
    	
	}
	private static void calculateHouseConstructionCost(float totalArea, String materialType, boolean fullyAutomated) {
    	House house = new House(totalArea, materialType, fullyAutomated);
    	LOGGER.info("Total Price of the house would be {}" , house.getHousePrice());
	}
    
	private static void getInterestParameters(Scanner scanner) {
		try {
			int compoundingTime = 0;
			LOGGER.info("What do you want to calculate?  \n\t1.Simple Interest\n\t2.Compound Interest");
			int interestChoice = scanner.nextInt();
			
			LOGGER.info("Input principal amount");
			float principalAmount = scanner.nextFloat();
			
			LOGGER.info("Input interest rate");
			float interestRate = scanner.nextFloat();
			
			LOGGER.info("Input total time (in years)");
			int totalTime = scanner.nextInt();
			
			if(interestChoice == 2) {
				LOGGER.info("Enter compounding time");
				compoundingTime = scanner.nextInt();
			}	
			calculateInterest(principalAmount, interestRate, totalTime, compoundingTime);
		} catch(Exception exception) {
			LOGGER.error(exception);
		}	finally {
			scanner.close();
		}
	}
	private static void calculateInterest(float principal,float interestRate,int totalTime,int compoundingTime) {
    	Interest interest;
    	float interestAmount;
		if(compoundingTime == 0) {
    		interest = new Interest(interestRate, principal, totalTime); 
    		interestAmount = interest.calculateSimpleInterest();
    	} else {
    		interest = new Interest(interestRate, principal, totalTime, compoundingTime);
    		interestAmount = interest.calculateCompoundInterest();
    	}
		LOGGER.info("Interest amount is {}" , interestAmount);
    }
}
