package trabalhos.trab1.models;

//import org.jetbrains.annotations.NotNull;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.List;

public class CandidateSol implements Comparable<CandidateSol> {
    private final double[] points;
    private final String label;

    public CandidateSol(String label,double ...points) {
        this.points= points;
        this.label = label;
    }
    public CandidateSol(String label, List<Double> points) {
        assert points!=null;
        this.points= new double[points.size()];
        for(int idx = 0;idx<points.size();idx++){
            this.points[idx] = points.get(idx);
        }
        this.label = label;
    }

    public double[] getPoints() {
        return points;
    }

    public String getLabel() {
        return label;
    }

    /**
     * Verifica se o ponto atual domina o ponto recebido como parametro
     * */
    public boolean dominates(CandidateSol candidateSol){
        boolean allEqual = true;
        for(int idx=0;idx<this.points.length;idx++){
            if(this.points[idx]!=candidateSol.points[idx]){
                allEqual = false;
                break;
            }
        }
        if(allEqual)return false;
        boolean allGreaterOrEqual = true;
        for(int idx=0;idx<this.points.length;idx++){
            if(candidateSol.points[idx]<this.points[idx]){
                allGreaterOrEqual =false;
                break;
            }
        }
        return allGreaterOrEqual;
    }

    @Override
    public int compareTo(@NotNull CandidateSol candidateSol) {
        return dominates(candidateSol)?-1:1;
    }

    @Override
    public String toString() {
        return "CandidateSol{" +
                "points=" + Arrays.toString(points) +
                ", label='" + label + '\'' +
                '}';
    }
}
