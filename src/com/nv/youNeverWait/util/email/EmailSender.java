package com.nv.youNeverWait.util.email;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.nv.framework.util.ClassUtil;
import com.nv.framework.util.StringUtil;
import com.nv.framework.util.collection.ArrayConverter;

/**
 * Utility to send email via SMTP.
 * 
 * 
 */

public class EmailSender {
	// todo-mike: needs to rework on the sendMail methods to 1)
	// streamline/consolidate/refactor, 2) better method signature

	private static final String CLASSNAME = EmailSender.class.getName();
	private static final Logger log = Logger.getLogger(EmailSender.class);
	// constants
	private static final String DEF_MAIL_HOST = "mail.smtp.host";
	private static final String DEFAULT_MAIL_HOST = System.getProperty(
			CLASSNAME + ".DEFAULT_MAIL_HOST", "default_mail_server.com");

	private static final String TEXT_TYPE = "text/plain";
	@SuppressWarnings("unchecked")
	private static final ArrayList INVALID_EMAIL_CHARACTERS = (ArrayList) ArrayConverter
	.toList(new String[] { "!", "?", "[", "]", "+", "&", "{", "}", "#",
			"(", ")", "*", "=", ":", "/", " " });

	/**
	 * The purpose of this class is for handling byte array attachments for
	 * emails.
	 */
	static class ByteArrayDataSource implements DataSource {
		private byte[] data;
		private String type;

		public ByteArrayDataSource(byte[] data, String type) {
			this.data = data;
			this.type = type;
		}

		public InputStream getInputStream() throws IOException {
			if (data == null) {
				throw new IOException("no data to be read!!");
			}
			return new ByteArrayInputStream(data);
		}

		public OutputStream getOutputStream() throws IOException {
			throw new IOException("this operation is not supported!!");
		}

		public String getName() {
			return ClassUtil.getName(ByteArrayDataSource.class);
		}

		public String getContentType() {
			return type;
		}

	}

	/**
	 * singleton constr
	 */
	private EmailSender() {
	}

	/**
	 * @param email
	 *            java.lang.String
	 * @return java.lang.String[]
	 */
	public static String[] getEmailElements(String email)
	throws AddressException {
		String[] elements = new String[] { "", "", "" };
		int indexUser;
		int indexHost;
		int indexDomain;

		// user
		indexUser = email.indexOf("@");
		if (indexUser <= 0) {
			throw new AddressException(email
					+ " does not contain user name of the email address.");
		} else {
			elements[0] = email.substring(0, indexUser);
		}

		// host (optional)
		indexHost = email.indexOf(".", indexUser);
		if (indexHost > 0) {
			elements[1] = email.substring(indexUser + 1, indexHost);
		}

		// domain
		indexDomain = email.indexOf(".", indexHost + 1);
		if (indexDomain > 0) {
			elements[2] = email.substring(indexHost + 1);
		} else {
			if (StringUtil.isBlank(elements[1])) {
				throw new AddressException(email
						+ " does not contain domain name of the email address.");
			} else {
				// I mistaken part of the domain as host name
				elements[1] = "";
				elements[2] = email.substring(indexUser + 1).trim();
			}
		}
		return elements;
	}

	/**
	 * return <code>boolean</code> true/false indicating if <code>email</code>
	 * is a well- formated email address. Email, in this method, is defined as a
	 * <code>String</code> in the format as <br>
	 * <ul>
	 * <code>xxxxx@xxxxx.xxx</code>
	 * </ul>
	 * <br>
	 * where <code>xxxx</code> is must not contain the following characters:<br>
	 * <ul>
	 * <code>!, ?, [, ], +, &,
	 * (, ), #, {, }, *, =, :, /</code>
	 * </ul>
	 * <br>
	 * 
	 * @param email
	 *            String
	 * @return boolean
	 * @see #isValidEmailSection(String) used internally
	 */
	public static boolean isEmail(String email) {
		if (StringUtil.isBlank(email)) {
			return false;
		}

		// must be in the form of xxx@xxx.xxx, minimium: a@b.c
		final int positionAt = email.indexOf("@");
		if (positionAt < 1) {
			return false;
		}

		final int positionDot = email.indexOf(".", positionAt);
		if (positionDot < 3 || positionDot == email.length()) {
			return false;
		}

		// Check the integrity of every section
		String section = new String();

		// Section 1
		section = email.substring(0, positionAt);
		if (!isValidEmailSection(section)) {
			return false;
		}

		// Section 2
		section = email.substring(positionAt + 1, positionDot);
		if (!isValidEmailSection(section)) {
			return false;
		}

		// Section 3
		section = email.substring(positionDot + 1);
		if (!isValidEmailSection(section)) {
			return false;
		}

		return true;
	}

	/**
	 * @param to
	 *            java.lang.String
	 * @param from
	 *            java.lang.String
	 * @param subject
	 *            java.lang.String
	 * @param message
	 *            java.lang.String
	 */
	public static void sendEmail(String[] to, String from, String subject,
			String message) throws MessagingException {

		// Set up the connection parameters
		Properties props = new Properties();
		props.put(DEF_MAIL_HOST, DEFAULT_MAIL_HOST);
		Session mailSession = Session.getDefaultInstance(props, null);

		// Create the message
		Message mailMessage = new MimeMessage(mailSession);

		// Set the from address
		InternetAddress sender = new InternetAddress(from);
		mailMessage.setFrom(sender);

		// Set the recipient
		InternetAddress[] recipients = new InternetAddress[to.length];
		for (int i = 0; i < to.length; i++) {
			recipients[i] = new InternetAddress(to[i]);
		}
		mailMessage.setRecipients(Message.RecipientType.TO, recipients);
		mailMessage.setSubject(subject);

		// Set the content of the message
		mailMessage.setContent(message, TEXT_TYPE);

		// Send the message
		Transport.send(mailMessage);

	}

	public static void sendEmailToMultipleRecipients(String[] to,
			String subject, String message) throws MessagingException {

		java.security.Security
		.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true"); // added this line
		props.put("mail.smtp.host", MailSettings.SMTP_HOST_NAME);
		props.put("mail.smtp.user", MailSettings.SMTP_AUTH_USER);
		props.put("mail.smtp.password", MailSettings.SMTP_AUTH_PWD);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props, null);

		// create a mime message
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(MailSettings.EMAIL_SEND_FROM));

		// set the recipient
		InternetAddress[] recipients = new InternetAddress[to.length];
		for (int i = 0; i < to.length; i++) {
			recipients[i] = new InternetAddress(to[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, recipients);
		// set the subject
		msg.setSubject(subject);

		// set the message
		msg.setText(message);
		msg.setContent(message, "text/html");
		// msg.setContent(message, "text/html");
		Transport transport = session.getTransport("smtp");
		transport.connect(MailSettings.SMTP_HOST_NAME,
				MailSettings.SMTP_AUTH_USER, MailSettings.SMTP_AUTH_PWD);
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
	}

	/**
	 * Send Server shutdown email.
	 *
	 * @param to the to email
	 * @param from the from email
	 * @param fromPass the from email password
	 * @param subject the subject
	 * @param message the message
	 * @throws MessagingException the messaging exception
	 */
	public static void sendShutdownEmail(String[] to,String from, String fromPass,
			String subject, String message) throws MessagingException {

		java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true"); // added this line
		props.put("mail.smtp.host", MailSettings.SMTP_HOST_NAME);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", fromPass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props, null);

		// create a mime message
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from));

		// set the recipient
		InternetAddress[] recipients = new InternetAddress[to.length];
		for (int i = 0; i < to.length; i++) {
			recipients[i] = new InternetAddress(to[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, recipients);
		// set the subject
		msg.setSubject(subject);

		// set the message
		msg.setText(message);
		msg.setContent(message, "text/html");
		// msg.setContent(message, "text/html");
		Transport transport = session.getTransport("smtp");
		transport.connect(MailSettings.SMTP_HOST_NAME, from, fromPass);
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
	}

	/**
	 * @param mailHost
	 *            java.lang.String
	 * @param to
	 *            java.lang.String
	 * @param from
	 *            java.lang.String
	 * @param subject
	 *            java.lang.String
	 * @param message
	 *            java.lang.String
	 */

	public static void sendEmail(String mailHost, String[] to, String from,
			String subject, String message) throws MessagingException {
		String mHost="";
		if (StringUtil.isBlank(mailHost)) {
			mHost = DEFAULT_MAIL_HOST;
		}

		// Set up the connection parameters
		Properties props = new Properties();
		props.put(DEF_MAIL_HOST, mHost);

		Session mailSession = Session.getDefaultInstance(props, null);

		// Create the message
		Message mailMessage = new MimeMessage(mailSession);

		// Set the from address
		InternetAddress sender = new InternetAddress(from);
		mailMessage.setFrom(sender);

		// Set the recipient
		InternetAddress[] recipients = new InternetAddress[to.length];
		for (int i = 0; i < to.length; i++) {
			recipients[i] = new InternetAddress(to[i]);
		}
		mailMessage.setRecipients(Message.RecipientType.TO, recipients);
		mailMessage.setSubject(subject);

		// Set the content of the message
		mailMessage.setContent(message, TEXT_TYPE);

		// Send the message
		Transport.send(mailMessage);
	}

	/**
	 * @param to
	 *            java.lang.String
	 * @param from
	 *            java.lang.String
	 * @param subject
	 *            java.lang.String
	 * @param message
	 *            java.lang.String
	 */
	public static void sendEmail(String to, String from, String subject,
			String message) throws MessagingException {
		java.security.Security
		.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true"); // added this line
		props.put("mail.smtp.host", MailSettings.SMTP_HOST_NAME);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", MailSettings.SMTP_AUTH_PWD);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, null);

		// create a mime message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(MailSettings.EMAIL_SEND_FROM));

		// set the recipient
		InternetAddress recipient = new InternetAddress(to);
		msg.addRecipient(Message.RecipientType.TO, recipient);

		// set the subject
		msg.setSubject(subject);

		// set the message
		msg.setText(message);
		msg.setContent(message, "text/html");
		// msg.setContent(message, "text/html");
		Transport transport = session.getTransport("smtp");
		transport.connect(MailSettings.SMTP_HOST_NAME,
				MailSettings.SMTP_AUTH_USER, MailSettings.SMTP_AUTH_PWD);
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
	}

	/**
	 * @param mailHost
	 *            java.lang.String
	 * @param to
	 *            java.lang.String
	 * @param from
	 *            java.lang.String
	 * @param subject
	 *            java.lang.String
	 * @param message
	 *            java.lang.String
	 */
	public static void sendEmail(String mailHost, String to, String from,
			String subject, String message) throws MessagingException {
		String mHost="";
		if (StringUtil.isBlank(mailHost)) {
			mHost = DEFAULT_MAIL_HOST;
		}

		// Set up the connection parameters
		Properties props = new Properties();
		props.put(DEF_MAIL_HOST, mHost);
		Session mailSession = Session.getDefaultInstance(props, null);

		// Create the message
		Message mailMessage = new MimeMessage(mailSession);

		// Set the from address
		InternetAddress sender = new InternetAddress(from);
		mailMessage.setFrom(sender);

		// Set the recipient
		InternetAddress recipient = new InternetAddress(to);
		mailMessage.setRecipient(Message.RecipientType.TO, recipient);
		mailMessage.setSubject(subject);

		// Set the content of the message
		mailMessage.setContent(message, TEXT_TYPE);

		// Send the message
		Transport.send(mailMessage);
	}

	public static void sendEmailWithAttachment(String to, String from,
			String subject, String message, byte[] attachment, String mimeType)
	throws MessagingException {
		// set up the connection parameters
		Properties props = new Properties();
		props.put(DEF_MAIL_HOST, DEFAULT_MAIL_HOST);
		Session mailSession = Session.getDefaultInstance(props, null);

		// create the message
		Message mailMessage = new MimeMessage(mailSession);

		// set the from address
		InternetAddress sender = new InternetAddress(from);
		mailMessage.setFrom(sender);

		// set the recipient
		InternetAddress recipient = new InternetAddress(to);
		mailMessage.setRecipient(Message.RecipientType.TO, recipient);
		mailMessage.setSubject(subject);

		// set the text content of the message
		Multipart multipart = new MimeMultipart();
		MimeBodyPart messageBodyPart = null;
		if (!StringUtil.isEmpty(message)) {
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(message);
			multipart.addBodyPart(messageBodyPart);
		}

		// then add the byte[] attachement
		messageBodyPart = new MimeBodyPart();
		DataSource dataSource = new ByteArrayDataSource(attachment, mimeType);
		messageBodyPart.setDataHandler(new DataHandler(dataSource));
		multipart.addBodyPart(messageBodyPart);

		// now add all parts to message
		mailMessage.setContent(multipart);

		// send the message
		Transport.send(mailMessage);
	}

	public static void sendEmailAttachment(String to, String subject,
			String message, String filename) throws MessagingException {
		java.security.Security
		.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true"); // added this line
		props.put("mail.smtp.host", MailSettings.SMTP_HOST_NAME);
		props.put("mail.smtp.user", MailSettings.SMTP_AUTH_USER);
		props.put("mail.smtp.password", MailSettings.SMTP_AUTH_PWD);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, null);
		// create a mime message
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(MailSettings.EMAIL_SEND_FROM));
		// set the recipient
		InternetAddress recipient = new InternetAddress(to);
		msg.addRecipient(Message.RecipientType.TO, recipient);
		// set the subject
		msg.setSubject(subject);
		// create and fill the first message part
		MimeBodyPart mbp1 = new MimeBodyPart();
		mbp1.setText(message);
		// create the second message part
		MimeBodyPart mbp2 = new MimeBodyPart();
		// attach the file to the message
		FileDataSource fds = new FileDataSource(filename);
		mbp2.setDataHandler(new DataHandler(fds));
		mbp2.setFileName(fds.getName());
		// create the Multipart and add its parts to it
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(mbp1);
		mp.addBodyPart(mbp2);
		// add the Multipart to the message
		msg.setContent(mp);
		// set the Date: header
		msg.setSentDate(new Date());

		Transport transport = session.getTransport("smtp");
		transport.connect(MailSettings.SMTP_HOST_NAME,
				MailSettings.SMTP_AUTH_USER, MailSettings.SMTP_AUTH_PWD);
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
	}

	// code by Prathibha
	public static void sendEmail(String to, String subject, String message)
	throws MessagingException {
		java.security.Security
		.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true"); // added this line
		props.put("mail.smtp.host", MailSettings.SMTP_HOST_NAME);
		props.put("mail.smtp.user", MailSettings.SMTP_AUTH_USER);
		props.put("mail.smtp.password", MailSettings.SMTP_AUTH_PWD);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, null);

		// create a mime message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(MailSettings.EMAIL_SEND_FROM));

		// set the recipient
		InternetAddress recipient = new InternetAddress(to);
		msg.addRecipient(Message.RecipientType.TO, recipient);

		// set the subject
		msg.setSubject(subject);

		// set the message
		msg.setText(message);
		msg.setContent(message, "text/html");
		// msg.setContent(message, "text/html");
		Transport transport = session.getTransport("smtp");
		transport.connect(MailSettings.SMTP_HOST_NAME,
				MailSettings.SMTP_AUTH_USER, MailSettings.SMTP_AUTH_PWD);
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
	}

	/**
	 * to be used internally by <code>isEmail(Sting)</code> method to verify a
	 * <code>String</code> as email address. This method will return false if
	 * the following characters are found in <code>
	 * emailSection</code>:<br>
	 * <ul>
	 * <code>!, ?, [, ], +, &, (, ), #, {, }, *,
	 * =, :, /</code>
	 * </ul>
	 * <br>
	 * 
	 * @param emailSection
	 *            java.lang.String
	 * @return boolean
	 * @see org.javaexpert.foundation.j2ee.EmailSender#isEmail
	 */
	private static boolean isValidEmailSection(String emailSection) {
		for (int i = 0; i < emailSection.length(); i++) {
			if (INVALID_EMAIL_CHARACTERS.contains(emailSection.substring(i,
					i + 1))) {
				return false;
			}
		}
		return true;
	}

	

	public static MimeMessage getMessageObj(String from, String subject, String body, Session session){
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(from));
			msg.setSubject(subject);
			msg.setText(body);
			msg.setContent(body, "text/html");
			msg.saveChanges();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return msg;
	}
}

