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
 * Servlet implementation class remove
 */
@WebServlet("/remove")
public class remove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public remove() {
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
		String prod = request.getParameter("prod");
		String insertQuery1 = "DELETE FROM prodotti WHERE nome=(?);";
		 String url = "jdbc:mysql://localhost:3306/"; // URL del database
		    String dbName = "mydb"; // Nome del database
		    String user = "root"; // Nome utente
		    String password = "Ilfoggia1"; 
		    
		    try (Connection conn = DriverManager.getConnection(url + dbName, user, password);
	        		PreparedStatement stmt = conn.prepareStatement(insertQuery1))
	        		
	        		
	        		{
	        	stmt.setString(1, prod);
	        	
	        	
	        	
	        	
	            // Esecuzione della query per l'inserimento dei dati
	            int rowsAffected = stmt.executeUpdate();

	            // Stampa il numero di righe aggiornate
	            System.out.println("Numero di righe aggiornate: " + rowsAffected);

	        } catch (SQLException e) {
	            // Gestione dell'eccezione per la connessione al database o l'esecuzione della query
	            System.out.println("Errore durante l'inserimento dei dati nella tabella 'prodotti':");
	            e.printStackTrace();
	        }
		    
		    response.getWriter().println(prod + " eliminato con successo!");
		    
		
	}

}
