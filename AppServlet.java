package app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AppServlet
 */
@WebServlet("/submit")
public class AppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppServlet() {
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
		String nome = request.getParameter("nome");
        String campo2 = request.getParameter("prezzo");
        String campo3 = request.getParameter("quantita");
        int quantita = Integer.parseInt(campo3);
        double prezzo = Double.parseDouble(campo2);
        
        // Creazione di un oggetto DatiModulo e impostazione dei valori
        Prodotto p1 = new Prodotto();
        p1.setNome(nome);
        p1.setPrezzo(prezzo);
        p1.setQuantita(quantita);
        
        
     // Ottieni l'oggetto ServletContext
        ServletContext context = getServletContext();

        // Ottieni l'ArrayList dalla ServletContext, se già esiste, altrimenti crea un nuovo ArrayList
        ArrayList<Prodotto> listaDati = (ArrayList<Prodotto>) context.getAttribute("listaDati");
        if (listaDati == null) {
            listaDati = new ArrayList<>();
        }

        // Aggiungi il nuovo oggetto DatiModulo all'ArrayList
        listaDati.add(p1);

        // Memorizza l'ArrayList nell'oggetto ServletContext
        context.setAttribute("listaDati", listaDati);
        
        // Esegui eventuali operazioni aggiuntive qui...
        
        // Passa l'oggetto datiModulo alla JSP per l'elaborazione successiva
        request.setAttribute("datiModulo", p1);
        
        String insertQuery1 = "INSERT INTO prodotti (nome, prezzo, quantita) VALUES (?, ?, ?)";
		// Informazioni di connessione al database MySQL
	    String url = "jdbc:mysql://localhost:3306/"; // URL del database
	    String dbName = "mydb"; // Nome del database
	    String user = "root"; // Nome utente
	    String password = "Ilfoggia1"; 
	    try (Connection conn = DriverManager.getConnection(url + dbName, user, password);
        		PreparedStatement stmt = conn.prepareStatement(insertQuery1))
        		
        		
        		{
        	stmt.setString(1, nome);
        	stmt.setDouble(2, prezzo);
        	stmt.setInt(3, quantita);
        	
        	
        	
            // Esecuzione della query per l'inserimento dei dati
            int rowsAffected = stmt.executeUpdate();

            // Stampa il numero di righe aggiornate
            System.out.println("Numero di righe aggiornate: " + rowsAffected);

        } catch (SQLException e) {
            // Gestione dell'eccezione per la connessione al database o l'esecuzione della query
            System.out.println("Errore durante l'inserimento dei dati nella tabella 'prodotti':");
            e.printStackTrace();
        }
	    
        
        // Inoltra la richiesta alla pagina JSP per l'elaborazione ulteriore
        request.getRequestDispatcher("/risultato.jsp").forward(request, response);
	}

}



