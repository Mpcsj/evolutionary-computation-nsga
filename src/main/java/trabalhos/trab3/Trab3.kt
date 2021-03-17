package trabalhos.trab3

import trabalhos.CefetJob
import trabalhos.trab3.mainLoop.MainLoopImpl
import trabalhos.trab3.mainLoop.models.problem.BasicProblem
import trabalhos.trab3.mainLoop.models.problem.Problem
import trabalhos.trab3.mainLoop.models.specimen.Specimen
import trabalhos.trab3.mainLoop.models.specimen.SpecimenFactory
import trabalhos.trab3.mainLoop.strategies.recombination.BlxAlphaStrategy

class Trab3:CefetJob {
    private val TAG = Trab3::class.java.canonicalName
    override fun run() {
        // declarando constantes do projeto
        val numGenes=7;val numOutputs = 3;
        val initialPopSize = 20;val numGenerations = 45000
        // instanciando objetos necessarios
        val specimenFactory = SpecimenFactory(logLevel = 1)
        val problem:Problem  = BasicProblem(numOutputs)
        val initialPopulation:List<Specimen> = MutableList(initialPopSize) {
            specimenFactory.newSpecimen(numGenes,problem)
        }
        println("Populacao inicial:$initialPopulation")
        // executando main loop propriamente dito
        val result = MainLoopImpl(
            initialPopulation,
            specimenFactory,
            BlxAlphaStrategy(specimenFactory,0)
        ).execute(numGenerations,problem,1)
        println("$TAG::final generation:$result")
    }

    override fun getTAG(): String {
        return TAG
    }
}