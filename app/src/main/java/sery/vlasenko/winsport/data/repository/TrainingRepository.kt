package sery.vlasenko.winsport.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import sery.vlasenko.winsport.data.NetworkModule
import sery.vlasenko.winsport.model.DataResult
import sery.vlasenko.winsport.model.TrainingItem

class TrainingRepository {
    fun getTraining(weekday: String): Flow<DataResult<TrainingItem>> {
        return flow {
            emit(DataResult.loading())

            val result = try {
                val training = NetworkModule.apiService.getTraining(weekday)
                DataResult.success(training.first())
            } catch (e: Exception) {
                DataResult.error("Error: ${e.message}", null)
            }

            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}