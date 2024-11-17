package test;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.util.Arrays;

public class BookScrabbleHandler implements ClientHandler {
    public DictionaryManager DM = new DictionaryManager();
    private BufferedReader reader;
    

public void handleClient(InputStream inFromclient, OutputStream outToClient) 
{
    String line;
    try
     {
       reader = new BufferedReader(new InputStreamReader(inFromclient));
        while((line=reader.readLine())!=null)
        {
            String[] words = line.split(",");
            String[] newWordsAfterTrim = Arrays.copyOfRange(words,1,words.length);
            char firstLetter = words[0].trim().charAt(0);
            if(firstLetter=='Q')
            {
                if(DM.query(newWordsAfterTrim))
                {
                    try
                    {
                    outToClient.write("true\n".getBytes());
                    }   
                    catch(IOException e )
                    {
                            e.printStackTrace();
                    }  
                }
                else
                {
                    try
                    {
                    outToClient.write("false\n".getBytes());
                    }   
                    catch(IOException e )
                    {
                            e.printStackTrace();
                    }  

                }
    
               
            }
            else if(firstLetter=='C')
            {
                if(DM.challenge(newWordsAfterTrim))
                {
                    try
                    {
                    outToClient.write("true\n".getBytes());
                    }   
                    catch(IOException e )
                    {
                            e.printStackTrace();
                    }  
                }
                else
                {
                    try
                    {
                    outToClient.write("false\n".getBytes());
                    }   
                    catch(IOException e )
                    {
                            e.printStackTrace();
                    }  

                }

            }
            else
            {
                try
                {
                outToClient.write("false\n".getBytes());
                }   
                catch(IOException e )
                {
                        e.printStackTrace();
                }  

            }            


        }
    }
    catch(IOException e)
    {
        e.printStackTrace();
    }
    
   


}

public void close()
    {
        try{
       reader.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

}
