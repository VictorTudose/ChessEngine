package bpod;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;



public class Main {

	
	public static void main (String[] args) throws Exception {
		// TODO Auto-generated method stub
        	 
            InputStreamReader in = new InputStreamReader(System.in);
 
            BufferedReader input = new BufferedReader(in);
 
            String str;
            int nr = 0;
            String line = null;
            while (true) {
            	line = input.readLine();
        		if(line.contains("protover 2")) {
        			System.out.println("feature sigint=0");
        		}
        		if(line.matches("^([a-h][1-8]+)+$")) {
        			//metoda actualizeaza board
        			//metoda calculeaza mutare
        			System.out.println("move g7g6");
        			System.out.println("resign");
        		}
            	nr++;
            }
 
	}
	
}

