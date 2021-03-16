package trabalhos.trab3.mainLoop.models.specimen

import trabalhos.trab1.models.CandidateSol

class Specimen(val genes:List<Double>,val solutions:CandidateSol) {

    override fun toString(): String {
        return "Specimen{" +
                "genes:$genes;" +
                "\nsolutions:$solutions" +
                "}"
    }
}