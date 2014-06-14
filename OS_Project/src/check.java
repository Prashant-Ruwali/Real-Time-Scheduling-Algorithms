
public class check 
{
	public static void main(String [] args)
	{
		int a = 5;
		Output output_detail[] = new Output[2];
		output_detail[1]= new Output();
		output_detail[1].name = 'p' + String.valueOf(a);
		output_detail[1].start = a;
		System.out.println(output_detail[1].name);
		System.out.println(output_detail[1].start);
	}
	
}
