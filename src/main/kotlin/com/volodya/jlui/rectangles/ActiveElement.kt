package com.volodya.jlui.rectangles

import com.raylib.Jaylib.*
import com.raylib.Raylib
import com.volodya.jlui.State

abstract class ActiveElement @JvmOverloads constructor(
    bounds: AlignableRectangle,
    text: String,
    var disabledColor: Raylib.Color = GRAY,
    var normalColor: Raylib.Color = WHITE,
    var hoverColor: Raylib.Color = LIGHTGRAY,
    var activeColor: Raylib.Color = GRAY,
    var onAction: () -> Unit = {}
) : TextRectangle(
    bounds,
    text
) {
    var isDisabled: Boolean = false
    var state: State = State.NormalState

    protected fun setColorByState() {
        backgroundColor = when(state) {
            State.NormalState -> normalColor
            State.HoverState -> hoverColor
            State.ActiveState -> activeColor
            else -> disabledColor
        }
    }

    protected abstract fun updateState()
    protected open fun action() {
        onAction()
    }

    @Override
    override fun draw() {
        val prevState = state
        updateState()
        setColorByState()
        if (prevState == State.ActiveState && state != prevState) {
            action()
        }
        super.draw()
    }
}
