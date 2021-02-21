package trabalhos.trab2.crowdDist.models

import trabalhos.common.utils.ValidationUtils
import trabalhos.trab1.models.CandidateSol
import trabalhos.trab1.models.InvalidCandidateSolutionException

class CrowdDistModel constructor(candidateSolList: List<CandidateSol>) {
    val candidateSolWrapperList: List<CandidateSolWrapper> =
        candidateSolList.map { candidateSol -> CandidateSolWrapper(candidateSol) };
    init {
        if(!ValidationUtils.allDimensionsEqual(candidateSolList)){
            throw InvalidCandidateSolutionException()
        }
    }
    fun getNumberOfObjectives():Int{
        return candidateSolWrapperList[0].candidateSol.points.size
    }
    fun getNumberOfSolutions():Int{
        return candidateSolWrapperList.size
    }


    fun getMinByObjective(idxObjective:Int):Double{
        var res = candidateSolWrapperList[0].candidateSol.points[idxObjective]
        for(el in 0 until candidateSolWrapperList.size){
            res = Math.min(res,candidateSolWrapperList[el].candidateSol.points[idxObjective])
        }
        return res
    }

    fun getMaxByObjective(idxObjective:Int):Double{
        var res = candidateSolWrapperList[0].candidateSol.points[idxObjective]
        for(el in 0 until candidateSolWrapperList.size){
            res = Math.max(res,candidateSolWrapperList[el].candidateSol.points[idxObjective])
        }
        return res
    }

    override fun toString(): String {
        return "CrowdDistModel={$candidateSolWrapperList}"
    }
}