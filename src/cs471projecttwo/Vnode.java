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
public class Vnode implements File {      

    String mFileSystemName;
    
    //default constructor
    public Vnode()
    {
        mFileSystemName = "";
    }
    
    
    //constructor
    public Vnode(String name)
    {
        setmFileSystemName(name);
    }
    
    
    
    public char getType()
    {
        return 'v'; //for v-node
    }
    
    public void setmFileSystemName(String name)
    {
        mFileSystemName = name;
    }
    
    public boolean canDelete()
    {
        return true; //Vnode can always be deleted
    }
}
