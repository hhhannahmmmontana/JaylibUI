package com.volodya.jlui.ui

import com.raylib.Raylib
import com.raylib.Raylib.DrawRectangleRec
import com.volodya.jlui.Align
import com.volodya.jlui.IContainer
import com.volodya.jlui.rectangles.AlignableRectangle
import com.volodya.jlui.rectangles.DrawableRectangle

open class Container @JvmOverloads constructor(
    bounds: AlignableRectangle,
    override var horizontalAlign: Align.Horizontal = Align.Horizontal.Left,
    override var verticalAlign: Align.Vertical = Align.Vertical.Top,
    override var margin: Float = 10f,
    override var backgroundColor: Raylib.Color? = null
) : DrawableRectangle(
    bounds
), IContainer {
    @JvmOverloads
    constructor(
        width: Float? = null, height: Float? = null,
        x: Float = 0f, y: Float = 0f
    ) : this(
        AlignableRectangle(x, y, width, height)
    )

    private val elements = arrayListOf<DrawableRectangle>()

    @Override
    override fun addChild(el: DrawableRectangle) {
        el.parent = this
        elements.add(el)
    }

    @Override
    override fun adjust() {
        super.adjust()
        for (el in elements) {
            el.adjust()
        }
    }

    @Override
    override fun resize(width: Float, height: Float) {
        super.resize(width, height)
        for (el in elements) {
            el.adjust()
        }
    }

    @Override
    override fun draw() {
        if (backgroundColor != null) {
            DrawRectangleRec(this, backgroundColor)
        }
        for (el in elements) {
            el.draw()
        }
    }
}
