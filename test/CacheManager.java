package test;

import java.util.HashSet;

public class CacheManager {
    int sizeOfCache;
    CacheReplacementPolicy crp;
    HashSet<String>wordsInCache;
    public CacheManager(int size,CacheReplacementPolicy c)
    {
        this.crp = c;
        this.sizeOfCache = size;
        this.wordsInCache = new HashSet<String>();
    }
    public boolean query(String word)
    {
        return wordsInCache.contains(word);
    }
    public void add(String word)
    {
        if(wordsInCache.size()>=this.sizeOfCache)
        {
            String s =this.crp.remove();
            this.wordsInCache.remove(s);


        }
        crp.add(word);
        wordsInCache.add(word);
       
    }

    
	
	

}
