package com.volodya.jlui.text

import com.raylib.Jaylib
import com.raylib.Jaylib.BLACK
import com.raylib.Raylib
import com.raylib.Raylib.LoadFont
import com.raylib.Raylib.Rectangle
import com.raylib.Raylib.Vector2
import com.volodya.jlui.Align
import com.volodya.jlui.IAlignable
import com.volodya.jlui.IText
import com.volodya.jlui.rectangles.AlignableRectangle

class TextBox @JvmOverloads constructor(
    bounds: AlignableRectangle,
    override var text: String = "",
    override var textHorizontalAlign: Align.Horizontal = Align.Horizontal.Center,
    override var textVerticalAlign: Align.Vertical = Align.Vertical.Center,
    override var textMargin: Float = 10f,
    override var textColor: Raylib.Color = BLACK,
    override var fontSize: Float = 20f,
    override var fontSpacing: Float = 2f,
    override var font: Raylib.Font = LoadFont(""),
    override var maxSize: Int = 7,
) : AlignableRectangle(
    bounds
), IText {
    private fun horizontalPos(textBounds: Vector2): Float {
        return x() + if (textHorizontalAlign == Align.Horizontal.Right) {
            width() - textBounds.x() - textMargin
        } else if (textHorizontalAlign == Align.Horizontal.Center) {
            width() / 2 - textBounds.x() / 2
        } else {
            textMargin
        }
    }

    private fun verticalPos(textBounds: Vector2): Float {
        return y() + if (textVerticalAlign == Align.Vertical.Bottom) {
            height() - textBounds.y() - textMargin
        } else if (textVerticalAlign == Align.Vertical.Center) {
            height() / 2 - textBounds.y() / 2
        } else {
            textMargin
        }
    }

    @Override
    override fun getTextSize(): Vector2 {
        val textBounds = Raylib.MeasureTextEx(font, text, fontSize, fontSpacing)
        return Jaylib.Vector2(horizontalPos(textBounds), verticalPos(textBounds))
    }

    @Override
    override fun draw() {
        text = text.take(maxSize)
        Raylib.DrawTextEx(
            font, text, getTextSize(), fontSize, fontSpacing, textColor
        )
    }
}
