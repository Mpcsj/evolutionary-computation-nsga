package trabalhos.common.utils

import trabalhos.trab1.models.CandidateSol

class ValidationUtils {
    companion object{
        fun allDimensionsEqual(candidateSolList: List<CandidateSol>?):Boolean{
            if(candidateSolList!=null && !candidateSolList.isEmpty()){
                val size = candidateSolList[0].points.size
                for(sol in candidateSolList){
                    if(sol.points.size != size){
                        return false
                    }
                }
                return true
            }else{
                return true
            }
        }
    }
}