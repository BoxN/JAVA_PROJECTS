/**
 * 
 */
package pkg;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Sportello extends JFrame {

	public JTextField[] targets;
	public ArrayList<Cliente> clienti;
	
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
		
		signUp.setText("Sing Up");
		signIn.setText("Sing In");
		contentPane.add(signUp);
		contentPane.add(signIn);
	}
	
	public void signin()
	{
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.300));

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();

		container.add(panel1);
		container.add(panel2);
	        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,600);
		
		targets = new JTextField[8];
		for(JTextField target:targets){
			target = new JTextField();
			target.setColumns(25);
			panel2.add(target);
		}
		JButton singUp = initSingUpButton();
		panel2.add(singUp);
	}
	
	public void initData(){
		clienti = new ArrayList<Cliente>();
		clienti.add(new Cliente(targets));
	}
	
	public JButton initSingUpButton(){
		JButton singUp = new JButton();
		singUp.setText("Sing Up");
		ActionListener btnListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt) {
				
				initData();
				clearFields();
			}
		};
		
		return singUp;
	}
	
	public void clearFields(){
		for(JTextField target:targets){
			target.setText(null);
		}
	}
	
	
	
	
	
	
	
}
