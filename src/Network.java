import java.util.ArrayList;

public class Network {

    private String mName                    = "";               //Nom du réseau
    private double mError                   = 0.001;            //Valeur de l'erreur
    private int mActivation                 = 1;                //Fonction d'activation 1 sigmoide, 2 tangente
    private ArrayList<Integer> mCouches;                        //Le nombre de couches
    private ArrayList<ArrayList<ArrayList<Double>>> mLiens;     //La valeur du lien entre 2 neurones
    private ArrayList<ArrayList<Double>> mValeurs;              //La valeur du neurone
    private boolean mInitialise             = false;            //A vrai si le réseau a été initialisé

    Network(String nom, double erreur) {
        this.mName              = nom;
        this.mError             = erreur;
        this.mCouches           = new ArrayList<Integer>();
    }

    public void setName(String nom) { this.mName = nom; }
    public void setError(double erreur) { this.mError = erreur; }
    public void setActivation(int activation) { this.mActivation = activation; }
    public void setNbCouches(int nbCouches) {this.mCouches = new ArrayList<Integer>(nbCouches); }

    public String getName() { return this.mName; }
    public double getErreur() { return this.mError; }
    public int getActivation() { return this.mActivation; }
    public int getNbCouches() { return this.mCouches.size(); }

    @Override
    public String toString(){
        return "Nom du réseau : " + getName() +"\nErreur : " + getErreur() + "\nFonction d'activation : " + getActivation();
    }
}
