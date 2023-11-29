case class Cell(x: Int, y: Int):
  override def toString: String = s"($x, $y)"

  def neighbours: List[Cell] =
    var neighbourList: List[Cell] = Nil
    for y <- this.y - 1 to this.y + 1 do
      for x <- this.x - 1 to this.x + 1 do
        if Cell(x, y) != this then
          neighbourList = neighbourList :+ Cell(x, y)
    neighbourList