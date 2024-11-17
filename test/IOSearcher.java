package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOSearcher {

    public static boolean search(String word, String... fileNames) 
    {
        try{
            Pattern p = Pattern.compile("\\b"+word+"\\b");
            for(String fileName : fileNames)
            {
               
                BufferedReader bf = new BufferedReader(new FileReader(fileName));
                String line;
                while((line= bf.readLine()) !=null)
                {
                    Matcher m = p.matcher(line);
                    if(m.find())
                    {
                        bf.close();
                        return true;
                    }
                }
               


            
            }
    
            

           
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        return false;
    }


}
