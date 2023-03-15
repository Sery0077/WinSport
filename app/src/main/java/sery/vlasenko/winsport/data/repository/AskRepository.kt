package sery.vlasenko.winsport.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sery.vlasenko.winsport.data.NetworkModule
import sery.vlasenko.winsport.model.DataResult
import sery.vlasenko.winsport.model.GetAnswerBody
import sery.vlasenko.winsport.model.PostQuestionBody
import sery.vlasenko.winsport.model.ResponseItem

class AskRepository {
    fun postQuestion(body: PostQuestionBody): Flow<DataResult<Boolean>> {
        return flow {
            emit(DataResult.loading())

            val result = try {
                val data = NetworkModule.apiService.postQuestion(body)
                DataResult.success(data.isSuccessful)
            } catch (e: Exception) {
                DataResult.error("Error: ${e.message}", null)
            }

            emit(result)
        }
    }

    fun getAnswer(body: GetAnswerBody): Flow<DataResult<ResponseItem>> {
        return flow {
            emit(DataResult.loading())

            val result = try {
                val data = NetworkModule.apiService.getAnswer(body)
                DataResult.success(data)
            } catch (e: Exception) {
                DataResult.error("Error: ${e.message}", null)
            }

            emit(result)
        }
    }
}