
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.security.sasl.SaslException;

import oracle.jdbc.driver.OracleDriver;

public class Model 
{
	public ArrayList al1=new ArrayList();
	public ArrayList al2=new ArrayList();
	
	private String name;
	private String accno;
	private String pwd;
	private String balance;
	private String oldpwd;
	private String newpwd;
	private String raccno;
	private String email;
	private String amt;
	private String npwd;

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	
	public String getOldpwd() {
		return oldpwd;
	}
	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	
	
	public String getRaccno() {
		return raccno;
	}
	public void setRaccno(String raccno) {
		this.raccno = raccno;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNpwd() {
		return npwd;
	}
	public void setNpwd(String npwd) {
		this.npwd = npwd;
	}
	
	
	//Driver Connection
	
	Model()
	{
		try
		{
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("Driver loaded");
			con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "system", "system");
			System.out.println("Connected");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	int register()
	{
		try 
		{  
			String s="INSERT INTO BANK VALUES (?,?,?,?,?)";
			pstmt=con.prepareStatement(s);
			pstmt.setString(1, name);
			pstmt.setString(2, accno);
			pstmt.setString(3, pwd);
			pstmt.setString(4, balance);
			pstmt.setString(5, email);
			int x=pstmt.executeUpdate();
			return x;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;	
	}
	
	//Login _Page
	
	 int login() 
	 {
		try
		{
			String s = "SELECT * FROM BANK WHERE ACCNO=? AND PWD=?";
			pstmt = con.prepareStatement(s);
			pstmt.setString(1, accno);
			pstmt.setString(2, pwd);
			
			res = pstmt.executeQuery();
			
			if(res.next()==true)
			{
				name = res.getString("Name");
				return 1;
			}
			else
			{
				return 0;
			}
			
	    } 
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return 0;
	 }
	
	 
	 //check_balance
	 
	 void checkBalance() 
	 {
		 try
			{
				String s = "SELECT * FROM BANK WHERE ACCNO=?";
				pstmt = con.prepareStatement(s);
				pstmt.setString(1, accno);
				
				res = pstmt.executeQuery();
				
				if(res.next()==true)
				{
					balance=res.getString("BALANCE");//Database name BALANCE
					
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
	}
	 
	 //Change_Password
	 
	 int changePwd() 
	 {
		
		 try
			{
				String s = "SELECT * FROM BANK WHERE ACCNO=? AND PWD=?";
				pstmt = con.prepareStatement(s);
				pstmt.setString(1, accno);
				pstmt.setString(2, oldpwd);
				
				res = pstmt.executeQuery();
				
				if(res.next()==true)
				{
					String s1 = "UPDATE BANK SET PWD=? WHERE ACCNO=?";
					pstmt = con.prepareStatement(s1);
					pstmt.setString(1, newpwd);
					pstmt.setString(2, accno);
					
					int x = pstmt.executeUpdate();
					return x;
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		 
		return 0;
	 }
	 
	//Transfer_Money
	
	 int transferMoney() 
	 {
					 try
					 {
						 String s1="SELECT * FROM BANK WHERE ACCNO=?";
						 pstmt = con.prepareStatement(s1);
						 pstmt.setString(1, raccno);
						 res = pstmt.executeQuery();
						 
						 if(res.next()==true)
						 {
								 String s2 = "UPDATE BANK SET BALANCE = BALANCE+? WHERE ACCNO=?";
								 pstmt = con.prepareStatement(s2);
								 pstmt.setString(1, amt);
								 pstmt.setString(2, raccno);
								 int x = pstmt.executeUpdate();
								 
								 if(x==1)
								 {
									 String s3 = "UPDATE BANK SET BALANCE = BALANCE-? WHERE ACCNO=?";
									 pstmt = con.prepareStatement(s3);
									 pstmt.setString(1, amt);
									 pstmt.setString(2, accno);
									 x = pstmt.executeUpdate();
									
									 if(x==1) 
									 {
											String s4="INSERT INTO GETSTATEMENT VALUES(?,?,?)";
											pstmt=con.prepareStatement(s4);
											pstmt.setString(1, accno);
											pstmt.setString(2, raccno);
											pstmt.setString(3, amt);
											x = pstmt.executeUpdate();
											return x;
									}
									else
									{
										return 0;
										 
									}
									
								}
							    else
								{
									 return 0;
								}
								 
						}
						 
					 }
			 catch(Exception e)
			 {
					 e.printStackTrace();
			 }
				return 0;
		}
	 
	 
		
	 //Apply loan
	 
	 int loan()
	 {
		 try
		 {
			 String s="SELECT *FROM BANK WHERE ACCNO=?";
			 pstmt=con.prepareStatement(s);
			 pstmt.setString(1, accno);
			 res=pstmt.executeQuery();
			 
			 if(res.next()==true)
			 {
				 email=res.getString("EMAIL");
				 
			 }
		 }
		 catch (Exception e) 
		 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
		 
		 
		return 0;
	 }
	 
	 //transcation_Details
	 
	 void getstatement() 
	 {
		 try
		 {

				String s="SELECT * FROM GETSTATEMENT WHERE SACCNO=?";
				pstmt=con.prepareStatement(s);
				pstmt.setString(1, accno);
				res=pstmt.executeQuery();
				
				while(res.next()==true)
				{
					al1.add(res.getString("RACCNO"));
					al2.add(res.getString("AMOUNT"));
					System.out.println(al1);
					System.out.println(al2);
					
				}
		 }
		 
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	}
	 
	 //forgot_password
	 
	 int resetPassword() 
	 {
		 try 
		 {
				String s="UPDATE BANK SET PWD=? WHERE EMAIL=?";
				pstmt=con.prepareStatement(s);
				pstmt.setString(1, npwd);
				pstmt.setString(2, email);
				int x=pstmt.executeUpdate();
				
				if(x==1) 
				{
					return 1;
				}
				else 
				{
					return 0;
				}
			}
			catch(Exception e) 
		    {
				e.printStackTrace();
			}
		
		return 0;
	 }
	
	
		
	 
	 
	 
}
		
