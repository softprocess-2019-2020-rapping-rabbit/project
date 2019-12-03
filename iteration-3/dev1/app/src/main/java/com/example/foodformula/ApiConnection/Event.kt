package com.example.foodformula.ApiConnection

data class Event<out T>(val status: Status, val data: T?, val error: Error?) {

    companion object {
        fun <T> loading(): Event<T> = Event(Status.LOADING, null, null)

        fun <T> success(data: T?): Event<T> = Event(Status.SUCCESS, data, null)

        fun <T> error(error: Error?): Event<T> = Event(Status.ERROR, null, error)

        fun <T> successArray(data: List<T>?): Event<List<T>>? = Event(Status.SUCCESS, data, null)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}