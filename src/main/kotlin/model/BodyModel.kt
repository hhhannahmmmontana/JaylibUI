package model

class BodyModel(
    private var pos: Coordinates,
    private val mass: Float,
    private var velocity: Float
) {
    companion object {
        @JvmStatic
        fun collide(a: BodyModel, b: BodyModel) {
            val temp = a
            a.calculateVelocity(b)
            b.calculateVelocity(temp)
        }
        @JvmStatic
        fun doCollide(a: BodyModel, b: BodyModel): Boolean {
            return (
                (a.getMaxX() >= b.getMinX() && a.getMinX() <= b.getMaxX()) ||
                (b.getMaxX() >= a.getMinX() && b.getMinX() <= a.getMaxX())
            )
        }
    }

    val radius = 1f

    private fun calculateVelocity(other: BodyModel) {
        this.velocity = (this.mass - other.mass) * this.velocity + 2 * other.mass * other.velocity
        this.velocity /= this.mass + other.mass
    }

    fun getMinX(): Float {
        return pos.x - radius
    }
    fun getMaxX(): Float {
        return pos.x + radius
    }

    fun getCenterPos() = pos

    fun nextFrame(fps: Int) {
        pos = Coordinates(pos.x + velocity / fps, pos.y)
    }
}
