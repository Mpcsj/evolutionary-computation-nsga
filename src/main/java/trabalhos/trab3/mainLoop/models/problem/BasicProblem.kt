package trabalhos.trab3.mainLoop.models.problem

import trabalhos.trab1.models.CandidateSol

class BasicProblem(val outputSize:Int):Problem {
    override fun calculate(genes: List<Double>,label:String?): CandidateSol {
        val res:Array<Double> = Array(outputSize) { Math.random() }
        val labelToShow:String = label ?: "Problem-"+Math.random()
        return CandidateSol(labelToShow,res.toList())
    }
}