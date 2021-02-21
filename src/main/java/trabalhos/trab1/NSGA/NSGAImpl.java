package trabalhos.trab1.NSGA;

import trabalhos.trab1.NSGA.models.NSGAModel;
import trabalhos.trab1.NSGA.models.PointInfo;
import trabalhos.trab1.models.CandidateSol;
import trabalhos.trab1.models.InvalidCandidateSolutionException;

import java.util.ArrayList;
import java.util.List;

public class NSGAImpl {
    public static final String TAG = NSGAImpl.class.getCanonicalName();
    private NSGAModel process1(NSGAModel model){
        System.out.println(TAG+"::process1");
        for (PointInfo p: model.getPointInfoList()) {
            List<CandidateSol>sp = p.getS();
            for(CandidateSol q:model.getOriginalList()){
//                int comparation =p.getDataPoint().compareTo(q);
                if(p.getDataPoint().dominates(q)){ // se p domina q
                    sp.add(q);
                }else if(q.dominates(p.getDataPoint())){ // se p é dominado por q
                    p.incrN();
                }
            }
            if(p.getN()==0){
                p.setRank(1);
                model.getF1().add(p);
            }
        }
        return model;
    }

    private NSGAModel process2(NSGAModel model){
//        System.out.println(TAG+"::process2::f1:"+new Gson().toJson(model.getF1()));
        int idx = 0;
        List<PointInfo> currFrontier = model.getFrontiers().get(idx);
        while (!currFrontier.isEmpty()){
            List<PointInfo> qZao = new ArrayList<>();
            for(PointInfo pointInfo:currFrontier){// para cada ponto da fronteira atual
                for(CandidateSol candidateSol :pointInfo.getS()){
                    PointInfo q = model.getPointInfoByDataPoint(candidateSol);
                    // nao poder ser null
                    q.decrN();
                    if(q.getN() == 0){
                        q.setRank(idx+2);
                        qZao.add(q);
                    }
                }
            }
            idx++;
            currFrontier = qZao;
        }
        model.handleFrontiers();
        return model;
    }


    public NSGAModel execute(List<CandidateSol> data) {
        //        int pRank = 0;
        NSGAModel model = null;
        try {
            model = new NSGAModel(data);
        } catch (InvalidCandidateSolutionException e) {
            e.printStackTrace();
        }
        return process2(process1(model));
    }
}
