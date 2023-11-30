import scala.collection.mutable.ArrayBuffer

class BusStops:
  val busStops = ArrayBuffer[BusStop]()

  def addStop(busStop: BusStop): Unit =
    busStops.addOne(busStop)

  def sortByName(): Unit =
    busStops.sortInPlaceBy(_.name)

//  var busStops: List[BusStop] = Nil
//
//  def addStop(busStop: BusStop): Unit =
//    busStops = busStops :+ busStop
//
//  def sortByName(): Unit =
//    busStops = busStops.sortBy(_.name)

  override def toString: String =
    busStops.map((bs: BusStop) => s"[$bs]").mkString(", ")