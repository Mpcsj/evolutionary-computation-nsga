package trabalhos.trab3

import trabalhos.CefetJob
import trabalhos.trab3.mainLoop.MainLoopImpl
import trabalhos.trab3.mainLoop.models.problem.SchafferProblem
import trabalhos.trab3.mainLoop.models.problem.Problem
import trabalhos.trab3.mainLoop.models.specimen.Specimen
import trabalhos.trab3.mainLoop.models.specimen.SpecimenFactory
import trabalhos.trab3.mainLoop.strategies.recombination.BlxAlphaStrategy

class Trab3:CefetJob {
    private val TAG = Trab3::class.java.canonicalName
    override fun run() {
        // declarando constantes do projeto
        val numGenes=1;val numOutputs = 3;
        val initialPopSize = 20;val numGenerations = 45000
        // instanciando objetos necessarios
        val specimenFactory = SpecimenFactory(
            -30.0,
            30.0,
            logLevel = 0
        )
//        val problem:Problem  = BasicProblem(numOutputs)
        val problem:Problem  = SchafferProblem()
        val initialPopulation:List<Specimen> = MutableList(initialPopSize) {
            specimenFactory.newSpecimen(numGenes,problem)
        }
        println("Populacao inicial:$initialPopulation")
        // executando main loop propriamente dito
        val result = MainLoopImpl(
            initialPopulation,
            specimenFactory,
            BlxAlphaStrategy(specimenFactory,currLogLevel = 0)
        ).execute(numGenerations,problem,1)
        println("$TAG::final generation:$result")
    }

    override fun getTAG(): String {
        return TAG
    }
}