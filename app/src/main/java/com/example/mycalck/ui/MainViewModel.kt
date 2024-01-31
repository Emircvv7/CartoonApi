package com.example.mycalck.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycalck.character.CharacterZ
import com.example.mycalck.data.CharacterRepository
import com.example.mycalck.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {
    private val _characters = MutableLiveData<Resource<List<CharacterZ>>>()
    val characters: LiveData<Resource<List<CharacterZ>>> get() = _characters

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        _characters.value = Resource.Loading()
        viewModelScope.launch {
            _characters.value = repository.getCharacters()
        }
    }
}
