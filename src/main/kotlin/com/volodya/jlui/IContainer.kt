package com.volodya.jlui

import com.volodya.jlui.rectangles.DrawableRectangle

interface IContainer : IDrawable {
    fun addChild(
        el: DrawableRectangle
    )
}
