package trabalhos.trab3.mainLoop.models.problem

import trabalhos.trab1.models.CandidateSol
import java.util.function.Consumer

class BasicProblem(private val outputSize:Int):Problem {
    override fun calculate(genes: List<Double>,label:String?): CandidateSol {
        val res:Array<Double> = Array(outputSize) { Math.random() }
//        val res:Array<Double> = Array(outputSize) {
//            genes.reduce { sum, d ->sum+d }
//        }
        val labelToShow:String = label ?: "Problem-"+Math.random()
        return CandidateSol(labelToShow,res.toList())
    }
}