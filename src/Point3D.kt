class Point3D(x: Int, y: Int, z: Int) {
    companion object {
        fun of(input: String): Point3D {
            val (x, y, z) = input.split(',').map { it.trim().toInt() }
            return Point3D(x, y, z)
        }
    }
}