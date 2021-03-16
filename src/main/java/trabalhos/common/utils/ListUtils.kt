package trabalhos.common.utils

class ListUtils {
    companion object{
        fun <T> concatenate(vararg lists: List<T>): List<T> {
            return listOf(*lists).flatten()
        }

        fun <T> splitListInTwo(list:List<T>):Array<List<T>>{
            assert(list.size%2 == 0)
            val listA = list.subList(0,list.size/2)
            val listB = list.subList(list.size/2,list.size)
            return arrayOf(listA,listB)
        }
    }
}