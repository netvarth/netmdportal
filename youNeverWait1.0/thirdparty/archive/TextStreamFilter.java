

import java.io.IOException;
import java.io.InputStream;
import org.apache.log4j.Logger;

/**
 * This class is one of the possible implementation of StreamFilter interface. It checks to see if all characters in
 * the stream is printable. Printable character will one satisfy the following conditions:
 * a, it is one of the following characters: CR, LF, TAB, or SPACE
 * b, its range is from 33 to 126.
 * 
 * 
 */
public class TextStreamFilter implements StreamFilter {
	private static final String CLASSNAME = TextStreamFilter.class.getName();
	private static final Logger logger = Logger.getLogger(CLASSNAME);

	private byte[] buf = new byte[8 * 1024];

	/**
	 * This method checks to see if the input stream contains ASCII PRINTABLE characters only.
	 * 
	 * @param inputStream the input stream we are checking
	 * @return true if it contains only ASCII data, false if it has binary data
	 */
	public boolean validate(InputStream inputStream) {
		// we know that the input stream is ISO-8859-1 encoded, at least it is from the way we use it.
		// we now check every character to see if it is ASCII PRINTABLE.

		int index = 0;
		int realSize = 0;

		try {
			while ((realSize = inputStream.read(buf, index, buf.length)) != -1) {
				for (int i = 0; i < realSize; i++) // we only check what is really in the byte array
				{
					byte b = buf[i];
					if ((b >= 32 && b < 127) || b == 9 || b == 10 || b == 13)
					// this is the printable range. Starting from "!"(21H)(33) to "~"(7EH)(126) plus CR, CF, SPACE and tab.
					{
					} else {
						logger.error("Some character in the input stream is not printable: " + b);
						return false;
					}
				}
			}
		} catch (IOException e) {
			return false;
		}

		return true;
	}



	// this is the test driver.
/*
	public static void main(String[] args) throws FileNotFoundException
	{
		FileInputStream fi = new FileInputStream("c:\\test.wav");
		TextStreamFilter tsf = new TextStreamFilter();
		boolean result = tsf.process(fi);
		System.out.println(result ? "TRUE" : "FALSE");
	}
*/

}

