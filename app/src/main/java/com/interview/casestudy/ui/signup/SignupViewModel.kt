package com.interview.casestudy.ui.signup

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.interview.casestudy.repository.MainRepository
import com.interview.casestudy.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _validateEmailLiveData: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val validateEmailLiveData: LiveData<Event<Boolean>> = _validateEmailLiveData

    private val _validatePasswordLiveData: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val validatePasswordLiveData: LiveData<Event<Boolean>> = _validatePasswordLiveData

    private val _validateSuccessLiveData: MutableLiveData<Event<Unit>> = MutableLiveData()
    val validateSuccessLiveData: LiveData<Event<Unit>> = _validateSuccessLiveData

    fun validateSignup(username: String, password: String) {
        if (username.length <= 2) {
            _validateEmailLiveData.value = Event(true)
            _validatePasswordLiveData.value = Event(false)
        } else if (password.length < 6 || password.lowercase() == password || password.isDigitsOnly() || password.any { it.isDigit() }.not()) {
            _validateEmailLiveData.value = Event(false)
            _validatePasswordLiveData.value = Event(true)
        } else {
            _validateEmailLiveData.value = Event(false)
            _validatePasswordLiveData.value = Event(false)
            mainRepository.username = username
            _validateSuccessLiveData.value = Event(Unit)
        }
    }

}