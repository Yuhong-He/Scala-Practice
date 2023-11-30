class BusStop(val id: Int, val name: String, val location: Point):
  override def toString: String = s"$id, $name, $location"