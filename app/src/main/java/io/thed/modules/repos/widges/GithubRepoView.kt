package io.thed.modules.repos.widges

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import io.thed.databinding.GithubRepoViewBinding
import io.thed.modules.repos.response.RepoItem

class GithubRepoView : LinearLayout {
    private lateinit var binding: GithubRepoViewBinding

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initView(context)
    }

    private fun initView(context: Context) {
        binding = GithubRepoViewBinding.inflate(LayoutInflater.from(context), this, true)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }

    fun bind(item: RepoItem) {
        binding.tvRepoTitle.text = item.name
        binding.tvRepoDescription.text = item.description
        binding.tvLanguage.text = item.language
        binding.tvStars.text = "${item.stargazersCount}"
        binding.tvForks.text = "${item.forksCount}"
    }
}