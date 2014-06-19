/**
 * Criteria.java
 *
 * Aug 24, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.NetlimsPortal.util.filter.core;

import javax.persistence.criteria.Predicate;

public class Criteria {
	private Predicate predicate;
	private Expression expression;
	
	/**
	 * @return the predicate
	 */
	public Predicate getPredicate() {
		return predicate;
	}
	/**
	 * @param predicate the predicate to set
	 */
	public void setPredicate(Predicate predicate) {
		this.predicate = predicate;
	}
	/**
	 * @return the expression
	 */
	public Expression getExpression() {
		return expression;
	}
	/**
	 * @param expression the expression to set
	 */
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
}
