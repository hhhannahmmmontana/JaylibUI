package com.volodya.jlui

import com.raylib.Jaylib.BLACK
import com.raylib.Jaylib.Vector2
import com.raylib.Raylib
import com.raylib.Raylib.DrawLineEx
import com.volodya.jlui.rectangles.AlignableRectangle

open class Line @JvmOverloads constructor(
    fromX: Float,
    fromY: Float,
    toX: Float,
    toY: Float,
    var width: Float = 1f,
    var color: Raylib.Color = BLACK
) : AlignableRectangle(
    AlignableRectangle(fromX, fromY, toX - fromX, toY - fromY)
), IDrawable {
    @JvmOverloads
    constructor(
        from: Raylib.Vector2,
        to: Raylib.Vector2,
        width: Float = 1f,
        color: Raylib.Color = BLACK
    ) : this(
        from.x(), from.y(), to.x(), to.y(), width, color
    )

    init {
        margin = 0f
    }

    @Override
    override fun draw() {
        DrawLineEx(
            Vector2(x(), y()),
            Vector2(x() + width(), y() + height()),
            width, color
        )
    }
}
