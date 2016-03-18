package pkg;

import javax.swing.JTextField;

public class Cliente {

	public String[] data = new String[Sportello.NUMBER_OF_CLIENT_DATA];
	
	public Cliente(JTextField[] targets) {
		for(int i = 0; i < Sportello.NUMBER_OF_CLIENT_DATA; i++)
			data[i] = targets[i].getText();
	}
	
	@Override
	public String toString(){
		String thisIsASTring = "";
		for(int i = 0; i < Sportello.NUMBER_OF_CLIENT_DATA; i++){
			thisIsASTring = thisIsASTring + Sportello.CLIENT_DATA[i] + ": " + data[i] + "\r\n";
		}
		return thisIsASTring;
	}
	
	

}
