package trabalhos.trab3.mainLoop.models.problem

import trabalhos.trab1.models.CandidateSol

class SchafferProblem: GenericProblem() {
    override fun calculate(genes: List<Double>,label:String?): CandidateSol {
        val first = genes[0]
        return CandidateSol(
            getBasicLabel(label),
            listOf(first*first,(first-2)*(first-2))
        )
    }
}