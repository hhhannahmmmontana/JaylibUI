package com.volodya.jlui.rectangles

import com.raylib.Jaylib.*
import com.raylib.Raylib
import com.volodya.jlui.IDrawable

open class DrawableRectangle @JvmOverloads constructor(
    bounds: AlignableRectangle,
    open var backgroundColor: Raylib.Color? = WHITE,
    var strokeWidth: Float = 2f,
    var strokeColor: Raylib.Color = BLACK
) : AlignableRectangle(
    bounds
), IDrawable {
    @Override
    override fun draw() {
        DrawRectangleRec(this, backgroundColor)
        if (strokeWidth > 0f) {
            DrawRectangleLinesEx(this, strokeWidth, strokeColor)
        }
    }
}
