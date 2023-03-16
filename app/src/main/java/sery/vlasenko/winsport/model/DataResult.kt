package sery.vlasenko.winsport.model

class DataResult<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): DataResult<T> {
            return DataResult(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, error: Error?): DataResult<T> {
            return DataResult(Status.ERROR, null, error?.message)
        }

        fun <T> loading(data: T? = null): DataResult<T> {
            return DataResult(Status.LOADING, data, null)
        }
    }

    override fun toString(): String {
        return "Result(status=$status, data=$data, error=$message)"
    }
}