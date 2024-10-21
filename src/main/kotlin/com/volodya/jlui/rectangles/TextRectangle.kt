package com.volodya.jlui.rectangles

import com.raylib.Jaylib.BLACK
import com.raylib.Raylib
import com.raylib.Raylib.LoadFont
import com.volodya.jlui.Align
import com.volodya.jlui.IAlignable
import com.volodya.jlui.IText
import com.volodya.jlui.text.TextBox

open class TextRectangle @JvmOverloads constructor(
    bounds: AlignableRectangle,
    override var text: String = "",
    override var textColor: Raylib.Color = BLACK,
    override var fontSize: Float = 24f,
    override var fontSpacing: Float = 2f,
    override var textHorizontalAlign: Align.Horizontal = Align.Horizontal.Center,
    override var textVerticalAlign: Align.Vertical = Align.Vertical.Center,
    override var textMargin: Float = 10f,
    override var font: Raylib.Font = LoadFont(""),
    override var maxSize: Int = 6
) : DrawableRectangle(
    bounds
), IAlignable, IText {
    protected lateinit var textBox: IText

    protected open fun makeTextBox() {
        textBox = TextBox(
            this,
            text,
            textHorizontalAlign, textVerticalAlign, textMargin,
            textColor, fontSize, fontSpacing, font,
            maxSize
        )
    }

    @Override
    override fun relocate(x: Float, y: Float) {
        super.relocate(x, y)
    }

    @Override
    override fun resize(width: Float, height: Float) {
        super.resize(width, height)
    }

    @Override
    override fun draw() {
        super.draw()
        makeTextBox()
        textBox.draw()
    }

    @Override
    override fun getTextSize(): Raylib.Vector2 {
        return textBox.getTextSize()
    }
}
