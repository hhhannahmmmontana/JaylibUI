package com.volodya.jlui

import com.raylib.Raylib
import com.raylib.Raylib.DrawCircleV
import com.volodya.jlui.rectangles.AlignableRectangle

class Circle(
    val centerPos: Raylib.Vector2,
    val radius: Float,
    val color: Raylib.Color
) : AlignableRectangle(
    centerPos.x() - radius,
    centerPos.y() - radius,
    2f * radius,
    2f * radius
), IDrawable {
    override fun draw() {
        DrawCircleV(centerPos, radius, color)
    }
}
