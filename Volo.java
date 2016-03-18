package pkg;

import javax.swing.JTextField;

public class Volo {

	public String[] data = new String[Sportello.NUMBER_OF_FLIGHT_DATA];

	public Volo(JTextField[] buildData){
		for(int i = 0; i < Sportello.NUMBER_OF_FLIGHT_DATA; i++)
			data[i] = buildData[i].getText();
	}
	@Override
	public String toString(){
		String thisIsASTring = "";
		for(int i = 0; i < Sportello.NUMBER_OF_FLIGHT_DATA; i++){
			thisIsASTring = thisIsASTring + Sportello.FLIGHT_DATA[i] + ": " + data[i] + "\r\n";
		}
		return thisIsASTring;
	}
	
}
