package test;


import java.util.Iterator;
import java.util.LinkedHashMap;


public class LRU implements CacheReplacementPolicy {
    LinkedHashMap<String,Boolean>LRU_Cache;
    int MAX_SIZE;
    LRU()
    {
        LRU_Cache = new LinkedHashMap<String,Boolean>();
    }
    LRU(LinkedHashMap<String,Boolean> a,int MAXSIZE)
    {
        this.LRU_Cache = a;
        this.MAX_SIZE = MAXSIZE;

    }
    public void add(String word)
    {
       if(LRU_Cache.size()==MAX_SIZE) // if  cache is full, remove 
         remove();

    
        LRU_Cache.putLast(word, true); // after i removed by LRU i will put in the last place the new word
      

    }
	public String remove()
    {
        if(LRU_Cache.size()<=0)
        {
            return null;
        }
        Iterator<String> iterator = LRU_Cache.keySet().iterator();
        String firstString = iterator.next();
        LRU_Cache.remove(firstString);
        return firstString;

    }
   


}
