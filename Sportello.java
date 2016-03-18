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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Sportello extends JFrame implements Serializable{

	public JTextField[] regTargets;
	public JLabel[] regLabels;
	public JLabel[] voloLabels;
	public JTextField[] voloData;

	public ArrayList<Cliente> clienti = new ArrayList<Cliente>();
	public static final String[] CLIENT_DATA = {"Nome","Cognome","Nazione di nascita","Città di nascita","Data di nascita","Codice cliente"};
	public static final int NUMBER_OF_CLIENT_DATA = 6;
	public static final String[] FLIGHT_DATA = {"codice volo","aeroporto di partenza","aeroporto di arrivo","data del volo","ora di partenza","ora di arrivo","numero di posti","costo del volo"};
	public static final int NUMBER_OF_FLIGHT_DATA = 8;

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
		setSize(300,300);

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
				boolean found = true;
				for(Cliente i:clienti){
					if(i.data[0].equals(username.getText())){
						contentPane.removeAll();
						loginComplete(i);
						found = false;
						break;
					}
				}
				if(found) 
					JOptionPane.showMessageDialog(null,"Username not found");
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
				JOptionPane.showMessageDialog(null,"DA IMPLEMENTEREEEE");
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
		setSize(450,300);

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

		EmptyBorder labelBorder = new EmptyBorder(1, 70, 3, 5);
		regTargets = new JTextField[NUMBER_OF_CLIENT_DATA];
		regLabels = new JLabel[NUMBER_OF_CLIENT_DATA];
		for(int i = 0; i<NUMBER_OF_CLIENT_DATA; i++){
			regLabels[i] = new JLabel();
			regLabels[i].setText(CLIENT_DATA[i]);
			regLabels[i].setBorder(labelBorder);
			leftPanel.add(regLabels[i]);
			regTargets[i] = new JTextField();
			regTargets[i].setColumns(18);
			rightPanel.add(regTargets[i]);
		}


		JButton signIn = new JButton("Sign In");

		signIn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt) 
			{
				//Quando viene premuto il pulsante SingUp
				//Aggiungo un nuovo cliente nella lista dei iscritti
				//Controllo di completamento
				boolean compiled = true;
				for(JTextField i:regTargets){
					if(i.getText().equals(""))
					{
						compiled = false;
						JOptionPane.showMessageDialog(null,"Cant proceed without some informations!! Please fill all the fields");
						break;
					}
				}
				if(compiled){
					Cliente identity = new Cliente(regTargets);
					clienti.add(identity);
					saveUsers();
					JOptionPane.showMessageDialog(null,identity);
					contentPane2.removeAll();
					loginComplete(identity);
				}
			}
		});

		rightPanel.add(signIn);

		JButton clear = initClearFieldsButton();
		rightPanel.add(clear);

		contentPane2.add(leftPanel);
		contentPane2.add(rightPanel);
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

	public void loginComplete(Cliente cliente) 
	{
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,300);

		JPanel leftPanel = new JPanel(new BorderLayout());
		JPanel rightPanel = new JPanel(new FlowLayout());
		
		DefaultListModel<String> model = new DefaultListModel<>();

		if(cliente.voli != null)
			for(Volo i:(cliente.voli))
				model.addElement(i.data[0]);

		JList<String> list = new JList<>(model);
		JScrollPane pane = new JScrollPane(list);
		JButton removeFlight = new JButton("Remove");
		JButton addFlight = new JButton("Add");

		addFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				newVolo(cliente);
			}
		});

		removeFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeElementAt(list.getSelectedIndex());
			}
		});

		
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				rightPanel.removeAll();
				Volo selected = (Volo) arg0.getSource();
				JLabel printLabel = new JLabel();
				JLabel printFields = new JLabel();
				for(int i = 0; i< NUMBER_OF_FLIGHT_DATA;i++){
					printLabel.setText(FLIGHT_DATA[i]);
					rightPanel.add(printLabel);
					printFields.setText(selected.data[i]);
					rightPanel.add(printLabel);
				}
			}
		});
		leftPanel.add(pane,BorderLayout.NORTH);
		leftPanel.add(addFlight,BorderLayout.WEST);
		leftPanel.add(removeFlight,BorderLayout.EAST);
		contentPane.add(leftPanel, BorderLayout.WEST);
		contentPane.add(rightPanel, BorderLayout.EAST);
	}

	public void newVolo(Cliente cliente){

		Container contentPane2 = getContentPane();
		contentPane2.setLayout(new GridLayout(1,1));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(400,500);

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


		EmptyBorder labelBorder = new EmptyBorder(1, 50, 3, 5);
		voloData= new JTextField[NUMBER_OF_FLIGHT_DATA];
		voloLabels = new JLabel[NUMBER_OF_FLIGHT_DATA];
		for(int i = 0; i<NUMBER_OF_FLIGHT_DATA; i++){
			voloLabels[i] = new JLabel(FLIGHT_DATA[i]);
			voloLabels[i].setBorder(labelBorder);
			leftPanel.add(voloLabels[i]);
			voloData[i] = new JTextField();
			voloData[i].setColumns(10);
			rightPanel.add(voloData[i]);
		}


		JButton save = new JButton("Save Flight");

		save.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt) 
			{
				//Quando viene premuto il pulsante SingUp
				//Aggiungo un nuovo cliente nella lista dei iscritti
				//Controllo di completamento
				boolean compiled = true;
				for(JTextField i:voloData){
					if(i.getText().equals(""))
					{
						compiled = false;
						JOptionPane.showMessageDialog(null,"Cant proceed without some informations!! Please fill all the fields");
						break;
					}
				}
				if(compiled){
					Volo identity = new Volo(voloData);
					cliente.voli.add(identity);
					saveUsers();
					JOptionPane.showMessageDialog(null,identity);
					contentPane2.removeAll();
					loginComplete(cliente);
				}
			}
		});

		rightPanel.add(save);

		JButton clear = initClearFieldsButton();
		rightPanel.add(clear);

		contentPane2.add(leftPanel);
		contentPane2.add(rightPanel);
	}
	public void saveUsers(){
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("database.ping"));
			out.writeObject(clienti);
		}catch(Exception e){}
	}







}
