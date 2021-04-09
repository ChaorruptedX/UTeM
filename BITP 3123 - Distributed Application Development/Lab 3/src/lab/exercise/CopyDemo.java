package lab.exercise;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyDemo {

	public static void main(String[] args) {
	
		try
		{
			FileInputStream in = new FileInputStream("ProductUML.png");
			FileOutputStream ou = new FileOutputStream("ProductUML2.png");
			BufferedInputStream bin = new BufferedInputStream(in);
			BufferedOutputStream bou = new BufferedOutputStream(ou);
			
			int b=0;
			
			while(b!=-1)
			{
				b=bin.read();
				bou.write(b);
			}
			
			bin.close();
			bou.close();
		}
		catch (IOException e)
		{
			System.out.println("we got problem");

			e.printStackTrace();
		}

			System.out.println("Successfully Copy !");
	}
}