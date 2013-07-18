

import java.io.InputStream;

/**
 * This is the interface that defines the way we can process an input stream. We can have different implementation according
 * to different situations. One of the implementation checks to see if all characters are printable or not.
 * 
 * */

public interface StreamFilter {

	/**
	 * Checks to see if an input stream is valid or not.
	 * 
	 * @param inputStream 
	 * @return true if valid, false if not.
	 */
	public boolean validate(InputStream inputStream);

}
