/*******************************************
 * Class: MainData
 * Description: Interacts with the MainData.txt file. In this project,
 *              all that this will need to do is read a country line.
 * Project: WorldDataProject V3
 * Author: Moses Troyer
 * For Dr. Kaminski's 3310 Data and File Structures, WMU
 *******************************************/

package SharedClassLibrary;

import java.io.*;

public class MainData {
    private Logger log = new Logger();
    private RandomAccessFile mdInfile; 
    private int[] mdLength = {3, 18, 13, 10, 4}; //code, name, continent, population, lifeExpectancy
    private int[] reqLengths = {3, 21, 34, 44, 48}; // each added up
    
    public MainData() throws IOException{
        //log.writeln("MainData object created");
        
        mdInfile = new RandomAccessFile("MainDataA3.txt", "r");
        //log.writeln("opened MainData file");
    } //end MainData Constructor
    
     //************************PUBLIC METHODS************************//
    
    //reads a line of data from MainData.txt and returns 
    //it in the form of a string array
    public String[] getThisData(int r) throws IOException {
        int i;
        String line;
        String[] data = new String[mdLength.length];
        
        mdInfile.seek(seek(r));
        line = mdInfile.readLine();
        
        //splits the line up into the array
        for(i=0;i<mdLength.length;i++){
            if(i == 0)
                data[i] = line.substring(0, reqLengths[i]);
            else 
                data[i] = line.substring(reqLengths[i-1], reqLengths[i]);
        }
  
        return data;
    } //end readOneRecord
    
    //closes the file. Be sure to call when you finish using maindata
    public void closeFile() throws IOException {
        //log.writeln("closed MainData file");
        mdInfile.close();
        log.close();
    } //end closeFile
    
    //************************PRIVATE METHODS************************//
    
    //gets the byte offset when seeking for a record
    private int seek(int r){
        int offset = 0, i;
        for(i=0;i<mdLength.length;i++){
            offset += mdLength[i];
        }
        
        return (offset + 2) * (r-1); //plus 2 for <CR><LF>
    } //end seek
    
} //end mainData Class
