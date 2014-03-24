package com.nv.youNeverWait.user.bl.validation;

import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.util.filter.core.Property;
import com.nv.youNeverWait.util.filter.validation.FilterValidator;
import com.nv.youNeverWait.util.filter.queryBuilder.InstallerPropertyEnum;

public class InstallerValidator extends FilterValidator{

	public ErrorDTO validateInstallerFilter(FilterDTO filter) {
		
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = InstallerPropertyEnum.valueOf(exp.getName());
			} catch (IllegalArgumentException e) {
				error = getInvalidExpNameError(exp);
				return error;
			}
			error = validateExp(exp, property);
			if (error != null) {
				return error;
			}
		}
		return null;
		
	}
	

}
