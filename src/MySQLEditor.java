import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


public class MySQLEditor {
	
	public static void main(String s[]) throws IOException
	{
		Connection con=null;
		Statement st=null;
		String driverClassname="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="khome";
		
		String pass="oracle";
		String sql;
		try{
			Class.forName(driverClassname);
			con=DriverManager.getConnection(url, user, pass);
			st=con.createStatement();
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Editor started connected to database");
		while(true)
		{
			System.out.println("\nSQL>");
			String query=br.readLine();
			if(query.equals("exit"))
			{
				return;
			}
			boolean flag=st.execute(query);
			if(flag)
			{
				ResultSet rs=st.getResultSet();
				ResultSetMetaData rsmd=rs.getMetaData();
				for(int i=1;i<=rsmd.getColumnCount();i++)
				{
					System.out.print(rsmd.getColumnName(i)+"\t");
				}
				System.out.println();
				System.out.println("-----------------------------------------------------"
						+ "-------------------------------");
				while(rs.next())
				{
					for(int i=1;i<=rsmd.getColumnCount();i++)
					{
						System.out.print(rs.getString(i)+"\t");
					}
				}
				
			}
			else
			{
				int count=st.getUpdateCount();
				System.out.println(count+" no(s) of records has been effected by this query");
				
			}
			
		}
	
		}
		catch(Exception e)
		{
			
			System.out.println("unable to connect to database please try later");
		}
		}

}
