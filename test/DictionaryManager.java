package test;

import java.util.HashMap;

public class DictionaryManager {
    public HashMap<String,Dictionary> map;
    public static DictionaryManager DM = null;

    public DictionaryManager() {
        map = new HashMap<String,Dictionary>();
    }

   


  
    public boolean query(String ...bookNames)
    {
        boolean flag = false;
       for(int i = 0 ; i < bookNames.length-1;i++)
       {
        map.put(bookNames[i], new Dictionary(bookNames[i]));
        if(map.get(bookNames[i]).query(bookNames[bookNames.length-1]))
        {
           flag = true;
        }        

       }
       return flag;

    }
    public boolean challenge(String ...bookNames)
    {
        boolean flag = false;
       for(int i = 0 ; i < bookNames.length-1;i++)
       {
        map.put(bookNames[i], new Dictionary(bookNames[i]));
        if(map.get(bookNames[i]).challenge(bookNames[bookNames.length-1]))
        {
           flag = true;
        }        

       }
       return flag;

    }
    public int getSize()
    {
        return this.map.size();
    }
    public static DictionaryManager get()
    {
        if(DM==null)
        {
            DM = new DictionaryManager();
        }
        return DM;

    }



    


   
}