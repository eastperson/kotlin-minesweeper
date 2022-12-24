package minesweeper.domain.board

import minesweeper.domain.component.Component
import minesweeper.domain.component.Components
import minesweeper.domain.component.DefaultComponent
import minesweeper.domain.Mine
import minesweeper.domain.MineGenerator
import minesweeper.domain.coordinate.BoardCoordinateSystem
import minesweeper.domain.coordinate.MineCoordinateSystemDecorator

class MineBoard(
    height: Int,
    width: Int,
    mineCount: Int,
    mineGenerator: MineGenerator
) : Board, Components {
    private val coordinateSystem: MineCoordinateSystemDecorator
    private val mineList: List<Mine>
        get() = coordinateSystem.mineList

    override val height: Int
        get() = coordinateSystem.height
    override val width: Int
        get() = coordinateSystem.width

    init {
        val boardCoordinateSystem = BoardCoordinateSystem(height = height, width = width)
        coordinateSystem = MineCoordinateSystemDecorator(coordinateSystem = boardCoordinateSystem, mineGenerator, mineCount)
    }

    override fun components(): List<Component> {
        val minePositionList = mineList.map { it.position }

        return this.coordinateSystem.coordinate.map {
            val isMine = minePositionList.contains(it)
            DefaultComponent(position = it, isMine = isMine)
        }.sortedWith(
            compareBy({it.position.x}, {it.position.y})
        )
    }
}