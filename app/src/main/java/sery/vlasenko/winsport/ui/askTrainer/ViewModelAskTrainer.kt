package sery.vlasenko.winsport.ui.askTrainer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sery.vlasenko.winsport.data.repository.AskRepository
import sery.vlasenko.winsport.model.DataResult
import sery.vlasenko.winsport.model.GetAnswerBody
import sery.vlasenko.winsport.model.PostQuestionBody
import sery.vlasenko.winsport.model.ResponseItem

class ViewModelAskTrainer : ViewModel() {

    private val _askResult: MutableLiveData<DataResult<Boolean>> = MutableLiveData()
    val askResult: LiveData<DataResult<Boolean>>
        get() = _askResult

    private val _responseResult: MutableLiveData<DataResult<ResponseItem>> = MutableLiveData()
    val responseResult: LiveData<DataResult<ResponseItem>>
        get() = _responseResult

    private val repository = AskRepository()

    private var mQuestionId: String? = null

    fun getAnswer(questionId: String? = null) {
        if (questionId != null) {
            mQuestionId = questionId
        }

        viewModelScope.launch {
            mQuestionId?.let { question ->
                repository.getAnswer(GetAnswerBody(question)).collect() {
                    _responseResult.value = it
                }
            }
        }
    }

    fun ask(question: String, questionId: String) {
        viewModelScope.launch {
            val body = PostQuestionBody(ask = question, id = questionId)

            repository.postQuestion(body).collect() {
                _askResult.value = it
            }
        }
    }
}
