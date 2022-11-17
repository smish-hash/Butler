package com.smish.butler.news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smish.butler.R
import com.smish.butler.base.BaseFragment
import com.smish.butler.databinding.FragmentNewsBinding
import com.smish.butler.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels()

    private var country = "in"
    private var page = 1
    private var isScrolling = false
    private var isLastPage = false
    private var pages = 0

    private lateinit var newsAdapter: NewsAdapter

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.rvNews.layoutManager as LinearLayoutManager
            val sizeOfList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()
            val hasReachedToEnd = topPosition+visibleItems >= sizeOfList
            val shouldPaginate = !isLastPage && hasReachedToEnd && isScrolling
            if (shouldPaginate) {
                showSmallToast("Getting more news")
                page++
                fetchNews()
                isScrolling = false
            }
        }
    }

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
        setListeners()

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

                        // for pagination
                        pages = if (news.totalResults?.rem(20) == 0) {
                            news.totalResults/20
                        } else {
                            news.totalResults?.div(20)?.plus(1) ?: 1
                        }
                        isLastPage = page == pages
                    }
                }

                is Resource.Error -> {
                    closeProgressDialog()
                    showSmallToast("${it.message}")
                }
            }
        }

    }

    private fun setListeners() {
        binding.newsSwipeRefresh.setOnRefreshListener {
            binding.newsSwipeRefresh.isRefreshing = true
            fetchNews()
            binding.newsSwipeRefresh.isRefreshing = false
        }

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }
            findNavController().navigate(R.id.action_nav_news_to_newsDetailFragment, bundle)
        }
    }

    private fun initRecyclerView() {
        newsAdapter = NewsAdapter(requireContext())
        binding.rvNews.adapter = newsAdapter
        binding.rvNews.addOnScrollListener(this@NewsFragment.onScrollListener)
        fetchNews()
    }

    private fun fetchNews() {
        viewModel.getNewsHeadlines(country, page)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}