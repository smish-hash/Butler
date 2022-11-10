package com.smish.butler.news.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.smish.butler.base.BaseFragment
import com.smish.butler.databinding.FragmentNewsBinding
import com.smish.butler.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class NewsFragment : BaseFragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels()

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        viewModel.newsHeadlines.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showProgressDialog("Getting top headlines")
                }

                is Resource.Success -> {
                    closeProgressDialog()
                    debugLog("res", "${it.data}")
                    it.data?.let { news ->
                        newsAdapter.differ.submitList(news.articles?.toList())
                    }
                }

                is Resource.Error -> {
                    closeProgressDialog()
                    showSmallToast("${it.message}")
                }
            }
        }


//        binding.btnCurrency.setOnClickListener {
//            findNavController().navigate(R.id.nav_currency)
//            val navView = activity?.findViewById<NavigationView>(R.id.nav_view)
            /*val navHostFragment =
                parentFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NewsFragment
            val navController =
                navHostFragment.findNavController()*/

//            navController.navigate(R.id.nav_currency)
//            navView?.con()?.navigate(R.id.nav_currency)
//            navView?.menu?.getItem(0)?.isChecked = true
//            navView?.setCheckedItem(R.id.nav_food)
//            viewModel.getNewsHeadlines("in", 1)

//        }

    }

    private fun initRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvNews.adapter = newsAdapter
        fetchNews()
    }

    private fun fetchNews() {
        viewModel.getNewsHeadlines("in", 1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}