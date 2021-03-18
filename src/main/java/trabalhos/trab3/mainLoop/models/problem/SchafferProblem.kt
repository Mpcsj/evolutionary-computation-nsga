package trabalhos.trab3.mainLoop.models.problem

import trabalhos.trab1.models.CandidateSol

class SchafferProblem:Problem {
    override fun calculate(genes: List<Double>,label:String?): CandidateSol {
//        val res:Array<Double> = Array(outputSize) { Math.random() }
//
////        val res:Array<Double> = Array(outputSize) {
////            genes.reduce { sum, d ->sum+d }
////        }
        val first = genes[0]
        val labelToShow:String = label ?: "Problem-"+Math.random()
        return CandidateSol(labelToShow, listOf(first*first,(first-2)*(first-2)))
    }
}