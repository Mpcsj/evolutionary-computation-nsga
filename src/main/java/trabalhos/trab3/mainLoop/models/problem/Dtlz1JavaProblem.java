package trabalhos.trab3.mainLoop.models.problem;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import trabalhos.trab1.models.CandidateSol;

import java.util.List;

public class Dtlz1JavaProblem extends GenericProblem{
    private final int numObjectives;
    public Dtlz1JavaProblem(int numObjectives){
        this.numObjectives = numObjectives;
    }
    @NotNull
    @Override
    public CandidateSol calculate(@NotNull List<Double> genes, @Nullable String label) {
        int numberOfObjectives = this.numObjectives;
        int numberOfVariables = genes.size();
        double[] f = new double[numberOfObjectives];
        double[] x = new double[numberOfVariables];

        int k = numberOfVariables - numberOfObjectives + 1;

        for (int i = 0; i < numberOfVariables; i++) {
            x[i] = genes.get(i);
        }

        double g = 0.0;
        for (int i = numberOfVariables - k; i < numberOfVariables; i++) {
            g += (x[i] - 0.5) * (x[i] - 0.5) - Math.cos(20.0 * Math.PI * (x[i] - 0.5));
        }

        g = 100 * (k + g);
        for (int i = 0; i < numberOfObjectives; i++) {
            f[i] = (1.0 + g) * 0.5;
        }

        for (int i = 0; i < numberOfObjectives; i++) {
            for (int j = 0; j < numberOfObjectives - (i + 1); j++) {
                f[i] *= x[j];
            }
            if (i != 0) {
                int aux = numberOfObjectives - (i + 1);
                f[i] *= 1 - x[aux];
            }
            if(f[i]>100 || f[i]<-100){
                System.out.println("...");
            }
        }

        return new CandidateSol(getBasicLabel(label),f);
    }
}
