package jdbctest;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class case1 extends JFrame implements ActionListener {
	public static void main(String[] args) {

	case1 p1 = new case1();
	p1.printing();
	}
public void addC(Component cc,int r,int c,int w,int h)
{
gc.gridx=c;
gc.gridy=r;
gc.gridwidth=w;
gc.gridheight=h;
gc.fill=gc.BOTH;
add(cc,gc);
}
public void actionPerformed(ActionEvent e)
{
if(e.getSource()==b1)
{
sending();
}
}
public void printing()
{

try {
con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","gowthami1234");
st = con.createStatement();
CallableStatement st1 = con.prepareCall("{call new_procedure(?)}");
st1.registerOutParameter(1,Types.INTEGER);
st1.execute();
last = st1.getInt(1);
int safe = last;
while(last!=1)
{
rs   =  st.executeQuery("SELECT * FROM db1.fundmanagement where id = "+ safe);
rs.next();
s1=s1 + rs.getInt(3)+" "+ rs.getString(2)+"\n";
if(i<=4)
jt1.setText(s1);
++i;
--safe;
if(i==5)
{
i=0;
break;
}
}
s1 =" ";
rs   =  st.executeQuery("SELECT * FROM db1.fundmanagement where id = 1" );
rs.next();
t1.setText(Integer.toString(rs.getInt(3)));
balance =rs.getInt(3);
}catch(Exception ex) {}
}
public void sending()
{
try {
con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","gowthami1234");
st = con.createStatement();
st.executeUpdate("update fundmanagement set Amount = (Amount - 1000)  where id = 1" );
++last;
String s1 = "Sent Money on "+d1.format(d2);
st.executeUpdate("insert into fundmanagement (id,Message,Amount) VALUES ('"+last+"','"+s1+"','"+1000+"')");
printing();
}catch(Exception e) {}
}

DateFormat d1 = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
Date d2 = new Date();
GridBagConstraints gc;
JTextField t1;
Connection con;
ResultSet rs;
JButton b1;
JLabel l1;
JTextArea jt1;
Statement st;
int balance=0,i=0,last;
case1()
{
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setLayout(new GridBagLayout());
setVisible(true);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
gc = new GridBagConstraints();
jt1 = new JTextArea(5,5);
jt1.setEditable(false);
t1 = new JTextField(10);
b1 = new JButton("Transfer");
l1 = new JLabel("INTERNET BANKING");
addC(l1 ,1,1,10,1);
addC(new JLabel("PassBook    :"),2,1,1,1);
addC(jt1,2,3,1,1);
addC(new JLabel("Balance: "),15,1,1,1);
addC(t1,15,3,1,1);
addC(b1,16,3,2,1);
b1.addActionListener(this);
}
String s1 = "";
}