/**
 * 
 */
package sample.project;

import java.io.PrintStream;

/**
 * 
 * A sample Java application demonstrating:
 * <ul>
 * <li>command-line execution</li>
 * <li>Eclipse project setup</li>
 * <li>JUnit4 setup</li>
 * </ul>
 * 
 * @author vagrant
 *
 */
public class EchoMessage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EchoMessage m = new EchoMessage(new InputStreamTextProvider(System.in));
		
		System.out.println("Enter: ");
		m.echo(System.out);
		
	}


	private TextProviderInterface _provider;

	/**
	 * Construct a Main object using the default
	 * SampleProvider, MainProvider
	 */
	public EchoMessage() {
		this(new MainProvider());
	}
	
	/**
	 * Construct a Main object using an implementation
	 * of SampleProvider interface
	 * 
	 * @param provider
	 */
	public EchoMessage(TextProviderInterface provider) {
		this._provider = provider;
	}
	
	public String getMessage() {
		return this._provider.getText();
	}
	
	public void echo(PrintStream out) {
		out.println(getMessage());
	}
	
	/**
	 * Internal implementation of the SampleProvider interface
	 * 
	 * @author vagrant
	 *
	 */
	private static class MainProvider implements TextProviderInterface {
		
		private static final String DEFAULT_TEXT = "Hello World";
	
		@Override
		public String getText() {
			return DEFAULT_TEXT;
		}
	}
}
