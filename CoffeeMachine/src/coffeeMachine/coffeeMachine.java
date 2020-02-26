package coffeeMachine;
import java.util.*;

public class coffeeMachine {
	private DrinkMaker maker = new DrinkMaker();
	private HashMap<String, String> drinkTypes = new HashMap<String, String>(); ;
	public coffeeMachine() {
    	drinkTypes.put("T", "tea");
    	drinkTypes.put("C", "coffee"); 
    	drinkTypes.put("H", "chocolate"); 
    	drinkTypes.put("M", "message");    	
    }
	
	private void sendCommand(String command) {
    	String[] commandArray = command.split(":");
    	if (commandArray.length < 2) {
    		throw new IllegalArgumentException("Command not in correct format");
    	}
        String drinkType = getDrinkType(commandArray[0]);
        if (drinkType == "message") {
        	maker.sendCommand(commandArray[1]);
        } else {
        	if (commandArray.length < 3) {
        		throw new IllegalArgumentException("Command not in correct format");
        	}
        	maker.sendCommand(drinkType + " with " + getNumberOfSugar(commandArray[1])+ " and " + isThereAStick(commandArray[2]));
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
        machine.sendCommand("T:1:0");
        machine.sendCommand("H:2:0");
        machine.sendCommand("M:aaa");
    }
}