package br.com.prime.mail.mails;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

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

	public void setConfigGmail() {
		props.put("mail.smtp.starttls.enable", MailConfig.SMTP_GMAIL_STARTTLS_ENABLE);
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
	
	public void setSession(PasswordAuthentication password) {}

	public void setSession() {
		super.session = Session.getDefaultInstance(props, null);
	}

	public void enviarEmail(Message email) throws MessagingException {
		
		email.setFrom(new InternetAddress(MailConfig.SMTP_GMAIL_USUARIO));
		
		Transport transport = super.session.getTransport("smtp");
        transport.connect(MailConfig.SMTP_GMAIL, MailConfig.SMTP_GMAIL_USUARIO, MailConfig.SMTP_GMAIL_SENHA);
        transport.sendMessage(email, email.getAllRecipients());
        transport.close();
	}

	public void enviarEmail(String para, String assunto, String texto, String[] urlArquivo) throws AddressException, MessagingException {
		Message email = construirEmail(para, assunto, texto, urlArquivo, RecipientType.TO);
		enviarEmail(email);
	}
	
	public void enviarEmailHTML(String para, String assunto, String html, String[] urlArquivo) throws AddressException, MessagingException {
		Message email = construirEmailHTML(para, assunto, html, urlArquivo, RecipientType.TO);
		enviarEmail(email);
	}

	public void enviarCCoHTML(String para, String assunto, String html, String[] urlArquivo) throws AddressException, MessagingException {
		Message email = construirEmailHTML(para, assunto, html, urlArquivo, RecipientType.BCC);
		enviarEmail(email);
	}
	
	public void enviarEmailCCo(String para, String assunto, String texto, String[] urlArquivo) throws AddressException, MessagingException {
		Message email = construirEmail(para, assunto, texto, urlArquivo, RecipientType.BCC);
		enviarEmail(email);
	}
}
