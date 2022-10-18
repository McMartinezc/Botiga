import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class App {

    public static void main(String[] args) throws NoItems {

        ArrayList<Carro> llistaCarros = new ArrayList<Carro>();
        int opcions, indexCarro = -1, indexProducte;
        String nomCarro, nomProducte, marcaProducte;

        do {
            opcions = menuEntrada();

            if (opcions == 1) { //Sortir programa
                System.out.println("Has sortit del programa.");

            } else {
                switch (opcions) {

                    case 2://Crear carro
                        nomCarro = introInfo("Introdueix nom del carro: ");
                        indexCarro = buscarCarro(nomCarro, llistaCarros);

                        if (indexCarro != -1) {
                            System.out.println("Carro ja existeix.");
                        } else {
                            crearCarro(nomCarro, llistaCarros);
                        }
                        break;

                    case 3: //Eliminar carro
                        nomCarro = introInfo("Introdueix nom del carro: ");
                        indexCarro = buscarCarro(nomCarro, llistaCarros);

                        if (indexCarro == -1) {
                            System.out.println("Carro no existeix.");
                        } else {
                            eliminarCarro(indexCarro, llistaCarros);
                        }
                        break;

                    case 4: //Afegir producte al carro
                        nomCarro = introInfo("Introdueix nom del carro: ");
                        indexCarro = buscarCarro(nomCarro, llistaCarros);

                        if (indexCarro == -1) {
                            System.out.println("Carro no existeix.");
                        } else {
                            afegirProducte(indexCarro, llistaCarros);
                        }
                        break;

                    case 5: //Buscar producte
                        nomCarro = introInfo("Introdueix nom del carro: ");
                        indexCarro = buscarCarro(nomCarro, llistaCarros);
                        nomProducte = introInfo("Introdueix nom producte");
                        marcaProducte = introInfo("Introdueix marca producte: ");
                        indexProducte = llistaCarros.get(indexCarro).buscarProducte(nomProducte, marcaProducte);

                        if (indexCarro == -1) {
                            System.out.println("Carro no existeix.");
                        } else {
                            System.out.println(llistaCarros.get(indexCarro).getLlistaProductes().get(indexProducte));
                        }

                        break;
                    case 6: //Total carro
                        nomCarro = introInfo("Introdueix nom del carro: ");
                        indexCarro = buscarCarro(nomCarro, llistaCarros);
                        llistaCarros.get(indexCarro).preuTotal();
                        break;
                    case 7://Modificar quantitat de producte
                        nomCarro = introInfo("Introdueix nom del carro: ");
                        indexCarro = buscarCarro(nomCarro, llistaCarros);
                        modificarProducte(indexCarro, llistaCarros);
                        break;
                    case 8://Buscar per marca
                        nomCarro = introInfo("Introdueix nom del carro: ");
                        indexCarro = buscarCarro(nomCarro, llistaCarros);
                        if(indexCarro==-1){
                            System.out.println("Carro no existeix");
                        }else {
                            marcaProducte = introInfo("Introdueix marca que vols buscar en el carro: ");
                            llistaCarros.get(indexCarro).producteMarca(marcaProducte);
                        }
                        break;
                    case 9://Mostrar carros
                        System.out.println(llistaCarros.toString());
                        break;
                    case 10://Mostrar productes d'un carro
                        nomCarro = introInfo("Introdueix nom del carro: ");
                        indexCarro = buscarCarro(nomCarro, llistaCarros);
                        try {
                            mostraProductesCarro(indexCarro, llistaCarros);
                        } catch (Exception error) {
                            System.out.println(error.getMessage());
                        }
                        break;
                    case 11: //Crear arxiu d'un carro
                        nomCarro = introInfo("Introdueix nom del carro: ");
                        indexCarro = buscarCarro(nomCarro, llistaCarros);
                        Carro carro = llistaCarros.get(indexCarro);
                        crearArxiu(carro);
                        break;
                }
            }
        } while (opcions != 1);

    }

    ///////FUNCIONALIDADES DEL PROGRAMA
    //Metodo para crear un fichero Csv
    static void crearArxiu(Carro carro) {

        final String CSV_SEPARATOR = ",";
        {
            try {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("productes.csv"), "UTF-8"));
                for (Producte producte : carro.getLlistaProductes()) {
                    StringBuffer oneLine = new StringBuffer();
                    oneLine.append(producte.getNomProducte().trim().length() == 0 ? "" : producte.getNomProducte());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(producte.getMarcaProducte().trim().length() == 0 ? "" : producte.getMarcaProducte());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(producte.getPreu() < 0 ? "" : producte.getPreu());
                    oneLine.append(CSV_SEPARATOR);
                    oneLine.append(producte.getQuantitatProducte() < 0 ? "" : producte.getQuantitatProducte());
                    oneLine.append(CSV_SEPARATOR);
                    bw.write(oneLine.toString());
                    bw.newLine();
                }
                bw.flush();
                bw.close();
            } catch (UnsupportedEncodingException e) {
            } catch (FileNotFoundException e) {
            } catch (IOException e) {
            }
        }
    }
    //Mostrar productes d'un carro si no hi ha productes salta exception
    static void mostraProductesCarro (int indexCarro, ArrayList<Carro> llistaCarros) throws NoItems {

        if(llistaCarros.get(indexCarro).getLlistaProductes().isEmpty()){
            throw new NoItems("El carro està buit!. Has d'afegir productes.");
        }else{
                System.out.println(llistaCarros.get(indexCarro).getLlistaProductes());
            }
        }

    //Metode crear carro
    static void crearCarro (String nomCarro, ArrayList<Carro> llistaCarros ) {
        llistaCarros.add(new Carro (nomCarro));
        System.out.println(nomCarro +" carro afegit");
    }

    //Metode eliminar carro
    static void eliminarCarro(int indexCarro, ArrayList<Carro> llistaCarros ){
        llistaCarros.remove(indexCarro);
        System.out.println ("Carro eliminat.");

    }
    //Metode buscar carro
    static int buscarCarro(String nomCarro, ArrayList<Carro> llistaCarros){
        int i=0;
        int indexCarro=-1;
        boolean encontrado =false;

        while( i<llistaCarros.size() && encontrado== false){
            if(llistaCarros.get(i).getNomCarro().equalsIgnoreCase(nomCarro)){
                indexCarro = i;
                encontrado = true;
            }
            i++;
        }
        return indexCarro;
    }
    //Metode afegirProducte
    static void afegirProducte (int indexCarro, ArrayList<Carro> llistaCarros) {
        int quantitatProducte;
        double preuProducte;
        String nomProducte,marcaProducte ;
        Producte producte;

        nomProducte=introInfo("Introdueix nom del producte: ");
        marcaProducte = introInfo("Introdueix marca del producte: ");
        preuProducte = introInfoDouble("Introdueix preu:");
        quantitatProducte =introInfoInt("Introdueix quantitat:");
        //Creem producte
        producte = new Producte(nomProducte,marcaProducte, preuProducte, quantitatProducte );
        //Afegim producte al carro
        llistaCarros.get(indexCarro).afegirProducte(producte);
    }
    //Modificar producte
    static void modificarProducte(int indexCarro, ArrayList<Carro> llistaCarros) {
        Scanner input = new Scanner(System.in);
        int quantitatProducte, indexProducte=-1;
        String nomProducte,marcaProducte ;

        nomProducte=introInfo("Introdueix nom del producte: ");
        marcaProducte = introInfo("Introdueix marca del producte: ");
        indexProducte = llistaCarros.get(indexCarro).buscarProducte(nomProducte, marcaProducte);
        if(indexProducte!=-1){
            input.nextLine();//neteja buffer
            quantitatProducte =introInfoInt("Introdueix quantitat:");
            llistaCarros.get(indexCarro).getLlistaProductes().get(indexProducte).setQuantitatProducte(quantitatProducte);
        }else{
            System.out.println("Producte no existeix, en aquest carro");
        }

        System.out.println("Quantitat de producte modificada. ");
    }



    //Metode menu info entrada
    static int menuEntrada() {
        int opcions = introInfoInt("Escull:\n1.Sortir de l'aplicació"
                + "\n2.Crear carro"
                + "\n3.Eliminar carro"
                + "\n4.Afegir producte"
                + "\n5.Buscar producte"
                + "\n6.Total carro"
                + "\n7.Modificar quantitat de producte d'un carro"
                + "\n8.Buscar producte per marca"
                + "\n9.Mostrar carros "
                + "\n10.Mostrar productes d'un carro"
                + "\n11.Crear arxiu");
        return opcions;
    }

    /////ENTRADA DE DATOS

    //Metodes introduïr informació
    static String introInfo(String missatge) {
        Scanner input = new Scanner(System.in);
        System.out.println(missatge);
        return input.nextLine();//Retorna String
    }

    static int introInfoInt(String missatge) {
        Scanner input = new Scanner(System.in);
        System.out.println(missatge);
        return input.nextInt();//Retorna Int
    }
    static double introInfoDouble(String missatge) {
        Scanner input = new Scanner(System.in);
        System.out.println(missatge);
        return input.nextDouble();//Retorna double
    }

}
