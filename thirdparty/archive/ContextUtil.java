

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.nv.framework.util.io.PropertiesInfuser;
import com.nv.framework.util.StringUtil;



/**
 * JNDI-related utility class.  This class assumes a J2EE environment as RT.<p>
 * <p/>
 * This class currently supports J2EE 1.1 and above.
 * 
 * 
 */
public final class ContextUtil {
	private static final String CLASSNAME = ContextUtil.class.getName();
	private static final Logger logger = Logger.getLogger(CLASSNAME);
	//private static final Messages MESSAGES = Bootstrap.getMessages();
	private static final String JNDI_PREFIX = "java:comp/env";

	/* JNDI context factory class -- see "infusion.properties" */
	private static String DEFAULT_JNDI_FACTORY_CLASS = "";
	private static String JBOSS_JNDI_FACTORY_CLASS = "";
	private static String OPENJMS_JNDI_FACTORY_CLASS = "";
	private static String WEBLOGIC_JNDI_FACTORY_CLASS = "";
	private static String WEBSPHERE_JNDI_FACTORY_CLASS = "";

	static { PropertiesInfuser.infuseStatic(ContextUtil.class); }

	/**
	 * retrieve intial context based on connection information such as
	 * <code>url</code>, <code>username</code> and <code>password</code>.  This
	 * method assumes the use of default JNDI Factory class as defined in j2ee.properties file
	 * 
	 * @param url      
	 * @param username 
	 * @param password 
	 * @return context
	 * @throws javax.naming.NamingException if error occurrs while constructing the Context object
	 */
	public static Context getContext(String url, String username, String password) throws NamingException {
		// get things ready
		Hashtable env = prepareEnvironment(DEFAULT_JNDI_FACTORY_CLASS, url, username, password);

		// go for it!
		return new InitialContext(env);
	}

	/**
	 * retrieve a Websphere-specific intial context based on connection information such as
	 * <code>url</code>, <code>username</code> and <code>password</code>.  This
	 * method assumes the use of WebSphere JNDI Factory class as defined in j2ee.properties file
	 * 
	 * @param url      
	 * @param username 
	 * @param password 
	 * @return context
	 * @throws javax.naming.NamingException if error occurrs while constructing the Context object
	 */
	public static Context getWebSphereContext(String url, String username, String password) throws NamingException {
		// get things ready
		Hashtable env = prepareEnvironment(WEBSPHERE_JNDI_FACTORY_CLASS, url, username, password);

		// go for it!
		return new InitialContext(env);
	}

	/**
	 * retrieve a WebLogic-specific intial context based on connection information such as
	 * <code>url</code>, <code>username</code> and <code>password</code>.  This
	 * method assumes the use of WebLogic JNDI Factory class as defined in j2ee.properties file
	 * 
	 * @param url      
	 * @param username 
	 * @param password 
	 * @return context
	 * @throws javax.naming.NamingException if error occurrs while constructing the Context object
	 */
	public static Context getWeblogicContext(String url, String username, String password) throws NamingException {
		// get things ready
		Hashtable env = prepareEnvironment(WEBLOGIC_JNDI_FACTORY_CLASS, url, username, password);

		// go for it!
		return new InitialContext(env);
	}

	/**
	 * retrieve a JBoss-specific intial context based on connection information such as
	 * <code>url</code>, <code>username</code> and <code>password</code>.  This
	 * method assumes the use of JBoss JNDI Factory class as defined in j2ee.properties file
	 * 
	 * @param url      
	 * @param username 
	 * @param password 
	 * @return context
	 * @throws javax.naming.NamingException if error occurrs while constructing the Context object
	 */
	public static Context getJBossContext(String url, String username, String password) throws NamingException {
		// get things ready
		Hashtable env = prepareEnvironment(JBOSS_JNDI_FACTORY_CLASS, url, username, password);

		// go for it!
		return new InitialContext(env);
	}

	/**
	 * retrieve a OpenJMS-specific intial context based on connection information such as
	 * <code>url</code>, <code>username</code> and <code>password</code>.  This
	 * method assumes the use of OpenJMS JNDI Factory class as defined in j2ee.properties file
	 * 
	 * @param url      
	 * @param username 
	 * @param password 
	 * @return context
	 * @throws javax.naming.NamingException if error occurrs while constructing the Context object
	 */
	public static Context getOpenJMSContext(String url, String username, String password) throws NamingException {
		// get things ready
		Hashtable env = prepareEnvironment(OPENJMS_JNDI_FACTORY_CLASS, url, username, password);

		// go for it!
		return new InitialContext(env);
	}

	/**
	 * retrieve a custom intial context based on connection information such as
	 * <code>url</code>, <code>username</code> and <code>password</code>.  This
	 * method assumes the use of user-specified JNDI Factory class <code>jndiFactoryClass</code>.
	 * 
	 * @param url      
	 * @param username 
	 * @param password 
	 * @return context
	 * @throws javax.naming.NamingException if error occurrs while constructing the Context object
	 */
	public static Context getCustomContext(String jndiFactoryClass, String url, String username, String password)
	    throws NamingException {
		// sanity check
		if (StringUtil.isBlank(jndiFactoryClass)) { return null; }

		// get things ready
		Hashtable env = prepareEnvironment(jndiFactoryClass, url, username, password);

		// go for it!
		return new InitialContext(env);
	}

	public final static String getEnviromentPrefix() { return JNDI_PREFIX; }

	public static Object lookup(Context context, String jndiName) throws NamingException {
		// sanity check
		/*if (StringUtil.isBlank(jndiName)) {
			throw new IllegalArgumentException(MESSAGES.getMessage("EJBUtil.badEnvName"));
		}
		if (context == null) {
			throw new IllegalArgumentException(MESSAGES.getMessage("EJBUtil.badArguments", "context"));
		}
*/
		// get reference
//		Context env = (Context) context.lookup(getEnviromentPrefix());
//		return env.lookup(jndiName);
//		return context.lookup("java:comp/env/" + jndiName);
		return context.lookup(jndiName);
	}

	private static Hashtable prepareEnvironment(String jndiFactoryClass, String url, String username, String password) {
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, jndiFactoryClass);
		if (!StringUtil.isBlank(url)) { env.put(Context.PROVIDER_URL, url); }

		if (!StringUtil.isBlank(username)) {
			env.put(Context.SECURITY_PRINCIPAL, username);
			if (!StringUtil.isEmpty(password)) {
				env.put(Context.SECURITY_CREDENTIALS, password);
			}
		}
		return env;
	}
}
