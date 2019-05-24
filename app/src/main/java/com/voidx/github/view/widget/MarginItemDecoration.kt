package com.voidx.github.view.widget

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class MarginItemDecoration : RecyclerView.ItemDecoration {

    private val mLeft: Int
    private val mTop: Int
    private val mRight: Int
    private val mBottom: Int

    constructor(space: Int) {
        mLeft = space
        mTop = space
        mRight = space
        mBottom = space
    }

    constructor(horizontal: Int, vertical: Int) {
        mLeft = horizontal
        mTop = vertical
        mRight = horizontal
        mBottom = vertical
    }

    constructor(left: Int, top: Int, right: Int, bottom: Int) {
        mLeft = left
        mTop = top
        mRight = right
        mBottom = bottom
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = mLeft
        outRect.right = mRight
        outRect.bottom = mBottom
        outRect.top = mTop
    }
}