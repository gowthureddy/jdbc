package jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbctest {

public static void main(String[] args)
{
// TODO Auto-generated method stub
Connection conn=null;
Statement stmt=null;
ResultSet rs=null;
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","gowthami1234");

String sql="select sum(amt) from data";
stmt=conn.createStatement();
rs=stmt.executeQuery(sql);
int totalBal=0;
if(rs.next())
{
	totalBal=rs.getInt(1);
  System.out.println("Total balance--->"+totalBal);	
}

sql="select * from data order by date desc limit 5;";
stmt=conn.createStatement();
rs=stmt.executeQuery(sql);
int total=0;
while(rs.next())
{
System.out.println(rs.getInt("amt"));
total=total+rs.getInt("amt");
}

System.out.println("Last 5 transactions amount-->"+total);

System.out.println("Total account balance-->"+(totalBal-total));
}
catch(Exception e)
{
System.out.println(e);
}
finally
{
	try {
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
}
