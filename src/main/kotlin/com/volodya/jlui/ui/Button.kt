package com.volodya.jlui.ui

import com.raylib.Jaylib.*
import com.volodya.jlui.State
import com.volodya.jlui.rectangles.ActiveElement
import com.volodya.jlui.rectangles.AlignableRectangle

class Button @JvmOverloads constructor(
    bounds: AlignableRectangle,
    text: String,
    onAction: () -> Unit = {}
) : ActiveElement(
    bounds = bounds,
    text = text,
    onAction = onAction
) {
    @JvmOverloads
    constructor(text: String, onAction: () -> Unit = {}) :
        this(AlignableRectangle(0f, 0f, 125f, 50f), text, onAction);

    @Override
    override fun updateState() {
        state = if (isDisabled) {
            State.DisabledState
        } else if (CheckCollisionPointRec(GetMousePosition(), this)) {
            if (IsMouseButtonDown(MOUSE_BUTTON_LEFT)) {
                State.ActiveState
            } else {
                State.HoverState
            }
        } else {
            State.NormalState
        }
    }
}
