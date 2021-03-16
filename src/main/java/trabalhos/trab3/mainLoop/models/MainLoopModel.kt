package trabalhos.trab3.mainLoop.models

import trabalhos.trab3.mainLoop.models.specimen.Specimen
import trabalhos.trab3.mainLoop.models.specimen.SpecimenFactory
import trabalhos.trab3.mainLoop.models.specimen.SpecimenFactoryInterface
import trabalhos.trab3.mainLoop.strategies.recombination.RecombinationStrategy

class MainLoopModel(
    val initialPop:List<Specimen>,
    val specimenFactory: SpecimenFactoryInterface,
    val recombinationStrategy: RecombinationStrategy
) {
    private val TAG = MainLoopModel::class.java.canonicalName
    init {
        print("$TAG::Initial pop:${initialPop}")
    }
    fun getN():Int{
        return initialPop.size
    }
}