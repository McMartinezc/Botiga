import java.util.ArrayList;

public class Carro {

    private ArrayList<Producte> llistaProductes;
    private String nomCarro;

    //Constructor
    public Carro(String nomCarro){
        this.nomCarro = nomCarro;
        llistaProductes = new ArrayList<Producte>();
    }


    //Getters i Setters
    public ArrayList<Producte> getLlistaProductes() {
        return llistaProductes;
    }

    public String getNomCarro() {
        return nomCarro;
    }

    public void setNomCarro(String nomCarro) {
        this.nomCarro = nomCarro;
    }

    //Metode afegir producte
    public void afegirProducte(Producte producte){
        llistaProductes.add(producte);
    }

    //Sumatori preu total d'un carro
    public void preuTotal(){
        double preuTotal = 0;

        for(int i=0; i<llistaProductes.size(); i++){
            preuTotal += llistaProductes.get(i).getPreu() * llistaProductes.get(i).getQuantitatProducte();
        }
        System.out.println("El preu total és: " +preuTotal);
    }

    //Metode buscar per marca
    public void producteMarca(){
        llistaProductes.forEach((marcaProducte)->System.out.println(marcaProducte));
    }

    //Metode buscar producte al carro per nom i marca
    public int buscarProducte(String nomProducte, String marcaProducte){
        int indexProducte =-1;
        int i =0;
        boolean encontrado = false;

        while((i < llistaProductes.size()) && (encontrado == false)){
            if (llistaProductes.get(i).getNomProducte().equalsIgnoreCase(nomProducte) && llistaProductes.get(i).getMarcaProducte().equalsIgnoreCase(marcaProducte)){
                indexProducte = i;
                encontrado = true;
            }
            i++;
        }

        if(!encontrado){
            System.out.println("Producte no existeix.");
        }

        return indexProducte;
    }

    @Override
    public String toString() {
        return "Nom Carro: " +nomCarro +
                " Llista de Productes: " + llistaProductes;
    }

}
