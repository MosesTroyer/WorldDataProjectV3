/*******************************************
 * Class: RawData
 * Description: 
 * Project: WorldDataProject V3
 * Author: Moses Troyer
 * For Dr. Kaminski's 3310 Data and File Structures, WMU
 *******************************************/

package SharedClassLibrary;

import java.io.*;

public class RawData {
    
    private Logger log = new Logger();
    private BufferedReader input = null;

    public RawData() throws IOException {
        System.out.println("RawData object created");
        log.writeln("RawData object created");
        
        FileReader inFile = new FileReader("RawDataA3.csv");
        input = new BufferedReader(inFile);
        System.out.println("opened RawData file");
        log.writeln("opened RawData file");
        
    } //end RawData constructor
    
    //************************PUBLIC METHODS************************//
    
    public String[] readLine() throws IOException {
        String line;
        if ((line = input.readLine()) != null){
            String fields[] = line.split(",");
            return fields;
        }
        return null;
    } //end readLine
    
    //Closes the input file
    public void closeFile() throws IOException {
        System.out.println("closed RawData file");
        log.writeln("closed RawData file");
        
        input.close();
        log.close();
    } //end closeFile
    
}