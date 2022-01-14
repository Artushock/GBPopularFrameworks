package com.artushock.home_work_5.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.artushock.home_work_5.application.App
import com.artushock.home_work_5.data.models.UserListItem
import com.artushock.home_work_5.databinding.FragmentUsersBinding
import com.artushock.home_work_5.recycler.UsersAdapter
import com.artushock.home_work_5.ui.BaseFragment
import moxy.ktx.moxyPresenter

class UsersFragment : BaseFragment(), UsersView, UsersAdapter.OnItemClickListener {

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
        hideAll()
        adapter.setUsers(users)
    }

    override fun showError(message: String, tumbler: Boolean) {
        changeViewVisibility(binding.usersFragmentErrorContainer, tumbler)
        if (tumbler) {
            binding.usersFragmentErrorMessage.text = message
            binding.usersFragmentTryAgainButton.setOnClickListener {
                hideAll()
                displayProgress(true)
                presenter.updateUsersList()
            }
        }
    }

    override fun displayProgress(tumbler: Boolean) {
        changeViewVisibility(binding.usersFragmentProgressBar, tumbler)
    }

    override fun onUserClick(user: UserListItem) {
        presenter.showUserFragment(user.login)
    }

    private fun hideAll(){
        changeViewVisibility(binding.usersFragmentErrorContainer, false)
        changeViewVisibility(binding.usersFragmentProgressBar, false)
    }
}