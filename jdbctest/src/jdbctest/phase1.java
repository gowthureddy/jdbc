package jdbctest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class phase1 extends JFrame implements ActionListener {
	Connection con;
	Statement st;
	ResultSet rs;
	JButton b1;
	JLabel l1;
	GridBagConstraints gc;
	JTextField t1,t2,t3,t4,t5,t6;
	int amount=0;
	phase1()
	{
		setSize(600,600);
		setLocation(450,125);
		setLayout(new GridBagLayout());
		setVisible(true);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ee)
			{dispose();}
			});
		gc = new GridBagConstraints();
		t1 = new JTextField(30);
		t2 = new JTextField(30);
		t3 = new JTextField(30);
		t4 = new JTextField(30);
		t5 = new JTextField(30);
		t6= new JTextField(10);
		b1 = new JButton("Fund Transfer");
		l1 = new JLabel("  ---------------- ONLINE BANKING ----------------");
		addC(l1 ,1,1,10,1);
		addC(new JLabel("Last Transaction    :"),2,1,1,1);
		addC(t1,2,3,1,1);
		addC(new JLabel("  "),3,3,1,1);
		addC(new JLabel("Last Transaction - 1 :"),4,1,1,1);
		addC(t2,4,3,1,1);
		addC(new JLabel("  "),5,3,1,1);
		addC(new JLabel("Last Transaction - 2 :"),6,1,1,1);
		addC(t3,6,3,1,1);
		addC(new JLabel("  "),7,3,1,1);
		addC(new JLabel("Last Transaction - 3 :"),8,1,1,1);
		addC(t4,8,3,1,1);
		addC(new JLabel("  "),9,3,1,1);
		addC(new JLabel("Last Transaction - 4 :"),10,1,1,1);
		addC(t5,10,3,1,1);
		addC(new JLabel("  "),11,3,1,1);
		//addC(new JLabel("  "),12,3,1,1);
		addC(new JLabel("Balance: "),12,1,1,1);
		addC(t6,12,3,1,1);
		addC(new JLabel("  "),13,3,1,1);
		addC(b1,14,3,2,1);
		b1.addActionListener(this);
		}//constructor ends
	int i=0,last;
	public void method()
	{
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","gowthami1234");
			st = con.createStatement();
			while(i!=6)
			{

				rs   =  st.executeQuery("SELECT * FROM db1.fundmanagement order by id desc limit 5 ");
				int var = rs.getInt(1);
				System.out.println(var);
				rs.next();
				String s1 = "Rs." + rs.getInt(3)+"   "+ rs.getString(2);
				if(i==5)
					t5.setText(s1);
				if(i==4)
					t4.setText(s1);
				if(i==3)
					t3.setText(s1);
				if(i==2)
					t2.setText(s1);
				if(i==0)
					t1.setText(s1);
				s1 = "";
				++i;
				}
			rs   =  st.executeQuery("SELECT * FROM db1.fundmanagement where id = 1" );
			rs.next();
			t6.setText("Rs."+Integer.toString(rs.getInt(1)));
			amount =rs.getInt(1);
			}catch(Exception ex) {System.out.println("Error in Loading Details");}

	}
	//JOptionPane.showMessageDialog(null,"DONE!");
	public void transfer()
	{
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","gowthami1234");
			st = con.createStatement();
			st.executeUpdate("update fundmanagement set amount = (amount - 1000)  where id = 1" );
			t6.setText(Integer.toString(rs.getInt(1)));
			System.out.println("update done");
			++last;
			int current_amount = rs.getInt(1)-1000;
			String message = "Transferred to XXXX on "+df.format(dateobj);
			st.executeUpdate("insert into fundmanagement (id,message,amount) VALUES ('"+last+"','"+message+"','"+1000+"')");
			System.out.println("Insert done");
			//INSERT INTO `db1`.`fundmanagement` (`id`, `Message`, `Amount`) VALUES ('8', 'fd', '876');
			method();
			}catch(Exception ey) { System.out.println("Error in Updating Information");}
		}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			transfer();
			}
		
	}//action ends
	public void addC(Component cc,int r,int c,int w,int h)
	{
		gc.gridx=c;
		gc.gridy=r;
		gc.gridwidth=w;
		gc.gridheight=h;
		gc.fill=gc.BOTH;
		add(cc,gc);
		}//component ends
	
	public static void main(String[] args) {
		phase1 p1 = new phase1();
		p1.method();
		}//main fn ends here
	
}//entire main class 
