import java.util.Scanner;
import java.io.*;
import java.net.*;

class testclient{
private Socket socket;
private DataInputStream in;
private DataOutputStream out;

public testclient(String address,int port){
                   
try{ 
	socket=new Socket(address,port);
        System.out.println("Socket Created");
        in = new DataInputStream(socket.getInputStream());
        out= new DataOutputStream(socket.getOutputStream());
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the No of frames to be transmitted from server");
        int noofframes;
        noofframes=sc.nextInt();
        System.out.println("Enter the window size of the transmitter");
        int windowsize;
        windowsize=sc.nextInt();
        System.out.println("1.For simple transmission");
        System.out.println("2.For error transmission");
        int choice;
        choice=sc.nextInt();
        out.write(noofframes);
        out.write(windowsize);
        out.write(choice);
                    	
	if(choice==1)
	{
		System.out.println("starting to receive frames");
              	int counter=0;
              	int frameno,errorframe;
			
		for(int i=0;i<noofframes;i++)
		{
			if((((counter%windowsize)==0) || counter==noofframes) && counter!=0 )
			{
				try{Thread.sleep(4500);}catch(Exception e){}
				System.out.println("Sending Positive Acknowlegement");
				out.write(0);
				frameno=in.read();
				System.out.println("got frame no"+frameno);
			}

			else
			{
				frameno=in.read();
				System.out.println("got the frame no:"+frameno);
			}
			counter++;
		}

		try
		{
			System.out.println("Sending acknowledgement.......");
			Thread.sleep(4500);
		}
		catch(Exception e){}
		out.write(1);
		System.out.println("Receiving Successful");
  	}
 
	else
	{
	        int flag=0,frameno,counter=0,errorframe;
                System.out.println("Enter the frame where you want error to happen");
                errorframe=sc.nextInt();
                out.write(errorframe);
 
                for(int i=0;i<noofframes;i++)
		{
               
               		if((((counter%windowsize)==0 || counter==noofframes))&& counter!=0)
               		{
		             try{Thread.sleep(4500);}catch(Exception e){}

                             if(errorframe<counter && flag==0)
			     {
                               //first time error
                               System.out.println("Sending negative acknowlegement for frame no="+errorframe);
                               flag++;
                               out.write(errorframe);
                               
                               System.out.println("Discarding frame no:"+(errorframe));
                               
                               for(int k=errorframe;k<=counter;k++)
				{
                              		frameno=in.read();
                               		System.out.println("got frame no:"+frameno);                               
                                }
                               counter=((counter-errorframe)+1);
                               frameno=in.read();
                               System.out.println("got frame no:"+frameno);
                              }
                               
                              else
				{ 
					System.out.println("Sending Positive Acknowlegement");
                               		out.write(0);
                                }
                	}
               
               		else
			{
                      		frameno=in.read();
                      		System.out.println("got frame no:"+frameno);
               		}
               counter++;
               }
    }                 
  }
 catch(Exception e){}
          
}

public static void main(String [] args){
	testclient object=new testclient("127.0.0.1",5000);
}

}
