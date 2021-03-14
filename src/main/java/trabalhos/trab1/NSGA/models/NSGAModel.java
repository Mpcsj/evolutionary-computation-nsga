package trabalhos.trab1.NSGA.models;

import trabalhos.common.utils.ValidationUtils;
import trabalhos.trab1.models.CandidateSol;
import trabalhos.trab1.models.InvalidCandidateSolutionException;

import java.util.ArrayList;
import java.util.List;

public class NSGAModel {
    private List<List<PointInfo>> frontiers; // fronteiras
    private List<CandidateSol> originalList;
    private List<PointInfo> pointInfoList;

    private boolean allEqual(List<CandidateSol>list){
        return ValidationUtils.Companion.allDimensionsEqual(list);
    }
    public NSGAModel(List<CandidateSol>list) throws InvalidCandidateSolutionException {
        if(!allEqual(list)){
            throw new InvalidCandidateSolutionException();
        }
        pointInfoList = new ArrayList<>(list.size());
        frontiers = new ArrayList<>();
        this.originalList = list;
        list.forEach(dataPoint -> pointInfoList.add(new PointInfo(dataPoint)));
    }

    public List<PointInfo> getF1() {
        if(frontiers.isEmpty()){
            frontiers.add(new ArrayList<>());
        }
        return frontiers.get(0);
    }

    public PointInfo getPointInfoByDataPoint(CandidateSol candidateSol){
        PointInfo res = null;
        for (PointInfo pi: getPointInfoList()) {
            if(pi.getDataPoint() == candidateSol){
                res = pi;
                break;
            }
        }
        return res;
    }

    public List<PointInfo> getPointInfoList() {
        return pointInfoList;
    }

    public List<CandidateSol> getOriginalList() {
        return originalList;
    }

    public List<List<PointInfo>> getFrontiers() {
        if(frontiers == null){
            frontiers = new ArrayList<>();
        }
        return frontiers;
    }

    public List<List<CandidateSol>>getPointsByFrontier(){
        List<List<CandidateSol>> res = new ArrayList<>();
        for(List<PointInfo>f:getFrontiers()){
            List<CandidateSol> curr = new ArrayList<>();
            for(PointInfo pointInfo:f){
                curr.add(pointInfo.getDataPoint());
            }
            res.add(curr);
        }
        return res;
    }

    public List<CandidateSol>getOrderedPointsByFrontier(){
        List<CandidateSol>res = new ArrayList<>();
        for(List<CandidateSol>curr:getPointsByFrontier()){
            res.addAll(curr);
        }
        return res;
    }
    private void fixFrontiersListSize(int desiredSize){
        if(getFrontiers().size() < desiredSize){
            for(int idx=getFrontiers().size();idx<desiredSize;idx++){
                getFrontiers().add(new ArrayList<>());
            }
        }
    }

    public void handleFrontiers(){
        for(PointInfo p:getPointInfoList()){
            fixFrontiersListSize(p.getRank());
            if(!getFrontiers().get(p.getRank()-1).contains(p)){
                getFrontiers().get(p.getRank()-1).add(p);
            }
        }
    }
}
