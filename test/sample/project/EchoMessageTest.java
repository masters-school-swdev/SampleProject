package sample.project;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class EchoMessageTest {

	/**
	 * JUnit4 test methods are marked with the Test annotation,
	 * signifying to the TestRunner that this method should be
	 * executed and assert success or failure.
	 */
	@Test
	public void testGetMessage() {
		// Using a hard-coded string like this should be
		// avoided. In Java, properties files are used to
		// load external resources, such as Strings and Images.
		// But, for a quick introduction, a hard-coded string
		// will demonstrate the Test execution.
		//
		// In addition to creating a properties file there
		// is another way we could access the String,
		// "Hello World" for our test. The Main and MainProvider
		// classes can be altered slightly to expose DEFAULT_TEXT.
		// Can you find out how?
		//
		// Note that a properties file would be preferable 
		// in most cases but for many simple implementations
		// this second alternative solves the problem and
		// does not introduce any security or functional bugs.
		assertEquals("Hello World", 
				new EchoMessage().getMessage());
	}

	/**
	 * This test demonstrates that JUnit will fail. It is
	 * important to include negative test cases, such as
	 * proving that in our example getText() does _not_ 
	 * return an empty String.
	 * <p>
	 * In normal test suites you'd want negative test cases
	 * but not failing test cases like this one.
	 */
	@Test
	public void testGetMessageThatFails() {
		assertEquals("", 
				new EchoMessage().getMessage());
	}
	/**
	 * To complete the point about negative test cases... 
	 */
	@Test
	public void testGetMessageNegativeTest() {
		// Notice the signature of the assertNotEquals method,
		// the two arguments on this one are Object _not_ String.
		// 
		// Internally, JUnit is calling the Object#equals() method
		// to perform the comparison. If you set a breakpoint here
		// and StepInto the assertNotEquals method you will see the
		// code execution path.
		assertNotEquals("", 
				new EchoMessage().getMessage());
	}
	
	/**
	 * Test that Main consumes an interface passed into the
	 * Main(SampleProvider) constructor.
	 * <p>
	 * In a future demonstration, we will see the use of a "mock"
	 * library. Mocks are a powerful testing paradigm allowing you
	 * to create temporary implementations of interfaces and/or 
	 * temporarily override methods to validate functionality without
	 * altering production code.
	 */
	@Test
	public void testOverrideProvider() {
		// In lieu of a mocking library, we use an internal
		// implementation of SampleProvider
		//
		// Note in this case, we have complete control of
		// our expected String - unlike MainProvider - 
		// therefore using a hard-coded String in the test
		// method is perfectly acceptable.
		assertEquals("Test Provider", 
			new EchoMessage(
				new TestProvider("Test Provider")
			).getMessage());
	}

	/**
	 * Before Java 8, Java did not support higher-order functions,
	 * unlike Python, C#, JavaScript and others. Higher-order functions
	 * are functions that take other functions as parameters.
	 * <p>
	 * In Java 6 & 7, you may use anonymous classes to get closely
	 * approximate the power of higher-order functions.
	 * <p>
	 * Without a mocking library, using anonymous classes in unit tests
	 * can reduce the clutter of class implementations whose sole 
	 * purpose is for a single test. The important caveat is, if you
	 * want to reuse a test implementation creating a full class is 
	 * cleaner than copy-paste the same anonymous class over and over!!
	 * <p>
	 * You could also instantiate the anonymous class, store it in a 
	 * field on your test class and reference it multiple times.
	 * Such as,
	 * <blockquote>
	 * <pre>
	 * <code>
	 * private TextProviderInterface tpi = new TextProviderInterface() {...};
	 * </code>
	 * </pre>
	 * </blockquote>
	 */
	@Test
	public void testTextProviderAnonymousClass() {
		assertEquals("AnonClass",
			new EchoMessage(
				// anonymous class implementing TextProviderInterface
				new TextProviderInterface() {
					public String getText() {
						return "AnonClass";
					}
				}
			).getMessage());
	}
	
	/**
	 * All the tests above demonstrate that EchoMessage and TextProvider interfaces are interacting properly.
	 * The only thing left to test is the echo method of EchoMessage. 
	 */
	@Test
	public void testEcho() {
		// an output stream to collect our echoed message
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// Java uses the Decorator pattern heavily, for things like this:
		// a new PrintStream object uses our byte array outputstream as its
		// destination stream. But the object doing the writing, our EchoMessage,
		// can use methods added by PrintStream. In the case of our echo(PrintStream out) method
		// we utilize the println(String str) method which exists in PrintStream but
		// does not exist in OutputStream.
		
		// Review the Javadoc for PrintStream and its class heirarchy FilterOutputStream and OutputStream.
		// http://docs.oracle.com/javase/7/docs/api/java/io/PrintStream.html
		new EchoMessage().echo(new PrintStream(baos));
		
		
		// Like the additional method println added to PrintStream, using ByteArrayOutputStream
		// is an implementation of OutputStream that allow us to grab to bytes of data that were
		// just written.
		String actual_message = baos.toString();
		
		// Here we assert that our echo method did what we wanted. Note that the result
		// is _not_ equal to the default text, "Hello World". The string "\n" represents
		// a newline character in a Java String.
		
		// Do you understand why it is correct to expect that a newline character was added
		// to our message when we called echo?
		assertEquals("Hello World\n", actual_message);
		
	}
	
	
	
	/**
	 * Use solely for unit tests in MainTest.
	 * 
	 * @author vagrant
	 *
	 */
	private static class TestProvider implements TextProviderInterface {

		private String _text;
		public TestProvider(String text) {
			this._text = text;
		}
		@Override
		public String getText() {
			return this._text;
		}
		
	}
}
