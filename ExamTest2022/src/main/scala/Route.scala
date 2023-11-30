class Route(val id: Int, val busStopIds: List[Int], val busStops: BusStops):
  def length: Double =
    var sum = 0.0
    for i <- 0 until busStopIds.length - 1 do
      val thisBusStop = busStops.busStops.find(_.id == busStopIds(i)).get
      val thisPoint = thisBusStop.location
      val nextBusStop = busStops.busStops.find(_.id == busStopIds(i + 1)).get
      val nextPoint = nextBusStop.location
      sum += thisPoint.distanceTo(nextPoint)
    sum
