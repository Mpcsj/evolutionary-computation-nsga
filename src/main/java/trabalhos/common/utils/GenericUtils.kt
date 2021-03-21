package trabalhos.common.utils

class GenericUtils {
    companion object{
        fun getRandomBoolean():Boolean{
            return MathUtils.getNextDouble()>=.5
        }
    }
}