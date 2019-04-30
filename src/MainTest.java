import java.util.ArrayList;

public class MainTest {

    public static void main(String[] args) {

        int[] table = {2,5,7,1};
        Reseau reseau = new Reseau();

        reseau.setError(0.05);
        reseau.setCouche(4);
        reseau.addAllNeurone(table);
        reseau.createNetwork();

        System.out.print("Nombre de couche(s) : " + reseau.getNbCouches() + "\n");
        System.out.print("Nombre de valeur(s) : " + reseau.nbValeurs() + "\n");
        System.out.print("Nombre de Neurone(s) : " + reseau.getNbNeurones() + "\n");
        System.out.print("Nombre de Lien(s) : " + reseau.getNbLiens() + "\n");



        double[] data = {1, 1};
        double[] out = {1};
        reseau.learn(data, out);

        double[] data1 = {1, 0};
        double[] out1 = {0};
        reseau.learn(data1, out1);

        double[] data2 = {0, 1};
        double[] out2 = {0};
        reseau.learn(data2, out2);

        double[] data3 = {0, 0};
        double[] out3 = {0};
        reseau.learn(data3, out3);

    }
}
