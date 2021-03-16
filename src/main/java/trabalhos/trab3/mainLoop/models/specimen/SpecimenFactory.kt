package trabalhos.trab3.mainLoop.models.specimen

import trabalhos.trab3.mainLoop.models.problem.Problem
import java.lang.Double.max
import java.lang.Double.min

class SpecimenFactory(
    private val inferiorLimit:Double=.0,
    private val superiorLimit:Double=1.0):SpecimenFactoryInterface {
    // criar um limite superior e inferior para os genes
    override fun newSpecimen(
        genesQuantity:Int,
        problem:Problem,
        label:String): Specimen {
        /*
        label="Problem-"+Math.random()
        * */
        val genes = ArrayList<Double>(genesQuantity)
        for(el in genes.indices){
            val curr =max(inferiorLimit,Math.random())
            genes[el] = min(curr,superiorLimit)
        }
        return Specimen(genes,problem.calculate(genes,label))
    }
}