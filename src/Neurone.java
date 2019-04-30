import java.util.ArrayList;

public class Neurone {

    private ArrayList<Double> mLiens;
    private ArrayList<Neurone> mNeurones;
    private double mPoids;
    private double mBiais;

    Neurone(double biais) {
        this.mPoids         = 0.0;
        this.mBiais         = biais;
    }

    public double getBiais() { return this.mBiais; }
    public double getPoids() { return this.mPoids; }

    public void setBiais(double biais) { this.mBiais = biais; }
    public void setPoids(double poids) { this.mPoids = poids; }

    public double somme() {

        double valRetour        = 0.0;

        for (int i = 0 ; i < this.mNeurones.size() ; i++) {
            valRetour += this.mNeurones.get(i).getPoids() * this.mLiens.get(i);
        }
        return valRetour;
    }

    public double activation() {
        return this.sigmoide(somme());
    }

    private double sigmoide(double valeur) {
        return 1 / (1 + Math.exp(-1 * valeur));
    }


}
