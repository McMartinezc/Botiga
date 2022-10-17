public class Producte {

    private String nomProducte;
    private String marcaProducte;
    private double preu;
    private int quantitatProducte;

    //Constructor
    public Producte(String nomProducte, String marcaProducte, double preu, int quantitatProducte) {
        this.nomProducte = nomProducte;
        this.marcaProducte = marcaProducte;
        this.preu = preu;
        this.quantitatProducte = quantitatProducte;
    }

    //Getters i Setters
    public String getNomProducte() {
        return nomProducte;
    }

    public void setNomProducte(String nomProducte) {
        this.nomProducte = nomProducte;
    }

    public String getMarcaProducte() {
        return marcaProducte;
    }

    public void setMarcaProducte(String marcaProducte) {
        this.marcaProducte = marcaProducte;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    public int getQuantitatProducte() {
        return quantitatProducte;
    }

    public void setQuantitatProducte(int quantitatProducte) {
        this.quantitatProducte = quantitatProducte;
    }

    @Override
    public String toString() {
        return "Producte{" +
                "Producte: '" + nomProducte + '\'' +
                ", Marca:'" + marcaProducte + '\'' +
                ", Preu: " + preu +'\'' +
                ", Quantitat : " + quantitatProducte +
                '}';
    }
}
