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
        val initialPopSize = 20;val numGenerations = 40500
        // instanciando objetos necessarios
        val specimenFactory = SpecimenFactory()
        val problem:Problem  = BasicProblem(numOutputs)
        val initialList:List<Specimen> = MutableList(initialPopSize) {
            specimenFactory.newSpecimen(numGenes,problem)
        }
        // executando main loop propriamente dito
        val result = MainLoopImpl(
            initialList,
            specimenFactory,
            BlxAlphaStrategy()
        ).execute(numGenerations,problem)
        println("$TAG::result:$result")
    }

    override fun getTAG(): String {
        return TAG
    }
}