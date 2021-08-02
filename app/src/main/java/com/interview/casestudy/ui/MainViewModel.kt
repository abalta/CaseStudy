package com.interview.casestudy.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.casestudy.model.BlobResponse
import com.interview.casestudy.model.DetailItem
import com.interview.casestudy.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel  : ViewModel() {

    private val _detailItemLiveData: MutableLiveData<DetailItem> = MutableLiveData()
    val detailItemLiveData: LiveData<DetailItem> = _detailItemLiveData


    fun getDetail(item: DetailItem) {
        _detailItemLiveData.value = item
    }

}