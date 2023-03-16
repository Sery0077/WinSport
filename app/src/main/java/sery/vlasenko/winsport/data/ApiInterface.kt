package sery.vlasenko.winsport.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import sery.vlasenko.winsport.model.GetAnswerBody
import sery.vlasenko.winsport.model.PostQuestionBody
import sery.vlasenko.winsport.model.ResponseItem
import sery.vlasenko.winsport.model.TrainingItem

interface ApiInterface {
    @GET("{weekday}.json")
    suspend fun getTraining(@Path("weekday") weekday: String): List<TrainingItem>

    @POST("ask.php")
    suspend fun postQuestion(@Body postQuestionBody: PostQuestionBody): Response<Unit>

    @POST("response.php")
    suspend fun getAnswer(@Body getAnswer: GetAnswerBody): ResponseItem
}