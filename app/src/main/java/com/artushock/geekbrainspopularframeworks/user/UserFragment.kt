package com.artushock.geekbrainspopularframeworks.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artushock.geekbrainspopularframeworks.application.App
import com.artushock.geekbrainspopularframeworks.databinding.FragmentUserBinding
import com.artushock.geekbrainspopularframeworks.model.GithubUser
import com.artushock.geekbrainspopularframeworks.view.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment(private val user: GithubUser) : MvpAppCompatFragment(), UserView,
    BackButtonListener {

    companion object {
        fun newInstance(user: GithubUser) = UserFragment(user)
    }

    private val presenter by moxyPresenter {
        UserPresenter(user, App.instance.router)
    }

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun setLoginName(name: String) {
        binding.fragmentUserLoginTextView.text = name
    }

    override fun backPressed() = presenter.backPressed()
}