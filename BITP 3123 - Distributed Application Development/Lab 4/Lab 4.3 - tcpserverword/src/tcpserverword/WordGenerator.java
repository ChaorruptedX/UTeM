/**
 * 
 */
package tcpserverword;
import java.util.Date;

/**
 * This class process length of a text.
 * 
 * @author - Chaorrupted X -
 *
 */
public class WordGenerator {

	/**
	 * 
	 */
	public WordGenerator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method process length of a text.
	 * 
	 * @param args
	 * @return word length
	 */
	public String getWordCount() {
		// TODO Auto-generated method stub
		
		String string = "Hello there. Nice to meet you!"; // The Input Words   

		Integer number_of_words = countWord(string);
		String wordCounts = number_of_words.toString() + " words.";
		
		return wordCounts;
	}
	
	static Integer countWord(String string)  
    {  
		int count=0;  
	  
        char ch[]= new char[string.length()];     
        for(int i=0;i<string.length();i++)  
        {  
            ch[i]= string.charAt(i);  
            if( ((i>0)&&(ch[i]!=' ')&&(ch[i-1]==' ')) || ((ch[0]!=' ')&&(i==0)) )  
                count++;  
        }
        
        return count;  
    }  
}