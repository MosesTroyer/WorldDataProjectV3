/*******************************************
 * Class: Logger
 * Description: This writes to the log.txt file, Solves the problems 
 * with trying to open buffered readers in each class 
 * Project: WorldDataProject V3
 * Author: Moses Troyer
 * For Dr. Kaminski's 3310 Data and File Structures, WMU
 *******************************************/

package SharedClassLibrary;

import java.io.*;

public class Logger {

    private static BufferedWriter log;
    
    //constructor, opens file
    public static void Logger() throws IOException {
        log = new BufferedWriter(new FileWriter("log.txt", true));
    } //end logger
    
    //************************PUBLIC METHODS************************//
    
    //writes the the log file
    public void write(String s) throws IOException {
        try{
        log.write(s);
        } catch (NullPointerException|IOException e){ 
            //The catch reopens the file if it was closed somewhere else
            log = new BufferedWriter(new FileWriter("log.txt", true));
            log.write(s);
        } //end catch
    } //end write
    
    //this just adds the "\n" to write, so I don't have to write it every time
    public void writeln(String s) throws IOException {
        write(s + "\n");      
    } //end writeln
    
    //be sure to call this when done using the logger
    public void close() throws IOException {
        try{
            log.close();
        } catch (NullPointerException e) {}
    } //end close
    
} //end logger class