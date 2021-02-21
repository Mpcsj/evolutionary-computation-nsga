package trabalhos.trab1;
import trabalhos.CefetJob;
import trabalhos.trab1.NSGA.NSGAImpl;
import trabalhos.trab1.NSGA.models.NSGAModel;
import trabalhos.trab1.models.CandidateSol;

import java.util.ArrayList;
import java.util.List;

public class Trab1 implements CefetJob {
    public static final String TAG = Trab1.class.getCanonicalName();
    @Override
    public void run() {
        System.out.println("Preparando ambiente 2...");
        List<CandidateSol> points = new ArrayList<>();
        points.add(new CandidateSol("A",1,5));
        points.add(new CandidateSol("B",2,3));
        points.add(new CandidateSol("C",4,1));
        points.add(new CandidateSol("D",3,4));
        points.add(new CandidateSol("E",4,3));
        points.add(new CandidateSol("F",5,5));
        NSGAModel model = new NSGAImpl().execute(points);
        System.out.println(model.getFrontiers().size()+" fronteiras encontradas");
        for(List<CandidateSol>f:model.getPointsByFrontier()){
            System.out.println("Fronteira atual");
            for(CandidateSol p:f){
                System.out.print(p.getLabel()+"\t");
            }
            System.out.println("\n");
        }
    }

    @Override
    public String getTAG() {
        return TAG;
    }
}
