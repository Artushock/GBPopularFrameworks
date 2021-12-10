package com.artushock.home_work_2_2.authorization

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.artushock.home_work_2_2.databinding.FragmentAuthorizationBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class AuthorizationFragment : MvpAppCompatFragment(), AuthorizationView{

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
        savedInstanceState: Bundle?
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

        val submitButton = binding.authFragSubmitBtn
        val loginEditText = binding.authFragLoginEt
        val passwordEditText = binding.authFragPasswordEt

        submitButton.setOnClickListener {
            presenter.setUserData(
                loginEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }
    }

    override fun setLoginText(login: String) {
        binding.authFragLoginEt.setText(login)
    }

    override fun setPasswordText(password: String) {
        binding.authFragPasswordEt.setText(password)
    }

    override fun showLoginError(message: String) {
        Toast.makeText(context, "Error: $message", Toast.LENGTH_SHORT).show()
    }

    override fun showPasswordError(message: String) {
        Toast.makeText(context, "Error: $message", Toast.LENGTH_SHORT).show()
    }
}