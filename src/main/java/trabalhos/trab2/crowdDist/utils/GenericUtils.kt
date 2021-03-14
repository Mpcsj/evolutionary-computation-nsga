package trabalhos.trab2.crowdDist.utils

import trabalhos.trab2.crowdDist.models.CandidateSolWrapper

class GenericUtils {
    companion object{// equivalente a uma classe com metodos estaticos

        /**
         * Ordena uma lista de pontos com base em um objetivo
         * */
        fun sortMethod(
            candidateSolWrapperList: List<CandidateSolWrapper>,
            objective:Int):List<CandidateSolWrapper>{
            var res:List<CandidateSolWrapper> = candidateSolWrapperList.map { el -> el }
            res = res.sortedBy {
                it.candidateSol.points[objective]
            }
            return res
        }
        fun sortMethodByD(
            candidateSolWrapperList: List<CandidateSolWrapper>,
            ):List<CandidateSolWrapper>{
            var res:List<CandidateSolWrapper> = candidateSolWrapperList.map { el -> el }
            res = res.sortedBy {
                it.d
            }
            return res
        }

    }
}