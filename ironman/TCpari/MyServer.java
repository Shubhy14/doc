import java.io.*;
import java.net.*;
public class MyServer
{
	ServerSocket ss;
	Socket s;
	DataInputStream dis;
	DataOutputStream dos;
	float num1,num2,ans;
	String symb;
	public MyServer() throws Exception
	{
		System.out.println("Started...............");
		ss=new ServerSocket(3123);
		s=ss.accept();
		dis=new DataInputStream(s.getInputStream());
		dos=new DataOutputStream(s.getOutputStream());
		while(true)
		{
			num1=dis.readFloat();
			symb=dis.readUTF();
			num2=dis.readFloat();
			System.out.println("Equation Received "+num1+" "+symb+" "+num2);
			System.out.println("Sending the result");
			if(symb.equals("+"))
			{
				ans=num1+num2;
				dos.writeFloat(ans);
			}
			if(symb.equals("-"))
			{
				ans=num1-num2;
				dos.writeFloat(ans);
			}
			if(symb.equals("*"))
			{
				ans=num1*num2;
				dos.writeFloat(ans);
			}
			if(symb.equals("/"))
			{
				ans=num1/num2;
				dos.writeFloat(ans);
			}
		}
	}
	public static void main(String[] args)
	{
		try
		{
			new MyServer();
		}
		catch(Exception e) {System.out.println(e);}
	}
}

	