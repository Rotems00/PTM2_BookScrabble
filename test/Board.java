package test;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;





public class Board {
    private static Board b = null;
   static ArrayList<Word>ExistingWords = new ArrayList<>();
    private Map<Coordinate,Cell> ourBoard;
   static final int SIZE=15;

   enum CellColor{
    green, // REGULAR
    yellow, // DOUBLE LETTER
    red,// TRIPLE WORD
    blue,// TRIPLE LETTER
    teal // DOUBLE LETTER
    
    

   }
   boolean isCenterOccupied()
    {
        Coordinate center = new Coordinate(7, 7);
       if ((ourBoard.get(center)).getTile()!=null)
         return true;
       
       
       return false;
    }

  
   boolean isBoardEmpty()
   {
    for(Cell c:ourBoard.values())
    {
        if(c.getTile()!=null)
        {
            return false;
        }
    }
    return true;

   }

   boolean isNoneNullNeighbors(int row,int col,boolean isVertical)
   {
    if(isVertical)
    {
        // check left
        Coordinate point = new Coordinate(row, col-1);
        Coordinate point1 = new Coordinate(row, col+1);
        if(col>0 && (ourBoard.get(point)).getTile()!=null)
        {
            return true;

        }
        //check right
        if(col<SIZE-1 && (ourBoard.get(point1)).getTile()!=null)
        return true;
    }
    else
    {
        Coordinate point = new Coordinate(row+1, col);
        Coordinate point1 = new Coordinate(row-1, col);

        //check top
        if(row>0 && (ourBoard.get(point1)).getTile()!=null)
        return true;

        // check bottom

        if(row<SIZE-1 && (ourBoard.get(point)).getTile()!=null)
        return true;

    }

    return false;
   }
   boolean isHorizontalWordLegal(Word w)
   {
    Coordinate center = new Coordinate(7,7);
    int lastTileInWordPlace = w.colOfFirstTile+w.tilesOfaWord.length;
    if(lastTileInWordPlace <=SIZE && w.getRowOfFirstTile()==7 && lastTileInWordPlace >7 && (ourBoard.get(center)).getTile()==null)
        return true;
    if(lastTileInWordPlace>=SIZE)
        return false;

     
   
    
    // if center is occupied
    
        for(int i = 0; i < w.tilesOfaWord.length;i++)
        {
            if(isNoneNullNeighbors(w.rowOfFirstTile,w.colOfFirstTile+i, false))
            {
                return true;

            }
        }
        return false;

    
   
   }

   boolean isVerticalWordLegal(Word w)
   {
    

    int lastTileInWordPlace = w.getRowOfFirstTile()+w.getTilesOfaWord().length;
    Coordinate center = new Coordinate(7,7);
    if(lastTileInWordPlace <= SIZE &&(ourBoard.get(center)).getTile()==null && w.getColOfFirstTile()==7 && lastTileInWordPlace>7)
        return true;

    if(lastTileInWordPlace >= SIZE)
     return false;
     
    

    

   
     // board has already 1 word atleast, so i need to scan the word to the point where i can add a valid next word
    
        for(int i = 0; i < w.tilesOfaWord.length;i++)
        {
         if(isNoneNullNeighbors(w.getRowOfFirstTile()+i,w.getColOfFirstTile(),true))
         {
            return true;
         }

        }
        return false;

    
   


   }


   
  


   boolean boardLegal(Word word)
   {

    if(word.colOfFirstTile < 0 || word.rowOfFirstTile < 0)
    return false;
    else
    {
        if(word.isVertical())
        {
            return isVerticalWordLegal(word);
        }
        else
        {
            return isHorizontalWordLegal(word);
        }
    }
    


   }

   public void initColorsToBoard()
   {
    initRed();
    initBlue();
    initTeal();
    initYellow();
   
   }
   public void initRed()
   {

    ourBoard.put(new Coordinate(0, 0),new Cell(null,CellColor.red));
    ourBoard.put(new Coordinate(0, 7),new Cell(null,CellColor.red));
    ourBoard.put(new Coordinate(0, 14),new Cell(null,CellColor.red));
    ourBoard.put(new Coordinate(7, 14),new Cell(null,CellColor.red));
    ourBoard.put(new Coordinate(14, 0),new Cell(null,CellColor.red));
    ourBoard.put(new Coordinate(14, 7),new Cell(null,CellColor.red));
    ourBoard.put(new Coordinate(14, 14),new Cell(null,CellColor.red));
    ourBoard.put(new Coordinate(7, 0),new Cell(null,CellColor.red));
   }
   public void initBlue()
   {

    ourBoard.put(new Coordinate(1, 5),new Cell(null,CellColor.blue));
    ourBoard.put(new Coordinate(1, 9),new Cell(null,CellColor.blue));
    ourBoard.put(new Coordinate(5, 1),new Cell(null,CellColor.blue));
    ourBoard.put(new Coordinate(5, 5),new Cell(null,CellColor.blue));
    ourBoard.put(new Coordinate(5, 9),new Cell(null,CellColor.blue));
    ourBoard.put(new Coordinate(5, 13),new Cell(null,CellColor.blue));
    ourBoard.put(new Coordinate(9, 1),new Cell(null,CellColor.blue));
    ourBoard.put(new Coordinate(9, 5),new Cell(null,CellColor.blue));
    ourBoard.put(new Coordinate(9, 9),new Cell(null,CellColor.blue));
    ourBoard.put(new Coordinate(9, 13),new Cell(null,CellColor.blue));
    ourBoard.put(new Coordinate(13, 5),new Cell(null,CellColor.blue));
    ourBoard.put(new Coordinate(13, 9),new Cell(null,CellColor.blue));
  
   }

   public void initTeal()
   {
    ourBoard.put(new Coordinate(0, 3),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(0, 11),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(2, 6),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(2, 8),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(3, 7),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(3, 0),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(3, 14),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(6, 2),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(6, 6),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(6, 8),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(6, 12),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(7, 3),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(7, 11),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(8, 2),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(8, 6),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(8, 8),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(8, 12),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(11, 0),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(11, 7),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(11, 14),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(12, 6),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(12, 8),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(14, 3),new Cell(null,CellColor.teal));
    ourBoard.put(new Coordinate(14, 11),new Cell(null,CellColor.teal));




   }

   public void initYellow()
        {
            ourBoard.put(new Coordinate(1, 1),new Cell(null,CellColor.yellow));
            ourBoard.put(new Coordinate(2, 2),new Cell(null,CellColor.yellow));
            ourBoard.put(new Coordinate(3, 3),new Cell(null,CellColor.yellow));
            ourBoard.put(new Coordinate(4, 4),new Cell(null,CellColor.yellow));

            ourBoard.put(new Coordinate(10, 10),new Cell(null,CellColor.yellow));
            ourBoard.put(new Coordinate(11, 11),new Cell(null,CellColor.yellow));
            ourBoard.put(new Coordinate(12, 12),new Cell(null,CellColor.yellow));
            ourBoard.put(new Coordinate(13, 13),new Cell(null,CellColor.yellow));

            ourBoard.put(new Coordinate(13, 1),new Cell(null,CellColor.yellow));
            ourBoard.put(new Coordinate(12, 2),new Cell(null,CellColor.yellow));
            ourBoard.put(new Coordinate(11, 3),new Cell(null,CellColor.yellow));
            ourBoard.put(new Coordinate(10, 4),new Cell(null,CellColor.yellow));

            ourBoard.put(new Coordinate(4, 10),new Cell(null,CellColor.yellow));
            ourBoard.put(new Coordinate(3, 11),new Cell(null,CellColor.yellow));
            ourBoard.put(new Coordinate(2, 12),new Cell(null,CellColor.yellow));
            ourBoard.put(new Coordinate(1, 13),new Cell(null,CellColor.yellow));

            ourBoard.put(new Coordinate(7, 7),new Cell(null,CellColor.yellow));

    

        }


    boolean dictionaryLegal(Word w)
    {
        return true;

    }

    Word getVerticalNeighborWord(int row,int col, Tile specifiecTile) // the word itself is vertical, i want to check Hortizontally
    {
        if(!isNoneNullNeighbors(row, col, true))
            return null;
        

    
        
        int startIndex = col;
        if(startIndex!=0)
        {

            while(startIndex-1>0 && ourBoard.get(new Coordinate(row, startIndex-1)).getTile()!=null)
            {
                startIndex--;
            }
        }   
        

        ArrayList<Tile> tiles = new ArrayList<>();
        Word addingTiles = new Word(null, row, startIndex, true);
        for(int i = startIndex;i<SIZE;i++)
        {
            Tile t = ourBoard.get(new Coordinate(row, i)).getTile();
            if(t!=null)
            {
                tiles.add(t);
            }
            else
            {
                if(i==col) // the word is blank as we said in the task
                {
                    tiles.add(specifiecTile);
                }
                else
                    break;
            }
        }
        addingTiles.setTilesOfaWord(tiles.toArray(new Tile[tiles.size()]));
        return addingTiles;



    }

    Word getHorizontalNeighborWord(int row,int col, Tile specifiecTile)// the word is Horizontal i want to check up and down neighbors
    {
        if(!isNoneNullNeighbors(row, col, false))
        return null;

        int startIndex=row;
        if(startIndex!=0)
        {
            while(startIndex-1>0 && ourBoard.get(new Coordinate(startIndex-1, col)).getTile()!=null)
            {
                startIndex--;
            }
        }
        
        ArrayList<Tile> tiles= new ArrayList<>();
        Word addingTiles = new Word(null, startIndex, col, true);

        for(int i = startIndex;i<SIZE;i++)
        {
            Tile t = ourBoard.get(new Coordinate(i, col)).getTile();
            if(t!=null)
            {
                tiles.add(t);
            }
            else
            {
                if(i==row)
                {
                    tiles.add(specifiecTile);
                }
                else
                {
                    break;
                }
            }
        }

        addingTiles.setTilesOfaWord(tiles.toArray(new Tile[tiles.size()]));
        return addingTiles;
    }

    ArrayList<Word> getWords(Word w)
    {
        ArrayList<Word> wordList = new ArrayList<>();

        if(w.isVertical())
        {
            
               
                int currentRow= w.getRowOfFirstTile();
                wordList.add(w);
                
                for(int i =0;i<w.getTilesOfaWord().length;i++)
                {
                    Tile currentTile = ourBoard.get(new Coordinate(currentRow+i, w.getColOfFirstTile())).getTile();
                    if(currentTile==null)
                    { 
                        Word newWord = getVerticalNeighborWord(currentRow+i, w.getColOfFirstTile(), w.getTilesOfaWord()[i]);
                        
                        if(newWord!=null )
                        {
                           wordList.add(newWord);
                              
                        }      
                        
                    }
                   
                }


            
           

        }
        else
        {
               
                int currentCol= w.getColOfFirstTile();
                wordList.add(w);
                for(int i = 0;i<w.getTilesOfaWord().length;i++)
                {
                    Tile currentTile=ourBoard.get(new Coordinate(w.getRowOfFirstTile(),currentCol+i)).getTile();
                    if(currentTile==null)
                    {   Word newWord= getHorizontalNeighborWord(w.getRowOfFirstTile(), currentCol+i, w.getTilesOfaWord()[i]);
                    
                        if(newWord!=null )
                        {
                            wordList.add(newWord);

                        }
                    }

                }
            
            
        }
        
        return wordList;
    }

  
    int getScore(Word w)
    {
        if(w.isVertical())
         return getVerticalScore(w);

        else
            return getHorizontalScore(w);

    }



    int getVerticalScore(Word w) {
        int totalScore = 0;
        int multiplier = 1;
        
        for (int i = 0; i < w.getTilesOfaWord().length; i++) 
        {
            Coordinate coord = new Coordinate(w.getRowOfFirstTile() + i, w.getColOfFirstTile());
            Cell cell = ourBoard.get(coord);
            CellColor c = cell.getColor();
            int currentScore = 0;
           
           
            
                        if (cell.getTile() != null ) 
                        {
                             currentScore = cell.getTile().getScore();
                            
                        }
                        else
                        {
                             currentScore = w.tilesOfaWord[i].getScore();
                           

                        }
                        switch (c) {
                            case green:
                                totalScore += currentScore;
                                break;
                            case yellow:
                                if(isCenterOccupied())
                                    totalScore += currentScore;
    
                                else 
                                {totalScore += currentScore;
                                multiplier *= 2;}
                                break;
                            case red:
                                totalScore += currentScore;
                                multiplier *= 3;
                                break;
                            case teal:
                                totalScore += (currentScore * 2);
                                break;
                            case blue:
                                totalScore += (currentScore * 3);
                                break;
                            
                           
                                }
                                            
                                        
                                            
                                        
        }
        return totalScore * multiplier;
    }

   

   
    int getHorizontalScore(Word w) {
        int totalScore = 0;
        int multiplier = 1;
        
        for (int i = 0; i < w.getTilesOfaWord().length; i++) {
            Coordinate coord = new Coordinate(w.getRowOfFirstTile(), w.getColOfFirstTile() + i);
            Cell cell = ourBoard.get(coord);
            int currentScore = 0;
            CellColor c = cell.getColor();
            
            if (cell.getTile() != null) 
                {
                    currentScore = cell.getTile().getScore();
                }
            else
                {
                    currentScore = w.tilesOfaWord[i].getScore();

                }
                
                
                switch (c) {
                        case green:
                            totalScore += currentScore;
                            break;
                        case yellow:
                            totalScore += currentScore;
                            multiplier *= 2;
                            break;
                        case red:
                            totalScore += currentScore;
                            multiplier *= 3;
                            break;
                        case teal:
                            totalScore += (currentScore * 2);
                            break;
                        case blue:
                            totalScore += (currentScore * 3);
                            break;
                    }
            }
        

        
        return totalScore * multiplier;
    }
    
   int tryPlaceWord(Word w)
  {
    if(!boardLegal(w))
    {
        return 0;
    }
    int totalScore=0;
    ArrayList<Word> words = getWords(w);
 
    for( Word x: words)
    {
        if(!dictionaryLegal(x))
            return 0;
        else
        {
            totalScore+=getScore(x);
            
        }
    }
   
    if(w.isVertical())
    {
           for(int i=0;i<w.getTilesOfaWord().length;i++)
          {
                if(w.getTilesOfaWord()[i]!=null)
                    {
                        ourBoard.get(new Coordinate(w.getRowOfFirstTile()+i,w.getColOfFirstTile())).setTile(w.getTilesOfaWord()[i]);
                    }
          }

    }
    else
    {
        for(int i=0;i<w.getTilesOfaWord().length;i++)
        {
                if(w.getTilesOfaWord()[i]!=null)
                        { 
                            ourBoard.get(new Coordinate(w.getRowOfFirstTile(),w.getColOfFirstTile()+i)).setTile(w.getTilesOfaWord()[i]);
                            
                        } 
        }

    }
  
       

   



    return totalScore;
 }

 
 
           



    




   

    






  
  
   public class Cell {
    Tile tile;
    CellColor color;
    public Cell(Tile tile, CellColor color) {
        this.tile = tile;
        this.color = color;
    }
    public Tile getTile() {
        return tile;
    }
    public void setTile(Tile tile) {
        this.tile = tile;
    }
    public CellColor getColor() {
        return color;
    }
    public void setColor(CellColor color) {
        this.color = color;
    }
    public Cell()
    {
        this.tile = null;
        this.color = CellColor.green;

    }
    
   
    
   }
    

    
    
    

    public static class Coordinate {
        int x,y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
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
            Coordinate other = (Coordinate) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
        

    
        
    }
    void initEmptyBoard()
    {
        for(int i = 0; i < SIZE;i++)
        {
            for(int j = 0 ; j < SIZE;j++)
            {
                Coordinate c = new Coordinate(i, j);
                ourBoard.put(c,new Cell());
            }
        }
    }

    public static Board getBoard()
    {
        if(b==null)
        {
            b = new Board();
            return b;

        }
        return b;
    }

    public Board()
    {
        ourBoard = new HashMap<Coordinate,Cell>();
        initEmptyBoard();
        initColorsToBoard();
       

    }
    
   

  
}
