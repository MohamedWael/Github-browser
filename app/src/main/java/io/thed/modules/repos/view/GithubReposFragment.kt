package io.thed.modules.repos.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.thed.databinding.GithubReposFragmentBinding
import io.thed.modules.repos.adapter.GithubReposAdapter
import io.thed.modules.repos.viewmodel.GithubReposViewModel
import io.thed.modules.repos.viewmodel.GithubReposViewModelFactory

class GithubReposFragment : Fragment() {

    companion object {
        fun newInstance() = GithubReposFragment()
    }

    private lateinit var viewModel: GithubReposViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this, GithubReposViewModelFactory())
            .get(GithubReposViewModel::class.java)

        val adapter = GithubReposAdapter()
        val binding = GithubReposFragmentBinding.inflate(layoutInflater, container, false)
        binding.viewModel = viewModel
        binding.rvRepos.layoutManager = LinearLayoutManager(context)
        binding.rvRepos.adapter = adapter
        observeOnReposList(adapter)
        observeOnLiveError()
        viewModel.getUserData()

        return binding.root
    }

    private fun observeOnLiveError() {
        viewModel.errorHandler.observe(viewLifecycleOwner, Observer {
            viewModel.hideProgress()
            showMsg(if (it.errorMsgString.isNullOrEmpty()) getString(it.errorMsgStringRes) else it.errorMsgString)
        })
    }

    private fun observeOnReposList(adapter: GithubReposAdapter) {
        viewModel.liveRepoList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                viewModel.hideProgress()
            }
        })
    }

    private fun showMsg(msgString: String) {
        Toast.makeText(context, msgString, Toast.LENGTH_LONG).show()
    }

}
