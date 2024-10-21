package com.volodya.jlui.chart

import com.raylib.Jaylib
import com.raylib.Jaylib.BLACK
import com.raylib.Raylib
import com.raylib.Raylib.DrawLineEx
import com.raylib.Raylib.DrawTextEx
import com.raylib.Raylib.GetFontDefault
import com.raylib.Raylib.Vector2Distance
import com.volodya.jlui.Align
import com.volodya.jlui.Line
import com.volodya.jlui.rectangles.AlignableRectangle
import com.volodya.jlui.ui.Container

open class Chart(
    bounds: AlignableRectangle,
    protected val maxX: Float,
    protected val maxY: Float
) : Container(
    bounds
) {
    var showX: Boolean = true
    var showY: Boolean = true
    var axisColor: Raylib.Color = BLACK
    var axisLineWidth: Float = 2f
    var dotLength: Float = 7f
    private val dots = arrayListOf<Raylib.Vector2>()

    fun drawMeasurement(coordinates: Raylib.Vector2) {
        val data = relativeToTheoreticalCoordinates(coordinates)
        if (showX) {
            val textX = String.format("%.3f", data.x())
            val textSize = Raylib.MeasureTextEx(GetFontDefault(), textX, 10f, 2f)
            DrawTextEx(
                GetFontDefault(),
                textX,
                Jaylib.Vector2(x() + coordinates.x() - textSize.x() / 2, y() + height() + 3f),
                10f, 2f, BLACK
            )
        }
        if (showY) {
            val textY = String.format("%.3f", data.y())
            val textSize = Raylib.MeasureTextEx(GetFontDefault(), textY, 10f, 2f)
            DrawTextEx(
                GetFontDefault(),
                textY,
                Jaylib.Vector2(x() - textSize.x() - 3f, y() + height() - coordinates.y() - textSize.y() / 2),
                10f, 2f, BLACK
            )
        }
    }

    fun theoreticalToRelativeCoordinates(coordinates: Raylib.Vector2): Raylib.Vector2 {
        return Jaylib.Vector2(
            coordinates.x() * width() / maxX,
            coordinates.y() * height() / maxY
        )
    }

    fun relativeToTheoreticalCoordinates(coordinates: Raylib.Vector2): Raylib.Vector2 {
        return Jaylib.Vector2(
            coordinates.x() * maxX / width(),
            (coordinates.y()) * maxY / height()
        )
    }

    fun relativeToActualCoordinates(coordinates: Raylib.Vector2): Raylib.Vector2 {
        return Jaylib.Vector2(
            x() + coordinates.x(),
            y() + height() - coordinates.y()
        )
    }

    fun addDots(vararg coordinates: Raylib.Vector2) {
        for (c in coordinates) {
            dots.add(c)
        }
    }

    private fun drawAxis() {
        if (showX) {
            val xAxis = Line(
                0f, 0f,
                width(), 0f,
                axisLineWidth, axisColor
            )
            xAxis.parent = this
            xAxis.horizontalAlign = Align.Horizontal.Left
            xAxis.verticalAlign = Align.Vertical.Bottom
            xAxis.draw()
            xAxis.margin = axisLineWidth / 2
        }
        if (showY) {
            val yAxis = Line(
                0f, 0f,
                0f, height(),
                axisLineWidth, axisColor
            )
            yAxis.margin = axisLineWidth / 2
            yAxis.parent = this
            yAxis.horizontalAlign = Align.Horizontal.Left
            yAxis.verticalAlign = Align.Vertical.Bottom
            yAxis.draw()
        }
    }

    private fun drawDots() {
        var counter: Int = 0
        var currentLength: Float = 0f
        var skip: Boolean = false
        while (counter < dots.size - 1) {
            currentLength += Vector2Distance(dots[counter], dots[counter + 1])
            if (currentLength >= dotLength) {
                skip = !skip
                currentLength = 0f
            }
            if (!skip) {
                DrawLineEx(
                    relativeToActualCoordinates(dots[counter]),
                    relativeToActualCoordinates(dots[counter + 1]),
                    2f, BLACK
                )
            }
            ++counter
        }
    }

    @Override
    override fun draw() {
        super.draw()
        drawAxis()
        drawDots()
        drawMeasurement(if (dots.isEmpty()) Jaylib.Vector2(500f, 500f) else dots.last())
    }
}
