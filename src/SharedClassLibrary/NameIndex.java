/*******************************************
 * Class: NameIndex
 * Description: 
 * Project: WorldDataProject V3
 * Author: Moses Troyer
 * For Dr. Kaminski's 3310 Data and File Structures, WMU
 *******************************************/

//ask about special cases at the bottom
//ask about logger being userinterface?
//see if formatting is acceptable  
//status message on creaitng from backup

package SharedClassLibrary;

import java.io.*;
import java.text.NumberFormat;

public class NameIndex {
    
    private BSTNode[] BST = new BSTNode[50];
    private short rootPtr = -1, n;
    private MainData md;
    private UserInterface log;
    private int nameLength = 18;
    private int nodesVisited;
    
    public NameIndex() throws IOException {
        log = new UserInterface();
        log.writeln("NameIndex object created");
        
        rootPtr = -1;
        n = 0;
        
        log.finishUp('l');
    } //end constructor
    
    //overloaded constructor that specifies N and root
    public NameIndex(short rootPointer, short countries) throws IOException {
        log = new UserInterface();
        log.writeln("NameIndex object created");
        
        rootPtr = rootPointer;
        n = countries;
        
        log.finishUp('l');
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
    
    //writes the BST to a .bin file
    public void writeBackupBST() throws IOException {
        int i, c;
        log = new UserInterface();
        String m;
        
        RandomAccessFile backup = new RandomAccessFile("IndexBackup.bin", "rw");
        log.writeln("opened IndexBackup file");
        
        //header
        backup.writeShort(rootPtr);
        backup.writeShort(n);
        
        //writes each country
        for(i=0;i<n;i++){
            backup.writeShort(BST[i].getLeft());
            
            m = BST[i].getName();
            for(c=0;c<nameLength;c++){
                if(c<m.length()) backup.writeChar(m.charAt(c));
                else backup.writeChar(' ');
            }
            
            backup.writeShort(BST[i].getDRP());
            backup.writeShort(BST[i].getRight());
        }
        
        log.writeln("closed IndexBackup file");
       
        backup.close(); 
        log.finishUp('l');
    } //end writeBackup
    
    public void recoverFromBackup() throws IOException {
        int i, c, nameLength = 18;
        short l, r, drp;
        String name;
        log = new UserInterface();
        
        RandomAccessFile backup = new RandomAccessFile("IndexBackup.bin", "r");
        log.writeln("opened IndexBackup file");
        
        rootPtr = backup.readShort();
        n = backup.readShort();
        
        for(i=0;i<n;i++){
            name = "";
            l = backup.readShort();
            for(c=0;c<nameLength;c++) name += backup.readChar();
            drp = backup.readShort();
            r = backup.readShort();
            
            BST[i] = new BSTNode(l, name, drp, r);
        }
     
        log.writeln("closed IndexBackup file");
        
        backup.close();
        log.finishUp('l');
    } //end recoverFromBackup
    
    //reads and prints all of the nodes in Alphabetical order
    public void listAllByName(MainData m) throws IOException {
        log = new UserInterface();
        md = m;
        
        log.writeln("CODE NAME--------------  CONTINENT----  ---POPULATION  L.EXP");
        inOrderTraversal(rootPtr);
        
        log.finishUp('l');
    } //end listAllByName
    
    public void queryByName(String target, MainData m) throws IOException {
        md = m;
        int i;
        String[] fullTarget;
        nodesVisited = 0;
        
        //formmatiing the input
        fullTarget = target.split(" ");
        
        target = "";
        //capitalizes first letter of each word
        for(i=0;i<fullTarget.length;i++){
            fullTarget[i] = fullTarget[i].toLowerCase();
            if(!fullTarget[i].equals("")) {fullTarget[i] = Character.toUpperCase(fullTarget[i].charAt(0)) + fullTarget[i].substring(1);
                if(i == fullTarget.length-1) target += fullTarget[i];
                else target += fullTarget[i] + " ";
            }
        }
        
        
        if(!BSTSearch(target, rootPtr)) log.writeln("**ERROR: no country named " + target);
        
        log.writeln("  [" + nodesVisited + " BST nodes visited]");
    } //end queryByName
    
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
    
    //for list all by id
    //gets everything to its left, then prints itself, then prints everything to the right
    private void inOrderTraversal(int root) throws IOException {
        String[] line;
        int i, popLength = 13;
        
        if(BST[root].getLeft() != -1){
            inOrderTraversal(BST[root].getLeft());
        }
        line = md.getThisData(BST[root].getDRP());
        
        line[3] = NumberFormat.getNumberInstance().format(Integer.parseInt(line[3]));
        
        log.write(line[0] + "  " + 
                line[1] + "  " + 
                line[2] + "  ");
        for(i=0;i<(popLength - line[3].length());i++){
            log.write(" ");
        }
        log.writeln(line[3] + "  " + line[4]);        
        
        if(BST[root].getRight() != -1){
            inOrderTraversal(BST[root].getRight());
        }
                  
    } //end inOrderTraversal
    
    //finds a match of the target and prints the country if it finds one
    //returns true if found
    private boolean BSTSearch(String target, int rootPointer) throws IOException {
        String[] line;
        int i;
        nodesVisited++;

        if(target.equalsIgnoreCase(BST[rootPointer].getName().trim())){
            line = md.getThisData(BST[rootPointer].getDRP());
            log.writeln(line[0] + " " +
                    line[1] + " " +
                    line[2] + " " +
                    line[3] + " " +
                    line[4]);
            return true;
        }
        if(target.compareTo(BST[rootPointer].getName()) > 0){ //to the RIGHT
            if(BST[rootPointer].getRight() != -1) 
                return BSTSearch(target, BST[rootPointer].getRight());
            else return false;
        } else if(target.compareTo(BST[rootPointer].getName()) < 0){ //to the LEFT
            if(BST[rootPointer].getLeft() != -1) 
                return BSTSearch(target, BST[rootPointer].getLeft());
            else return false;
        }
        
        return false;
    } //end BSTSearch
    
} //end NameIndex class
