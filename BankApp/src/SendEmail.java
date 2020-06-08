import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SendEmail extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		try 
		{

			String toEmail=request.getParameter("toEmail");
			Credentials c=new Credentials();
			String fromEmail=c.fromEmail;//sender's mail id.
			String pwd=c.pwd;		//sender's mail pwd.  //reciever's mail id.
			double d=Math.random();
			int otp=(int)(d*1000000);
			
			String subject="RESET LINK FOR FORGOT PASSWORD AND OTP"; // mail subject line
			String msg="http://localhost:9090/BankApp/resetFP.html and otp is : " + otp; //mail body
		
			//Creating Session Object
			Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.port", 587);
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator()
			{
				protected PasswordAuthentication getPasswordAuthentication()
				{
					//sender's mail id and pwd is encapsulated inside "SendersCredentials.java"
					return new PasswordAuthentication(fromEmail, pwd);
				}
			});

			
			//Composing the Mail
			MimeMessage mesg = new MimeMessage(session);
			mesg.setFrom(new InternetAddress(fromEmail));
			mesg.addRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
			mesg.setSubject(subject);  
			mesg.setText(msg);  
			
			//Sending the Mail
			Transport.send(mesg);
			
			HttpSession session1=request.getSession(true);
			
			session1.setAttribute("email", toEmail); //ADDING TO SESSSION
			String sotp=Integer.toString(otp); //converting to string
			session1.setAttribute("sotp", sotp);
			
			response.sendRedirect("/BankApp/displayFP.html");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}