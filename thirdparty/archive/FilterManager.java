

import java.util.ArrayList;
import java.util.List;

/**
 * This class maintains a list of Filters.
 * 
 * 
 */
public class FilterManager {
	private List filters = new ArrayList();

	public void addFilter(StreamFilter filter) {
		filters.add(filter);
	}

	public List getFilters() {
		return filters;
	}
}
