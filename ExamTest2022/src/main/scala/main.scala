@main def main(): Unit =
  val busStop1 = BusStop(2765, "Ranelagh", Point(3, 5))
  println(busStop1)

  val busStop2 = BusStop(7532, "Donnybrook", Point(3, 5))
  val busStop3 = BusStop(2985, "Merrion", Point(3, 5))
  val busStops = BusStops()
  busStops.addStop(busStop1)
  busStops.addStop(busStop2)
  busStops.addStop(busStop3)
  println(busStops)
