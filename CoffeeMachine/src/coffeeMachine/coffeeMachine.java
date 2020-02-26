package coffeeMachine;
import java.util.*;

public class coffeeMachine {
	private DrinkMaker maker = new DrinkMaker();
	private HashMap<String, String> drinkTypes = new HashMap<String, String>(); ;
    private static class PriceHolder {
    	public double value;
    	PriceHolder(double value) {
        	this.value = value;
        }
    }
	private HashMap<String, PriceHolder> drinkPrices= new HashMap<String, PriceHolder>();
	
	public coffeeMachine() {
    	drinkTypes.put("T", "tea");
    	drinkTypes.put("C", "coffee"); 
    	drinkTypes.put("H", "chocolate"); 
    	drinkTypes.put("Th", "extra hot tea"); 
    	drinkTypes.put("Hh", "extra hot chocolate");
    	drinkTypes.put("Ch", "extra hot coffee");
    	drinkTypes.put("0", "orange juice");
    	drinkTypes.put("M", "message");
    	drinkPrices.put("tea", new PriceHolder(0.4));
    	drinkPrices.put("coffee", new PriceHolder(0.6));
    	drinkPrices.put("chocolate", new PriceHolder(0.5)); 
    	drinkPrices.put("extra hot tea", new PriceHolder(0.4)); 
    	drinkPrices.put("extra hot chocolate", new PriceHolder(0.5));
    	drinkPrices.put("extra hot coffee", new PriceHolder(0.6));
    	drinkPrices.put("orange juice", new PriceHolder(0.6));
    }
	
	private void sendCommand(String command, double moneyInserted) {
    	String[] commandArray = command.split(":");
    	if (commandArray.length < 2) {
    		throw new IllegalArgumentException("Command not in correct format");
    	}
        String drinkType = getDrinkType(commandArray[0]);
        if (drinkType == "message") {
        	maker.sendCommand(commandArray[1]);
        } else {
        	double moneyMissing = moneyMissing(drinkType, moneyInserted);
        	if (moneyMissing(drinkType, moneyInserted) >= 0) {
	        	if (commandArray.length < 3) {
	        		throw new IllegalArgumentException("Command not in correct format");
	        	}
	        	maker.sendCommand(drinkType + " with " + getNumberOfSugar(commandArray[1])+ " and " + isThereAStick(commandArray[2]));
        	}
        	else {
        		maker.sendCommand("Missing " + -moneyMissing + " euros");
        	}
        }
    }
    private double moneyMissing(String drinkType, double moneyInserted) {
    	if (drinkPrices.containsKey(drinkType)){
    		return moneyInserted - drinkPrices.get(drinkType).value;
    	}
    	else {
        	throw new IllegalArgumentException("Command not in correct format");
    	}
    }
	
    private String getDrinkType(String type) {
    	if (drinkTypes.containsKey(type)){
    		return drinkTypes.get(type);
    	}
    	else {
        	throw new IllegalArgumentException("Command not in correct format");
        } 
    }
    private String getNumberOfSugar(String numberSugars) {
    	switch (numberSugars)
        { 
        case "": 
    		return "no sugar";
        case "1": 
        	return "1 sugar"; 
        case "2": 
        	return "2 sugars";
        default: 
        	throw new IllegalArgumentException("Command not in correct format");
        }
    }
    private String isThereAStick(String stick) {
    	switch (stick)
        { 
        case "": 
    		return "no stick";
        case "0": 
        	return "a stick"; 
        default: 
        	throw new IllegalArgumentException("Command not in correct format");
        } 
    }
    public static void main(String[] args) {
    	coffeeMachine machine = new coffeeMachine();
        machine.sendCommand("T:1:0", 0.3);
        machine.sendCommand("H:2:0", 1.0);
        machine.sendCommand("H:2:0", 0.1);
        machine.sendCommand("0:2:0", 0.1);
        machine.sendCommand("0:2:0", 0.6);
        machine.sendCommand("Th:1:0", 0.5);
        machine.sendCommand("M:aaa", 0.5);
    }
}