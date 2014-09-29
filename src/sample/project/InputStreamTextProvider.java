package sample.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Capture text from the user
 */
public class InputStreamTextProvider implements TextProviderInterface {

    private InputStream is;

    public InputStreamTextProvider(InputStream is)
    {
	this.is = is;
    }
    
    @Override
    public String getText() {
	StringBuilder sb = new StringBuilder();
	BufferedReader br = new BufferedReader(new InputStreamReader(this.is));
	int nextChar = -1;
	try {
	    // read input until a newline character, e.g. the user presses the Enter key
	    while((nextChar = br.read()) != -1)
	    {
		char ch = (char)nextChar;
		if (ch == '\n') break;
	        sb.append(ch);
	    }
	} catch (IOException e) {
	    return null;
	}
	
	return sb.toString();
    }

}
