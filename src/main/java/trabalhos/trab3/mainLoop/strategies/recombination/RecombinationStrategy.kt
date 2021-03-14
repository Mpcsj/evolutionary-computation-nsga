package trabalhos.trab3.mainLoop.strategies.recombination

import trabalhos.trab3.mainLoop.models.problem.Problem
import trabalhos.trab3.mainLoop.models.specimen.Specimen

interface RecombinationStrategy {
    fun recombine(parentA:Specimen,parentB:Specimen,problem:Problem):List<Specimen>
    fun recombineAll(parentAList:List<Specimen>,parentBList:List<Specimen>,problem:Problem):List<Specimen>
}