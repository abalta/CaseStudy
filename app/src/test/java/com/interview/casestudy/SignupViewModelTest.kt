package com.interview.casestudy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.interview.casestudy.network.NetworkApi
import com.interview.casestudy.repository.MainRepository
import com.interview.casestudy.ui.signup.SignupViewModel
import com.nhaarman.mockitokotlin2.mock
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SignupViewModelTest {

    private lateinit var viewModel: SignupViewModel
    private lateinit var repository: MainRepository
    private val service: NetworkApi = mock()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutinesRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        repository = MainRepository(service)
        viewModel = SignupViewModel(repository)
    }

    @Test
    fun validateSignupCase1() {

        val emailValidationLiveData = viewModel.validateEmailLiveData
        val passwordValidationLiveData = viewModel.validatePasswordLiveData

        val username = "ads"
        val password = "Asdqewq1"

        viewModel.validateSignup(username, password)

        assertFalse(emailValidationLiveData.value!!.peekContent())
        assertFalse(passwordValidationLiveData.value!!.peekContent())

    }

    @Test
    fun validateSignupCase2() {

        val emailValidationLiveData = viewModel.validateEmailLiveData
        val passwordValidationLiveData = viewModel.validatePasswordLiveData

        val username = "ad"
        val password = "Asdqewq1"

        viewModel.validateSignup(username, password)

        assertTrue(emailValidationLiveData.value!!.peekContent())
        assertFalse(passwordValidationLiveData.value!!.peekContent())

    }

    @Test
    fun validateSignupCase3() {

        val emailValidationLiveData = viewModel.validateEmailLiveData
        val passwordValidationLiveData = viewModel.validatePasswordLiveData

        val username = "ads"
        val password = "Asdqewq"

        viewModel.validateSignup(username, password)

        assertFalse(emailValidationLiveData.value!!.peekContent())
        assertTrue(passwordValidationLiveData.value!!.peekContent())

    }
}