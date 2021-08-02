package com.interview.casestudy.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.interview.casestudy.R
import com.interview.casestudy.databinding.FragmentSignupBinding
import com.interview.casestudy.ui.MainViewModel
import com.interview.casestudy.util.eventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private val signupViewModel: SignupViewModel by viewModels()

    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(signupViewModel) {
            binding.apply {
                btnContinue.setOnClickListener {
                    validateSignup(edtUsername.text.toString(), edtPassword.text.toString())
                }

                eventObserve(validateEmailLiveData) {
                    if (it) {
                        inputUsername.error = getString(R.string.error_username)
                    } else {
                        inputUsername.error = ""
                    }
                }

                eventObserve(validatePasswordLiveData) {
                    if (it) {
                        inputPassword.error = getString(R.string.error_password)
                    } else {
                        inputPassword.error = ""
                    }
                }

                eventObserve(validateSuccessLiveData) {
                    findNavController().navigate(R.id.action_signupFragment_to_homeFragment)
                }
            }

        }
    }

}