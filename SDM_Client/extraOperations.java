import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class extraOperations {
	List<String> strings = new ArrayList<String>();
    public List<String> _findStr(String _input[]){
        
        Scanner sc ;
        String _op = null ;
        for(String s : _input)
        {
        	sc = new Scanner(s);
        	while(sc.hasNext())
        		if(sc.next().contains("Profile"))
        		{
        			_op = sc.next().replace(",", "");
        			strings.add(_op);
        			//System.out.println(_op);
        		}
        }
        return strings;
    }
    
    public int _compare(int i[]){
        int temp = 100000000;
        for(int index=0;index<i.length;index++)
            if(i[index]<temp)
                temp = i[index];
        System.out.println(temp);
        return temp;
    }
    
    public static void main(String args[]){
        String input[] = {"SM 7.0 Profile 5 Max Devices 23,300","SM 7.0 Profile 1 Max Devices 2,500","SM 7.0 Profile 2 Max Devices 4,500"};
        int tem[] = {12,20,25,6};
        extraOperations ob = new extraOperations();
        int arr[] = {0,0,0} ;
        List<Integer> i= new ArrayList<Integer>();
       
        for(int z=0;z<input.length;z++)
            i.add(Integer.parseInt(ob._findStr(input).get(z)));
            
        for(int y : i)
            System.out.println(y);
        
        for(int z=0;z<input.length;z++)
            arr[z]=i.get(z);
        ob._compare(arr);
        
//        if(i>ob._compare(tem))
//            System.out.println("Greater");
        
        ob._compare(tem);
        
    }
}
