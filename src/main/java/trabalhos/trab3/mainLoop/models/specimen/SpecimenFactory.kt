package trabalhos.trab3.mainLoop.models.specimen

import trabalhos.trab3.mainLoop.models.problem.Problem

class SpecimenFactory {

    fun newSpecimen(genesQuantity:Int,problem:Problem,label:String="Problem-"+Math.random()): Specimen {
        val genes = ArrayList<Double>(genesQuantity)
        for(el in genes.indices){
            genes[el] = Math.random()
        }
        return Specimen(genes,problem.calculate(genes,label))
    }
}