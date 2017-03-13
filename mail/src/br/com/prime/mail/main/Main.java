package br.com.prime.mail.main;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;

import br.com.prime.mail.interfaces.Mail;

public class Main {

	public static void main(String args[]) throws AddressException, MessagingException {

		Mail mail = new MailGmail(new PasswordAuthentication("excursa.teste@gmail.com", "windows98"));
        mail.enviarEmail("excursa.teste@gmail.com", "reinaldo.torresrj@gmail.com", "Email de teste do Prime", "Hello World! Este é pimeiro e-mail do prime!", null);
	}
}