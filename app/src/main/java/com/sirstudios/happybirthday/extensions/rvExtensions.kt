package com.sirstudios.happybirthday.extensions

import android.util.Log
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.sirstudios.happybirthday.R

fun RecyclerView.getLinearLayoutManager(): LinearLayoutManager? =
    this.layoutManager as? LinearLayoutManager

fun RecyclerView.getCurrentPosition(): Int {
    val linearLayout = this.getLinearLayoutManager()
    return linearLayout?.findFirstCompletelyVisibleItemPosition()
        ?: linearLayout!!.findFirstVisibleItemPosition()
}

fun RecyclerView.getPreviousPosition(): Int =
    (this.layoutManager as? LinearLayoutManager)?.findLastVisibleItemPosition()?.minus(1) ?: 0

fun RecyclerView.getNextPosition(): Int =
    this.getLinearLayoutManager()?.findFirstVisibleItemPosition()?.plus(1) ?: 0

fun RecyclerView.getCurrentChildViewByPosition(position: Int? = null): View? =
    (this.getLinearLayoutManager())?.findViewByPosition(position ?: getCurrentPosition())

fun RecyclerView.getCurrentChildViewByTag(tag: Int? = null): View? {
    children.forEach {
        if (it.tag.toString() == tag.toString()) return it
    }
    return null
}
fun RecyclerView.getCurrentItemId(): Int =
    (getCurrentChildViewByPosition()?.tag as? String)?.toInt() ?: R.drawable.android_party_happy_birthday_card

fun RecyclerView.focusCurrentChild(isForward: Boolean? = null, previous: Int = getPreviousPosition(), next: Int = getNextPosition()): Boolean? {
    //TODO("Won't work with previous or next position's Ids, because, as far as I understand it only works with the currently visible elements.
    // Thus since @smoothScrollToPosition() haven't finished to scroll to the desired position, the Id will be null since the gotten element's id
    // will perish")

    val willFocusView = when(isForward) {
        true -> {
            getCurrentChildViewByPosition(next)
        }
        false -> {
            getCurrentChildViewByPosition(previous)
        }
        else -> {
            getCurrentChildViewByPosition()
        }
    } as? ShapeableImageView

    val isFocus = willFocusView?.requestFocus()

    Log.i("recyclerView", "isFocus: $isFocus, willFocusViewId: ${willFocusView?.tag.toString()}  willFocusView: $willFocusView")

    willFocusView?.setOnFocusChangeListener { _, hasFocus ->
        if (hasFocus) willFocusView.strokeWidth = 40F
        else willFocusView.strokeWidth = 0F
    }
    return isFocus
}

fun RecyclerView.previousItem() {
    val previous =
        getPreviousPosition()
    val moveTo = if (previous < 0) 0 else previous
    this.smoothScrollToPosition(moveTo)
    focusCurrentChild()
}

fun RecyclerView.nextItem(layoutManager: LinearLayoutManager? = this.layoutManager as? LinearLayoutManager) {
    val limit = layoutManager?.itemCount?.minus(1)
    val next = getNextPosition()
    val isRunning: Boolean? = null
    val moveTo = if (next > limit ?: 0) limit ?: 0 else next

    //TODO("must focusCurrentChild() right after smoothScrollPosition() has finished in order to get the newCurrentChild() ")
    smoothScrollToPosition(moveTo)/*.runCatching {
        isRunning = itemAnimator?.isRunning
        if (itemAnimator?.isRunning == false) focusCurrentChild() }*/
    focusCurrentChild()
    Log.i("recyclerView", "limit: $limit, next: $next, isRunning = ${isRunning}, $layoutManager")
}

fun RecyclerView.smoothScrollToViewByTag(tag: Int) {
    val view = getCurrentChildViewByTag(tag)
    this.smoothScrollToPosition(getChildAdapterPosition(view ?: findViewById(getCurrentItemId())))
}


/*
fun RecyclerView.smoothScrollToPositionAndFocusChild(position: Int = getCurrentPosition()) {
    //runBlocking{} from coroutines.
}*/
