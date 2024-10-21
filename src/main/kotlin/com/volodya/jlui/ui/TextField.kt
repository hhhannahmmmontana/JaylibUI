package com.volodya.jlui.ui

import com.raylib.Jaylib.*
import com.raylib.Raylib
import com.volodya.jlui.Align
import com.volodya.jlui.State
import com.volodya.jlui.rectangles.ActiveElement
import com.volodya.jlui.rectangles.AlignableRectangle
import com.volodya.jlui.text.TextBox

open class TextField @JvmOverloads constructor(
    bounds: AlignableRectangle,
    text: String = "",
    var placeholder: String = "",
    var placeholderColor: Raylib.Color = GRAY,
    onAction: () -> Unit = {}
) : ActiveElement(
    bounds = bounds,
    text = text,
    activeColor = WHITE,
    onAction = onAction
) {
    init {
        this.verticalAlign = Align.Vertical.Center
    }

    @Override
    override fun updateState() {
        if (!isDisabled && IsKeyPressed(KEY_ENTER)) {
            state = State.NormalState
        }
        state = if (isDisabled) {
            State.DisabledState
        } else if (CheckCollisionPointRec(GetMousePosition(), this)) {
            if (state == State.ActiveState || IsMouseButtonDown(MOUSE_BUTTON_LEFT)) {
                State.ActiveState
            } else {
                State.HoverState
            }
        } else {
            if (state != State.ActiveState || IsMouseButtonDown(MOUSE_BUTTON_LEFT) || IsKeyPressed(KEY_ENTER)) {
                State.NormalState
            } else {
                State.ActiveState
            }
        }
    }

    protected open fun input() {
        if (state == State.ActiveState) {
            val key = GetCharPressed()
            if ((key >= 32) && (key <= 125) && text.length <= maxSize) {
                text += key.toChar()
            } else if (IsKeyPressed(KEY_BACKSPACE)) {
                if (text.isNotEmpty()) {
                    text = text.dropLast(1)
                }
            }
        }
    }

    @Override
    override fun makeTextBox() {
        val currentColor: Raylib.Color = if (
            state != State.ActiveState && text.isEmpty()
        ) {
            placeholderColor
        } else {
            textColor
        }
        val currentText: String = if (state == State.ActiveState) {
            text + '_'
        } else if (text.isEmpty()) {
            placeholder
        } else {
            text
        }
        textBox = TextBox(this)
        textBox.text = currentText
        textBox.textColor = currentColor
    }

    @Override
    override fun draw() {
        input()
        super.draw()
    }
}
