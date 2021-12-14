package com.artushock.home_work_2_2.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artushock.home_work_2_2.databinding.FragmentAuthorizationBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class AuthorizationFragment : MvpAppCompatFragment(), AuthorizationView {

    companion object {
        fun newInstance() = AuthorizationFragment()
    }

    private var _binding: FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!

    private val presenter by moxyPresenter {
        AuthorizationPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with (binding){
            authFragSignInBtn.setOnClickListener {
                presenter.signIn(
                    authFragLoginEt.text.toString(),
                    authFragPasswordEt.text.toString()
                )
            }

            authFragRegisterBtn.setOnClickListener {
                presenter.register(
                    authFragLoginEt.text.toString(),
                    authFragPasswordEt.text.toString()
                )
            }
        }
    }

    override fun showLoginError(message: String) {
        binding.authFragLoginEt.error = message
    }

    override fun showPasswordError(message: String) {
        binding.authFragPasswordEt.error = message
    }
}