package com.example.mvvmpr.hilt.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
@HiltViewModel : Hilt에게 해당 컴포넌트가 ViewModel임을 알려줍니다
@Inject : 해당 컴포넌트를 생성하는데 어떤 다른 종속성이 필요한지 알려줍니다.
위 예시에서는 Hilt가 NoteRepository를 제공하는 법을 알기 때문에 사용가능합니다
 * **/

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {
    private val _note = MutableLiveData<Note>()
    val note : LiveData<Note>
        get() = _note


    init {
        viewModelScope.launch {
            _note.value = repository.getNote()
        }
    }

    fun saveNote(title : String, description : String){
        _note.value?.title = title
        _note.value?.description = description
        viewModelScope.launch {
            _note.value?.let { repository.saveNote(it) }
        }
    }
}