package trabalhos.trab3.mainLoop.strategies.recombination

import trabalhos.common.models.Logger
import trabalhos.common.utils.ListUtils
import trabalhos.trab3.mainLoop.models.problem.Problem
import trabalhos.trab3.mainLoop.models.specimen.Specimen
import trabalhos.trab3.mainLoop.models.specimen.SpecimenFactoryInterface
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

class BlxAlphaStrategy(
    val specimentFactory:SpecimenFactoryInterface,
    currLogLevel:Int=0):RecombinationStrategy {
    private val TAG = BlxAlphaStrategy::class.java.canonicalName
    private val logger:Logger = Logger(TAG,currLogLevel)
    val alpha:Double = Random().nextGaussian()
    override fun recombine(
        parentA: Specimen,
        parentB: Specimen,
        problem: Problem): List<Specimen> {
        assert(parentA.genes.size == parentB.genes.size && parentA.genes.isNotEmpty())
        logger.showMessage("parentA genes list size:${parentA.genes.size}",1)
        logger.showMessage("parentB genes list size:${parentB.genes.size}",1)
        val childAGenes = parentA.genes.mapIndexed() { idx,d -> d+alpha*(abs(d - parentB.genes[idx])) }
        val childBGenes = parentB.genes.mapIndexed(){idx,d->d+alpha*(abs(d-parentA.genes[idx]))}
        return arrayListOf(
            specimentFactory.newSpecimen(childAGenes ,problem),
            specimentFactory.newSpecimen(childBGenes ,problem)
        )
    }

    override fun recombineAll(
        parentAList: List<Specimen>,
        parentBList: List<Specimen>,
        problem: Problem
    ): List<Specimen> {
        assert(parentAList.size == parentBList.size)
        logger.showMessage("recombineAll:parentAList::size${parentAList.size}::parentBList:${parentBList.size}",2)
        var res =ArrayList<Specimen>()
        for(idx in parentAList.indices){
            res = ListUtils.concatenate(
                res,
                recombine(
                    parentAList[idx],
                    parentBList[idx],
                    problem
                )
            ) as ArrayList<Specimen>
        }
        logger.showMessage("lista final::size:${res.size}",1)
        return res
    }

}