package trabalhos.trab2.crowdDist

import trabalhos.trab1.models.CandidateSol
import trabalhos.trab2.crowdDist.models.CrowdDistModel
import trabalhos.trab2.crowdDist.utils.GenericUtils

class CrowdDistImpl {
    private val TAG: String = CrowdDistImpl::class.java.canonicalName

    fun process(model: CrowdDistModel): CrowdDistModel {
        for (objective in 0 until model.getNumberOfObjectives()) { // para cada objetivo
            val t = GenericUtils.sortMethod(model.candidateSolWrapperList, objective)
            t[0].d = Double.MAX_VALUE
            t[model.getNumberOfSolutions() - 1].d = Double.MAX_VALUE
            for (idx in 1 until (model.getNumberOfSolutions()-1)) {
                val aux =
                    (t[idx + 1].candidateSol.points[objective] - t[idx - 1].candidateSol.points[objective]) /
                            (model.getMaxByObjective(objective) - model.getMinByObjective(objective))
                t[idx].d += aux
            }
        }
        return model
    }

    fun execute(candidateSolList: List<CandidateSol>): CrowdDistModel {
        println("$TAG::execute::candidateSolList:$candidateSolList")
//        val res = CrowdDistModel(candidateSolList)
        return process(CrowdDistModel(candidateSolList))
    }
}