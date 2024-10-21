package com.volodya.jlui.rectangles

import com.raylib.Jaylib.Vector2
import com.raylib.Jaylib.Rectangle
import com.raylib.Raylib
import com.volodya.jlui.IAlignable
import com.volodya.jlui.Align
import com.volodya.jlui.ui.Container

open class AlignableRectangle : Rectangle, IAlignable {
    override var horizontalAlign: Align.Horizontal = Align.Horizontal.Left
        set(value) {
            makeX(parent, value)
            field = value
        }
    override var verticalAlign: Align.Vertical = Align.Vertical.Top
        set(value) {
            makeY(parent, value)
            field = value
        }

    override var margin: Float = 5f
    var relativeX: Float = super<Rectangle>.x()
        private set
    var relativeY: Float = super<Rectangle>.y()
        private set

    private var autoWidth = false
    private var autoHeight = false

    override var parent: Container? = null
        set(value) {
            if (value == null) {
                autoWidth = false
                autoHeight = false
            }
            adjust(value)
            field = value
        }

    @JvmOverloads
    constructor(
        x: Float = 0f, y: Float = 0f,
        width: Float? = null, height: Float? = null,
        parent: Container? = null
    ) {
        this.x(x)
        this.y(y)
        if (width != null) {
            this.width(width)
        } else {
            autoWidth = true
        }
        if (height != null) {
            this.height(height)
        } else {
            autoHeight = true
        }
        this.parent = parent
    }

    constructor(other: AlignableRectangle) : this(
        other.x(), other.y(), other.width(), other.height(), other.parent
    )

    private fun adjust(value: Container? = parent) {
        if (autoWidth) {
            autoWidth(value)
        }
        if (autoHeight) {
            autoHeight(value)
        }
        makeX(value)
        makeY(value)
    }

    open fun adjust() {
        adjust(parent)
    }

    private fun autoWidth(value: Container? = parent) {
        if (value == null) {
            return
        }
        super.width(value.width() + value.x() - x() - 2 * margin)
    }

    fun autoWidth() {
        autoWidth = true
        autoWidth(parent)
    }

    private fun autoHeight(value: Container? = parent) {
        if (value == null) {
            return
        }
        super.height(value.height() + value.y() - y() - 2 * margin)
    }

    fun autoHeight() {
        autoHeight = true
        autoHeight(parent)
    }

    private fun makeX(value: Container? = parent, align: Align.Horizontal = horizontalAlign) {
        if (value == null) {
            return
        }
        super.x(value.x() + when(align) {
            Align.Horizontal.Right -> value.width() - (relativeX + width() + margin)
            Align.Horizontal.Center -> value.width() / 2 - width() / 2
            else -> relativeX + margin
        })
    }

    private fun makeY(value: Container? = parent, align: Align.Vertical = verticalAlign) {
        if (value == null) {
            return
        }
        super.y(value.y() + when(align) {
            Align.Vertical.Bottom -> value.height() - (relativeY + height() + margin)
            Align.Vertical.Center -> value.height() / 2 - height() / 2
            else -> relativeY + margin
        })
    }

    @Override
    override fun x(p0: Float): Raylib.Rectangle {
        relativeX = p0
        makeX()
        return this
    }

    @Override
    override fun y(p0: Float): Raylib.Rectangle {
        relativeY = p0
        makeY()
        return this
    }

    @Override
    override fun width(p0: Float): Raylib.Rectangle {
        autoWidth = false
        return super.width(p0)
    }

    @Override
    override fun height(p0: Float): Raylib.Rectangle {
        autoHeight = false
        return super.height(p0)
    }

    @Override
    override fun relocate(x: Float, y: Float) {
        x(x).y(y)
    }

    @Override
    override fun resize(width: Float, height: Float) {
        width(width)
        height(height)
    }

    @Override
    override fun getPos(): Vector2 {
        return Vector2(x(), y())
    }

    @Override
    override fun getSize(): Vector2 {
        return Vector2(width(), height())
    }
}
