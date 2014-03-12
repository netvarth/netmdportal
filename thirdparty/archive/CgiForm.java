

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.nv.framework.util.StringUtil;


/**
 * <code>CgiForm</code> class encapsulates <code>CGI</code> and
 * <code>HTTP</code> related information.  It provides easily to use and
 * Java-like methods to access <code>HTTP</code> request information.
 * Information that are captured are as follows:<br>
 * <ul>
 * <li>requested URL
 * <li>PATH_INFO
 * <li>USER_AGENT (browser signature)
 * <li>REMOTE_IP
 * <li>CGI Cookie for the current request
 * <li>CGI request name/value pair
 * </ul>
 * <p>Construction of this class requires <code>javax.servlet.http.
 * HttpServletRequest</code>, at which time the needed
 * <code>HTTP</code>-related information.
 * 
 * 
 */
public final class CgiForm implements Serializable {
	public static final String CLASSNAME = CgiForm.class.getName();
	static final long serialVersionUID = -1261322753661814915L;

	// constants
	public static final String PATH_INFO_DELIMITER = "/";
	public static final String USER_AGENT = "user-agent";

	// to store CGI information
	private final Map cgi = new HashMap();

	// to store cookie
	private final Map cookie = new HashMap();

	// to store useful information
	private String url = "";
	private String pathInfo = "";
	private String remoteIP = "";
	private String userAgent = "";
	private String[] pathInfoArray = new String[0];

	/**
	 * we get everything ready at the constructor method.
	 *
	 * @param request javax.servlet.http.HttpServletRequest represents the
	 *                user's HTTP request
	 * @author <a href="mailto:mike@javaexpert.org">Mike Liu</a>
	 */
	public CgiForm(HttpServletRequest request) {
		// get url

		// following changed due to deprecation of HttpUtils class
		//url = new String(HttpUtils.getRequestURL(request).toString());
		url = request.getRequestURL().toString();

		// get HTTP variables
		Enumeration keys = request.getParameterNames();
		String[] values;
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			values = request.getParameterValues(key);
			cgi.put(key, values);
		}

		// get cookies
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie.put(cookies[i].getName(), cookies[i]);
			}
		}

		// get other info
		remoteIP = request.getRemoteAddr();
		userAgent = request.getHeader(USER_AGENT);
		pathInfo = request.getPathInfo();
		if (!StringUtil.isBlank(pathInfo)) { parse(pathInfo); }
	}

	/**
	 * return the value of PATH_INFO variable in CGI
	 * 
	 * @return java.lang.String
	 */
	public String getPathInfo() { return pathInfo; }

	/**
	 * returns the REMOTE_IP variable for this <code>HTTP</code> request.
	 * 
	 * @return java.lang.String
	 */
	public String getRemoteIP() { return remoteIP; }

	/**
	 * return the browser signature that is represented in the current <code>HTTP</code> request.
	 * 
	 * @return java.lang.String
	 */
	public String getUserAgent() { return userAgent; }

	/**
	 * returns all the <code>Cookie</codE> objects that is sent via the current <code>HTTP</code> request.
	 * 
	 * @return javax.servlet.servlet.Cookie[] all the cookies not the dough.
	 */
	public Cookie[] getCookies() {
		// sanity check
		if (cookie == null || cookie.size() <= 0) { return null; }

		// make a cookie array
		Cookie[] cookies = new Cookie[cookie.size()];
		Iterator keys = cookie.keySet().iterator();
		int i = 0;
		while (keys.hasNext()) {
			cookies[i++] = (Cookie) cookie.get(keys.next());
		}

		// done!
		return cookies;
	}

	/**
	 * returns the number of "/" found in the PATH_INFO of the current <code>HTTP</code> request.
	 * 
	 * @return int
	 */
	public int getPathInfoDepth() { return (this.pathInfoArray == null ? 0 : this.pathInfoArray.length); }

	/**
	 * returns the requested URL of this <code>HTTP</code> request.
	 * 
	 * @return java.lang.String
	 */
	public String getRequestURL() { return url; }

	/**
	 * parse CGI PATH_INFO into array
	 * 
	 * @param path java.lang.String
	 */
	private void parse(String path) {
		// sanity check
		if (StringUtil.isBlank(path)) { return; }

		// parse it out
		StringTokenizer stkn = new StringTokenizer(path, PATH_INFO_DELIMITER);
		this.pathInfoArray = new String[stkn.countTokens()];
		int counter = 0;
		while (stkn.hasMoreTokens()) { this.pathInfoArray[counter++] = stkn.nextToken(); }

		// done!
		return;
	}

	/**
	 * return the <code>Cookie</code> object in the current <code>HTTP</code> request represented by the
	 * <code>cookieName</code>.
	 * 
	 * @param cookieName java.lang.String the cookie name.
	 * @return javax.servlet.servlet.Cookie the cookie object.
	 */
	public Cookie getCookie(String cookieName) {
		try { return (Cookie) cookie.get(cookieName); } catch (Exception e) { return null; }
	}

	/**
	 * returns the appropriate index of the <code>PATH_INFO</code> variable. The index, in this case, is determined by the
	 * "/" found in the <code>PATH_INFO</code> variable.
	 * 
	 * @param index int
	 * @return java.lang.String
	 */
	public String getPathInfo(int index) {
		try {
			return this.pathInfoArray[index];
		} catch (NullPointerException e) {
			return null;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * return the first CGI value, as a <code>java.lang.String</code>, that matches the <code>key</code> paramter name. In
	 * CGI environment, all values are passed as array, even if there is only one item in the array.  Thus, this method is
	 * created to differentiate between multiple values of the same key and single value of one key.
	 * 
	 * @param key java.lang.String the parameter name
	 * @return java.lang.String
	 */
	public String getFirstValue(String key) {
		return isKeyExists(key) ? ((String[]) cgi.get(key))[0] : null;
	}

	/**
	 * returns all the CGI values, as a <code>java.lang.String[]</code>, that is associated with the <code>key</code>.  In
	 * CGI environment, all values are passed as array, even if there is only one item in the array.  Thus, this method is
	 * created to differentiate between multiple values of the same key and single value of one key.
	 * 
	 * @param key java.lang.String the parameter name
	 * @return java.lang.String[]
	 */
	public String[] getValue(String key) { return (isKeyExists(key) ? (String[]) cgi.get(key) : null); }

	/**
	 * verify if the <code>key</code> parameter name exists for this <code>HTTP</code> request.
	 * 
	 * @param key java.lang.String the parameter name
	 * @return boolean true if the <code>key</code> exists.
	 */
	public boolean isKeyExists(String key) { return cgi.containsKey(key); }
}
