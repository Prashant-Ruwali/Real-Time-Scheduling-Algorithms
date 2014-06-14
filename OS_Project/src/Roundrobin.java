import java.io.FileOutputStream;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Roundrobin  
{ 
	public static void main(String [] args)
	{
		// Declaring the variables
		int temp, no_of_process, sum_of_all, Process_execution, flag=1;
		String s ;
		byte b[];
		
		try
		{ 
			FileOutputStream fout=new FileOutputStream("Log_roundrobin.txt",true);
			System.out.println();
			System.out.println("We are using Round Robin real time scheduling algorithm"); 
			System.out.println();
			
			// Get the no of process to execute from the user
			System.out.println("Enter the number of Process you want to Execute");
			Scanner Input_from_user = new Scanner(System.in);
			no_of_process = Input_from_user.nextInt();
			
			s = "\nWe are using Round Robin real time scheduling algorithm\n";
		    b=s.getBytes();  
		    fout.write(b);
		    
		    s = "Enter the number of Process you want to Execute\n";
		    b=s.getBytes();  
		    fout.write(b);
		    
			if(no_of_process == 0)
			{
				System.out.println("Are you kidding me :@");
				
				s = "Number of process in execution are : " + no_of_process + "\n";
			    b=s.getBytes();  
			    fout.write(b); 
				s = "\nAre you kidding me :@\n";
			    b=s.getBytes();  
			    fout.write(b);
			}
			else
			{
			int[] period_for_lcm = new int[no_of_process];
			int[] b_time = new int[no_of_process];
			Process process_detail[] =new Process[no_of_process];
			
			for(int i=0; i<no_of_process; i++)
			{ // Get data input in all the processes
				process_detail[i]= new Process();
				System.out.println("Enter name of Process ");	
				process_detail[i].name=Input_from_user.nextInt();
				if(process_detail[i].name < 0 || process_detail[i].name > 65535)
				{
					System.out.println("Please Enter a valid integer value");
					
					s = "Name of process is : " + process_detail[i].name + "\n";
					b=s.getBytes();  
				    fout.write(b);
				    s = "Please Enter a valid integer value \n";
					b=s.getBytes();  
				    fout.write(b);
				    s = "-----------------------------------------------------------------------------------------------------\n";
					b=s.getBytes();  
					fout.write(b);
					break;
				}
				
				s = "Name of process is : " + process_detail[i].name + "\n";
				b=s.getBytes();  
			    fout.write(b); 
			    
				System.out.println("Enter burst time of Process " + process_detail[i].name );
				process_detail[i].burst_t=Input_from_user.nextInt();
				s = "Burst time of process is : " + process_detail[i].burst_t + "\n";
				b=s.getBytes();  
			    fout.write(b); 
			    
				System.out.println("Enter period of Process " + process_detail[i].name);
				process_detail[i].period=Input_from_user.nextInt();
				s = "Period of process is : " + process_detail[i].period + "\n";
				b=s.getBytes();  
			    fout.write(b);
			    
				if(process_detail[i].burst_t > process_detail[i].period)
				{
					System.out.println("Burst time should be less than Period");
					s = "Burst time should be less than Period\n";
					b=s.getBytes();  
				    fout.write(b);
					break;
				}
				process_detail[i].no_of_times=0;
				process_detail[i].check_m=0;
				process_detail[i].check_l=process_detail[i].period;
			}
			
			int time_quanta;
			
			System.out.println("Enter the time quanta");
			time_quanta = Input_from_user.nextInt();
			
			System.out.println("Select the type of output you want ?");
			System.out.println("1. Step wise");
			System.out.println("2. Complete");
			int type_of_output = Input_from_user.nextInt();
			
			for(int i=0; i<no_of_process; i++)								
			{ // Save the period in an array to calculate LCM
				period_for_lcm[i]=process_detail[i].period;
			}
			
			// Calculate LCM using the LCM function
			lcm lcm_calculator = new lcm();
			int lcm_total = lcm_calculator.lcm(period_for_lcm);					
			
			
			// Calculate the no of times of times a process may execute
			for(int i=0; i<no_of_process; i++)
			{
				process_detail[i].no_of_times = lcm_total/process_detail[i].period;
			}
			
			// calculate the sum of all to check if this process is allowed to execute or not
			sum_of_all = 0;
			for(int i=0; i<no_of_process; i++)
			{
				sum_of_all = sum_of_all + process_detail[i].no_of_times * process_detail[i].burst_t;
				if(sum_of_all > lcm_total)
				{
					System.out.println("All the Processes will not be executed as they requires more slots");
					flag=0;
					
					s = "Total time required for processing is : "+ sum_of_all  + "\n";
					b=s.getBytes();  
				    fout.write(b);
				    s = "Total time available for processing is : " + lcm_total + "\n";
					b=s.getBytes();  
				    fout.write(b);
					s = "All the Processes will not be executed as they requires more slots\n";
					b=s.getBytes();  
				    fout.write(b);
				    
					break;
				}
			}
			
			
			// Sort the array on the basis of no of times
			for(int k=0; k<no_of_process; k++)
			{
				for(int j=k+1; j<no_of_process; j++)
				{
						if(process_detail[k].no_of_times > process_detail[j].no_of_times)
						{
		            				temp = process_detail[k].no_of_times;
		            				process_detail[k].no_of_times = process_detail[j].no_of_times;
		            				process_detail[j].no_of_times = temp;
		            				temp = process_detail[k].name;
		            				process_detail[k].name = process_detail[j].name;
		            				process_detail[j].name = temp;
		            				temp = process_detail[k].period;
		            				process_detail[k].period = process_detail[j].period;
		            				process_detail[j].period = temp;
		            				temp = process_detail[k].burst_t;
		            				process_detail[k].burst_t = process_detail[j].burst_t;
		            				process_detail[j].burst_t = temp;
		            				temp = process_detail[k].check_l;
		            				process_detail[k].check_l = process_detail[j].check_l;
		            				process_detail[j].check_l = temp;
		            				temp = process_detail[k].check_m;
		            				process_detail[k].check_m = process_detail[j].check_m;
		            				process_detail[j].check_m = temp;
						}				
				}
			}
			
			
			for(int j=0; j<=no_of_process-1; j++)
			{
				b_time[j]=process_detail[j].burst_t;
			}
			
			String [] output_name = new String[lcm_total];
			int [] output_time = new int[lcm_total];
			int last_value = 0;
			if(flag == 1)
			{
				System.out.println(" ");
				int c_in=0, c_out=0, array_count=0;
				Process_execution = no_of_process-1;
				int i=0;
				while(i < lcm_total-1)
				{
					if(i >= process_detail[Process_execution].check_m && process_detail[Process_execution].no_of_times != 0 && process_detail[Process_execution].burst_t >=0 && func_check(i,Process_execution, no_of_process, process_detail))
						{
							c_in++;
							System.out.print(process_detail[Process_execution].name + "executes.");
							System.out.print(" It executes from " + i);
							output_name[array_count]= 'p' + String.valueOf(process_detail[Process_execution].name);
							output_time[array_count] = i;
							array_count++;
							
							s = "Process " + process_detail[Process_execution].name + "executes from " + i;
							b=s.getBytes();  
							fout.write(b);

							if(process_detail[Process_execution].burst_t > time_quanta)
							{
								i=i+time_quanta;
								System.out.println("to " + i);
								
								s = "to " + i + "\n";
								b=s.getBytes();  
								fout.write(b);
								
								process_detail[Process_execution].burst_t = process_detail[Process_execution].burst_t - time_quanta;
							}
							else
							{
								i=i+process_detail[Process_execution].burst_t;
								System.out.println("to " + i);
								
								s = "to " + i + "\n";
								b=s.getBytes();  
								fout.write(b);
								
								process_detail[Process_execution].burst_t = 0;
							}
							if(process_detail[Process_execution].burst_t == 0)
							{
								process_detail[Process_execution].check_m = process_detail[Process_execution].check_m + process_detail[Process_execution].period;
								process_detail[Process_execution].check_l = process_detail[Process_execution].check_l + process_detail[Process_execution].period;
								process_detail[Process_execution].burst_t = b_time[Process_execution];
								process_detail[Process_execution].no_of_times--;
								Process_execution--;
							}
							last_value = i;
						}
					else
						Process_execution--;
		
					c_out++;
					if(Math.abs(c_out-c_in) >= no_of_process)
					{
						System.out.print(" The slack period is from " + i);
						output_name[array_count] = "slack";
						output_time[array_count] = i;
						array_count++;
						
						s = "No Process executes from " + i;
						b=s.getBytes();  
					    fout.write(b);
					    
						i++;
						
						s = "to " + i + "\n";
						b=s.getBytes();  
					    fout.write(b);
					    
						System.out.println("to " + i);
						c_out=0;
						c_in=0;
						last_value = i;
					}
					
					if(i < lcm_total)
					{ // Repeat the process till we reach LCM , but we need to check this condition after we are at first process
						if(Process_execution < 0)
						{
							Process_execution=no_of_process-1;
							c_out=0;
							c_in=0;
						}
					}
						
				}
				
				s = "-----------------------------------------------------------------------------------------------------\n";
				b=s.getBytes();  
				fout.write(b);
				
				
				for (int p = array_count; p < lcm_total ; p++)
				{
					output_name[p] = "-1";
					output_time[p] = 0;
				}
				
				
				JFrame frame = new JFrame("Round Robin scheduling");
			    frame.setSize(900, 520);
			    frame.setLocation(100, 100);
			    JLabel jb1 = new JLabel("GANTT CHART");
			   
			    jb1.setFont(jb1.getFont().deriveFont(20.0f));
			    jb1.setHorizontalAlignment( SwingConstants.CENTER );
			    jb1.setBounds(250,20, 300, 50);
			    frame.add(jb1);
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    switch(type_of_output)
			    {
			    case 1:
			    	frame.add(new Draw(output_name,output_time,last_value,lcm_total,frame));
			    	break;
			    case 2:
			    	frame.add(new Draw_direct(output_name,output_time,last_value,lcm_total));
			    	break;
			    default:
			    	System.out.println("Enter a valid Option");
			    }
			 
			 frame.setVisible(true);
			
			}
			}
		}
	catch(Exception e)
	{
		//System.out.println(e);
	}  
	
	}

	private static boolean func_check(int time, int Process_no, int no_of_process, Process process_detail[]) 
	{
		// check deadline for all and if i should execute
					// then return 1 
					// else 
		for(int i=0; i<no_of_process; i++)
		{
			if(i==Process_no)
				continue;
			else
			{
				if(process_detail[i].check_l == time + process_detail[i].burst_t)
					return false;
			}
		}
			
		return true;
	}
}
