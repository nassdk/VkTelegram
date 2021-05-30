package com.nassdk.vkvideo.library.coreui.util

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.ContentViewCallback
import com.nassdk.vkvideo.R

internal class CustomSnackBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ContentViewCallback {

    val icon: AppCompatImageView
    val dismissButton: MaterialButton

    init {
        val view = View.inflate(context, R.layout.view_snack_bar_merge, this)
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.primary_secondary))
        icon = view.findViewById(R.id.warningIcon)
        dismissButton = view.findViewById(R.id.action)
    }

    override fun animateContentIn(delay: Int, duration: Int) {
        val scaleX = ObjectAnimator.ofFloat(icon, View.SCALE_X, 0f, 1f)
        val scaleY = ObjectAnimator.ofFloat(icon, View.SCALE_Y, 0f, 1f)
        val animatorSet = AnimatorSet().apply {
            interpolator = OvershootInterpolator()
            setDuration(500)
            playTogether(scaleX, scaleY)
        }
        animatorSet.start()
    }

    override fun animateContentOut(delay: Int, duration: Int) {

    }

    fun setText(text: String) {
        findViewById<TextView>(R.id.text).text = text
    }
}

class FloatingSnackBar private constructor(
    parent: ViewGroup,
    content: CustomSnackBar
) : BaseTransientBottomBar<FloatingSnackBar>(parent, content, content) {

    init {
        getView().setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                android.R.color.transparent
            )
        )
        getView().setPadding(0, 0, 0, 0)
        getView().elevation = 50f
    }

    companion object {

        fun make(
            activity: Activity,
            text: String = "",
            isError: Boolean = true,
            actionButtonListener: (() -> Unit)? = null
        ): FloatingSnackBar {

            val parent = activity
                .findViewById<ViewGroup>(android.R.id.content)
                .getChildAt(0).findSuitableParent()
                ?: throw IllegalArgumentException(
                    "No suitable parent found from the given view. Please provide a valid view."
                )

            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_snack_bar, parent, false) as CustomSnackBar

            v.setText(text)

            return FloatingSnackBar(
                parent,
                v
            ).also { sb ->
                v.dismissButton.isVisible(actionButtonListener != null)
                v.dismissButton.setOnClickListener {
                    actionButtonListener?.invoke()
                    sb.dismiss()
                }
                v.icon.setImageResource(
                    if (isError)
                        R.drawable.ic_warning
                    else R.drawable.ic_check_mark
                )
                sb.animationMode = ANIMATION_MODE_SLIDE
                sb.duration = if (actionButtonListener == null || !isError)
                    LENGTH_LONG
                else
                    LENGTH_INDEFINITE
            }
        }
    }
}
