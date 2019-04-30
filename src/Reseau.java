import java.util.ArrayList;

public class Reseau {



    private String mName                        = "";           // Le nom du réseau
    private double mError                       = 0.0;          // Erreur d'apprentissage
    private String mActivationName              = "";           // La fonction d'activation
    private ArrayList<Integer> mTabCouche;                      // Le tableau des couches avec le nombre de neurones par couches
    private ArrayList<ArrayList<ArrayList<Double>>> mLiens;     // Les poids
    private ArrayList<ArrayList<Double>> mValeurs;              // Le tableau contenant la valeur des neurones
    private boolean mControle;                                  // A vrai si le réseau est déjà initialisé


    Reseau() {
        this("Default");
    }

    private Reseau(String name) { this(name, 0.0001); }

    private Reseau(String name, double error) {
        this(name, error, "sigmoide");
    }

    private Reseau(String name, double error, String activationName) {
        this(name, error, activationName, 1);
    }

    private Reseau(String name, double error, String activationName, int nbCouche) {
        this.mName              = name;
        this.mError             = error;
        this.mActivationName    = activationName;
        this.mTabCouche         = new ArrayList<Integer>(nbCouche);
        this.mControle          = false;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public double getError() {
        return mError;
    }

    public void setError(double error) {
        if (error > 0.0)
            this.mError = error;
    }

    public String getActivationName() {
        return mActivationName;
    }

    public void setActivationName(String activationName) {
        this.mActivationName = activationName;
    }

    public String toString(){
        return this.getName() + "\n" + this.getActivationName() + "\n" + this.getError() + this.getNbCouches();
    }
/*
    public ArrayList<Integer> getTabCouche() {
        return mTabCouche;
    }

    public void setTabCouche(ArrayList<Integer> tabCouche) {
        this.mTabCouche = tabCouche;
    }
*/
    public int nbNeuronesEntree() {
        return this.mTabCouche.get(0);
    }

    public int getNbNeurones() {
        int nbNeurones = 0;

        if (!this.mTabCouche.isEmpty()) {
            for (int i = 0 ; i < this.mTabCouche.size() ; i++) {
                nbNeurones += this.mTabCouche.get(i);
            }
        }
        return nbNeurones;
    }

    public int getNbLiens() {
        int nbLiens = 0;

        if (!this.mLiens.isEmpty()) {
            System.out.print(this.mLiens.size() + "\n");

            for (int i = 0 ; i < this.mLiens.size() ; i++) {
                //System.out.print("get(i).size " + this.mLiens.get(i).size() + "\n");
                nbLiens += this.mLiens.get(i).size();
                for (int j = 0 ; j < this.mLiens.get(i).size() ; j++) {
                    //System.out.print("Taille this.mLiens.get(i).get(j).size() : " + this.mLiens.get(i).get(j).size() + "\n");
                    nbLiens += this.mLiens.get(i).get(j).size();
                    for (int k = 0 ; k < this.mLiens.get(i).get(j).size() ; k++) {
                        //System.out.print("Taille this.mLiens.get(i).get(j).get(k) : " + this.mLiens.get(i).get(j).get(k) + "\n");
                        nbLiens += this.mLiens.get(i).get(j).get(k);
                    }
                }
            }
        }

        return nbLiens;
    }
    public boolean isControle() {
        return mControle;
    }

    public void setControle(boolean controle) {
        this.mControle = controle;
    }

    public void setNbCouche(int nbCouche) {
        //setTabCouche(new ArrayList<Integer>(nbCouche));
    }

    public int getNbCouches() { return this.mTabCouche.size(); }

    public void setCouche(int nbCouche) {
        if (!this.mControle) {
            if (nbCouche > 2) {
//                setNbCouche(nbCouche);
                for (int i = 0 ; i < nbCouche ; i++) {
                    this.mTabCouche.add(0);
                }
            }
        }
    }
    public void addCouche(int pos) {
        if (!this.mControle) {
            if (pos >= 0 && pos < this.mTabCouche.size()) {
                this.mTabCouche.add(pos, 0);
            }
        }
    }

    public void addNeurone(int couche, int nbrNeurones) {
        if (!this.mControle) {
            if (couche >= 0 && couche <= this.mTabCouche.size() - 1 && nbrNeurones > 0) {
                this.mTabCouche.set(couche, this.mTabCouche.get(couche) + nbrNeurones);
            }
        }
    }

    public void addAllNeurone(int [] tableau) {
        if (!this.mControle) {
            if (tableau.length == this.mTabCouche.size()) {
                for (int i = 0 ; i < tableau.length ; i++) {
                    addNeurone(i, tableau[i]);
                }
            }
        }
    }

    public void createNetwork() {

        this.mLiens     = new ArrayList<ArrayList<ArrayList<Double>>>();
        this.mValeurs   = new ArrayList<ArrayList<Double>>();
        int controle        = 0;
        for (int i = 0 ; i < this.mTabCouche.size() ; i++) {
            if (this.mTabCouche.get(i) <= 0)
                controle = 1;
        }

        if (controle != 1) {
            if (!this.mControle) {
                this.mControle = true;

                //System.out.print("Nombre de couches : " + this.mTabCouche.size() + "\n");
                for (int i = 0 ; i < this.mTabCouche.size() ; i++) {
                    ArrayList<ArrayList<Double>> addCouche      = new ArrayList<ArrayList<Double>>();
                    ArrayList<Double> addValues                 = new ArrayList<Double>();
                    //System.out.print("Couche : " + (i + 1) + " Nombre de neurones : " + this.mTabCouche.get(i) + "\n");
                    ArrayList<Double> addLien;
                    for (int j = 0 ; j < this.mTabCouche.get(i) ; j++) {
                        if (i != (this.mTabCouche.size() - 1)) {
                            addLien                   = new ArrayList<Double>();
                            //System.out.print("Nombre de Liens : " + this.mTabCouche.get(i + 1) + "\n");
                            for (int k = 0 ; k < this.mTabCouche.get(i + 1) ; k++) {
                                addLien.add(0.5);
                            }
                            //System.out.print("Taille de addLien : " + addLien.size() + "\n");
                            addCouche.add(addLien);
                            //addLien.clear();
                        }
                        addValues.add(0.0);
                    }
                    if (i != (this.mTabCouche.size() - 1)) {
                        this.mLiens.add(addCouche);
                    }
                    this.mValeurs.add(addValues);
                }
                //System.out.print("Taille de mValeurs : " + this.mValeurs.size() + "\n");
                //System.out.print("Taille de mValeurs : " + this.mValeurs.get(0) + "\n");
                //System.out.print("Taille de mValeurs : " + this.mValeurs.get(1) + "\n");
            }
        }
    }

    public void propagation(double[] tab) {

        if (isControle()) {
            if (tab.length == nbNeuronesEntree()) {

                for (int i = 0 ; i < tab.length ; i++) {
                    (this.mValeurs.get(0)).set(i, tab[i]);
                }

                for (int i = 1 ; i < this.mValeurs.size() ; i++) {
                    for (int j = 0 ; j < this.mValeurs.get(i).size(); j++) {
                        double variable = 0.0;

                        //System.out.print("Taille : " + this.mValeurs.get(i - 1).size()  + "\n");
                        for (int k = 0 ; k < this.mValeurs.get(i - 1).size() ; k++) {
                            //System.out.print("propagation Indice k : " + k + " Valeur : " + this.mValeurs.get(i - 1).get(k) + "\n");
                            //System.out.print("propagation Indice j : " + j + " Valeur : " + this.mValeurs.get(i - 1).get(k) + "\n");
                            variable += (double)this.mValeurs.get(i - 1).get(k) * this.mLiens.get(i - 1).get(k).get(j);

                        }
                        this.mValeurs.get(i).set(j, FonctionsActivations.sigmoide(variable));
                    }
                }
            }
        }
    }

    public void retropropagation(double[] tab) {

        if (tab.length == this.mValeurs.get(this.mValeurs.size() - 1).size()) {
            for (int i = 0 ; i < tab.length ; i++) {
                this.mValeurs.get(this.mValeurs.size() - 1 ).set(i, tab[0] - this.mValeurs.get(this.mValeurs.size() - 1 ).get(i));
            }

            for (int i = this.mValeurs.size() - 1 ; i > 0 ; i--) {
                for (int j = 0 ; j < this.mValeurs.get(i - 1).size() ; j++) {
                    for (int k = 0 ; k < this.mLiens.get(i - 1).get(j).size() ; k++) {
                        double somme = 0;
                        for (int m = 0 ; m < this.mValeurs.get(i - 1).size() ; m++) {
                            //System.out.print("Retropropagation : " + this.mValeurs.get(i - 1).get(m) + "\n");
                            //System.out.print("Retropropagation : " + this.mLiens.get(i - 1).get(m).get(k) + "\n");
                            somme += this.mValeurs.get(i - 1).get(m) * this.mLiens.get(i - 1).get(m).get(k);
                        }
                        somme = FonctionsActivations.sigmoide(somme);

                        this.mLiens.get(i - 1).get(j).set(k, this.mLiens.get(i - 1).get(j).get(k) - this.getError() * (-1 * this.mValeurs.get(i).get(k) * somme * (1 - somme) * this.mValeurs.get(i - 1).get(j)));
                    }
                }

                for (int j = 0 ; j < this.mValeurs.get(i - 1).size() ; j++) {
                    double somme = 0;
                    for (int k = 0 ; k < this.mValeurs.get(i).size() ; k++) {
                        somme += this.mValeurs.get(i).get(k);// * this.mLiens.get(i - 1).get(j).get(k);
                    }
                    this.mValeurs.get(i - 1).set(j, somme);
                }
            }
        }
    }

    public void learn(double[] entree, double[] sortie) {

        if (this.mControle) {
            //System.out.print(entree.length);
            if (entree.length == this.mTabCouche.get(0) && sortie.length == this.mTabCouche.get(this.mTabCouche.size() - 1)) {
                this.propagation(entree);
                this.retropropagation(sortie);
            }
        }
    }

    public int nbValeurs() {
        return this.mValeurs.get(this.mValeurs.size()-1).size();
    }
//    private ArrayList<Integer> mTabCouche;                      // Le tableau des couches avec le nombre de neurones par couches
//    private ArrayList<ArrayList<ArrayList<Double>>> mLiens;     // Les poids
//    private ArrayList<ArrayList<Double>> mValeurs;

    public int test(double[] entree) {
        return 1;
    }
}
