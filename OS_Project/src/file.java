
import java.io.*;  
class file{  
  public static void main(String args[]){  
   try{  
	   int a =5;
	   FileOutputStream fout=new FileOutputStream("abc.txt");  
	   for(int i=0; i<a; i++)
	   {
		     String s="Sachin Tendulkar is my favourite player\n";    
		     byte b[]=s.getBytes();  
		     fout.write(b);
		     s = "this is " + a + "\n";
		     b=s.getBytes();  
		     fout.write(b); 
	   }
     fout.close();  
  
     System.out.println("success...");  
    }catch(Exception e){System.out.println(e);}  
  }  
}  