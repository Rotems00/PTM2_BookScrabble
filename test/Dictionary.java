package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {
    CacheManager ExistingWords_LRU;
    CacheManager nonExistingWords_LFU;
    BloomFilter bloomfilter;
    String[] fileNames;
    public Dictionary(String... file_Names)
    {
        this.fileNames = file_Names;
        this.ExistingWords_LRU = new CacheManager(400, new LRU());
        this.nonExistingWords_LFU = new CacheManager(100, new LFU());
        this.bloomfilter = new BloomFilter(256, "MD5", "SHA1");
        for(String fileName : file_Names)
        {
            try
            {
                BufferedReader bf = new BufferedReader(new FileReader(fileName));
                String line;
                while((line=bf.readLine())!=null)
                {
                    String[] words = line.split(" ");
                    for(String word : words)
                    {
                        bloomfilter.add(word);
                    }

                }

            }
            catch(IOException io)
            {
                io.printStackTrace();

            }
        }

        
        



    }

    public boolean query(String word)
    {
        
        if(this.ExistingWords_LRU.query(word))
        {
            return true;
        }
        else if(this.nonExistingWords_LFU.query(word))
        {
            return false;

        }
        else 
        {
           boolean result = this.bloomfilter.contains(word);
           if(result)
           {
            this.ExistingWords_LRU.add(word);
            return true;
           }
           else
           {
            this.nonExistingWords_LFU.add(word);
            return false;
           }
        }

    }

    public boolean challenge(String word)
    {
        IOSearcher io = new IOSearcher();
        if(io.search(word, fileNames))
        {
            this.ExistingWords_LRU.add(word);
            return true;

        }
        else
        {
            this.nonExistingWords_LFU.add(word);
            return false;
        }
        

    }

}
