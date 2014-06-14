
import java.io.FileOutputStream;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class forground_background
{

	public static void main(String [] args)
	{
		// Declaring the variables
		int no_of_process_foreground, no_of_process_background, sum_of_all, flag=1,back_count=0;
		int temp;
		String s ;
		byte b[];
		byte b1[];
		byte b2[];
		
		try
		{ 
			FileOutputStream fout=new FileOutputStream("Log_foreground_background.txt",true);
			System.out.println("We are using Foreground Background real time scheduling algorithm");     
			// Get the no of process to execute from the user
			System.out.println("Enter the number of Forground Processes you want to Execute");
			Scanner Input_from_user = new Scanner(System.in);
			no_of_process_foreground=Input_from_user.nextInt();
			
			s = "\nWe are using Foreground Background real time scheduling algorithm\n";
		    b=s.getBytes();  
		    fout.write(b);
		    
		    s = "Enter the number of Process you want to Execute\n";
		    b=s.getBytes();  
		    fout.write(b);
		    
			if(no_of_process_foreground == 0)
			{
				System.out.println("Are you kidding me :@");
				s = "Number of process in execution are : " + no_of_process_foreground + "\n";
			    b=s.getBytes();  
			    fout.write(b); 
				s = "\nAre you kidding me :@\n";
			    b=s.getBytes();  
			    fout.write(b);
			}
			else
			{
			int[] period_for_lcm= new int[no_of_process_foreground];
			Process process_detail_foreground[] =new Process[no_of_process_foreground];
			
			for(int i=0; i<no_of_process_foreground; i++)
			{ // Get data input in all the processes
				process_detail_foreground[i]= new Process();
				System.out.println("Enter name of Process ");	
				process_detail_foreground[i].name=Input_from_user.nextInt();
				if(process_detail_foreground[i].name < 0 || process_detail_foreground[i].name > 65535)
				{
					System.out.println("Please Enter a valid integer value");
					
					s = "Name of process is : " + process_detail_foreground[i].name + "\n";
					b=s.getBytes();  
				    fout.write(b);
				    s = "Please Enter a valid integer value \n";
					b=s.getBytes();  
				    fout.write(b);
				    
					break;
				}
				
				s = "Name of process is : " + process_detail_foreground[i].name + "\n";
				b=s.getBytes();  
			    fout.write(b); 
			    
				System.out.println("Enter burst time of " + process_detail_foreground[i].name + " Process ");
				process_detail_foreground[i].burst_t=Input_from_user.nextInt();
				s = "Burst time of process is : " + process_detail_foreground[i].burst_t + "\n";
				b=s.getBytes();  
			    fout.write(b); 
			    
				System.out.println("Enter period of " + process_detail_foreground[i].name + " Process ");
				process_detail_foreground[i].period=Input_from_user.nextInt();
				s = "Period of process is : " + process_detail_foreground[i].period + "\n";
				b=s.getBytes();  
			    fout.write(b);
			    
				if(process_detail_foreground[i].burst_t > process_detail_foreground[i].period)
				{
					System.out.println("Burst time should be less than Period");
					System.out.println("Burst time should be less than Period");
					s = "Burst time should be less than Period\n";
					b=s.getBytes();  
				    fout.write(b);
				    s = "-----------------------------------------------------------------------------------------------------\n";
					b=s.getBytes();  
					fout.write(b);
					break;
				}
				process_detail_foreground[i].no_of_times=0;
				process_detail_foreground[i].check_m=0;
				process_detail_foreground[i].check_l=process_detail_foreground[i].period;
			}
			
			System.out.println("Enter the number of Background Processes you want to Execute");
			no_of_process_background=Input_from_user.nextInt();
			Process process_detail_background[] =new Process[no_of_process_background];
			
			for(int i=0; i<no_of_process_background; i++)
			{ // Get data input in all the processes
				process_detail_background[i]= new Process();
				System.out.println("Enter name of  Process ");	
				process_detail_background[i].name=Input_from_user.nextInt();
				if(process_detail_background[i].name < 0 || process_detail_background[i].name > 65535)
				{
					System.out.println("Please Enter a valid integer value");
					
					s = "Name of process is : " + process_detail_background[i].name + "\n";
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
				
				s = "Name of process is : " + process_detail_background[i].name + "\n";
				b=s.getBytes();  
			    fout.write(b); 
			    
				System.out.println("Enter burst time of " + process_detail_background[i].name + " Process ");
				process_detail_background[i].burst_t=Input_from_user.nextInt();
				s = "Burst time of process is : " + process_detail_background[i].burst_t + "\n";
				b=s.getBytes();  
			    fout.write(b);
			    
				process_detail_background[i].period=0;
				process_detail_background[i].no_of_times=0;
				process_detail_background[i].check_m=0;
				process_detail_background[i].check_l=0;
			}
				
			for(int i=0; i<no_of_process_foreground; i++)								
			{ // Save the period in an array to calculate LCM
				period_for_lcm[i]=process_detail_foreground[i].period;
			}
				
			// Calculate LCM using the LCM function
			lcm lcm_calculator = new lcm();
			int lcm_total = lcm_calculator.lcm(period_for_lcm);					
				
			// Calculate the no of times of times a process may execute
			for(int i=0; i<no_of_process_foreground; i++)
			{
				process_detail_foreground[i].no_of_times = lcm_total/process_detail_foreground[i].period;
			}
				
			// calculate the sum of all to check if this process is allowed to execute or not
			sum_of_all = 0;
			for(int i=0; i<no_of_process_foreground; i++)
			{
				sum_of_all = sum_of_all + process_detail_foreground[i].no_of_times * process_detail_foreground[i].burst_t;
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
				    s = "-----------------------------------------------------------------------------------------------------\n";
					b=s.getBytes();  
					fout.write(b);
					break;
				}
			}
				
				
			// Sort the array on the basis of no of times
			for(int k=0; k<no_of_process_foreground; k++)
			{
				for(int j=k+1; j<no_of_process_foreground; j++)
				{
						if(process_detail_foreground[k].no_of_times > process_detail_foreground[j].no_of_times)
						{
			            		temp = process_detail_foreground[k].no_of_times;
			            		process_detail_foreground[k].no_of_times = process_detail_foreground[j].no_of_times;
			            		process_detail_foreground[j].no_of_times = temp;
			            		temp = process_detail_foreground[k].name;
			           			process_detail_foreground[k].name = process_detail_foreground[j].name;
			           			process_detail_foreground[j].name = temp;
			           			temp = process_detail_foreground[k].period;
			           			process_detail_foreground[k].period = process_detail_foreground[j].period;
			           			process_detail_foreground[j].period = temp;
			           			temp = process_detail_foreground[k].check_l;
			           			process_detail_foreground[k].check_l = process_detail_foreground[j].check_l;
			           			process_detail_foreground[j].check_l = temp;
			           			temp = process_detail_foreground[k].burst_t;
			           			process_detail_foreground[k].burst_t = process_detail_foreground[j].burst_t;
			           			process_detail_foreground[j].burst_t = temp;
						}				
				}
			}
			
				
			for(int J=0; J<=no_of_process_foreground-1; J++)
			{
				for(int i=0; i<=no_of_process_foreground-1; i++)
				{
					if(i==J)
						continue;
					else if(((2*process_detail_foreground[J].burst_t) + process_detail_foreground[i].burst_t) > (2 * process_detail_foreground[J].period))
					{
						System.out.println("Processes will be executed but not correctly as the time constraint is not satisfied");
						flag=0;
						
						s = "Total time required for processing is : "+ ((2*process_detail_foreground[J].burst_t) + process_detail_foreground[J].burst_t)  + "\n";
						b=s.getBytes();  
					    fout.write(b);
					    s = "Total time available for processing is : " + (2 * process_detail_foreground[J].period)+ "\n";
						b=s.getBytes();  
					    fout.write(b);
						s = "Processes will not be executed correctly as the time constraint is not satisfied\n";
						b=s.getBytes();  
					    fout.write(b);
					    s = "-----------------------------------------------------------------------------------------------------\n";
						b=s.getBytes();  
						fout.write(b);
						break;
					}
				}	
					
			}
			
			System.out.println("Select the type of output you want ?");
			System.out.println("1. Step wise");
			System.out.println("2. Complete");
			int type_of_output = Input_from_user.nextInt();
				
				String [] output_name = new String[lcm_total];
				int [] output_time = new int[lcm_total];
				int last_value = 0;
				if(flag == 1)
				{
					System.out.println(" ");
					int i=0, c_in=0, c_out=0, array_count=0;
					for(int j=no_of_process_foreground-1; j>=0; j--)
					{
						if(i >= process_detail_foreground[j].check_m && process_detail_foreground[j].no_of_times != 0 && func_check((i+process_detail_foreground[j].burst_t),j , no_of_process_foreground, process_detail_foreground))
							{
								c_in++;
								System.out.print("f" + process_detail_foreground[j].name + "executes.");
								System.out.print(" It executes from " + i);
								output_name[array_count]= 'f' + String.valueOf(process_detail_foreground[j].name);
								output_time[array_count] = i;
								array_count++;
								
								s = "Foreground Process " + process_detail_foreground[j].name + "executes from " + i;
								b1=s.getBytes();  
								fout.write(b1);
								
								i=i+process_detail_foreground[j].burst_t;

								s = "to " + i + "\n";
								b=s.getBytes();  
								fout.write(b);
								
								System.out.println("to " + i);
								process_detail_foreground[j].check_m = process_detail_foreground[j].check_m + process_detail_foreground[j].period;
								process_detail_foreground[j].check_l = process_detail_foreground[j].check_l + process_detail_foreground[j].period;
								process_detail_foreground[j].no_of_times--;
								last_value = i;
							}
						c_out++;
						if(Math.abs(c_out-c_in) >= no_of_process_foreground)
						{
							c_out=0;
							c_in=0;
							if(back_count == no_of_process_background-1 && process_detail_background[back_count].burst_t == 0)
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
							    
								last_value = i;
								System.out.println("to " + i);
							}
							else
							{
								if(process_detail_background[back_count].burst_t == 0)
										back_count++;
								
								System.out.print("b" + process_detail_background[back_count].name + "executes.");
								System.out.print(" It executes from " + i);
								output_name[array_count] = 'b' + String.valueOf(process_detail_background[back_count].name);
								output_time[array_count] = i;
								array_count++;
								
								s = "Background Process " + process_detail_background[j].name + "executes from " + i;
								b2=s.getBytes();  
								fout.write(b2);
								
								i=i+1;

								s = "to " + i + "\n";
								b=s.getBytes();  
								fout.write(b);
								
								System.out.println("to " + i);
								process_detail_background[back_count].burst_t--;
								//count = process_detail_background[back_count].burst_t;
								last_value = i;
							}
						}
							
						if(i < lcm_total)
						{ // Repeat the process till we reach LCM , but we need to check this condition after we are at first process
							if(j == 0)
							{
								j=no_of_process_foreground;
								c_in=0;
								c_out=0;
							}
						}
					}
					
					s = "-----------------------------------------------------------------------------------------------------\n";
					b=s.getBytes();  
					fout.write(b);
					
				//background_detail(back_count,count,process_detail_background,no_of_process_background);
				for (int p = array_count; p < lcm_total ; p++)
				{
					output_name[p] = "-1";
					output_time[p] = 0;
				}
				
				JFrame frame = new JFrame("Foreground Background scheduling");
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
			    	frame.add(new draw_try(output_name,output_time,last_value,lcm_total,frame));
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


		private static boolean func_check(int time, int Process_no, int no_of_process_foreground, Process process_detail_foreground[]) 
		{
			// check deadline for all and if i should execute
						// then return 1 
						// else 
			for(int i=0; i<no_of_process_foreground; i++)
			{
				if(i==Process_no)
					continue;
				else
				{
					if(process_detail_foreground[i].check_l < time)
						return false;
				}
			}
			for(int i=0; i<no_of_process_foreground; i++)
			{
				if(i==Process_no)
					continue;
				else
				{
				if(process_detail_foreground[i].check_l < (time + process_detail_foreground[i].burst_t))
					return false;
				}
			}
				
			return true;
		}
}


