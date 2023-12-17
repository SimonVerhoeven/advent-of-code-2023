fun main() {
    data class Beam (val position: Coordinate, val dir: Direction)

    fun moveBeam (grid: Array<CharArray>, beam: Beam): List<Beam> {
        val next = mutableListOf<Beam>()
        val current = grid[beam.position.y][beam.position.x]
        val vertical = beam.dir == Direction.UP || beam.dir == Direction.DOWN
        val horizontal = beam.dir == Direction.LEFT || beam.dir == Direction.RIGHT

        if (current == '.' || (vertical && current == '|') || (horizontal && current == '-')) {
            val nextBeam = when (beam.dir) {
                Direction.UP -> Beam(Coordinate(beam.position.x, beam.position.y - 1), Direction.UP)
                Direction.DOWN -> Beam(Coordinate(beam.position.x, beam.position.y + 1), Direction.DOWN)
                Direction.LEFT -> Beam(Coordinate(beam.position.x - 1, beam.position.y), Direction.LEFT)
                Direction.RIGHT -> Beam(Coordinate(beam.position.x + 1, beam.position.y), Direction.RIGHT)
            }

            if (nextBeam.position.x in grid[0].indices && nextBeam.position.y in grid.indices) {
                next.add(nextBeam)
            }
        }

        if (current == '|' && horizontal) {
            var nextPosition = Pair(beam.position.x, beam.position.y - 1)
            if (nextPosition.first in grid[0].indices && nextPosition.second in grid.indices) {
                next.add(Beam(Coordinate(nextPosition.first, nextPosition.second), Direction.UP))
            }

            nextPosition = Pair(beam.position.x, beam.position.y + 1)
            if (nextPosition.first in grid[0].indices && nextPosition.second in grid.indices) {
                next.add(Beam(Coordinate(nextPosition.first, y = nextPosition.second), Direction.DOWN))
            }
        }

        if (current == '-' && vertical) {
            var nextPosition = Pair(beam.position.x - 1, beam.position.y)
            if (nextPosition.first in grid[0].indices && nextPosition.second in grid.indices) {
                next.add(Beam(Coordinate(nextPosition.first, nextPosition.second), Direction.LEFT))
            }

            nextPosition = Pair(beam.position.x + 1, beam.position.y)
            if (nextPosition.first in grid[0].indices && nextPosition.second in grid.indices) {
                next.add(Beam(Coordinate(nextPosition.first, y = nextPosition.second), Direction.RIGHT))
            }
        }

        if (current == '\\') {
            val nextBeam = when (beam.dir) {
                Direction.UP -> Beam(Coordinate(beam.position.x - 1, beam.position.y), Direction.LEFT)
                Direction.DOWN -> Beam(Coordinate(beam.position.x + 1, beam.position.y), Direction.RIGHT)
                Direction.LEFT -> Beam(Coordinate(beam.position.x, beam.position.y - 1), Direction.UP)
                Direction.RIGHT -> Beam(Coordinate(beam.position.x, beam.position.y + 1), Direction.DOWN)
            }

            if (nextBeam.position.x in grid[0].indices && nextBeam.position.y in grid.indices) {
                next.add(nextBeam)
            }
        }

        if (current == '/') {
            val nextBeam = when (beam.dir) {
                Direction.UP -> Beam(Coordinate(beam.position.x + 1, beam.position.y), Direction.RIGHT)
                Direction.DOWN -> Beam(Coordinate(beam.position.x - 1, beam.position.y), Direction.LEFT)
                Direction.LEFT -> Beam(Coordinate(beam.position.x, beam.position.y + 1), Direction.DOWN)
                Direction.RIGHT -> Beam(Coordinate(beam.position.x, beam.position.y - 1), Direction.UP)
            }

            if (nextBeam.position.x in grid[0].indices && nextBeam.position.y in grid.indices) {
                next.add(nextBeam)
            }
        }

        return next
    }

    fun fireBeams(grid: Array<CharArray>, startingBeam: Beam): Long {
        val activeBeams = mutableListOf(startingBeam)
        val visited = mutableListOf<Beam>()

        while (activeBeams.isNotEmpty()) {
            val current = activeBeams.removeFirst()
            visited.add(current)
            val nextBeams = moveBeam(grid, current)
            for (next in nextBeams) {
                if (!visited.contains(next) && !activeBeams.contains(next)) {
                    activeBeams.add(next)
                }
            }
        }

        return visited.map { it.position }.toSet().size.toLong()
    }

    fun part1(grid: Array<CharArray>): Long {
        return fireBeams(grid, Beam(Coordinate(0, 0), Direction.RIGHT))
    }

    fun part2(grid: Array<CharArray>): Long {
        val startingBeams = mutableListOf<Beam>()
        startingBeams += grid.indices.mapIndexed { _, y -> listOf(Beam(Coordinate(0, y), Direction.RIGHT), Beam(Coordinate(grid[0].size - 1, y), Direction.LEFT))}.flatten()
        startingBeams += grid[0].indices.mapIndexed { _, x -> listOf(Beam(Coordinate(x, 0), Direction.DOWN), Beam(Coordinate(x, grid.size - 1), Direction.UP))}.flatten()
        return startingBeams.map { fireBeams(grid, it)}.max()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day16_test")
    val testGrid = testInput.toCharMatrix()
    check(part1(testGrid) == 46L)
    check(part2(testGrid) == 51L)

    val input = readInput("Day16")
    val grid = input.toCharMatrix()

    part1(grid).println()
    part2(grid).println()
}
