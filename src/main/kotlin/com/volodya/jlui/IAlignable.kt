package com.volodya.jlui

import com.raylib.Jaylib
import com.volodya.jlui.ui.Container

interface IAlignable {
    var parent: Container?
    var horizontalAlign: Align.Horizontal
    var verticalAlign: Align.Vertical
    var margin: Float

    fun relocate(x: Float, y: Float)
    fun resize(width: Float, height: Float)
    fun getPos(): Jaylib.Vector2
    fun getSize(): Jaylib.Vector2
}
