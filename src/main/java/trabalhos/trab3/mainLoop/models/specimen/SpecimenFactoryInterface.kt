package trabalhos.trab3.mainLoop.models.specimen

import trabalhos.trab3.mainLoop.models.problem.Problem

interface SpecimenFactoryInterface {
    fun newSpecimen(
        genesQuantity:Int,
        problem: Problem,
        label:String="Problem-"+Math.random()): Specimen

    fun newSpecimen(
        genes:List<Double>,
        problem: Problem,
        label:String="Problem-"+Math.random()): Specimen
}