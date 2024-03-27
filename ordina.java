package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
		String[] ordiniSelezionati = request.getParameterValues("ordini");
		if (ordiniSelezionati != null) {
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
	    }
		    
		    request.setAttribute("lista", ordiniSelezionati);
	        
	        // Inoltra la richiesta alla pagina JSP per l'elaborazione ulteriore
	        request.getRequestDispatcher("/risultato3.jsp").forward(request, response);
	}
		    	
		    }
		

	}


