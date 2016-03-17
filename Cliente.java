package pkg;

import javax.swing.JTextField;

public class Cliente {

	
	public String nome;
	public String cognome;
	public String nazioneDiNascita;
	public String citt‡DiNascita;
	public String dataDiNascita;
	public String codiceCliente;
	
	public Cliente(JTextField[] targets) {
		nome = targets[0].getText();
		cognome = targets[1].getText();
		nazioneDiNascita = targets[2].getText();
		citt‡DiNascita = targets[3].getText();
		dataDiNascita = targets[4].getText();
		codiceCliente = targets[5].getText();
	}
	
	

}
