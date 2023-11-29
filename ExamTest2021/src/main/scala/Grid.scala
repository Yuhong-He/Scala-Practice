class Grid(val dimension: Int, var bStations: List[BaseStation] = Nil):
  if (dimension < 1) {
    throw new IllegalArgumentException("Dimension should be at least 1")
  }

  def add(bStation: BaseStation): Unit =
    if !bStations.exists(_.cell == bStation.cell) && checkInGrid(bStation.cell) then bStations = bStations :+ bStation

  private def checkInGrid(cell: Cell): Boolean =
    cell.x < dimension && cell.y < dimension && cell.x >= 0 && cell.y >= 0

  def coverage(cell: Cell): Double =
    val closestDistance = bStations.map(bStation => distance(cell, bStation.cell)).minByOption(identity)
    closestDistance.map(d => 1 / (1 + d)).getOrElse(0.0)

//  def coverage2(cell: Cell): Double =
//    1 / (1 + getClosestDistance(cell))
//
//  private def getClosestDistance(cell: Cell): Double =
//    var minDistance = Double.MaxValue
//    for(bStation <- bStations) do
//      val currDistance = distance(cell, bStation.cell)
//      minDistance = Math.min(minDistance, currDistance)
//    minDistance

  private def distance(c1: Cell, c2: Cell): Double =
    Math.sqrt((c1.x - c2.x) * (c1.x - c2.x) + (c1.y - c2.y) * (c1.y - c2.y))

  def allCoverage: List[Double] =
    var all: List[Double] = Nil
    for y <- 0 until dimension do
      for x <- 0 until dimension do
        all = all :+ coverage(Cell(x, y))
    all

  def gridQuality: Double =
    this.allCoverage.sum / (dimension * dimension)

  def neighbours(cell: Cell): List[Cell] =
    var neighbourList: List[Cell] = Nil
    for y <- cell.y - 1 to cell.y + 1 do
      for x <- cell.x - 1 to cell.x + 1 do
        if checkInGrid(Cell(x, y)) && cell != Cell(x, y) then
          neighbourList = neighbourList :+ Cell(x, y)
    neighbourList

  def bestNeighbour(bStation: BaseStation): (Cell, Double) =
    var best: (Cell, Double) = (bStation.cell, this.gridQuality)
    for cell <- neighbours(bStation.cell) do
      if !bStations.exists(_.cell == cell) then
        var newBStations: List[BaseStation] = Nil
        newBStations = newBStations :+ BaseStation(bStation.name, cell)
        for b <- bStations do
          if b.cell != bStation.cell then newBStations = newBStations :+ b
        val newGrid = Grid(this.dimension, newBStations)
        val currQuality = newGrid.gridQuality
        if currQuality > best._2 then best = (cell, currQuality)
    best

  override def toString: String =
    val str: StringBuilder = StringBuilder("")
    var index = 0
    for (bStation <- bStations) do
      str ++= bStation.toString
      if index != bStations.length - 1 then str ++= ", "
      index += 1

    str.toString
