import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Draw extends Applet implements ActionListener
{

	/**
	 * 
	 */
	int counter,t=0;
	String[] name;
	int[] k;
	int last_value;
	JButton jb;
	int finish=0;
	JLabel execution;
   public Draw(String[] name, int[] k,int last_value, int size,JFrame frame) 
   {
		// TODO Auto-generated constructor stub
	   	this.last_value=last_value;
	   	this.name=new String[size];
		this.k=new int[size];
		
		jb = new JButton("NEXT");
		jb.setBounds(750,350, 100, 30);
		frame.add(jb);
		System.arraycopy( k, 0, this.k, 0, k.length );
		//System.out.println("name length"+name.length);
		//System.out.println("last length"+last_value);
		for(int i=0;i<=last_value;i++)
		{
				this.name[i]=name[i];
		}
		jb.addActionListener(this);
		
   }
   
   public void paint(Graphics g) 
   {
	  
	   int var_x=0,change_y=0,flag=0;
	   counter=t++;
	   
	   for(int i=0;i<=counter;i++)
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
      	   {
      	   g.drawString(name[i], 110+var_x, change_y+120);
      	   }
      	   g.drawString(String.valueOf(k[i]),95+var_x , change_y+140);
      	   var_x=var_x+40;
      	   
      	 if((counter+1==0 && k[counter+1]!=0)||(counter+1!=0 && k[counter+1]!=0))
      	 {
      	   g.drawString(String.valueOf(k[i+1]),95+var_x , change_y+140);
      	   flag=1;
      	 }
      	   
	   } 
	   		if(counter+1!=0 && k[counter+1]==0)
	   		{
	   		g.drawString(String.valueOf(last_value), 95+var_x, change_y+140);
	   		}
	   	   
	   		g.drawRect(700,460,40,25);
	   		g.fillRect(700, 460, 40, 25);
	   		g.drawString(" -> indicates slack time", 750, 480);
	   		
   
   }

   
@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
	Object o =arg0.getSource();
	
	if(o.equals(jb))
	{
		//frame.add(new draw(output_name,output_time,last_value,lcm_total,frame));

		if((counter+1==0 && k[counter]!=0)||(counter+1!=0 && k[counter+1]!=0))
		repaint();			
		else
		{
			//repaint();
			JOptionPane.showMessageDialog(null, "Completed Schedule");
			finish=1;
		}
		
	}
	
}

}