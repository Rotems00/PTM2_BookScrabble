package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;


public class BloomFilter {

   
   
    String word;
    int sizeOfBytesArray;
    BitSet bytesArray;
    String a,b;
    
    public BloomFilter(int size,String x, String y)
    {
        
        
       this.a = x;
       this.b = y;
        this.sizeOfBytesArray = size;
        this.bytesArray = new BitSet(this.sizeOfBytesArray);


    }
    public void add(String str)
    {
       try 
        {

             
                    
                    MessageDigest md = MessageDigest.getInstance(a);
                    MessageDigest md2 = MessageDigest.getInstance(b);
                    byte[] bts = md.digest(str.getBytes());
                    byte[] bts2 = md2.digest(str.getBytes());
                    BigInteger bi = new BigInteger(bts);
                    BigInteger bi2 = new BigInteger(bts2);
                    int result = Math.abs(bi.intValue()% this.sizeOfBytesArray);
                    int result2 = Math.abs(bi2.intValue()% this.sizeOfBytesArray);
                    this.bytesArray.set(result);
                    this.bytesArray.set(result2);

                        
                
               
        }

        catch (NoSuchAlgorithmException e)
        {
            System.out.println(e.getMessage());

        }

       
         
    }

    boolean contains(String word)
    {
        try 
        {

             
                    
                    MessageDigest md = MessageDigest.getInstance(a);
                    MessageDigest md2 = MessageDigest.getInstance(b);
                    byte[] bts = md.digest(word.getBytes());
                    byte[] bts2 = md2.digest(word.getBytes());
                    BigInteger bi = new BigInteger(bts);
                    BigInteger bi2 = new BigInteger(bts2);
                    int result = Math.abs(bi.intValue()% this.sizeOfBytesArray);
                    int result2 = Math.abs(bi2.intValue()% this.sizeOfBytesArray);
                   
                    return bytesArray.get(result) && bytesArray.get(result2);

                        
                
               
        }

        catch (NoSuchAlgorithmException e)
        {
            System.out.println(e.getMessage());

        }
        return false;

       

    }

    @Override
    public String toString()
    {
        StringBuilder STR = new StringBuilder();
        for(int i = 0 ; i < this.bytesArray.length();i++)
        {
           if(this.bytesArray.get(i))
           {
            STR.append(1);

           }
           else
           {
            STR.append(0);
           }
        }
        return STR.toString();
    }
}
