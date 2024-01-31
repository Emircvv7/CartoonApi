package com.example.mycalck.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycalck.character.CharacterZ
import com.example.mycalck.data.CharacterRepository
import com.example.mycalck.data.RickAndMortyApiService
import com.example.mycalck.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {
    private val _character = MutableLiveData<Resource<CharacterZ>>()
    val character: LiveData<Resource<CharacterZ>> get() = _character

    fun loadCharacter(id: Int) {
        _character.value = Resource.Loading()
        viewModelScope.launch {
            _character.value = repository.getCharacter(id)
        }
    }
}