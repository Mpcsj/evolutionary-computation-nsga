package trabalhos.trab3.mainLoop

import trabalhos.common.models.Logger
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
    recombinationStrategy:RecombinationStrategy,
    private val logger:Logger = Logger()
) {
    private val model: MainLoopModel = MainLoopModel(
        initialPop,
        specimenFactory,
        recombinationStrategy
    )

    fun execute(numGenerations:Int,problem: Problem,logLevel:Int =0):List<Specimen>{
        var r:List<Specimen> = ArrayList();
        var p:List<Specimen> = ListUtils.concatenate(model.initialPop)
        var q:List<Specimen> = ArrayList()
        for(generation in 1..numGenerations){ // executa o main loop pra cada geracao
            logger.showMessage("Geração $generation",logLevel,3)
            r = ListUtils.concatenate(p,q)
            logger.showMessage("geração atual::size:${r.size}")
            val f = NSGAImpl().execute(
                r.map
                { it.solutions }.toList()
            ).pointsByFrontier// frontiers
            var p2:List<Specimen> = ArrayList();
            var idx = 0
            logger.showMessage("lista de fronteiras::size:${f.size}",logLevel,2)
            logger.showMessage("qtd itens na fronteira 1::${f[0].size}",logLevel,2)
            logger.showMessage("lista de fronteiras:${f}",logLevel,3)
            while (idx < f.size && (p2.size+ f[idx].size)<=model.getN()){
                logger.showMessage("Entrou no while",logLevel,2)
                p2 = ListUtils.concatenate(p2,GenericUtils.findSpecimenByItsCandidateSol(r,f[idx]))
                idx++
            }
            logger.showMessage("Tamanho da lista p2 ${p2.size}",logLevel,1)
            if(p2.size < model.getN()){
                logger.showMessage("Tam de p2 menor do que n:${model.getN()}")
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