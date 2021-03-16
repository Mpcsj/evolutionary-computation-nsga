package trabalhos.trab3.mainLoop.models.specimen

import trabalhos.common.models.Logger
import trabalhos.trab3.mainLoop.models.problem.Problem
import java.lang.Double.max
import java.lang.Double.min

class SpecimenFactory(
    private val inferiorLimit:Double=.0,
    private val superiorLimit:Double=1.0,
    logLevel:Int = 0
):SpecimenFactoryInterface {
    // criar um limite superior e inferior para os genes
    private val logger:Logger = Logger(SpecimenFactory::class.java.canonicalName,logLevel)
    override fun newSpecimen(
        genesQuantity:Int,
        problem:Problem,
        label:String): Specimen {
        /*
        label="Problem-"+Math.random()
        * */
//        val genes = mutableListOf<Double>()
//        for(el in 1..genesQuantity){
//            val curr =max(inferiorLimit,Math.random())
//            genes.add(min(curr,superiorLimit))
//        }
        val genes:List<Double> = MutableList(genesQuantity) {
            val curr =max(inferiorLimit,Math.random())
            min(curr,superiorLimit)
        }
        return newSpecimen(genes,problem,label)
    }

    override fun newSpecimen(genes: List<Double>, problem: Problem, label: String): Specimen {
        assert(genes.isNotEmpty())
        if(genes.isEmpty()){
            throw RuntimeException()
        }
        logger.showMessage("newSpecimen::genes::size:${genes.size}",1)
        logger.showMessage("newSpecimen::genes::$genes",2)
        val genesToUse = genes.map { curr -> min(superiorLimit,max(inferiorLimit,curr)) }.toList()
        return Specimen(genes,problem.calculate(genesToUse,label))
    }
}