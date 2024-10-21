package com.volodya.jlui

import com.raylib.Raylib

interface IText : IDrawable {
    var text: String
    var textColor: Raylib.Color
    var fontSize: Float
    var fontSpacing: Float
    var font: Raylib.Font
    var maxSize: Int
    var textHorizontalAlign: Align.Horizontal
    var textVerticalAlign: Align.Vertical
    var textMargin: Float
    fun getTextSize(): Raylib.Vector2
}
