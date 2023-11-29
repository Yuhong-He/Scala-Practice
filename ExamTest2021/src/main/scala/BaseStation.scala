class BaseStation(val name: String, val cell: Cell):
  override def toString: String = s"$name$cell"