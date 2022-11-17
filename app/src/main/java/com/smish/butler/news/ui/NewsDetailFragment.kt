package com.smish.butler.news.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.smish.butler.base.BaseFragment
import com.smish.butler.databinding.FragmentNewsDetailBinding
import com.smish.butler.news.data.model.topHeadlines.Article


class NewsDetailFragment : BaseFragment() {
    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var article: Article


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: NewsDetailFragmentArgs by navArgs()
        article = args.selectedArticle
        binding.webView.apply {
            webViewClient = WebViewClient()
            if (article.url.toString().isNotEmpty()) {
                loadUrl(article.url ?: "")
            }
        }
    }
}