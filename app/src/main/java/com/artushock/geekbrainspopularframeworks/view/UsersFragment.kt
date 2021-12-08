package com.artushock.geekbrainspopularframeworks.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.artushock.geekbrainspopularframeworks.application.App
import com.artushock.geekbrainspopularframeworks.databinding.FragmentUsersBinding
import com.artushock.geekbrainspopularframeworks.model.GithubUserRepo
import com.artushock.geekbrainspopularframeworks.presenter.UsersPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUserRepo(), App.instance.router)
    }
    private val adapter: UserRVAdapter? = null

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun init() {
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = UserRVAdapter(presenter.usersListPresenter)
        }
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}