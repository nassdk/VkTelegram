package com.nassdk.vkvideo.feature.videos.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.nassdk.vkvideo.feature.videos.domain.usecase.GetVideosUseCase
import com.nassdk.vkvideo.library.coreui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    private val getVideosUseCase: GetVideosUseCase
) : BaseViewModel() {

    val videos = Pager(PagingConfig(pageSize = 10)) {
        getVideosUseCase.invoke()
    }.flow.cachedIn(viewModelScope)
}