package trabalhos.trab3.mainLoop.models.problem

import trabalhos.trab1.models.CandidateSol
import java.util.function.Consumer

class BasicProblem(private val outputSize:Int):GenericProblem() {
    override fun calculate(genes: List<Double>,label:String?): CandidateSol {
        val res:Array<Double> = Array(outputSize) { Math.random() }
        return CandidateSol(getBasicLabel(label),res.toList())
    }
}