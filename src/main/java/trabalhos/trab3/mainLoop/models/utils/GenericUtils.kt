package trabalhos.trab3.mainLoop.models.utils

import trabalhos.trab1.models.CandidateSol
import trabalhos.trab3.mainLoop.models.specimen.Specimen

class GenericUtils {
    companion object{
        /**
         * Retorna uma lista de especies na ordem das solucoes candidatas
         * @author: mpcsj
         * @param specimens: Especies de origem
         * @param candidateSolList: lista de solucoes candidatas que determina a ordem da
         * lista de especies
         * */
        fun findSpecimenByItsCandidateSol(
            specimens:List<Specimen>,
            candidateSolList: List<CandidateSol>):List<Specimen>{
            val res:List<Specimen> = candidateSolList.map { candidateSol ->
                var curr = specimens[0]
                var achou = false
                for (el in specimens.indices){
                    if(specimens[el].solutions.label === candidateSol.label){
                        curr = specimens[el]
                        achou = true
                    }
                }
                if(!achou){
                    throw RuntimeException("Item não encontrado")
                }
                curr
            }.toList()
            return res
        }
    }
}