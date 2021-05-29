package com.nassdk.vkvideo.library.coreui.base

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.nassdk.vkvideo.R
import com.nassdk.vkvideo.databinding.ToolbarBinding
import com.nassdk.vkvideo.library.coreui.util.isVisible

class ToolbarBase @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr) {

    private val viewBinding: ToolbarBinding

    init {

        setBackgroundColor(ContextCompat.getColor(context, R.color.color_white))

        val view = inflate(context, R.layout.toolbar, this)

        viewBinding = ToolbarBinding.bind(view).apply {
            TypedValue().run {
                if (context.theme.resolveAttribute(android.R.attr.actionBarSize, this, true)) {
                    root.minimumHeight =
                        TypedValue.complexToDimensionPixelSize(data, resources.displayMetrics)
                }
            }
        }
    }

    fun setupToolbar(
        title: String? = null,
        subTitle: String? = null,
        menuEnabled: Boolean = false,
        backButtonListener: (() -> Unit)? = null,
        @DrawableRes customNavIcon: Int? = null
    ) {
        with(viewBinding) {

//            backButtonListener?.let {
//                toolbar.navigationIcon =
//                    ContextCompat.getDrawable(context, customNavIcon ?: R.drawable.refactor_arrow)
//                setNavigationOnClickListener { backButtonListener.invoke() }
//            }

            titleText.text = title.orEmpty()

            subtitleText.text = subTitle.orEmpty()
            subtitleText.isVisible(subTitle.isNullOrEmpty().not())

            navIconPlaceholder.isVisible(toolbar.navigationIcon == null)
            menuIconPlaceholder.isVisible(!menuEnabled)
        }
    }
}
