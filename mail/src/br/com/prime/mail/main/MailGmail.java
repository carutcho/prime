package br.com.prime.mail.main;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import br.com.prime.mail.config.MailConfig;
import br.com.prime.mail.interfaces.Mail;

//TODO: Adicionar property
public class MailGmail extends Mail {

	Properties props = new Properties();
	PasswordAuthentication password;

	public MailGmail() {
		setConfigGmail();
		setSession();
	}

	public MailGmail(PasswordAuthentication password) {
		setConfigGmail();
		session = Session.getDefaultInstance(props);
		setSession(password);
	}
	
	public void setConfigGmail() {
		//props.put("mail.smtp.host", MailConfig.SMTP_GMAIL);
/*		props.put("mail.smtp.socketFactory.port", MailConfig.SMTP_GMAIL_PORT);
		props.put("mail.smtp.socketFactory.class", MailConfig.SMTP_GMAIL_SESSION_FACTORY); */
		
		props.put("mail.smtp.user", MailConfig.SMTP_GMAIL_USUARIO);
	    props.put("mail.smtp.password", MailConfig.SMTP_GMAIL_SENHA);
		props.put("mail.smtp.starttls.enable", MailConfig.SMTP_GMAIL_STARTTLS_ENABLE);
		props.put("mail.smtp.ssl.trust", MailConfig.SMTP_GMAIL);
		props.put("mail.smtp.auth", MailConfig.SMTP_GMAIL_AUTH);
		props.put("mail.smtp.port", MailConfig.SMTP_GMAIL_PORT);
	}

	public Session getSession(){
		return super.session;
	}
	
	public Session getSession(PasswordAuthentication password){
		setSession(password);
		return getSession();
	}
	
	public void setSession(PasswordAuthentication password) {
		super.session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return password;
			}
		});
		super.session.setDebug(true);		
	}

	public void setSession() {
		setSession(new PasswordAuthentication(MailConfig.SMTP_GMAIL_USUARIO, MailConfig.SMTP_GMAIL_SENHA));		
	}

}
