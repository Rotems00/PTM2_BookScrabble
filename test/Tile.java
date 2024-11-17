package test;

import java.util.Random;

public class Tile {
    final char letter;
    final int score;
    

    private Tile(char l,int s)
    {
        this.letter = l;
        this.score = s;
    }

    

    public char getLetter() {
        return letter;
    }



    public int getScore() {
        return score;
    }





    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + letter;
        result = prime * result + score;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tile other = (Tile) obj;
        if (letter != other.letter)
            return false;
        if (score != other.score)
            return false;
        return true;
    }

    public static class Bag {
        static final int TYPES_OF_TILES = 26;
        int amountOfTiles; // Total amount
        int[] amoutOfEachTile; // An array that each index represents a letter A-Z and his specific amount
        final int[] amoutOfEachTileClone;
        Tile[] tiles;
        private static Bag b= null;

        private Bag()
        {

            amountOfTiles = 98;
            amoutOfEachTile = new int[]{9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
            int[]score = new int[]{1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
            tiles= new Tile[TYPES_OF_TILES];
            for(int i = 0 ; i <TYPES_OF_TILES;i++)
                {
                    tiles[i]=new Tile((char)('A'+i),score[i]); // a loop instead of writing 26 different letters and scores

                }
            amoutOfEachTileClone = new int[]{9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};






        }

        public static Bag getBag()
        {
            if(b==null)
                {
                    b= new Bag(); 
                }
            return b;
           

        }

        public Tile getRand()
        {
            Random r=  new Random();
            int index = r.nextInt(TYPES_OF_TILES); // index will be randomly selected between 0-25
            if(amountOfTiles==0)
                {
                    return null;
                }
            else
                {
                    while(amoutOfEachTile[index]==0)
                    {
                        index = r.nextInt(TYPES_OF_TILES);
                    }
                    amoutOfEachTile[index]--;
                    amountOfTiles--;
                    return tiles[index];
                }


        }

        public Tile getTile(char givenLetter)
        {
            
            int indexOfGivenLetter = givenLetter-'A';
            if(indexOfGivenLetter<0 || indexOfGivenLetter>=TYPES_OF_TILES)
                return null;
            if(amountOfTiles==0)
                {
                    return null;
                }
            if(amoutOfEachTile[indexOfGivenLetter]==0)
                {
                    return getRand();
                }
            else
                {
                    amoutOfEachTile[indexOfGivenLetter]--;
                    amountOfTiles--;
                    return tiles[indexOfGivenLetter];
                }

        }

         void put(Tile givenTile)
        {
            // need to check validation
            int indexOfGivenLetter= givenTile.letter- 'A';
            if(indexOfGivenLetter<0 || indexOfGivenLetter >=TYPES_OF_TILES)
             {
                return;
             }
            if(amountOfTiles==98)
                 {
                    return;

                 }
            else
                {
                    if(amoutOfEachTile[indexOfGivenLetter]<amoutOfEachTileClone[indexOfGivenLetter])
                    {
                        amountOfTiles++;
                        amoutOfEachTile[indexOfGivenLetter]++;
                    }
                    else
                    return;
                }



        }

        int size()
        {
            return amountOfTiles;
        }
        int[] getQuantities()
        {
            return amoutOfEachTile.clone();
        }










    
        
    }


  
	
}
