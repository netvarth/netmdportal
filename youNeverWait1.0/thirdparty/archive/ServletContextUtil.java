

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.nv.framework.util.StringUtil;




/**
 * Utility class for ServletContext.
 * 
 *
 */
public final class ServletContextUtil {
	private static final String CLASSNAME = ServletContextUtil.class.getName();
	private static final Logger logger = Logger.getLogger(CLASSNAME);

	public static void addToContext(ServletContext currentServletContext,
	                                String contextName,
	                                String objectName,
	                                Object objectToAdd) {
		// sanity check
		if (currentServletContext == null) {
			logger.warn("Unable to add object to context because 'currentServletContext' is null");
			return;
		}
		if (StringUtil.isBlank(contextName)) {
			logger.warn("Unable to add object to context because 'contextName' is null/empty");
			return;
		}
		if (StringUtil.isBlank(objectName)) {
			logger.warn("Unable to add object to context because 'objectName' is null/empty");
			return;
		}
		if (objectToAdd == null) {
			logger.warn("Unable to add object to context because 'objectToAdd' is null");
			return;
		}

		logger.debug("context=" +
		             currentServletContext +
		             ", contextName=" +
		             contextName +
		             ", objectName=" +
		             objectName +
		             ", objectToAdd=" +
		             objectToAdd);

		ServletContext newContext = currentServletContext.getContext(contextName);
		if (newContext == null) {
			logger.warn("Unable to add object to context because context '" + contextName + "'  does not exist");
			return;
		}

		// double check
		Object existedObject = newContext.getAttribute(objectName);
		if (existedObject == null || existedObject.equals(objectToAdd)) {
			newContext.setAttribute(objectName, objectToAdd);
			logger.info("setting " + objectToAdd + " to '" + contextName + "' as '" + objectName + "'");
		}
	}
}
