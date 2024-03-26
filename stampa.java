package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class stampa
 */
@WebServlet("/test")
public class stampa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public stampa() {
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
		// TODO Auto-generated method stub
		// Lista per memorizzare i prodotti
		// Informazioni di connessione al database MySQL
	    String url = "jdbc:mysql://localhost:3306/"; // URL del database
	    String dbName = "mydb"; // Nome del database
	    String user = "root"; // Nome utente
	    String password = "Ilfoggia1"; //
        List<Prodotto> listaProdotti = new ArrayList<>();
        String selectQuery = "SELECT * FROM prodotti";
        String query1 = "SELECT * FROM prodotti WHERE nome = (?)";
        try (Connection conn = DriverManager.getConnection(url + dbName, user, password);
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(selectQuery)) {

	            // Iterazione sui risultati e lettura dei dati
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String nome = rs.getString("nome");
	                double prezzo = rs.getDouble("prezzo");
	                int quantita = rs.getInt("quantita");
	                
	                // Creazione di un nuovo oggetto Prodotto e aggiunta alla lista
	                Prodotto prodotto = new Prodotto();
	                prodotto.setId(id);
	                prodotto.setNome(nome);
	                prodotto.setPrezzo(prezzo);
	                prodotto.setQuantita(quantita);
	                listaProdotti.add(prodotto);
	            }

	            // Stampa dei prodotti nella lista
	            

	        } catch (SQLException e) {
	            // Gestione delle eccezioni per la connessione al database o la lettura dei dati
	            System.out.println("Errore durante la lettura dei dati dalla tabella 'prodotti':");
	            e.printStackTrace();
	        }
         request.setAttribute("lista", listaProdotti);
        
        // Inoltra la richiesta alla pagina JSP per l'elaborazione ulteriore
        request.getRequestDispatcher("/risultato1.jsp").forward(request, response);
        
	}
	

}
