package com.example.mycalck.data

import com.example.mycalck.character.CharacterZ
import com.example.mycalck.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val retrofit: Retrofit
) {
    suspend fun getCharacters(): Resource<List<CharacterZ>> {
        val api = retrofit.create(RickAndMortyApiService::class.java)
        return try {
            val response = withContext(Dispatchers.IO) { api.getCharacters() }
            Resource.Success(response.results)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

    suspend fun getCharacter(id: Int): Resource<CharacterZ> {
        val api = retrofit.create(RickAndMortyApiService::class.java)
        return try {
            val response = withContext(Dispatchers.IO) { api.getCharacter(id) }
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }
}
