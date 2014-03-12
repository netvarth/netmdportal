/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Joshi
 *
 */
public class MailTransferInfo {
	private String to;
	private String from;
	private byte [] body;
	private String subject;
	/**
	 * @return to
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to
	 */
	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * @return from
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the body
	 */
	public byte [] getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(byte [] body) {
		this.body = body;
	}
}
