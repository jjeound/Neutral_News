package com.example.news.data.repository

import androidx.annotation.WorkerThread
import com.example.news.core.Resource
import com.example.news.data.Dispatcher
import com.example.news.data.NewsAppDispatchers
import com.example.news.data.dto.NewsInfo
import com.example.news.data.service.NewsClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val newsClient: NewsClient,
    @Dispatcher(NewsAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
): DetailRepository {

    @WorkerThread
    override suspend fun fetchNewsDetail(
        id: String,
    ): Flow<Resource<NewsInfo>> = flow {
        emit(Resource.Loading())
        try {
            val response = newsClient.getNewsDetail(id = id)
            if (response.isSuccess){
                emit(Resource.Success(response.result))
            }else{
                emit(Resource.Error(response.message))
            }
        }catch (e: HttpException){
            emit(Resource.Error(e.toString()))
        }catch (e: IOException){
            emit(Resource.Error(e.toString()))
        }
    }.flowOn(ioDispatcher)

}