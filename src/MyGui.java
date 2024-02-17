import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class MyGui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_Time;
	private JTextField textField_NumOfManagers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyGui frame = new MyGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		//texts on gui
		JLabel lblOurStore = new JLabel("Welcome to Gal's and Moshiko's");
		JLabel lblOurStore2 = new JLabel(" Electric Vehicles Store!");
		lblOurStore.setFont(new Font("Gisha", Font.BOLD, 20));
		lblOurStore.setBounds(63, 24, 312, 45);
		contentPane.add(lblOurStore);
		lblOurStore2.setFont(new Font("Gisha", Font.BOLD, 20));
		lblOurStore2.setBounds(90, 50, 312, 45);
		contentPane.add(lblOurStore2);
		
		JLabel lblInspectionTime = new JLabel("Technical test duration:"); 
		lblInspectionTime.setBounds(41, 100, 142, 14);
		contentPane.add(lblInspectionTime);

		JLabel lblNumOfManagers = new JLabel("Number of managers:");
		lblNumOfManagers.setBounds(258, 100, 166, 14);
		contentPane.add(lblNumOfManagers);
		
		
		//text boxes user can type in them
		textField_Time= new JTextField();
		textField_Time.setText("2");
		textField_Time.setBounds(51, 125, 86, 20);
		contentPane.add(textField_Time);
		textField_Time.setColumns(10);

		textField_NumOfManagers= new JTextField();
		textField_NumOfManagers.setText("1");
		textField_NumOfManagers.setBounds(300, 125, 86, 20);
		contentPane.add(textField_NumOfManagers);
		textField_NumOfManagers.setColumns(10);
		
		
		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//technical Inspection Time from GUI
				double technicalInspectionTime;
				try{
					technicalInspectionTime = Double.parseDouble(textField_Time.getText());
				}
				catch(NumberFormatException e){
					technicalInspectionTime=1;
					System.err.println("Error, the technical Inspection Time is not valid, the Time set as 1");
				}
				
				if(technicalInspectionTime <0 || technicalInspectionTime>5) {
					technicalInspectionTime=1;
					System.err.println("Error, the technical Inspection Time is not valid, the Time set as 1");
				}
				
				//num of mangers from GUI
				int numOfManagers;	
				try{
					numOfManagers=Integer.parseInt(textField_NumOfManagers.getText());
				}
				catch(NumberFormatException e){
					numOfManagers=1;
					System.err.println("Error, the number of Managers is not valid, the number set as 1");
				}
				
				try {
					Store store = new Store(numOfManagers, technicalInspectionTime);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			});
			
		btnStart.setBounds(41, 164, 89, 23);
		contentPane.add(btnStart);

		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(247, 164, 89, 23);
		contentPane.add(btnExit);
		
	}
	
}
