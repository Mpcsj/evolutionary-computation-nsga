package trabalhos.trab3.mainLoop.strategies.recombination

import trabalhos.common.models.Logger
import trabalhos.common.utils.GenericUtils
import trabalhos.common.utils.ListUtils
import trabalhos.common.utils.MathUtils
import trabalhos.trab3.mainLoop.models.problem.Problem
import trabalhos.trab3.mainLoop.models.specimen.Specimen
import trabalhos.trab3.mainLoop.models.specimen.SpecimenFactoryInterface
import kotlin.collections.ArrayList
import kotlin.math.abs

class BlxAlphaStrategy(
    private val specimenFactory:SpecimenFactoryInterface,
    private var shouldRecombineChildrenGenes:Boolean=GenericUtils.getRandomBoolean(),
    currLogLevel:Int=0):RecombinationStrategy {
    private val TAG = BlxAlphaStrategy::class.java.canonicalName
    private val logger:Logger = Logger(TAG,currLogLevel)
    private val standardDeviation:Double = MathUtils.getNextGaussian()

    private fun generateOneGene(geneFatherA:Double,geneFatherB:Double):Double{
        return geneFatherA + (MathUtils.getNextGaussian()*standardDeviation)*abs(
            geneFatherA-geneFatherB
        )
    }
    override fun recombine(
        parentA: Specimen,
        parentB: Specimen,
        problem: Problem): List<Specimen> {
        assert(parentA.genes.size == parentB.genes.size && parentA.genes.isNotEmpty())
        logger.showMessage("parentA genes list size:${parentA.genes.size}",1)
        logger.showMessage("parentB genes list size:${parentB.genes.size}",1)
        val childAGenes:List<Double>;
        val childBGenes:List<Double>;
        if(shouldRecombineChildrenGenes){
            if(GenericUtils.getRandomBoolean()){
                childAGenes = parentA.genes.mapIndexed() {
                        idx, d -> generateOneGene(d,parentB.genes[idx])
                }
                childBGenes = parentB.genes.mapIndexed(){
                        idx,
                        d->generateOneGene(d,parentA.genes[idx])
                }
            }else{
                childAGenes = parentB.genes.mapIndexed() {
                        idx, d -> generateOneGene(d,parentA.genes[idx])
                }
                childBGenes = parentA.genes.mapIndexed(){
                        idx,
                        d->generateOneGene(d,parentB.genes[idx])
                }
            }
        }else{
            childAGenes = parentA.genes.mapIndexed() {
                    idx, d -> generateOneGene(d,parentB.genes[idx])
            }
            childBGenes = parentB.genes.mapIndexed(){
                    idx,
                    d->generateOneGene(d,parentA.genes[idx])
            }
        }
        return arrayListOf(
            specimenFactory.newSpecimen(childAGenes ,problem),
            specimenFactory.newSpecimen(childBGenes ,problem)
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
//        shouldRecombineChildrenGenes = GenericUtils.getRandomBoolean()
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