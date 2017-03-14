package br.com.prime.mail.interfaces;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.prime.mail.config.MailConfig;
import br.com.prime.mail.enums.TipoConextEnum;

public abstract class Mail {

	protected Session session;

	public abstract void setConfigGmail();
	
	public abstract void setSession();

	public abstract void setSession(PasswordAuthentication password);

	public abstract void enviarEmail(String para, String assunto, String texto, String[] urlArquivo) throws AddressException, MessagingException;

	public abstract void enviarEmailCCo(String para, String assunto, String html, String[] urlArquivo) throws AddressException, MessagingException;
		
	public abstract void enviarCCoHTML(String para, String assunto, String html, String[] urlArquivo) throws AddressException, MessagingException;
			
	public abstract void enviarEmailHTML(String para, String assunto, String html, String[] urlArquivo) throws AddressException, MessagingException;
		
	public void anexar(BodyPart messageBodyPart, String caminhoArquivo) throws MessagingException {

		DataSource source = new FileDataSource(caminhoArquivo);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(caminhoArquivo);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
	}

	public Message construirEmail(String para, String assunto, String texto, String[] urlArquivo, RecipientType tipoEnvioDestinatario) throws AddressException, MessagingException {
		Message email = new MimeMessage(session);
		Multipart multipart = new MimeMultipart();
		
		adicionarFrom(email, para, tipoEnvioDestinatario);
		adicionarAssunto(email, assunto);
		adicionarTexto(multipart, texto);
		adicionarAnexos(multipart, urlArquivo);
        email.setContent(multipart); 
        
        return email;
	}
	
	public Message construirEmailHTML(String para, String assunto, String texto, String[] urlArquivo, RecipientType tipoEnvioDestinatario) throws MessagingException {
		
		Message email = new MimeMessage(session);
		Multipart multipart = new MimeMultipart();
		
		adicionarFrom(email, para, tipoEnvioDestinatario);
		adicionarAssunto(email, assunto);
		adicionarHTML(multipart, texto);
		adicionarAnexos(multipart, urlArquivo);
        email.setContent(multipart); 
        
        return email;
	}
	private void adicionarAssunto(Message email, String assunto) throws MessagingException {
		email.setSubject(assunto);
	}

	private void adicionarAnexos(Multipart multipart, String[] urlArquivo) throws MessagingException {
		
		if (urlArquivo != null && urlArquivo.length > 0){			
	        if (urlArquivo.length > 1){
	        	anexarArquivos(multipart, urlArquivo);
	        }else{
	        	anexarArquivo(multipart, urlArquivo[0]);
	        }	        
		}
	}

	public void anexarArquivos(Multipart multipart, String[] urlArquivo) throws MessagingException {
    	for (String arquivo : urlArquivo) {
    		anexarArquivo(multipart, arquivo);
		}
	}

	public void anexarArquivo(Multipart multipart, String urlArquivo) throws MessagingException {
		BodyPart messageBodyPart = new MimeBodyPart();			
		DataSource source = new FileDataSource(urlArquivo);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(urlArquivo);
		multipart.addBodyPart(messageBodyPart);
	}

	public void adicionarTexto(Multipart multipart, String texto) throws MessagingException {
		
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(texto);		
        multipart.addBodyPart(messageBodyPart);
	}

	public void adicionarHTML(Multipart multipart, String html) throws MessagingException{
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(html, TipoConextEnum.TEXT_HTML_UTF8.getLabel());		
        multipart.addBodyPart(messageBodyPart);		
	}
	
	public void adicionarFrom(Message email, String para, RecipientType tipo) throws AddressException, MessagingException {
		
		if (para != null && para != ""){
			String paraPadronizado = para;
			if(para.contains(MailConfig.SEPARADOR_PONTO_VIRGULA)){
				paraPadronizado = para.replace(MailConfig.SEPARADOR_PONTO_VIRGULA, MailConfig.SEPARADOR_VIRGULA);
			}
			email.setRecipients(tipo, InternetAddress.parse(paraPadronizado));
		}		
	}
}