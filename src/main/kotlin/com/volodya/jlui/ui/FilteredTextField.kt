package com.volodya.jlui.ui

import com.raylib.Jaylib.*
import com.raylib.Raylib
import com.volodya.jlui.State
import com.volodya.jlui.rectangles.AlignableRectangle
import kotlin.text.Regex

open class FilteredTextField @JvmOverloads constructor(
    bounds: AlignableRectangle,
    var filter: Regex,
    var defaultValue: String,
    placeholder: String = "",
    placeholderColor: Raylib.Color = GRAY,
    onAction: () -> Unit = {}
) : TextField(
    bounds = bounds,
    "",
    placeholder,
    placeholderColor,
    onAction
) {
    var errorColor: Raylib.Color = RED

    @JvmOverloads
    constructor(
        filter: Regex, defaultValue: String,
        x: Float = 0f, y: Float = 0f,
        width: Float? = 125f, height: Float? = 50f,
        placeholder: String = "", placeholderColor: Raylib.Color = GRAY,
        onAction: () -> Unit = {}
    ) : this(AlignableRectangle(x, y, width, height), filter, defaultValue, placeholder, placeholderColor, onAction)

    @Override
    override fun makeTextBox() {
        super.makeTextBox()
        if (state == State.ActiveState && !filter.matches(text)) {
            textBox.textColor = errorColor
        } else {
            textBox.textColor = textColor
        }
    }

    @Override
    override fun action() {
        if (!filter.matches(text)) {
            text = defaultValue
            textBox.text = defaultValue
        }
        super.action()
    }
}
