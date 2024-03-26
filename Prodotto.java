package app;
public class Prodotto {
    private int id;
    private String nome;
    private double prezzo;
    private int quantita;

    public Prodotto() {
        
       
    }

    // Metodi getter e setter per gli attributi della classe Prodotto

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    // Metodo toString per rappresentare l'oggetto Prodotto come stringa

    @Override
    public String toString() {
        return "Prodotto [id=" + id + ", nome=" + nome + ", prezzo=" + prezzo + ", quantita=" + quantita + "]";
    }
}

