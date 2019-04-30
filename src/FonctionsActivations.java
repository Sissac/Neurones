public class FonctionsActivations {

    public static double sigmoide(double valeur) {
        return 1 / (1 + Math.exp(-1 * valeur));
    }

    public static double tangente(double valeur) {
        return 1.7159 * Math.tanh((2/3) * valeur);
    }
}
