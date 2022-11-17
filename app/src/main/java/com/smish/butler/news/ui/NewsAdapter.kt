package com.smish.butler.news.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.smish.butler.R
import com.smish.butler.databinding.LayoutNewsItemBinding
import com.smish.butler.news.data.model.topHeadlines.Article
import com.smish.butler.util.extensions.getFormattedText
import com.smish.butler.util.extensions.loadImageWithGlide

class NewsAdapter(val context: Context): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val callback = object: DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    private var onItemClickListener: ((Article)->Unit)? = null
    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = LayoutNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class NewsViewHolder(val binding: LayoutNewsItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            val author = article.author
            val src = article.source?.name
            binding.tvAuthorSrc.text = context.resources.getFormattedText(R.string.news_author_src, author ?: "", src ?: "")
            binding.ivThumbnail.loadImageWithGlide(article.urlToImage ?: R.drawable.ic_round_photo_24)

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }
}