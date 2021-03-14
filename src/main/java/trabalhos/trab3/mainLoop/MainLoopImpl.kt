package trabalhos.trab3.mainLoop

import trabalhos.common.utils.ListUtils
import trabalhos.trab1.NSGA.NSGAImpl
import trabalhos.trab1.models.CandidateSol
import trabalhos.trab2.crowdDist.CrowdDistImpl
import trabalhos.trab3.mainLoop.models.MainLoopModel
import trabalhos.trab3.mainLoop.models.problem.Problem
import trabalhos.trab3.mainLoop.models.specimen.Specimen
import trabalhos.trab3.mainLoop.models.specimen.SpecimenFactory
import trabalhos.trab3.mainLoop.strategies.recombination.RecombinationStrategy
import trabalhos.trab3.mainLoop.models.utils.GenericUtils

class MainLoopImpl(
    initialPop:List<Specimen>,
    specimenFactory: SpecimenFactory,
    recombinationStrategy:RecombinationStrategy) {
    private val model: MainLoopModel = MainLoopModel(
        initialPop,
        specimenFactory,
        recombinationStrategy
    )

    fun execute(numGenerations:Int,problem: Problem):List<Specimen>{
        var r:List<Specimen> = ArrayList();
        var p:List<Specimen> = ListUtils.concatenate(model.initialPop)
        var q:List<Specimen> = ArrayList()
        for(generation in 1..numGenerations){ // executa o main loop pra cada geracao
            r = ListUtils.concatenate(p,q)
            val f = NSGAImpl().execute(
                r.map
                { it.solutions }.toList()
            ).pointsByFrontier// frontiers
            var p2:List<Specimen> = ArrayList();
            var idx = 0
            while (idx < f.size && (p.size+ f[idx].size)<=model.getN()){
                p2 = ListUtils.concatenate(p2,GenericUtils.findSpecimenByItsCandidateSol(r,f[idx]))
                idx++
            }
            if(p2.size < model.getN()){
                var currFrontier:List<CandidateSol> = f[idx]
                currFrontier = CrowdDistImpl().execute(currFrontier).getOrderedList()
                val faltantes =model.getN() - p2.size
                var toAddList:List<CandidateSol> = ArrayList()
                for(el in 0 until faltantes){
                    toAddList = ListUtils.concatenate(toAddList, listOf(currFrontier[el]))
                }
                p2 = ListUtils.concatenate(
                    p2,
                    GenericUtils.findSpecimenByItsCandidateSol(
                        r,
                        toAddList
                    )
                )
                p = p2
                q = model.recombinationStrategy.recombineAll(p,q,problem)
            }
        }
        return r
    }
}