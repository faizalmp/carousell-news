package id.faizalempe.carousellnews.news.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import id.faizalempe.carousellnews.R
import id.faizalempe.carousellnews.base.BaseActivity
import id.faizalempe.carousellnews.base.recyclerview.GenericAdapter
import id.faizalempe.carousellnews.databinding.ActivityNewsBinding
import id.faizalempe.carousellnews.databinding.ItemNewsBinding
import id.faizalempe.carousellnews.domain.model.News
import id.faizalempe.carousellnews.news.viewmodel.NewsUiState
import id.faizalempe.carousellnews.news.viewmodel.NewsUiStateAction
import id.faizalempe.carousellnews.news.viewmodel.NewsViewModel
import id.faizalempe.carousellnews.util.getDimen
import id.faizalempe.carousellnews.util.getItemViewBinding
import id.faizalempe.carousellnews.util.getViewBinding
import id.faizalempe.carousellnews.util.loadImage
import id.faizalempe.carousellnews.util.setItemMargin
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsActivity : BaseActivity<ActivityNewsBinding>(), NewsUiStateAction {

    private val vm: NewsViewModel by viewModels()


    private val newsAdapter: GenericAdapter<News, ItemNewsBinding> by lazy {
        GenericAdapter(
            itemBinding = { getItemViewBinding(ItemNewsBinding::inflate) },
            onBindItem = { news, _ ->
                ivBanner.loadImage(imageUrl = news.bannerUrl)
                tvTitle.text = news.title
                tvDesc.text = news.desc
                tvTime.text = news.timeAgo
            },
            onChanged = { binding.rvNews.scrollToPosition(0) }
        )
    }

    override fun inflateViewBinding(): ActivityNewsBinding =
        getViewBinding(ActivityNewsBinding::inflate)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.news_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.recent -> vm.sortRecent()
            R.id.popular -> vm.sortPopular()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initViews()
        observeUiState()
        vm.getNews()
    }

    private fun initViews() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvNews.apply {
            setItemMargin(getDimen(R.dimen.rv_margin))
            adapter = newsAdapter
        }
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) { vm.uiState.collect(::manageUiState) }
        }
    }

    private fun manageUiState(state: NewsUiState) = when (state) {
        is NewsUiState.OnSuccess -> doOnSuccess(state.newsList)
        is NewsUiState.OnError -> doOnError(state.error)
        is NewsUiState.OnLoading -> doOnLoading(state.isShow)
        is NewsUiState.OnSorted -> doOnSorted(state.newsList)
        else -> {}
    }

    override fun doOnSuccess(newsList: List<News>) {
        newsAdapter.set(newsList)
    }

    override fun doOnError(error: Throwable) {
        // TODO("Not yet implemented")
    }

    override fun doOnLoading(isShow: Boolean) {
        // TODO("Not yet implemented")
    }

    override fun doOnSorted(newsList: List<News>) {
        newsAdapter.set(newsList)
    }


}