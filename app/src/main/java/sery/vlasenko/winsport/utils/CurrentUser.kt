package sery.vlasenko.winsport.utils

import androidx.lifecycle.MutableLiveData

object CurrentUser {
    var name: String = ""
    var age: Int = 0
    var weight: Int = 0

    val progress = MutableLiveData(0)
}