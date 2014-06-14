
import java.applet.*;
import java.awt.*;
public class Draw_direct extends Applet
{
	String[] name;
	int[] k;
	int last_value;
	
   public Draw_direct(String[] name, int[] k,int last_value, int size) {
		// TODO Auto-generated constructor stub
	   this.last_value=last_value;
	   this.name=new String[size];
		this.k=new int[size];
		
	   System.arraycopy( k, 0, this.k, 0, k.length );
		for(int i=0;i<name.length;i++)
		{
			for(int j=0;j<name[i].length();j++)
			{
				this.name[i]=name[i];
			}
		}
   }
   public void paint(Graphics g) 
   {
	  
	   int var_x=0,change_y=0;   
	   for(int i=0;i<name.length;i++)
	   {
		   if(100+var_x>=780)
		   {
			   g.drawString(String.valueOf(k[i]),100+var_x , change_y+140);
			   change_y=change_y+100;
			   var_x=0;
			   
		   }
		   if(name[i]=="-1")
      	   {
      		   break;
      	   }
		   g.setColor( Color.black );
      	   g.drawRect( 100+var_x, change_y+100,40, 25 );//10 indicates start of x_pos, n=total blocks,20=size of each
      	   
      	   if(name[i]=="slack")
      	   {
      	   g.fillRect(100+var_x, change_y+100, 40, 25);
      	   }
      	   else
      	   g.drawString(name[i], 110+var_x, change_y+120);
      	   g.drawString(String.valueOf(k[i]),95+var_x , change_y+140);
      	   var_x=var_x+40;
	   } 
	   		g.drawString(String.valueOf(last_value), 95+var_x, change_y+140);
	   		g.drawRect(700,460,40,25);
	   		g.fillRect(700, 460, 40, 25);
	   		g.drawString(" ->indicates slack time", 750, 480);
   }
}