package trabalhos.trab2

import trabalhos.CefetJob
import trabalhos.trab1.models.CandidateSol
import trabalhos.trab2.crowdDist.CrowdDistImpl
import trabalhos.trab2.crowdDist.models.CrowdDistModel

class Trab2 :CefetJob{
    private val TAG =Trab2::class.java.canonicalName
    override fun run() {
        println("Executando o trabalho 2")
        val solutions:List<CandidateSol> = listOf(
            CandidateSol("A", 1.0,5.0),
            CandidateSol("B", 2.0,3.0),
            CandidateSol("C", 4.0,1.0),
            CandidateSol("D", 1.5,4.0),
        );
        val res:CrowdDistModel = CrowdDistImpl().execute(solutions)
        println("$tag::obtido::res:$res")
    }

    override fun getTAG(): String {
        return TAG
    }
}