package com.interview.casestudy.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.casestudy.model.BlobResponse
import com.interview.casestudy.repository.MainRepository
import com.interview.casestudy.util.AppConstant.GENERAL_ERROR
import com.interview.casestudy.util.AppConstant.UNKNOWNHOSTEXCEPTION_MESSAGE
import com.interview.casestudy.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _blobLiveData: MutableLiveData<Event<BlobResponse>> = MutableLiveData()
    val blobLiveData: LiveData<Event<BlobResponse>> = _blobLiveData

    private val _progressLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val progressLiveData: LiveData<Boolean> = _progressLiveData

    private val _showErrorLiveData: MutableLiveData<String> = MutableLiveData()
    val showErrorLiveData: LiveData<String> = _showErrorLiveData

    init {
        viewModelScope.launch {
            try {
                loading(true)
                _blobLiveData.value = Event(mainRepository.getBlobResponse())
            } catch (error: Throwable) {
                parseErrorMessage(error)
            } finally {
                loading(false)
            }
        }
    }

    private fun parseErrorMessage(ex: Throwable) {
        loading(false)
        if (ex is UnknownHostException) {
            _showErrorLiveData.value = UNKNOWNHOSTEXCEPTION_MESSAGE
        } else {
            _showErrorLiveData.value = GENERAL_ERROR
        }
    }

    private fun loading(isShow: Boolean) {
        _progressLiveData.value = isShow
    }

    fun getUsername() = mainRepository.username

}