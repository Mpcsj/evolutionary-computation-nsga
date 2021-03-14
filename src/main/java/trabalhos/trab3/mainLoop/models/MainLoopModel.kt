package trabalhos.trab3.mainLoop.models

import trabalhos.trab3.mainLoop.models.specimen.Specimen
import trabalhos.trab3.mainLoop.models.specimen.SpecimenFactory
import trabalhos.trab3.mainLoop.strategies.recombination.RecombinationStrategy

class MainLoopModel(
    val initialPop:List<Specimen>,
    val specimenFactory: SpecimenFactory,
    val recombinationStrategy: RecombinationStrategy
) {

    fun getN():Int{
        return initialPop.size
    }
}