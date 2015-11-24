/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs471projecttwo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Caroline Chey
 */
public class FileSystem {
    
    String mName;
    
    Dnode mRoot;
    
    
    //default constructor
    public FileSystem()
    {
        mName = "";
        mRoot = new Dnode();
    }
    
    //constructor
    public FileSystem(String name, Dnode root)
    {
        mName = name;
        
        mRoot = new Dnode();
        for(int i = 0; i < 5; ++i)
        {
            mRoot.setEntry(i, root.mEntries[i].filename, root.mEntries[i].fileType, root.mEntries[i].content);
        }
    }
    
    //copy constructor
    public FileSystem(FileSystem src) 
    {
        mName = src.mName;
        mRoot = src.mRoot;
        for(int i=0; i<5; ++i)
        {
            mRoot = new Dnode(src.mRoot.mEntries);   
        }
    }
    
    
    public void setmName(String name)
    {
        mName = name;
    }
    
    
    
    public void setmRoot(Dnode d1)
    {
        for(int i=0; i < 5; ++i)
        {
            mRoot.setEntries(d1.mEntries[0], d1.mEntries[1],d1.mEntries[2],d1.mEntries[3],d1.mEntries[4]);
        }

    }
    
    
    public String makeFilePath(String filename) //makes file path based on filename
    {
        String path = "C:\\Users\\Scar\\Documents\\NetBeansProjects\\CS471ProjectTwo\\src\\cs471projecttwo\\" + filename + ".txt";
        return path;
    }
    
    
    
   //build file system from input data files
    public void buildFileSystem( String datafile1 ) throws FileNotFoundException, IOException, NullPointerException, Exception
    {
        Scanner readIn = new Scanner(new FileInputStream(makeFilePath(datafile1)));

        setmName( datafile1 );
        setmRoot( new Dnode() );
        
        int lineNum = 0; //to use to count while loop iteration
        
        while( readIn.hasNext() )
        {  ++lineNum;
//DEBUG
//System.out.println("\n entering while \n");
            
            String lineRead = readIn.nextLine(); //reading in line by line
            lineRead = lineRead.trim();
            String[] inputline = lineRead.split(" "); //separating the line 
            for( int j=0; j < inputline.length; ++j ) //trimming each string
            {
                inputline[j] = inputline[j].trim();
            }
                    /*   
                        //inputline[0] = 'd' or 'v' or 'i'
                        //inputline[1] = filename
                        //inputline[2] = one line data, if any
                    */
            if( inputline.length != 3 ) //inputline[] has to have three elements!
            {//throw new Exception("[]inputline.length != 3");
                System.err.println("\n  ignoring line , []inputline.length != 3 ");
                continue;
            }
            
            String filetype = inputline[0];
            String filepath = inputline[1];
            String filedata = inputline[2];
//DEBUG
//System.out.println("made []inputline : " + inputline[0] + " , " + inputline[1] + " , " + inputline[2] +  " , []length=" + inputline.length);
 
            String[] fPath = filepath.split("/" , -1); //determining path for this file
/*/DEBUG
//System.out.print("made []fPath, []length= " + fPath.length );
for(int x =0; x < fPath.length; ++x)
{
    System.out.print(" " + fPath[x] + " / ");
}*/

            if( fPath.length < 2 )
            {//throw new Exception("fPath.length < 2");
                System.err.println("ignoring lineNum " + lineNum + " , fPath.length < 2 ");
                continue;
            }
            if( fPath[0].length() > 0  || fPath.length > 4 ) //if path does not start with "/..."
            {
                System.err.println("ignoring lineNum " + lineNum + " , invalid path; fPath[0].length() > 0 || fPath.length > 4");
            }
               
                

            //walk path till last component
            Dnode currentDir = mRoot;
    
            int i = 1;
            for( ; i < fPath.length - 1 ; ++ i)
            {
                if(fPath[i].length() < 1 )
                {
                    System.err.println( datafile1 + " : ignoring line " + lineNum + " , fPath[" + i + "] is empty");
                    currentDir = null;
                    continue;
                }

                File sd = currentDir.find( fPath[i] );

                if( sd == null || sd.getType() != 'd'  )
                {
                    System.err.println( datafile1 + " : ignoring line " + lineNum + " , fPath[" + i + "] = " + fPath[i] +" not found");
                    currentDir = null;
                    break;
                }
                currentDir = (Dnode)sd;
            }
            
            if( currentDir == null )        continue;
            
            String filename = fPath[i];
            
            if( ! currentDir.insertEntry( filetype.charAt(0), filename , filedata) )
            {
                System.err.println(" Line " + lineNum + " Failed to insert file! "); //error if failed to insertEntry
                continue;
            }  

        }  //exited while
            readIn.close();  
    }

    
}