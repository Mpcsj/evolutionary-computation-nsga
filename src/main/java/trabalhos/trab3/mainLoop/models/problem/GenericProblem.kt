package trabalhos.trab3.mainLoop.models.problem

abstract class GenericProblem:Problem {
    protected fun getBasicLabel(label:String?):String{
        return label ?: "Problem-"+Math.random()
    }
}