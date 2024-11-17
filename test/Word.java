package test;

import java.util.Arrays;

public class Word {
    Tile[] tilesOfaWord;
    int rowOfFirstTile;
    int colOfFirstTile;
    boolean vertical;
    public Word(Tile[] tilesOfaWord, int rowOfFirstTile, int colOfFirstTile, boolean vertical) {
        this.tilesOfaWord = tilesOfaWord;
        this.rowOfFirstTile = rowOfFirstTile;
        this.colOfFirstTile = colOfFirstTile;
        this.vertical = vertical;
    }
    public Tile[] getTilesOfaWord() {
        return tilesOfaWord;
    }

    public boolean myEqual(Word a)
    {
        if(this.tilesOfaWord.length==a.tilesOfaWord.length && this.getRowOfFirstTile() == a.getRowOfFirstTile() && this.getColOfFirstTile() == a.getColOfFirstTile()){
           return true;
        }
        else
            return false;

    }
    public void setTilesOfaWord(Tile[] tilesOfaWord) {
        this.tilesOfaWord = tilesOfaWord;
    }
    public int getRowOfFirstTile() {
        return rowOfFirstTile;
    }
    public void setRowOfFirstTile(int rowOfFirstTile) {
        this.rowOfFirstTile = rowOfFirstTile;
    }
    public int getColOfFirstTile() {
        return colOfFirstTile;
    }
    public void setColOfFirstTile(int colOfFirstTile) {
        this.colOfFirstTile = colOfFirstTile;
    }
    public boolean isVertical() {
        return vertical;
    }
    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(tilesOfaWord);
        result = prime * result + rowOfFirstTile;
        result = prime * result + colOfFirstTile;
        result = prime * result + (vertical ? 1231 : 1237);
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
        Word other = (Word) obj;
        if (!Arrays.equals(tilesOfaWord, other.tilesOfaWord))
            return false;
        if (rowOfFirstTile != other.rowOfFirstTile)
            return false;
        if (colOfFirstTile != other.colOfFirstTile)
            return false;
        if (vertical != other.vertical)
            return false;
        return true;
    }
    

    
   
	
}
