package com.nassdk.vkvideo.library.coreui.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nassdk.vkvideo.R

/**
 * Replace fragment transaction ext for making transactions from fragment.
 *
 * @param fragment - Fragment class
 */
fun Fragment.makeReplace(fragment: Fragment) {

    requireActivity()
        .supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(
            R.anim.enter_from_right,
            R.anim.exit_to_left,
            R.anim.enter_from_left,
            R.anim.exit_to_right
        )
        .replace(R.id.container, fragment)
        .commit()
}


/**
 * Add fragment transaction ext for making transactions from fragment.
 *
 * @param fragment - Fragment class
 * @param withBackStack - Should fragment be added to backstack
 * @param tag - Fragment tag for adding to backstack
 */
fun Fragment.makeForward(fragment: Fragment, withBackStack: Boolean = false, tag: String? = null) {

    when {
        withBackStack && tag.isNullOrBlank() -> throw IllegalStateException("Fragment Tag must not be empty or null.")
        withBackStack -> requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            .add(R.id.container, fragment)
            .addToBackStack(tag)
            .commit()
        else -> requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
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
 * @param tag - Fragment tag for adding to backstack
 */
fun AppCompatActivity.makeForward(fragment: Fragment, withBackStack: Boolean = false, tag: String? = null) {

    when {
        withBackStack && tag.isNullOrBlank() -> throw IllegalStateException("Fragment Tag must not be empty or null.")
        withBackStack -> supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            .add(R.id.container, fragment)
            .addToBackStack(tag)
            .commit()
        else -> supportFragmentManager
            .beginTransaction()
            .add(R.id.container, fragment)
            .commit()
    }
}


/**
 * Replace fragment transaction ext for making transactions from Activity.
 *
 * @param fragment - Fragment class
 */
fun AppCompatActivity.makeReplace(fragment: Fragment) {

    supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(
            R.anim.enter_from_right,
            R.anim.exit_to_left,
            R.anim.enter_from_left,
            R.anim.exit_to_right
        )
        .replace(R.id.container, fragment)
        .commit()
}