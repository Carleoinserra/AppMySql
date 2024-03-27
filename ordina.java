package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ordina
 */
@WebServlet("/ordina")
public class ordina extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ordina() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		
		
		String user = (String) context.getAttribute("user");
		
		
		
		String[] ordiniSelezionati = request.getParameterValues("ordini");
		
		
		if (ordiniSelezionati != null) {
			
			double somma = 0;
			
		    for (String ordine : ordiniSelezionati) {
		    	
		    	 try {
		    	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Ilfoggia1");
	            
	            // Query per aggiornare la quantità del prodotto selezionato
	            String updateQuery = "UPDATE prodotti SET quantita = quantita - 1 WHERE nome = (?)";
	            
	            // Creazione dello statement preparato
	            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
	            pstmt.setString(1, ordine); // Assumendo che ordine sia l'ID del prodotto
	            
	            // Esecuzione dell'aggiornamento
	            pstmt.executeUpdate();
	            
	            // Chiusura delle risorse
	            pstmt.close();
	            conn.close();
	            
	        } catch (SQLException e) {
	            e.printStackTrace(); // Gestisci eventuali errori di connessione o query SQL
	        }
		    	 
		    	 
		    	
		    	 
		    	 String query = "SELECT prezzo FROM prodotti WHERE nome = ?";
		    	 
		    	 try {
		    		 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Ilfoggia1");
		    	     PreparedStatement pstmt = conn.prepareStatement(query);
		    	     pstmt.setString(1, ordine);
		    	     ResultSet rs = pstmt.executeQuery();
		    	     if (rs.next()) {
		    	         double prezzo = rs.getDouble("prezzo");
		    	         System.out.println(prezzo);
		    	          somma += prezzo;
		    	         // Utilizza il prezzo ottenuto
		    	     } else {
		    	         // Prodotto non trovato
		    	     }
		    	     rs.close();
		    	     pstmt.close();
		    	 } catch (SQLException e) {
		    	     e.printStackTrace();
		    	 }

		    	 
	    }
		    
		    request.setAttribute("lista", ordiniSelezionati);
		    request.setAttribute("somma", somma);
		    
		    String prodotti = "";
		    for (String prod: ordiniSelezionati) {
		    	prodotti += " " + prod;
		    }
		    
		    
		    
		    String from = "inserracarlo@gmail.com";
	        // Password dell'account email del mittente
	        String password = "pnsk ijdh lpip xsow";
	        // Indirizzo email del destinatario
	        String to = "inserracarlo@gmail.com";

	        // Proprietà per la configurazione del server SMTP di Gmail
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");

	        // Creazione di un oggetto di autenticazione
	        Authenticator auth = new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(from, password);
	            }
	        };

	        // Creazione di una nuova sessione SMTP con autenticazione
	        Session session = Session.getInstance(props, auth);

	        try {
	            // Creazione del messaggio email
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(from));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	            message.setSubject("Il tuo ordine è stato raccolto con successo");
	            message.setText("Riepilogo ordine" + prodotti + " La somma da pagare è "+ somma  );

	            // Invio dell'email
	            Transport.send(message);

	            System.out.println("Email inviata con successo!");
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            System.out.println("Errore durante l'invio dell'email.");
	        }
		    
		    
		    
		    
		    
		    
	        // Inoltra la richiesta alla pagina JSP per l'elaborazione ulteriore
	        request.getRequestDispatcher("/risultato3.jsp").forward(request, response);
	}
		    	
		    }
		

	}


