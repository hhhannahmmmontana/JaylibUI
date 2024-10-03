import com.raylib.Jaylib.RAYWHITE
import com.raylib.Raylib.*

fun main(args: Array<String>) {
    InitWindow(1280, 720, "Demo")
    SetTargetFPS(60)
    while (!WindowShouldClose()) {
        BeginDrawing()
        ClearBackground(RAYWHITE)
        EndDrawing()
    }
    CloseWindow()
}
