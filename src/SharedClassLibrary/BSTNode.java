/*******************************************
 * Class: BSTNode
 * Description: 
 * Project: WorldDataProject V3
 * Author: Moses Troyer
 * For Dr. Kaminski's 3310 Data and File Structures, WMU
 *******************************************/

package SharedClassLibrary;

public class BSTNode {
    
    private short leftChPtr = -1, rightChPtr = -1, drp;
    private String name;
    
    public BSTNode(String n, short d) {
        name = n;
        drp = d;
    } //end constructor
    
    //************************PUBLIC METHODS************************//
    
    public void setLeft(short l){
        leftChPtr = l;
    }
    
    public void setRight(short r){
        rightChPtr = r; 
   }
    
    public short getLeft(){
        return leftChPtr;
    }
    
    public short getRight(){
        return rightChPtr;
    }
    
    public short getDRP(){
        return drp;
    }
    
    public String getName(){
        return name;
    }
    
} //end BSTNode class
