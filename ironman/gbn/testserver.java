import java.util.Scanner;
import java.io.*;
import java.net.*;

class testserver
{
private ServerSocket ss;
private Socket clientsocket;
private DataInputStream in;
private DataOutputStream out;

public testserver(int port)
{
	try{
		ss=new ServerSocket(5000);
		clientsocket=new Socket();
		System.out.println("Waiting for client......");
		clientsocket=ss.accept();
		System.out.println("Socket connected");
		in = new DataInputStream(clientsocket.getInputStream());
		out= new DataOutputStream(clientsocket.getOutputStream());
		Scanner sc= new Scanner(System.in);
		int noofframes,windowsize,choice,ack;
		noofframes=in.read();
		windowsize=in.read();
		choice=in.read();
		System.out.println("You have no of frames="+noofframes+"Window size="+windowsize+"choice is="+choice);

		if(choice==1)
		{
			System.out.println("Starting to send the frames");
			int counter=0,errorframe;
			for(int i=0;i<noofframes;i++)
			{

				if((((counter%windowsize)==0) || counter==noofframes) && counter!=0 )
				{
					System.out.println("Frames equal to one window size successfully sent, waiting for acknowledgement");

					try{Thread.sleep(5000);}catch(Exception e){}
					ack=in.read();
					if(ack==1)
					{
						System.out.println("Acknowlegement received progressing forward");
					}
					else if (ack==-1)	
					{
						System.out.println("Negative acknowledgement received, Resending");
					}
					System.out.println("Sending frame no"+(i+1));
					out.write(i+1);
				}

				else
				{
					System.out.println("Sending frame no:"+(i+1));
					out.write((i+1));
				}

				++counter;
			}
			System.out.println("waiting for final acknowlegement");
			try{Thread.sleep(5000);}catch(Exception e){}
			ack=in.read();
			if(ack==0)
			{
				System.out.println("Positive Acknowlegement received");
			}
			else if (ack==-1)	
			{
				System.out.println("Negative acknowledgement received, Resending");
			}
			System.out.println("Sending Successful,Receiver now has  "+noofframes+"  no of frames");
		}

		else
		{
		      int counter=0,flag=0,errorframe;
               		errorframe=in.read();
               		System.out.println("error will occur in frame:"+errorframe);
               		System.out.println("Starting to send the frames");
               		
			for(int i=0;i<noofframes;i++)
			{
               
               			if((((counter%windowsize)==0 || counter==noofframes))&& counter!=0)
               			{
                               		System.out.println("Frames equal to window size are sent...Waiting for acknowledgement");
                               try{Thread.sleep(5000);}catch(Exception e){}
                               ack=in.read();
                              	if(ack>1)
				{
                              		System.out.println("resending frame from: "+ack+"  till:  "+counter);
                              		 try{Thread.sleep(5000);}catch(Exception e){}
                               
                               		for(int k=ack;k<=counter;k++)
					{
                               			System.out.println("ReSending frame no:"+k);
                               			out.write(k);
                               		}
                               		counter=((counter-ack)+1);
                               		System.out.println("Sending frame no"+(i+1));
                               		out.write(i+1);
                               }
                              
                              else if(ack==0)
					{
                              			System.out.println("Positive Acknowlegement received");
                              			System.out.println("Sending frame no"+(i+1));
                              			out.write(i+1);
                              		}
                               }
               			else
				{
                     			System.out.println("sending frame no:"+(i+1));
                      			out.write(i+1);
               			}
               		counter++;
               }
                           
	}
}
catch(Exception e){}
}

public static void main(String [] args){
	testserver object=new testserver(5000);
}

} 
