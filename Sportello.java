package pkg;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Sportello extends JFrame implements Serializable{

	public JTextField[] regTargets;
	public JLabel[] regLabels;
	public ArrayList<Cliente> clienti = new ArrayList<Cliente>();;
	public static final String[] CLIENT_DATA = {"Nome","Cognome","Nazione di nascita","Città di nascita","Data di nascita","Codice cliente"};
	public static final int NUMBER_OF_CLIENT_DATA = 6;
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run(){
				try{
					Sportello frame = new Sportello();
					frame.setVisible(true);
				}catch(Exception e){}
			}
		});
	} 
	
	public Sportello()
	{
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(2,2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("database.ping"));
			clienti = (ArrayList<Cliente>)in.readObject();
		}catch(Exception e){}
		
		JPanel topLeft = new JPanel(new GridLayout(2,1));
		JPanel topRight = new JPanel(new FlowLayout());
		JPanel botLeft = new JPanel(new FlowLayout());
		JPanel botRight = new JPanel(new FlowLayout());
		
		JPanel topLefttop = new JPanel();
		JPanel topLeftbot = new JPanel();
		
		JLabel userLabel = new JLabel("Username");
		JTextField username = new JTextField();
		username.setColumns(18);
		JButton signUp = new JButton("sign Up");
		
		signUp.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt) 
			{
				if(username.getText().equals(""))
					JOptionPane.showMessageDialog(null,"Username not found");
				else{
					contentPane.removeAll();
					loginComplete();
				}
			}
		});
		
		JButton signIn = new JButton("sign In");
		
		signIn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt) 
			{
				contentPane.removeAll();
				signin();
			}
		});
		
		JButton help = new JButton("Help");
		
		help.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt) 
			{
				JOptionPane.showMessageDialog(null,"u get ur hand !");
			}
		});
		
		topLeft.add(topLefttop.add(userLabel));
		topLeft.add(topLeftbot.add(username));
		contentPane.add(topLeft);
		contentPane.add(topRight.add(signUp));
		contentPane.add(botLeft.add(help));
		contentPane.add(botRight.add(signIn));
	}
	
	public void signin()
	{
		Container contentPane2 = getContentPane();
		contentPane2.setLayout(new GridLayout(1,1));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(600,500);
		
		addWindowListener(new WindowAdapter() 
		{
            @Override
            public void windowClosing(WindowEvent e) {
                if(JOptionPane.showConfirmDialog(null, "Are you sure ?") == JOptionPane.OK_OPTION){
                	setVisible(false);
                	saveUsers();
                }
            }
        });
		
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
			regTargets[i].setColumns(22);
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
				//Quando viene premuto il pulsante SingUp
				//Aggiungo un nuovo cliente nella lista dei iscritti
				//Controllo di completamento
				boolean compeled = true;
				for(JTextField i:regTargets){
					if(i.getText().equals(""))
					{
						compeled = false;
						JOptionPane.showMessageDialog(null,"Cant proceed without some informations!! Please fill all the fields");
						break;
					}
				}
				if(compeled){
					Cliente identity = new Cliente(regTargets);
					JOptionPane.showMessageDialog(null,identity);
					clienti.add(identity);
					saveUsers();
					loginComplete();
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
	/*
	public void loginComplete(){
		
		Container contentPane2 = getContentPane();
		contentPane2.setLayout(new GridLayout(1,1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,500);
		
		JPanel leftPanel = new JPanel(new FlowLayout());
		JPanel rightPanel = new JPanel(new FlowLayout());
		
		DefaultListModel model = new DefaultListModel();
		JList userList = new JList();
		for(int i = 0; i < clienti.size();i++){
			model.add(i,clienti.get(i).data[0]);
		}
		userList.add(model);
		contentPane2.add(leftPanel);
		contentPane2.add(rightPanel);
	}*/
	
	public void loginComplete() 
	{
		Container contentPane2 = getContentPane();
		contentPane2.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,500);
		
		JPanel leftPanel = new JPanel(new BorderLayout());
		JPanel rightPanel = new JPanel(new FlowLayout());
		
		DefaultListModel<String> model = new DefaultListModel<>();
		
		for (int i = 0; i < clienti.size(); i++)
			model.addElement(clienti.get(i).data[0]);
		
		JList<String> list = new JList<>(model);
		JScrollPane pane = new JScrollPane(list);
		JButton removeButton = new JButton("Remove");

		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (model.getSize() > 0)
					model.removeElementAt(0);
			}
		});

		leftPanel.add(pane,BorderLayout.NORTH);
		leftPanel.add(removeButton,BorderLayout.SOUTH);
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.EAST);
	}
	
	public void saveUsers(){
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("database.ping"));
			out.writeObject(clienti);
		}catch(Exception e){}
	}
	
	
	
	
	
	
	
}
