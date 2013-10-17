/*******************************************
 * Class: NameIndex
 * Description: 
 * Project: WorldDataProject V3
 * Author: Moses Troyer
 * For Dr. Kaminski's 3310 Data and File Structures, WMU
 *******************************************/

package SharedClassLibrary;

public class NameIndex {
    
    private BSTNode[] BST = new BSTNode[50];
    private short rootPtr = -1, n;
    
    public NameIndex(){
        rootPtr = -1;
        n = 0;
    } //end constructor
    
    //************************PUBLIC METHODS************************//
    
    public void addCountry(String name, short ID){
        if(n == 0){
            BST[n] = new BSTNode(name, ID);
            rootPtr = 0;
            n++;
        }
        else {
            findPosition(name, rootPtr);
            BST[n] = new BSTNode(name, ID);
            n++;
        }
    } //end addCountry
    
    //test method to print out the BST
    public void printBST(){
        int i;
        for(i=0;i<n;i++){
            System.out.println(BST[i].getLeft() + " " + 
                    BST[i].getName() + " " +
                    BST[i].getDRP() + " " +
                    BST[i].getRight() + " ");
        }
    } //end printBST
    
    //************************PRIVATE METHODS************************//
    
    //PASS ROOT PTR OF TREE, DOESNT HAVE TO BE JUST THE TOP!!
    //So the passed root pointer could be a subtree
    //IOT = In Order Traversal
    //big call
    //IOT(root of A bst)
    //if root ptr == -1 do nothing
    //else
    
    //LN LISTS IN ALPHABETICAL!!
    
    //this finds where the new country should go in the tree and updates
    //the fields before it accordingly. 
    private void findPosition(String name, int rootPointer){
        if(name.compareTo(BST[rootPointer].getName()) > 0){ //to the RIGHT
            if(BST[rootPointer].getRight() != -1)
                findPosition(name, BST[rootPointer].getRight());
            else BST[rootPointer].setRight(n);
        } else if(name.compareTo(BST[rootPointer].getName()) < 0){ //to the LEFt
            if(BST[rootPointer].getLeft() != -1)
                findPosition(name, BST[rootPointer].getLeft());
            else BST[rootPointer].setLeft(n);
        } else {
            System.out.println("Error: Country already exists!");
        }
    } //end FindPosition
    
} //end NameIndex class
