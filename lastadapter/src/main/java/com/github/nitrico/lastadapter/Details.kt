@file:Suppress("UNCHECKED_CAST")

package com.github.nitrico.lastadapter

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView

open class DefaultDetail : ItemDetails<Long>() {
    override fun getSelectionKey(): Long? = itemPosition.toLong()
    override fun inSelectionHotspot(e: MotionEvent): Boolean = true
}

open class DefaultDetailsLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
    override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(e.x, e.y)
        if (view != null) {
            val viewHolder = recyclerView.getChildViewHolder(view)
            if (viewHolder is Holder<*>) {
                return viewHolder.detail as? ItemDetails<Long>
            }
        }
        return null
    }
}

open class DefaultMultiSelectPredicate : SelectionTracker.SelectionPredicate<Long>() {
    override fun canSelectMultiple(): Boolean = true
    override fun canSetStateForKey(key: Long, nextState: Boolean): Boolean = true
    override fun canSetStateAtPosition(position: Int, nextState: Boolean): Boolean = true
}

open class DefaultKeyProvider : ItemKeyProvider<Long>(SCOPE_MAPPED) {
    override fun getKey(position: Int): Long? = position.toLong()
    override fun getPosition(key: Long): Int = key.toInt()
}