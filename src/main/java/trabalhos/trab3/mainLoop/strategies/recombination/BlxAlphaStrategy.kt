package trabalhos.trab3.mainLoop.strategies.recombination

import trabalhos.common.utils.ListUtils
import trabalhos.trab3.mainLoop.models.problem.Problem
import trabalhos.trab3.mainLoop.models.specimen.Specimen
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

class BlxAlphaStrategy:RecombinationStrategy {
    val alpha:Double = Random().nextGaussian()
    override fun recombine(parentA: Specimen, parentB: Specimen, problem: Problem): List<Specimen> {
        assert(parentA.genes.size == parentB.genes.size)
        val childAGenes = parentA.genes.mapIndexed() { idx,d -> d+alpha*(abs(d - parentB.genes[idx])) }
        val childBGenes = parentB.genes.mapIndexed(){idx,d->d+alpha*(abs(d-parentA.genes[idx]))}
        return arrayListOf(
            Specimen(childAGenes,problem.calculate(childAGenes,null)),
            Specimen(childBGenes,problem.calculate(childBGenes,null))
        )
    }

    override fun recombineAll(
        parentAList: List<Specimen>,
        parentBList: List<Specimen>,
        problem: Problem
    ): List<Specimen> {
        var res =ArrayList<Specimen>()
        parentAList.forEach{el->parentBList.forEach{el2->
            run {
                res = ListUtils.concatenate(res, recombine(el, el2, problem)) as ArrayList<Specimen>
            }
        }}
        return res
    }

}