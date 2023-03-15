package sery.vlasenko.winsport.ui.training

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sery.vlasenko.winsport.data.repository.TrainingRepository
import sery.vlasenko.winsport.model.DataResult
import sery.vlasenko.winsport.model.TrainingItem
import java.text.SimpleDateFormat
import java.util.*

class ViewModelTraining : ViewModel() {

    private val _trainingResult = MutableLiveData<DataResult<TrainingItem>>()
    val trainingResult: LiveData<DataResult<TrainingItem>>
        get() = _trainingResult

    private val repository = TrainingRepository()

    init {
        getTraining()
    }

    fun getTraining() {
        val date = Calendar.getInstance().time
        val day = SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.time).lowercase()

        viewModelScope.launch {
            repository.getTraining(day).collect() {
                _trainingResult.value = it
            }
        }
    }
}