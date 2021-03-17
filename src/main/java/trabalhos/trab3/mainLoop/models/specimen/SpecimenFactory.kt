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

        val genes:List<Double> = MutableList(genesQuantity) {
            surroundValue(Math.random())
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
        val genesToUse = genes.map { curr -> surroundValue(curr) }.toList()
        return Specimen(genesToUse,problem.calculate(genesToUse,label))
    }

    private fun surroundValue(value:Double):Double{
        val res = min(superiorLimit,max(inferiorLimit,value))
        return res
    }
}