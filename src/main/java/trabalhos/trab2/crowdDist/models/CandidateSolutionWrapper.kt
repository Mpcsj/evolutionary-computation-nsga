package trabalhos.trab2.crowdDist.models

import trabalhos.trab1.models.CandidateSol

class CandidateSolWrapper constructor(//    private var d:Int = Int.MAX_VALUE;
    val candidateSol: CandidateSol
) {
    var d:Double =0.0;

    override fun toString(): String {
        return "CandidateSolWrapper:{d:$d,candidateSol:$candidateSol}"
    }
}