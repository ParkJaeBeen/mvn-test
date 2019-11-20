package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CharTest 
{
	public static void main(String[] args) {
		String sPath = "C:\\test.txt";
		String tPath = "C:\\test1.txt";
		try {
			FileInputStream fis = new FileInputStream(sPath);
			InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String str  = null;
			String string = "";
			while((str=br.readLine()) != null) {
				System.out.println(str);
				string += str;
			}
			
			FileOutputStream fos = new FileOutputStream(tPath);
			OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(string);
			bw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
