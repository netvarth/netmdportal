

import java.util.HashMap;
import java.util.Map;

import com.nv.framework.util.StringUtil;


/**
 * represents the JNDI entry of a EJB Home
 * 
 * 
 */
public class EJBHomeType {
	private static final String CLASSNAME = EJBHomeType.class.getName();

	// todo-mike: standardize on env var name!

	// internal constants: PREFIXES
	private final static String EJB_PREFIX = System.getProperty(CLASSNAME + ".EJB_PREFIX", "ejb.");

	// internal repository of all ejb home types
	private final static Map ALL_EJB_HOME_TYPE = new HashMap();

	// instance variable
	private String entryValue;

	protected EJBHomeType(String entryValue) {
		this.entryValue = entryValue;
		ALL_EJB_HOME_TYPE.put(entryValue, this);
	}

	public final static String getEJBPrefix() { return EJB_PREFIX; }

	/**
	 * returns a valid instance of EJBHomeType, based on the String representation of the return object.
	 * 
	 * @param name 
	 * @return EJBHomeType instance for <code>name</code>
	 * @throws NoSuchFieldException 
	 */
	public static EJBHomeType getInstance(String name) throws NoSuchFieldException {
		// sanity check
		if (StringUtil.isBlank(name) || !ALL_EJB_HOME_TYPE.containsKey(name)) {
			throw new NoSuchFieldException("The JNDI name " + name + " is either invalid or currently undefined!");
		}

		return (EJBHomeType) ALL_EJB_HOME_TYPE.get(name);
	}

	public String toString() { return getEJBPrefix() + getValue(); }

	protected final String getValue() { return entryValue; }
}
