class Point(val x: Int, val y: Int):
  override def toString: String = s"($x, $y)"

  def distanceTo(point: Point): Double =
    Math.sqrt(Math.pow(point.x - this.x, 2) + Math.pow(point.y - this.y, 2))