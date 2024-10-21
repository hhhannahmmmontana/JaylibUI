import com.raylib.Jaylib
import com.raylib.Jaylib.RAYWHITE
import com.raylib.Raylib.*
import com.volodya.jlui.Align
import com.volodya.jlui.chart.Chart
import com.volodya.jlui.rectangles.AlignableRectangle
import com.volodya.jlui.ui.Container

fun main() {
    SetConfigFlags(FLAG_WINDOW_RESIZABLE)
    InitWindow(1280, 720, "Demo")
    SetTargetFPS(60)
    val container = Container(
        GetScreenWidth().toFloat(), GetScreenHeight().toFloat()
    )
    val btn = Chart(AlignableRectangle(0f, 0f, 500f, 500f), maxX = 100f, maxY = 100f)
    container.addChild(btn)
    btn.horizontalAlign = Align.Horizontal.Center
    for (i in 0..500) {
        btn.addDots(Jaylib.Vector2(i.toFloat(), i.toFloat()))
    }
    while (!WindowShouldClose()) {
        BeginDrawing()
        ClearBackground(RAYWHITE)
        container.resize(
            GetScreenWidth().toFloat(), GetScreenHeight().toFloat()
        )
        container.draw()
        EndDrawing()
    }
    CloseWindow()
}
