data class Point3D(val x: Int, val y: Int, val z: Int) {
    operator fun plus(other: Point3D) = Point3D(this.x + other.x, y = this.y + other.y, z = this.z + other.z)

    companion object {
        fun of(input: String): Point3D {
            val (x, y, z) = input.split(',').map { it.trim().toInt() }
            return Point3D(x, y, z)
        }
    }
}