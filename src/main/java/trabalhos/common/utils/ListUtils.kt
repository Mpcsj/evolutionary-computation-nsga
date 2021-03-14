package trabalhos.common.utils

class ListUtils {
    companion object{
        fun <T> concatenate(vararg lists: List<T>): List<T> {
            return listOf(*lists).flatten()
        }
    }
}