//Importing the required classes or library
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

//Declaring the class which must be the same name as the file itself
public class MemberBioData extends JFrame implements ActionListener
{
	//Declaring the variables used in code
	//Use a JTable to bring out the Data stored in the Database.(Assignment)
	JButton closeButton;
	JButton clearButton;
	JButton saveButton;
	JTextField firstNameField;
	JTextField lastNameField;
	JTextField matricNoField;
	JTextField sexField;
	JLabel firstName;
	JLabel lastName;
	JLabel matricNo;
	JLabel sex;
	GridBagLayout gbl;
	GridBagConstraints gbc;
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
        //The Constructor
		public  MemberBioData()
		{
		          super();
				  //Sets the boundary and position of the GUI
				  setBounds(100, 100, 600 ,400);
				  //Method to help the program to be totally cleared from the system memory
				  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				  setTitle("Member Bio Data");
				  
				  //Settting up the Container that contains other component and it's layout
				  JPanel allPanel = new JPanel();
				  allPanel.setLayout(new BorderLayout());
				  
				  //Creating the Grids
				  gbl = new GridBagLayout();
				  gbc = new GridBagConstraints();
				  
				  saveButton = new JButton("Save");
				  saveButton.addActionListener(this);
				  clearButton = new JButton("Clear");
				  clearButton.addActionListener(this);
				  closeButton = new JButton("Close");
				  closeButton.addActionListener(this);
				  				  				  
				  
				  //Panel Container
				  JPanel centerPanel = new JPanel();
				  centerPanel.setLayout(gbl);
				  
				  //Header panel for Image
				  JPanel headerPanel = new JPanel();
				  ImageIcon headerIcon = new ImageIcon("image destination/image name.png");
				  JLabel headerLabel = new JLabel (headerIcon);
				  headerPanel.add(headerLabel);
				  add(headerPanel, BorderLayout.NORTH);
				  
				  //firstName Panel
				  firstName = new JLabel("First Name:    ");
				  gbc.gridx = 0;
				  gbc.gridy = 0;
				  centerPanel.add(firstName,gbc);				  
				  //lastNamePanel				  
				  lastName = new JLabel("Last Name:   ");
				  gbc.gridx = 0;
				  gbc.gridy = 1;
				  centerPanel.add(lastName,gbc);				  
				  //matricNo Panel				  
				  matricNo = new JLabel("Matric No.:  ");
				  gbc.gridx = 0;
				  gbc.gridy = 2;
				  centerPanel.add(matricNo,gbc);
				  //Sex Panel				  
				  sex = new JLabel("Sex");
				  gbc.gridx = 0;
				  gbc.gridy = 3;
				  centerPanel.add(sex,gbc);
				  
				  //Textfield for the First Name
				  firstNameField = new JTextField(15);
				  gbc.gridx = 1;
				  gbc.gridy = 0;
				  centerPanel.add(firstNameField, gbc);
				  allPanel.add(centerPanel, BorderLayout.CENTER);
				  
				  //Textfield for the Last Name
				  lastNameField = new JTextField(15);
				  gbc.gridx = 1;
				  gbc.gridy = 1;
				  centerPanel.add(lastNameField, gbc);
				  allPanel.add(centerPanel, BorderLayout.CENTER);
				  
				  //Textfield for the Matric No.
				  matricNoField = new JTextField(15);
				  gbc.gridx = 1;
				  gbc.gridy = 2;
				  centerPanel.add(matricNoField, gbc);
				  allPanel.add(centerPanel, BorderLayout.CENTER);
				  
				  //Textfield for the Sex
				  sexField = new JTextField(15);
				  gbc.gridx = 1;
				  gbc.gridy = 3;
				  centerPanel.add(sexField, gbc);
				  allPanel.add(centerPanel, BorderLayout.CENTER);
				  
				  JPanel buttonPanel = new JPanel();
				  buttonPanel.add(saveButton);
				  buttonPanel.add(clearButton);
				  buttonPanel.add(closeButton);				    
				  buttonPanel.setBackground(Color.GREEN);
				  allPanel.add(buttonPanel, BorderLayout.SOUTH);
				  				  				  				    
				  add(allPanel);
				  
				  setVisible(true);
				  Color red = new Color(255, 0, 0);
				  getContentPane().setBackground(red);
			
		}
		
		//Clears all the input
		public void clear()
		{
			firstNameField.setText("");
			lastNameField.setText("");
			matricNoField.setText("");
			sexField.setText("");
		}
		
		
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			if(source == closeButton)
			{
				System.exit(0);
			}
			else if(source == clearButton)
			{
				clear();
			}
			else if(source == saveButton)
			{
				saveData();
			}
		}
		
		void saveData()
		{
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			int matricNo = Integer.parseInt(matricNoField.getText());
						
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","root","Solomon@1993");
				System.out.println("Connected to database successfully...");
				
				ps = con.prepareStatement("INSERT INTO memberbiodata(firstName, lastName, matricNo) VALUES(?, ?, ?);");
				ps.setString(1, firstName);
				ps.setString(2, lastName);				
				ps.setInt(3, matricNo);
				ps.executeUpdate();
				con.close();
				clear();
				JOptionPane.showMessageDialog(null,"Member Inserted Succesfully!","Data Entry",JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Done!!");
			}
			catch(SQLException sqle)
			{
				System.out.println("Error: " + sqle);
				JOptionPane.showMessageDialog(null,"Member Inserted Succesfully!","Data Entry",JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception e)
			{
				System.out.println("Exception"+e);
				JOptionPane.showMessageDialog(null,"Member Inserted Succesfully!","Data Entry",JOptionPane.WARNING_MESSAGE);
			}
		}			
				
        //The Main Method of the program
		public  static void main(String args[])
        {
                 MemberBioData  member = new MemberBioData();
				 
		}
		 
		 //Default Layout for Panel is Flow layout
		
} 		