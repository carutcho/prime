package br.com.prime.mail.main;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import br.com.prime.mail.interfaces.Mail;


public class Main {

	public static final String PATH_ANEXO[] = {"C:/Users/Philips/Desktop/travel-01.jpg"};
	public static final String DESTINATARIOS = "reinaldo.torresrj@gmail.com, thaugustosouza@gmail.com ; bruno.antonio.souza.job@gmail.com";

	public static void main(String args[]) throws AddressException, MessagingException {

		Mail mail = new MailGmail();
        mail.enviarCCoHTML(DESTINATARIOS, 
        					 "Olá, prazer, Prime :)", 
        					 "<h1>Olá amiguinhos :)<h1/> "
        					 + "<font size=3>"
        					 + "<p><strong> Eu sou o <font color=red> PRIME </font> e agora eu sei enviar e-mail :)! <br>"
        					 + "<p>Olha que legal, esse e-mail esta escrito em HTML, possui anexo, suporto destinatários separados por virgula ou ponto e virgula e este e-mail está em cópia oculta para o Reinaldo, Thiago e Bruno :).</p>"
        					 + "<p>Em breve estarei no excursa amigos :).. "
        					 + "<br>um grande abraço</p>"
        					 + "<br><br> att,<br>"
        					 + "</font> "
        					 + "<strong>PRIME</strong>", PATH_ANEXO);
	}
}