/**
 * 
 */
package sample.project;

import org.hamcrest.BaseDescription;

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
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Main m = new Main();
		
		System.out.println(
				m.getProvider().getText());
		
	}


	private TextProviderInterface _provider;

	/**
	 * Construct a Main object using the default
	 * SampleProvider, MainProvider
	 */
	public Main() {
		this(new MainProvider());
	}
	
	/**
	 * Construct a Main object using an implementation
	 * of SampleProvider interface
	 * 
	 * @param provider
	 */
	public Main(TextProviderInterface provider) {
		this._provider = provider;
	}
	
	public TextProviderInterface getProvider() {
		return this._provider;
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
