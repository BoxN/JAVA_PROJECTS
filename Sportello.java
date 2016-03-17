/**
 * 
 */
package pkg;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Sportello extends JFrame {

	public JTextField[] regTargets;
	public JLabel[] regLabels;
	public ArrayList<Cliente> clienti = new ArrayList<Cliente>();;
	public static final String[] CLIENT_DATA = {"nome","cognome","nazioneDiNascita","citt‡DiNascita","dataDiNascita","codiceCliente"};
	public static final int NUMBER_OF_CLIENT_DATA = 6;
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run(){
				try{
					Sportello frame = new Sportello();
					frame.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				};
			}
		});

	} 
	
	public Sportello()
	{
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		
		JButton signUp = new JButton();
		
		signUp.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt) 
			{
				
			}
		});
		
		JButton signIn = new JButton();
		
		signIn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt) 
			{
				contentPane.removeAll();
				signin();
			}
		});
		
		signUp.setText("sign Up");
		signIn.setText("sign In");
		contentPane.add(signUp);
		contentPane.add(signIn);
	}
	
	public void signin()
	{
		Container contentPane2 = getContentPane();
		contentPane2.setLayout(new GridLayout(1,1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,500);
		
		JPanel leftPanel = new JPanel(new FlowLayout());
		JPanel rightPanel = new JPanel(new FlowLayout());
		
		EmptyBorder labelBorder = new EmptyBorder(1, 200, 3, 5);
		regTargets = new JTextField[NUMBER_OF_CLIENT_DATA];
		regLabels = new JLabel[NUMBER_OF_CLIENT_DATA];
		for(int i = 0; i<NUMBER_OF_CLIENT_DATA; i++){
			regLabels[i] = new JLabel();
			regLabels[i].setText(CLIENT_DATA[i]);
			regLabels[i].setBorder(labelBorder);
			leftPanel.add(regLabels[i]);
			regTargets[i] = new JTextField();
			regTargets[i].setColumns(20);
			rightPanel.add(regTargets[i]);
		}
		
		JButton signUp = initsignUpButton();
		rightPanel.add(signUp);

		JButton clear = initClearFieldsButton();
		rightPanel.add(clear);
		contentPane2.add(leftPanel);
		contentPane2.add(rightPanel);
		
	}
	
	public JButton initsignUpButton(){
		
		JButton btn = new JButton();
		btn.setText("Sign Up");
		
		btn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt) 
			{
				boolean compiled = true;
				//Quando viene premuto il pulsante SingUp
				//Aggiungo un nuovo cliente nella lista dei iscritti
				//Controllo di completamento
				for(JTextField i:regTargets){
					if(i.getText() == null)		///PROBLEMA <-- il controllo sui campi vuoti nn funge
					{
						compiled = false;
						JOptionPane.showMessageDialog(null,"Cant proceed without some informations!! Please fill all the fields");
						break;
					}
				}
				if(compiled){
					clienti.add(new Cliente(regTargets));
					JOptionPane.showMessageDialog(null,"Nome: "+ clienti.get(0));
				}
			}
		});
		return btn;
	}
	
	public JButton initClearFieldsButton(){
		
		JButton btn = new JButton();
		btn.setText("Clear");
		
		btn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt) 
			{
				for(JTextField target:regTargets){
					target.setText(null);
				}
			}
		});
		return btn;
	}
	
	
	
	
	
	
	
}
