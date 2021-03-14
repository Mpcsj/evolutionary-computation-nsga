package trabalhos.trab3.mainLoop.models.problem

import trabalhos.trab1.models.CandidateSol

interface Problem {
    fun calculate(genes:List<Double>,label:String?):CandidateSol
}