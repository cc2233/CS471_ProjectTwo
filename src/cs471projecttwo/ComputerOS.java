/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs471projecttwo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author Caroline Chey
 */
public class ComputerOS {
    
    public java.util.Vector<FileSystem> mDrives; //to contain all FileSystems
    public FileSystem mRootDevice;
    
    
    //default constructor
    public ComputerOS()
    {
        mDrives = new java.util.Vector<FileSystem>();
        mRootDevice = null;
    }
    
    
    
    public class searchResult
    {
        String filename;
        Dnode dir;
        File file;
    }
    
    
    public Dnode lookupDrive(String name)
    {
        for(int i=0; i<mDrives.size(); ++i)
        {
            if (mDrives.get(i).mName.equals(name))
                return mDrives.get(i).mRoot;
        }
        return null;
    }
    
    
    
    public Dnode findPath( String fpath )
    {
        //get path components
        String[] path = fpath.split( "/" , -1 );
        //walk path till last component
        Dnode currentD = mRootDevice.mRoot;
        
        for( int i = 1; i < path.length - 1; ++ i)
        {
            File sd = currentD.find( path[i] );
            if( sd != null && sd.getType() == 'v' )
                sd = lookupDrive( ( (Vnode)sd ).mFileSystemName );
            if( sd == null || sd.getType() != 'd'  )
                return null;
            
            currentD = (Dnode)sd;
        }
        return currentD;
    }
    
    
    
    public searchResult findFile( String fpath )
    {
        String[] path = fpath.split( "/" , -1 );
        
        searchResult res = new searchResult();
        res.filename = null;
        res.dir = null;
        res.file = null;
        
        Dnode containingD = findPath( fpath );
        
        if( containingD != null )
        {
            String filename = path[ path.length - 1 ];
            File sd = containingD.find( filename );
            if( sd != null  )
            {
                res.filename = filename;
                res.file = sd;
                res.dir = containingD;
            }
        }
        return res;
    }
    
    
    
    public void load( String filename ) throws FileNotFoundException, IOException, NullPointerException, Exception
    {
        FileSystem f = new FileSystem();
        f.buildFileSystem( filename );
        mDrives.add(f); //adding to Vector<FileSystem>
        if( mDrives.size() == 1 )
        { //first drive is the root drive
            mRootDevice = f;
        }
    }


    public String printNodeData( File fil, String prefix )
    {
        String disp = ""; //string to be returned
        
        char typ = fil.getType();
        if( typ == 'i' )
        {
            //disp += ( (Inode)fil ).mFilename; //one-line data of Inode
            return disp;
        }

        if( typ == 'd' )
        {
            disp += printDir( (Dnode)fil, prefix );
            return disp;
        }

        if( typ == 'v' )
        {
            Dnode other_root = lookupDrive( ((Vnode)fil).mFileSystemName );
            if( other_root != null )
            {
                disp += printDir( (Dnode)other_root, prefix );
                return disp;
            } 
        }

        return disp;
    }



    public String printDir( Dnode atdir, String prefix )
    {
        String disp2 = ""; //string to return
        for( int i = 0 ; i < 5 ; ++i )
        {
            if( atdir.mEntries[i] != null && atdir.mEntries[i].content != null )
            {
                disp2 +=  "\n  " + ("     " + prefix + atdir.mEntries[i].filename  );
                String myprefix = prefix + atdir.mEntries[i].filename + "/" ;
                disp2 += printNodeData( atdir.mEntries[i].content , myprefix );
            }
        }
        return disp2;
    }



    public String printFS()
    {
        String disp3 = ""; //string to return
        disp3 += printDir( mRootDevice.mRoot , "/" );
        
        return disp3;
    }
    
    
    
    public String myToString()
    {
        String ret = "";
        for(int i=0; i<mDrives.size(); ++i)
        {
            ret += "FileSystem: " + mDrives.get(i).mName + "\n";
            for(int j=0; j<5; ++j)
            {
                if(mDrives.get(i).mRoot.mEntries[j].fileType == 'd')
                    ((Dnode)(mDrives.get(i).mRoot.mEntries[j].content)).dToString();
                ret += "\nfiletype: " + mDrives.get(i).mRoot.mEntries[j].fileType + " , filename: " + mDrives.get(i).mRoot.mEntries[j].filename; 
            }
            ret+="\n\n";
        }
        return ret;
    }
    
    
    
    //below are different versions of the above print functions
    //to generate actual file system structure view (instead of user view)
    
    public String printNodeDataForActual( File fil, String prefix )
    {
        String disp = ""; //string to be returned
        
        char typ = fil.getType();
        if( typ == 'i' )
        {
            disp += "     (" + ( ((Inode)fil).mData ) + ") "; //one-line data of Inode
            return disp;
        }

        if( typ == 'd' )
        {
            disp += printDirForActual( (Dnode)fil, prefix );
            return disp;
        }

        if( typ == 'v' )
        {
            return disp;
        } 
     
        return disp;
    }
    
    
    
    public String printDirForActual( Dnode atdir, String prefix )
    {
        String disp2 = ""; //string to return
        for( int i = 0 ; i < 5 ; ++i )
        {
            if( atdir.mEntries[i] != null && atdir.mEntries[i].content != null )
            {
                disp2 +=  "\n  " + ( atdir.mEntries[i].fileType + "     " + prefix + atdir.mEntries[i].filename  );
                String myprefix = prefix + atdir.mEntries[i].filename + "/" ;
                disp2 += printNodeDataForActual( atdir.mEntries[i].content , myprefix );
            }
        }
        return disp2;
    }
    
    
    
    public String printFSForActual()
    {
        String disp3 = ""; //string to return
        disp3 += printDirForActual( mRootDevice.mRoot , "/" );
        
        return disp3;
    }
}
