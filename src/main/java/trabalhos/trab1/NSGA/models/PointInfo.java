package trabalhos.trab1.NSGA.models;

import trabalhos.trab1.models.CandidateSol;

import java.util.ArrayList;
import java.util.List;

public class PointInfo {
    private CandidateSol candidateSol; // ponto em questao
    private int n,rank;
    private List<CandidateSol> s; // pontos dominados pelo ponto atual

    public PointInfo(CandidateSol candidateSol){
        this.candidateSol = candidateSol;
        n = 0;
        this.rank = 0;
        this.s = new ArrayList<>();
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void decrN(){
        this.n--;
    }
    public void incrN(){
        this.n++;
    }
    public void setN(int n) {
        this.n = n;
    }

    public CandidateSol getDataPoint() {
        return candidateSol;
    }

    public int getN() {
        return n;
    }

    public List<CandidateSol> getS() {
        return s;
    }
}
