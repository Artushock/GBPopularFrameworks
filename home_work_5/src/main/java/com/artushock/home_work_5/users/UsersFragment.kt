package com.artushock.home_work_5.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.artushock.home_work_5.application.App
import com.artushock.home_work_5.data.models.UserListItem
import com.artushock.home_work_5.databinding.FragmentUsersBinding
import com.artushock.home_work_5.recycler.UsersAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, UsersAdapter.OnItemClickListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val adapter = UsersAdapter(this)

    private val presenter by moxyPresenter {
        UsersPresenter().apply {
            App.instance.component.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.fragmentUsersRecyclerView

        recyclerView.let { rv ->
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun showUsers(users: List<UserListItem>) {
        adapter.setUsers(users)
    }

    override fun showError(message: String) {
        Toast.makeText(context, "Error: $message", Toast.LENGTH_SHORT).show()
    }

    override fun onUserClick(user: UserListItem) {
        presenter.showUserFragment(user.login)
    }
}