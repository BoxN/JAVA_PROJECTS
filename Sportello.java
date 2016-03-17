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
		
		signUp.setText("sign Up");
		signIn.setText("sign In");
		contentPane.add(signUp);
		contentPane.add(signIn);
	}
	
	public void signin()
	{
		Container contentPane = getContentPane();
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		
		JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		targets = new JTextField[8];
		for(JTextField target:targets){
			target = new JTextField();
			target.setColumns(25);
			rightPanel.add(target);
		}
		JButton signUp = initsignUpButton();
		rightPanel.add(signUp);

		contentPane.add(leftPanel);
		contentPane.add(rightPanel);
	}
	
	public void initData(){
		clienti = new ArrayList<Cliente>();
		clienti.add(new Cliente(targets));
	}
	
	public JButton initsignUpButton(){
		JButton signUp = new JButton();
		signUp.setText("Sign Up");
		ActionListener btnListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt) {
				
				initData();
				clearFields();
			}
		};
		
		return signUp;
	}
	
	public void clearFields(){
		for(JTextField target:targets){
			target.setText(null);
		}
	}
	
	
	
	
	
	
	
}
