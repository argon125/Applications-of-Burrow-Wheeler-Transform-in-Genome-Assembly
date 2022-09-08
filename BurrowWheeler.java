import java.util.*;
public class BurrowWheeler
{
    public String[] bubbleSortList(String str[])
    {
        String t[] = str;
        String temp;
        for (int j = 0; j < str.length; j++) 
        {
            for (int i = j + 1; i < str.length; i++) 
            {
                // comparing adjacent strings
                if (t[i].compareTo(t[j]) < 0) 
                {
                    temp = t[j];
                    t[j] = t[i];
                    t[i] = temp;
                }
            }
        }
        return t;
    }
    
    public String compress(String str)
    {
        String comp = "";
        int count = 1;
        for(int i=1;i<str.length();i++)
        {
            if(str.charAt(i)==str.charAt(i-1))
            {
                count += 1;
            }
            else
            {
                comp += Integer.toString(count)+(str.charAt(i-1));
                count = 1;
            }
            
            if(i==str.length()-1)
            {
                comp += Integer.toString(count)+(str.charAt(i));
            }
        }
        System.out.println("Length of compressed Sequence: "+comp.length());
        return comp;
    }
    
    public String expand(String com)
    {
        String exp = ""; 
        int count=0;
        
        /*
        for(int i=0;i<com.length();i++)
        {
            if(Character.isDigit(com.charAt(i)))
            {
                count = (int)com.charAt(i)-48; //ASCII
                for(int j=0;j<count;j++)
                {
                    exp += com.charAt(i+1); 
                }
            }
            else
            {
                count = 0;
            }
        }
        */
        
       /*
        int pos = 0;
        for(int i=0;i<com.length();i++)
        {
            while(Character.isDigit(com.charAt(i)))
            {
                count += ((int)com.charAt(i) - 48)* Math.pow(10,pos); //ASCII
                pos += 1;
            
                i++;
                if(Character.isDigit(com.charAt(i))== false)
                {
                    for(int j=0;j<count;j++)
                    {
                        exp += com.charAt(i); 
                    }
                }   
            }
            count = 0;
            pos = 0;
        }
        */
        
        int start_ind = 0;
        int end_ind = 0;
        for(int i=0;i<com.length();i++)
        {
            start_ind = i; 
            while(Character.isDigit(com.charAt(i)))
            {    
                i++;
                if(Character.isDigit(com.charAt(i))== false)
                {
                    end_ind = i;
                    count = Integer.parseInt(com.substring(start_ind,end_ind));
                    for(int j=0;j<count;j++)
                    {
                        exp += com.charAt(i); 
                    }
                    start_ind = end_ind;
                }
            }
            count = 0;
        }
        
        return exp;
    }
    
    public String BWT(String str)
    {
        String word = str;
        int l = word.length();
        String lis[] = new String[l];
        for(int i=0;i<l;i++)
        {
            lis[i] = word;
            word = word.charAt(l-1) + word.substring(0,l-1);
        }
        System.out.println("\nList of words:");
        for(int i=0;i<l;i++)
        {
            System.out.print(lis[i]+"\t"); 
        }
        String bw = ""; 
        String sortedSeq[] = bubbleSortList(lis);
        System.out.println("\n\nList in sorted order:");
        for(int i=0;i<l;i++)
        {
            System.out.print(sortedSeq[i]+"\t"); 
        }
        for(int i=0;i<l;i++)
        {
            bw += sortedSeq[i].charAt(l-1);
        }
        return bw;
    }
    
    public String ibwt(String bwt)
    {
        int len = bwt.length();
        List<String> table = new ArrayList<>();
        for (int i = 0; i < len; ++i) {
            table.add("");
        }
        for (int j = 0; j < len; ++j) 
        {
            for (int i = 0; i < len; ++i) 
            {
                table.set(i, bwt.charAt(i) + table.get(i));
            }
            table.sort(String::compareTo);
        }
       
        return table.get(0);
    }
    
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String: ");
        String seq = sc.next()+"$";
        System.out.println("String input: "+seq);
        BurrowWheeler bw = new BurrowWheeler();
        String bwtSeq = bw.BWT(seq);
        System.out.println("\nLength of sequence :"+bwtSeq.length());
        System.out.println("\nBurrow Wheeler Transform of "+seq+ " yields:" +bwtSeq);
        String comp = bw.compress(bwtSeq);
        System.out.println("Compressed version of BWT is:" +comp);
        String expand = bw.expand(comp);
        System.out.println("Expanded version of BWT is:" +bw.expand(comp));
        System.out.println("\nInverse Burrow Wheeler Transform of "+bwtSeq+ " yields:" +bw.ibwt(expand));
        System.out.println("\nLength of IBWT sequence :"+bw.ibwt(expand).length());
    }
}