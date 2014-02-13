/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

import javax.mail.Multipart;

/**
 * @author Joshi
 *
 */
public class MailResult {
	private String to;
	private String from;
	private Multipart body;
	private String subject;
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public Multipart getBody() {
		return body;
	}
	public void setBody(Multipart body) {
		this.body = body;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
