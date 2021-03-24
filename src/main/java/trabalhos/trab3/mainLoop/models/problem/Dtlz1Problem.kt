package trabalhos.trab3.mainLoop.models.problem

import trabalhos.trab1.models.CandidateSol
import kotlin.math.cos
import kotlin.math.sqrt

class Dtlz1Problem(private val numObjectives:Int): GenericProblem() {
    override fun calculate(genes: List<Double>, label: String?): CandidateSol {
//        TODO("Not yet implemented")
        val k = genes.size - numObjectives +1
        var g:Double  = 0.0
        val f = arrayListOf<Double>()
        for(idx in (genes.size - k) until genes.size){
            val curr = (genes[idx] - 0.5) * (genes[idx] - 0.5) - Math.cos(20.0 * Math.PI * (genes[idx] - 0.5))
            g += curr
        }
        g = 100 * (k+g)
        for(idx in 0 until numObjectives){
            val curr = (1+g)*0.5
            f.add(curr)
        }
        for(i in 0 until numObjectives){
            for(j in 0 until (numObjectives - (i+1))){
                f[i]*=genes[j]
            }
            if(i!=0){
                val aux = numObjectives - (i+1)
                f[i]*= 1 - genes[aux]
            }
        }
        return CandidateSol(getBasicLabel(label),f)
    }
}