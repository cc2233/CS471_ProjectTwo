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
public class CS471ProjectTwo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VFS_GUI().setVisible(true);
            }
        });
    }
    
}
