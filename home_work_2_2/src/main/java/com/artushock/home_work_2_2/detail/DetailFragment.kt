package com.artushock.home_work_2_2.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artushock.home_work_2_2.data.GithubUser
import com.artushock.home_work_2_2.databinding.FragmentDetailBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class DetailFragment(
    private val user: GithubUser,
    private val message: String
) : MvpAppCompatFragment(), DetailView {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val presenter by moxyPresenter {
        DetailPresenter(user)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.init()
        binding.detailFragMessageTv.text = message
    }

    companion object {
        fun newInstance(user: GithubUser, message: String) = DetailFragment(user, message)
    }

    override fun setLogin(login: String) {
        binding.detailFragLoginTv.text = login
    }

    override fun setPassword(password: String) {
        binding.detailFragPasswordTv.text = password
    }
}