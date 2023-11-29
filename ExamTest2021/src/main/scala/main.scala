@main def main(): Unit =
  val c1 = Cell(1, 1)
  val c2 = Cell(2, 2)
  val c3 = Cell(1, 1)
  val c4 = Cell(-1, -1)
  val c5 = Cell(3, 3)

  val bStation1 = BaseStation("A", c1)
  val bStation2 = BaseStation("B", c2)
  val bStation3 = BaseStation("C", c3)
  val bStation4 = BaseStation("D", c4)

  val grid = Grid(21)
  grid.add(bStation1)
  grid.add(bStation2)
  grid.add(bStation3)
  grid.add(bStation4)
