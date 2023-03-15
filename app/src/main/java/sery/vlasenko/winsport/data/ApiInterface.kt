package sery.vlasenko.winsport.data

import retrofit2.http.GET
import retrofit2.http.Path
import sery.vlasenko.winsport.model.TrainingItem

interface ApiInterface {
    @GET("{weekday}.json")
    suspend fun getTraining(@Path("weekday") weekday: String): List<TrainingItem>
}