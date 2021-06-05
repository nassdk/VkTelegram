package com.nassdk.vkvideo.library.coreui.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nassdk.vkvideo.R

/**
 * Replace fragment transaction ext for making transactions from fragment.
 *
 * @param fragment - Fragment class
 * @param verticalAnimation - Should make transaction with vertical animation else horizontal
 */
fun Fragment.makeReplace(fragment: Fragment, verticalAnimation: Boolean = false) {

    requireActivity()
        .supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(
            if (verticalAnimation) R.anim.slide_in_bottom else R.anim.enter_from_right,
            if (verticalAnimation) R.anim.slide_out_top else R.anim.exit_to_left,
            if (verticalAnimation) R.anim.slide_in_top else R.anim.enter_from_left,
            if (verticalAnimation) R.anim.slide_out_bottom else R.anim.exit_to_right
        )
        .replace(R.id.container, fragment)
        .commit()
}


/**
 * Add fragment transaction ext for making transactions from fragment.
 *
 * @param fragment - Fragment class
 * @param withBackStack - Should fragment be added to backstack
 * @param verticalAnimation - Should make transaction with vertical animation else horizontal
 */
fun Fragment.makeForward(fragment: Fragment, withBackStack: Boolean = false, verticalAnimation: Boolean = false) {

    when {
        withBackStack -> requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                if (verticalAnimation) R.anim.slide_in_bottom else R.anim.enter_from_right,
                if (verticalAnimation) R.anim.slide_out_top else R.anim.exit_to_left,
                if (verticalAnimation) R.anim.slide_in_top else R.anim.enter_from_left,
                if (verticalAnimation) R.anim.slide_out_bottom else R.anim.exit_to_right
            )
            .add(R.id.container, fragment)
            .addToBackStack(fragment::class.java.simpleName)
            .commit()
        else -> requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                if (verticalAnimation) R.anim.slide_in_bottom else R.anim.enter_from_right,
                if (verticalAnimation) R.anim.slide_out_top else R.anim.exit_to_left,
                if (verticalAnimation) R.anim.slide_in_top else R.anim.enter_from_left,
                if (verticalAnimation) R.anim.slide_out_bottom else R.anim.exit_to_right
            )
            .add(R.id.container, fragment)
            .commit()
    }
}


/**
 * Add fragment transaction ext for making transactions from activity.
 *
 * @param fragment - Fragment class
 * @param withBackStack - Should fragment be added to backstack
 * @param verticalAnimation - Should make transaction with vertical animation else horizontal
 */
fun AppCompatActivity.makeForward(
    fragment: Fragment,
    withBackStack: Boolean = false,
    verticalAnimation: Boolean = false
) {

    when {
        withBackStack -> supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                if (verticalAnimation) R.anim.slide_in_bottom else R.anim.enter_from_right,
                if (verticalAnimation) R.anim.slide_out_top else R.anim.exit_to_left,
                if (verticalAnimation) R.anim.slide_in_top else R.anim.enter_from_left,
                if (verticalAnimation) R.anim.slide_out_bottom else R.anim.exit_to_right
            )
            .add(R.id.container, fragment)
            .addToBackStack(fragment::class.java.simpleName)
            .commit()
        else -> supportFragmentManager
            .beginTransaction()
            .add(R.id.container, fragment)
            .setCustomAnimations(
                if (verticalAnimation) R.anim.slide_in_bottom else R.anim.enter_from_right,
                if (verticalAnimation) R.anim.slide_out_top else R.anim.exit_to_left,
                if (verticalAnimation) R.anim.slide_in_top else R.anim.enter_from_left,
                if (verticalAnimation) R.anim.slide_out_bottom else R.anim.exit_to_right
            )
            .commit()
    }
}


/**
 * Replace fragment transaction ext for making transactions from Activity.
 *
 * @param fragment - Fragment class
 * @param verticalAnimation - Should make transaction with vertical animation else horizontal
 */
fun AppCompatActivity.makeReplace(fragment: Fragment, verticalAnimation: Boolean = false) {

    supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(
            if (verticalAnimation) R.anim.slide_in_bottom else R.anim.enter_from_right,
            if (verticalAnimation) R.anim.slide_out_top else R.anim.exit_to_left,
            if (verticalAnimation) R.anim.slide_in_top else R.anim.enter_from_left,
            if (verticalAnimation) R.anim.slide_out_bottom else R.anim.exit_to_right
        )
        .replace(R.id.container, fragment)
        .commit()
}