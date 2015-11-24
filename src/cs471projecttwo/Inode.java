/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs471projecttwo;

/**
 *
 * @author Caroline Chey
 */
public class Inode implements File {
     
   
    String mData; //one-line unique data
    //String mExt;
    String mFilename;
    
    
    //default constructor
    public Inode()
    {
        mData = "";
        //mExt = "";
        mFilename = "";
    }
    
    //constructor
    public Inode(String filename, String data)
    {
        mData = data;
        //mExt = ext;
        mFilename = filename;
    }
    
    //copy constructor
    public Inode(Inode src)
    {
        mData = src.mData;
        //mExt = src.mExt;
        mFilename = src.mFilename;
    }
    
   
    
    
    public char getType()
    {
        return 'i'; //for i-node
    }
    
    public String getmFilename()
    {
        return mFilename;
    }
    
    
    public boolean canDelete()
    {
        return true; //Inode can always be deleted
    }
}
