package br.com.prime.mail.interfaces;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.prime.mail.config.MailConfig;

public abstract class Mail {

	protected Session session;

	public abstract void setConfigGmail();
	
	public abstract void setSession();

	public abstract void setSession(PasswordAuthentication password);
	

	public void enviarEmail(Message email) throws MessagingException {
        //Transport.send(email);
		Transport transport = session.getTransport("smtp");
        // System.out.println("success point 5");

        transport.connect(MailConfig.SMTP_GMAIL, MailConfig.SMTP_GMAIL_USUARIO, MailConfig.SMTP_GMAIL_SENHA);
        transport.sendMessage(email, email.getAllRecipients());
        transport.close();
		
	}

	public void anexar(BodyPart messageBodyPart, String caminhoArquivo) throws MessagingException {

		DataSource source = new FileDataSource(caminhoArquivo);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(caminhoArquivo);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
	}

	public void enviarEmail(String de, String para, String assunto, String texto, String[] urlArquivo) throws AddressException, MessagingException {
		Message email = new MimeMessage(session);
		Multipart multipart = new MimeMultipart();

		adicionarDe(email, de);
		adicionarFrom(email, para);
		adicionarTexto(multipart, texto);
		adicionarAnexos(multipart, urlArquivo);
        email.setContent(multipart);
        enviarEmail(email);
	}

	private void adicionarAnexos(Multipart multipart, String[] urlArquivo) throws MessagingException {
		
		if (urlArquivo != null && urlArquivo.length > 0){
			BodyPart messageBodyPart = new MimeBodyPart();
			multipart.addBodyPart(messageBodyPart);
	        messageBodyPart = new MimeBodyPart();        	        
	        if (urlArquivo.length > 1){
	        	anexarArquivos(messageBodyPart, urlArquivo);
	        }else{
	        	anexarArquivo(messageBodyPart, urlArquivo[0]);
	        }
		}
	}

	public void anexarArquivos(BodyPart messageBodyPart, String[] urlArquivo) throws MessagingException {
    	for (String arquivo : urlArquivo) {
    		anexarArquivo(messageBodyPart, arquivo);
		}
	}

	public void anexarArquivo(BodyPart messageBodyPart, String urlArquivo) throws MessagingException {
		DataSource source = new FileDataSource(urlArquivo);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(urlArquivo);
	}

	public void adicionarTexto(Multipart multipart, String texto) throws MessagingException {
		
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(texto);		
        multipart.addBodyPart(messageBodyPart);
	}

	public void adicionarFrom(Message email, String para) throws AddressException, MessagingException {
		
		if (para != null && para != ""){
			String paraPadronizado = para;
			if(para.contains(MailConfig.SEPARADOR_PONTO_VIRGULA)){
				paraPadronizado = para.replace(MailConfig.SEPARADOR_PONTO_VIRGULA, MailConfig.SEPARADOR_VIRGULA);
			}
			email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(paraPadronizado));
		}
		
	}
	
	public void adicionarDe(Message email, String de) throws AddressException, MessagingException {

		if (de != null && de != "") {
			email.setFrom(new InternetAddress(de));
		}
	}
}