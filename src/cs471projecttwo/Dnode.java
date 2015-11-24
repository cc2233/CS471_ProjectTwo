/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs471projecttwo;

import javax.swing.JOptionPane;

/**
 *
 * @author Caroline Chey
 */
public class Dnode implements File{
     
    public class entry 
     {
            String filename;
            char fileType; //'v' or 'i' or 'd'
            File content; //vnode or inode or dnode


            //default constructor
            public entry()
            {
                filename = "";
                fileType = 'x';
                content = null;
            }

            //constructor
            public entry(String name, char type, File file1)
            {
                filename = name;
                fileType = type;
                content = file1;
            }

            //copy constructor
            public entry(entry src)
            {
                filename = src.filename;
                fileType = src.fileType;
                content = src.content;
            }
    };
        
    
    //default constructor
    public Dnode()
    {
        //default constructor
        for(int i =0; i < 5; ++i)
        {
            mEntries[i] = new entry();
            //mEntries[i] = null;
        }

    }

    //constructor
    public Dnode(entry[] entries)
    {
        for(int i=0; i < 5; ++i)
        {
            mEntries[i] = new entry(entries[i]);
        }
    }
    
    
    public boolean isFull()
    {
        for(int i=0; i < 5; ++i)
        {
            if(mEntries[i].content == null)
                return false;
        }
        
        return true;
    }
    
    public boolean canDelete()
    {//Dnode can be deleted only if it doesn't contain anything.
        for(int i=0; i<5; ++i)
        {
            if( mEntries[i].content != null ||  ! mEntries[i].filename.equals("") || mEntries[i].fileType!='x' )
            {
                JOptionPane.showMessageDialog(null, "Cannot delete directory if not empty!", "CANNOT DELETE FILE", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return true;
    }
    
    public boolean deleteEntry( int index ) throws Exception
    {
        if( index < 0 || index > 4 )
            return false;
        
        else
        {
            mEntries[index] = new entry();
            return true;
        }
    }
    
    public boolean deleteFile( String name ) throws Exception
    {
        for(int i=0; i<5; ++i)
        {
            if(mEntries[i].filename.equals(name))
            {
                mEntries[i] = new entry();
                return true;
            }
            
        }   return false;
    }
            
            
    
    
    public boolean addEntry( String fname, char fType, File content )
    {
        for(int i=0; i<5; ++i)
        {
            if(mEntries[i].content == null) //adding a new entry if previously null
            {
                mEntries[i] = new entry(fname, fType, content);
                return true;
            }
        }
        return false;
    }
    
    
    
    public boolean insertEntry( char filetype, String filename, String optional_data )
    {
        int i =0;
        
        if(isFull()) return false;

        for( ; i < 5; ++i)
        {
            if( mEntries[i].content == null )       break; //if null                  
        }
        
        if(i < 5)
        {
            if( filetype == 'i' ) //if Inode
            {
                mEntries[i] = new entry( filename , filetype , new Inode( filename, optional_data) );
            }
            else if( filetype == 'v' ) //if Vnode
            {
                mEntries[i] = new entry( filename , filetype , new Vnode( filename ) );
            }
            else if(filetype == 'd') //if Dnode
            {
                mEntries[i] = new entry( filename , filetype , new Dnode() );
            }
            else
                return false;
            
            
            return true;
        }
        return false;
    }
    
    
    public char getType()
    {
        return 'd'; //for directory
    }
    
    
    public String getName(int index)
    {
        return mEntries[index].filename;
    }

    
    public String getEntryType(int index)
    {
        
        char type = mEntries[index].fileType;
        return type + "";
    }
    
    //array of five common files
    entry[] mEntries = new entry[5];
    
    
    public void setEntry(int index, String fname, char fType, File fcontent)
    {
        mEntries[index].filename = fname;
        mEntries[index].fileType = fType;
        mEntries[index].content = fcontent;
    }
    
    
    //initializing mEntries array with function arguments
    public void setEntries(entry one, entry two, entry three, entry four, entry five)
    {
        mEntries[0] = new entry(one);
        mEntries[1] = new entry(two);
        mEntries[2] = new entry(three);
        mEntries[3] = new entry(four);
        mEntries[4] = new entry(five);
    }
    
    
    
    //find using parameter filepath
    public File find( String fname )
    {
        for(int i=0; i<5; ++i)
        {
            if (mEntries[i].filename.equals( fname ))
               return mEntries[i].content; 
        }
        return null; //if nothing is found
    }
    
    
    public String dToString()
    {
        String ret = "";
        
        for(int w=0; w<5; ++w)
        {
            if(mEntries[w].fileType == 'd')
                ret += ((Dnode)(mEntries[w].content)).dToString();
            
            ret += "\nfiletype: " + mEntries[w].fileType + " , filename: " + mEntries[w].filename;
        }
        
        return ret;
    }
}