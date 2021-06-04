package com.nassdk.vkvideo.feature.videos.presentation

import android.view.animation.AnimationUtils.loadAnimation
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.nassdk.vkvideo.R
import com.nassdk.vkvideo.databinding.ScreenVideosBinding
import com.nassdk.vkvideo.library.coreui.base.BaseFragment
import com.nassdk.vkvideo.library.coreui.util.isVisible
import com.nassdk.vkvideo.library.coreui.util.launchWhenStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class VideosFragment : BaseFragment(R.layout.screen_videos) {

    private var _viewBinding: ScreenVideosBinding? = null

    private val viewBinding: ScreenVideosBinding
        get() = _viewBinding!!

    private val viewModel: VideosViewModel by viewModels()

    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        VideosAdapter()
    }

    private var animationStarted = false

    override fun prepareUi() {

        _viewBinding = ScreenVideosBinding.bind(requireView())

        viewBinding.toolbar.setupToolbar(title = getString(R.string.videos_screen_toolbar_title))
        viewBinding.recyclerVideos.adapter = adapter.withLoadStateHeaderAndFooter(
            header = VideosLoaderStateAdapter(),
            footer = VideosLoaderStateAdapter()
        )

        adapter.addLoadStateListener { state ->
            viewBinding.recyclerVideos.isVisible(state.refresh != LoadState.Loading)
            viewBinding.progress.isVisible(state.refresh == LoadState.Loading)
        }
    }

    override fun setupViewModel() {

        val slideUp = loadAnimation(requireContext(), R.anim.slide_up)

        viewModel.videos.onEach { videoData ->
            if (adapter.itemCount == 0)
                viewBinding.linearContainer.startAnimation(slideUp)

            adapter.submitData(videoData)
        }.launchWhenStarted(lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}