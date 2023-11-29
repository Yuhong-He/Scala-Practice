
import org.scalatest.funsuite.AnyFunSuite
import scala.math.*

// These tests were given to the students

class MelBasicSuite extends AnyFunSuite {

  /********************** Part1 Tests **********************/

  test("Part1: toString correct for BusStop") {
    val busStop = BusStop(2765, "Ranelagh", Point(3, 5))
    assert(busStop.toString === "2765, Ranelagh, (3, 5)")
  }

  test("Part1: toString correct for BusStops") {
    val busStop1 = BusStop(2765, "Ranelagh", Point(3, 5))
    val busStop2 = BusStop(7532, "Donnybrook", Point(3, 5))
    val busStop3 = BusStop(2985, "Merrion", Point(3, 5))
    val busStops = BusStops()
    busStops.addStop(busStop1)
    busStops.addStop(busStop2)
    busStops.addStop(busStop3)
    val expected = "[2765, Ranelagh, (3, 5)], [7532, Donnybrook, (3, 5)], [2985, Merrion, (3, 5)]"
    assert(busStops.toString === expected)
  }

  test("Part1: sortByName correct for BusStops") {
    val busStop1 = BusStop(2765, "Ranelagh", Point(3, 5))
    val busStop2 = BusStop(7532, "Donnybrook", Point(3, 5))
    val busStop3 = BusStop(2985, "Merrion", Point(3, 5))
    val busStops = BusStops()
    busStops.addStop(busStop1)
    busStops.addStop(busStop2)
    busStops.addStop(busStop3)
    busStops.sortByName()
    val expected = "[7532, Donnybrook, (3, 5)], [2985, Merrion, (3, 5)], [2765, Ranelagh, (3, 5)]"
    assert(busStops.toString === expected)
  }

  /********************** Part2 Tests **********************/

  test("Part2: distance between points correct") {
    val point1 = Point(0, 0)
    val point2 = Point(1, 1)
    val point3 = Point(5, 5)
    assert(point1.distanceTo(point2) === sqrt(2))
    assert(point2.distanceTo(point3) === sqrt(32))
  }

  test("Part2: route length correct") {
    val busStop1 = BusStop(2765, "Ranelagh", Point(0, 0))
    val busStop2 = BusStop(7532, "Donnybrook", Point(1, 1))
    val busStop3 = BusStop(2985, "Merrion", Point(2, 2))
    val busStops = BusStops()
    busStops.addStop(busStop1)
    busStops.addStop(busStop2)
    busStops.addStop(busStop3)
    val route = Route(1, List(2765, 7532, 2985), busStops)
    assert(route.length === sqrt(2) * 2)
  }

  /********************** Part3 Tests **********************/

  test("Part3: mostCommonStopName correct") {
    val busStop1 = BusStop(2765, "Ranelagh", Point(0, 0))
    val busStop2 = BusStop(7532, "Donnybrook", Point(7, 1))
    val busStop3 = BusStop(2985, "Merrion", Point(2, 2))
    val busStop4 = BusStop(2987, "Clonskeagh", Point(3, 4))
    val busStop5 = BusStop(9583, "Milltown", Point(5, 5))
    val busStops1 = BusStops()
    busStops1.addStop(busStop1)
    busStops1.addStop(busStop2)
    busStops1.addStop(busStop3)
    busStops1.addStop(busStop4)
    val route1 = Route(1, List(2765, 7532, 2985, 2987), busStops1)

    val busStop6 = BusStop(5826, "Rathmines", Point(0, 0))
    val busStop7 = BusStop(1285, "Dundrum", Point(1, 1))
    val busStop8 = BusStop(9933, "Goatstown", Point(2, 2))
    val busStop9 = BusStop(7532, "Donnybrook", Point(7, 1))
    val busStops2 = BusStops()
    busStops2.addStop(busStop6)
    busStops2.addStop(busStop7)
    busStops2.addStop(busStop8)
    busStops2.addStop(busStop9)
    val route2 = Route(2, List(5826, 7532, 9933, 1285), busStops2)

    val routes = Routes()
    routes.addRoute(route1)
    routes.addRoute(route2)
    assert(routes.mostCommonStopName === "Donnybrook")
  }

  /********************** Part4 Tests **********************/

  test("Part4: findNearestStop correct") {
    val busStop1 = BusStop(2765, "Ranelagh", Point(6, 4))
    val busStop2 = BusStop(7532, "Donnybrook", Point(5, 5))
    val busStop3 = BusStop(2985, "Merrion", Point(4, 6))
    val busStop4 = BusStop(2987, "Clonskeagh", Point(3, 7))
    val busStop5 = BusStop(9583, "Milltown", Point(99, 99)) //not on route
    val busStops1 = BusStops()
    busStops1.addStop(busStop1)
    busStops1.addStop(busStop2)
    busStops1.addStop(busStop3)
    busStops1.addStop(busStop4)
    val route1 = Route(1, List(2765, 7532, 2985, 2987), busStops1)

    val busStop6 = BusStop(5826, "Rathmines", Point(2, 2))
    val busStop7 = BusStop(1285, "Dundrum", Point(3, 3))
    val busStop8 = BusStop(9933, "Goatstown", Point(4, 4))
    val busStop9 = BusStop(7532, "Donnybrook", Point(5, 5))
    val busStops2 = BusStops()
    busStops2.addStop(busStop6)
    busStops2.addStop(busStop7)
    busStops2.addStop(busStop8)
    busStops2.addStop(busStop9)
    val route2 = Route(2, List(5826, 7532, 9933, 1285), busStops2)

    val routes = Routes()
    routes.addRoute(route1)
    routes.addRoute(route2)

    val start1 = Point(6, 6)
    val destinationStop1 = "Donnybrook"
    val (stopName1, distance1) = routes.findNearestStop(start1, destinationStop1)
    assert(stopName1 === "Donnybrook")
    assert(distance1 === sqrt(2))

    val start2 = Point(6, 8)
    val destinationStop2 = "Clonskeagh"
    val (stopName2, distance2) = routes.findNearestStop(start2, destinationStop2)
    assert(stopName2 === "Merrion")
    assert(distance2 === sqrt(2)*2)

    val start3 = Point(1, 7)
    val destinationStop3 = "Donnybrook"
    val (stopName3, distance3) = routes.findNearestStop(start3, destinationStop3)
    assert(stopName3 === "Clonskeagh")
    assert(distance3 === 2.0)

    val start4 = Point(2, 1)
    val destinationStop4 = "Goatstown"
    val (stopName4, distance4) = routes.findNearestStop(start4, destinationStop4)
    assert(stopName4 === "Rathmines")
    assert(distance4 === 1.0)
  }
}
