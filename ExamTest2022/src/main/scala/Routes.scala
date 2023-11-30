import scala.collection.mutable

class Routes:
  private var routes: List[Route] = Nil

  def addRoute(route: Route): Unit =
    routes = routes :+ route

  def mostCommonStopName: String =
    val count: mutable.Map[String, Int] = mutable.Map()
    for route <- routes do
      for busStop <- route.busStops.busStops do
        if count.contains(busStop.name) then
          count(busStop.name) = count(busStop.name) + 1
        else count += busStop.name -> 1
    count.toList.maxBy(_._2)._1

  def findNearestStop(location: Point, stopName: String): (String, Double) =
    var nearestStop: (String, Double) = ("", Double.MaxValue)
    for route <- routes do
      for busStop <- route.busStops.busStops do
        val busPoint = busStop.location
        val busStopDistance = location.distanceTo(busPoint)
        if busStopDistance < nearestStop._2 then nearestStop = (busStop.name, busStopDistance)
    nearestStop