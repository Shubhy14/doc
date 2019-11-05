import java.util.Scanner;
import java.net.InetAddress;

class Subnet
{

public static void main(String args[]) 
{
Scanner sc= new Scanner(System.in);
System.out.print("Enter the ip address=");
String ip=sc.nextLine();


//----Split the Ip Address-------

String split_ip[] = ip.split("\\.");
		

//----- Converting the Ip Address to binary----

String split_bip[]= new String[4];

String bip = "";

for(int i=0;i<4;i++)
{
split_bip[i]=appendZeroes(Integer.toBinaryString(Integer.parseInt(split_ip[i])));
bip+=split_bip[i];
}
System.out.println("The binary IpAddress is="+bip);

//----- Finding the Subent mask-----

System.out.println("Enter the number of address");
int n=sc.nextInt();

int bits=(int)Math.ceil(Math.log(n)/Math.log(2));
System.out.println("The number of bits required="+bits);

int mask=32-bits;
int total_address=(int)Math.pow(2,bits);
System.out.println("Subnet mask is "+mask);

//---- Finding the first and last address----

//---- First address Calculation--------
int fbip[]=new int[32];

for(int i=0;i<32;i++)
{
//Convert to the character 1,0 to integer 1,0

fbip[i]=(int)bip.charAt(i)-48;

}

for(int i=31;i>31-bits;i--)
{
//Get first address by anding the last bits with 0

fbip[i] &=0;
}

String fip[]={"","","",""};
for(int i=0;i<32;i++)
{
fip[i/8]=new String(fip[i/8]+fbip[i]);
} 
int first_offset=0;
int ipAddr[]=new int[4];  	;
System.out.println("Group 1 \nThe First Address is:");
for(int i=0;i<4;i++)
{
System.out.print(ipAddr[i]=first_offset=Integer.parseInt(fip[i],2));
if(i!=3)
System.out.print(".");
}
System.out.println();


//--- Last address Calculation----
int lbip[]=new int [32];

for(int i=0;i<32;i++)
{
// Convert the character 1,0 to integer 1,0

lbip[i]=(int)bip.charAt(i)-48;
}

for(int i=31;i>31-bits;i--)
{

// Get last address by oring with last bits with 1

lbip[i]|= 1;
}
 String lip[]={"","","",""};
for(int i=0;i<32;i++)
{
lip[i/8]=new String(lip[i/8]+lbip[i]);
}
int ipLast[]=new int[4]; 
System.out.println("The Last Address is:");
for(int i=0;i<4;i++)
{
System.out.print(ipLast[i]=Integer.parseInt(lip[i],2));
if(i!=3)
System.out.print(".");
}
System.out.println();
System.out.println("How many subnets do you want to form?");
int scount=sc.nextInt();
for(int j=1;j<scount;j++)
{
System.out.println(" GROUP "+ (j+1)+" FIRST ADDRESS:");
for(int i=0;i<4;i++)
{
if(i<3)
{
System.out.print(ipAddr[i]+".");
}
else
System.out.println(ipAddr[i]=ipAddr[i]+total_address);
}
System.out.println(" GROUP "+ (j+1)+" LAST ADDRESS:");
for(int i=0;i<4;i++)
{
if(i<3)
{
System.out.print(ipLast[i]+".");
}
else
System.out.println(ipLast[i]=ipLast[i]+total_address);

}
System.out.println();
}
try
{

System.out.println("Enter the Ip address to ping=");
Scanner s=new Scanner(System.in);
String ip_add=s.nextLine();
 InetAddress inet = InetAddress.getByName(ip_add);
if(inet.isReachable(5000))
{
System.out.println("The ip address is reachable"+ip_add);
}
else
{
System.out.println("The ip address is not reachable"+ip_add);
}
}
 catch( Exception e)
{
System.out.println("Exception:"+e.getMessage());
}
}

static String appendZeroes(String s)
{
String temp= new  String("00000000");
return temp.substring(s.length())+ s;
}
}

/* *************OUTPUT*************

C:\Users\psh22>java Subnet.java
Enter the ip address=192.168.56.80
The binary IpAddress is=11000000101010000011100001010000
Enter the number of address
12
The number of bits required=4
Subnet mask is 28
Group 1
The First Address is:
192.168.56.80
The Last Address is:
192.168.56.95
How many subnets do you want to form?
12
 GROUP 2 FIRST ADDRESS:
192.168.56.96
 GROUP 2 LAST ADDRESS:
192.168.56.111

 GROUP 3 FIRST ADDRESS:
192.168.56.112
 GROUP 3 LAST ADDRESS:
192.168.56.127

 GROUP 4 FIRST ADDRESS:
192.168.56.128
 GROUP 4 LAST ADDRESS:
192.168.56.143

 GROUP 5 FIRST ADDRESS:
192.168.56.144
 GROUP 5 LAST ADDRESS:
192.168.56.159

 GROUP 6 FIRST ADDRESS:
192.168.56.160
 GROUP 6 LAST ADDRESS:
192.168.56.175

 GROUP 7 FIRST ADDRESS:
192.168.56.176
 GROUP 7 LAST ADDRESS:
192.168.56.191

 GROUP 8 FIRST ADDRESS:
192.168.56.192
 GROUP 8 LAST ADDRESS:
192.168.56.207

 GROUP 9 FIRST ADDRESS:
192.168.56.208
 GROUP 9 LAST ADDRESS:
192.168.56.223

 GROUP 10 FIRST ADDRESS:
192.168.56.224
 GROUP 10 LAST ADDRESS:
192.168.56.239

 GROUP 11 FIRST ADDRESS:
192.168.56.240
 GROUP 11 LAST ADDRESS:
192.168.56.255

 GROUP 12 FIRST ADDRESS:
192.168.56.256
 GROUP 12 LAST ADDRESS:
192.168.56.271

Enter the Ip address to ping=
127.0.0.1
The ip address is reachable127.0.0.1

C:\Users\psh22>
*/