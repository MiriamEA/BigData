/**
 * Instruction:
 *
 * Use scala doc to understand each functional combinator
 * https://www.scala-lang.org/api/2.11.8/#scala.collection.immutable.List
 *
 * common functional combinator
 *   map
 *   flatten
 *   flatMap (map().flatten)
 *   filter
 *   groupBy (generates a map)
 *   partition (one list into two)
 *   foldLeft
 *   reduce/reduceLeft
 *   forEach
 *   zip
 */

/**
 * Count number of elements
 * Get the first element
 * Get the last element
 * Get the first 5 elements
 * Get the last 5 elements
 *
 * hint: use the following methods
 * head
 * last
 * size
 * take
 * tails
 */
val ls = List.range(0,10)
println(ls.size)
ls.head
ls.last
ls.take(5)
ls.takeRight(5)


/**
 * Double each number from the numList and return a flatten list
 * e.g.res4: List[Int] = List(2, 3, 4)
 *
 * Compare flatMap VS ls.map().flatten
 */
val numList = List(List(1,2), List(3));
numList.flatten.map(x => x+1)
numList.map(itr => itr.map(x => x+1)).flatten
numList.flatMap(itr => itr.map(x => x+1))


/**
 * Sum List.range(1,11) in three ways
 * hint: sum, reduce, foldLeft
 *
 * Compare reduce and foldLeft
 * https://stackoverflow.com/questions/7764197/difference-between-foldleft-and-reduceleft-in-scala
 */
val numls = List.range(1,12)
numls.sum
numls.reduce(_+_)
numls.fold(0)((a,b) => a+b)
//reduce returns same type as in List, fold can return any type

/**
 * Practice Map and Optional
 *
 * Map question1:
 *
 * Compare get vs getOrElse (Scala Optional)
 * countryMap.get("Amy");
 * countryMap.getOrElse("Frank", "n/a");
 */
val countryMap = Map("Amy" -> "Canada", "Sam" -> "US", "Bob" -> "Canada")
countryMap.get("Amy")
countryMap.get("edward")
countryMap.getOrElse("edward", "n/a")
//get returns an option (some(value) or none if key doesn't exist)
//getOrElse returns the value or the specified default value if key doesn't exist

/**
 * Map question2:
 *
 * create a list of (name, country) tuples using `countryMap` and `names`
 * e.g. res2: List[(String, String)] = List((Amy,Canada), (Sam,US), (Eric,n/a), (Amy,Canada))
 */
val names = List("Amy", "Sam", "Eric", "Amy")
val nameAndCountry = names.map(s => (s, countryMap.getOrElse(s, "n/a")))

/**
 * Map question3:
 *
 * count number of people by country. Use `n/a` if the name is not in the countryMap  using `countryMap` and `names`
 * e.g. res0: scala.collection.immutable.Map[String,Int] = Map(Canada -> 2, n/a -> 1, US -> 1)
 * hint: map(get_value_from_map) ; groupBy country; map to (country,count)
 */
val countCountry = names.map(s => countryMap.getOrElse(s, "n/a")).groupBy(s => s).map(x => (x._1, x._2.length))


/**
 * number each name in the list from 1 to n
 * e.g. res3: List[(Int, String)] = List((1,Amy), (2,Bob), (3,Chris))
 */
val names2 = List("Amy", "Bob", "Chris", "Dann")
names2.map(s => (names2.indexOf(s) + 1 ,s))
List.range(1, names2.length + 1).zip(names2)


/**
 * SQL questions1:
 *
 * read file lines into a list
 * lines: List[String] = List(id,name,city, 1,amy,toronto, 2,bob,calgary, 3,chris,toronto, 4,dann,montreal)
 */
import scala.io.Source
val fileLines = Source.fromFile("/home/centos/dev/jrvs/bootcamp/bigdata/scala/src/main/resources/employees.csv")
  .getLines().toList

/**
 * SQL questions2:
 *
 * Convert lines to a list of employees
 * e.g. employees: List[Employee] = List(Employee(1,amy,toronto), Employee(2,bob,calgary), Employee(3,chris,toronto), Employee(4,dann,montreal))
 */
case class myEmployee(id:Int, name:String, city:String, age:Int)
val linesNoHeader = fileLines.drop(1)
val listOfEmployees = linesNoHeader.map(line =>{
  val tokens = line.split(",")
  new myEmployee(tokens(0).trim.toInt, tokens(1).trim, tokens(2).trim, tokens(3).trim.toInt)
})

/**
 * SQL questions3:
 *
 * Implement the following SQL logic using functional programming
 * SELECT uppercase(city)
 * FROM employees
 *
 * result:
 * upperCity: List[Employee] = List(Employee(1,amy,TORONTO,20), Employee(2,bob,CALGARY,19), Employee(3,chris,TORONTO,20), Employee(4,dann,MONTREAL,21), Employee(5,eric,TORONTO,22))
 */
val myUpperCity = listOfEmployees.map(e => new myEmployee(e.id, e.name, e.city.toUpperCase, e.age))



/**
 * SQL questions4:
 *
 * Implement the following SQL logic using functional programming
 * SELECT uppercase(city)
 * FROM employees
 * WHERE city = 'toronto'
 *
 * result:
 * res5: List[Employee] = List(Employee(1,amy,TORONTO,20), Employee(3,chris,TORONTO,20), Employee(5,eric,TORONTO,22))
 */
val torontoEmployees = myUpperCity.filter(e => e.city == "TORONTO")


/**
 * SQL questions5:
 *
 * Implement the following SQL logic using functional programming
 *
 * SELECT uppercase(city), count(*)
 * FROM employees
 * GROUP BY city
 *
 * result:
 * cityNum: scala.collection.immutable.Map[String,Int] = Map(CALGARY -> 1, TORONTO -> 3, MONTREAL -> 1)
 */
val myCityNum = myUpperCity.groupBy(e => e.city).map(t => (t._1, t._2.size))


/**
 * SQL questions6:
 *
 * Implement the following SQL logic using functional programming
 *
 * SELECT uppercase(city), count(*)
 * FROM employees
 * GROUP BY city,age
 *
 * result:
 * res6: scala.collection.immutable.Map[(String, Int),Int] = Map((MONTREAL,21) -> 1, (CALGARY,19) -> 1, (TORONTO,20) -> 2, (TORONTO,22) -> 1)
 */
val cityAgeCount = myUpperCity.groupBy(e => (e.city, e.age)).map(t => (t._1, t._2.size))









































//Solutions
names.map(name => (name, countryMap.getOrElse(name, "n/a")))
names.map(countryMap.getOrElse(_,"n/a")).groupBy(x=>x).map({case(c,n) => (c,n.length)})

//Solution
List.range(1,names2.length).zip(names2)

//Solution
numList.flatMap(n => n.map(_+1))

//read file lines into a list
//lines: List[String] = List(id,name,city, 1,amy,toronto, 2,bob,calgary, 3,chris,toronto, 4,dann,montreal)
import scala.io.Source
val fileName = "/Users/ewang/dev/jrvs/bootcamp/bigdata/scala/src/main/resources/employees.csv"
val bs =  Source.fromFile(fileName);
val lines = bs.getLines.toList
bs.close()

//Convert lines to a list of employees
//e.g. employees: List[Employee] = List(Employee(1,amy,toronto), Employee(2,bob,calgary), Employee(3,chris,toronto), Employee(4,dann,montreal))
case class Employee(id:Int, name:String, city:String, age:Int)
val mapToEmp = (line:String) => {
  val tokens = line.split(",")
  Employee(tokens(0).trim.toInt, tokens(1).trim, tokens(2).trim, tokens(3).trim.toInt )
}
val noHeader = lines.drop(1)
val employees = noHeader.map(mapToEmp)

/**
 * Implement the following SQL logic using functional programming
 * SELECT uppercase(city)
 * FROM employees
 *
 * result:
 * upperCity: List[Employee] = List(Employee(1,amy,TORONTO,20), Employee(2,bob,CALGARY,19), Employee(3,chris,TORONTO,20), Employee(4,dann,MONTREAL,21), Employee(5,eric,TORONTO,22))
 */
val upperCity = employees.map(emp => Employee(emp.id,emp.name,emp.city.toUpperCase(),emp.age))

/**
 * Implement the following SQL logic using functional programming
 * SELECT uppercase(city)
 * FROM employees
 * WHERE city = 'toronto'
 *
 * result:
 * res5: List[Employee] = List(Employee(1,amy,TORONTO,20), Employee(3,chris,TORONTO,20), Employee(5,eric,TORONTO,22))
 */
upperCity.filter(_.city.equals("TORONTO"))


/**
 * Implement the following SQL logic using functional programming
 *
 * SELECT uppercase(city), count(*)
 * FROM employees
 * GROUP BY city
 *
 * result:
 * cityNum: scala.collection.immutable.Map[String,Int] = Map(CALGARY -> 1, TORONTO -> 3, MONTREAL -> 1)
 */
val cityNum = upperCity
  .groupBy(_.city)
  .map({case(city, ls) => (city, ls.length)})

/**
 * Implement the following SQL logic using functional programming
 *
 * SELECT uppercase(city), count(*)
 * FROM employees
 * GROUP BY city,age
 *
 * result:
 * res6: scala.collection.immutable.Map[(String, Int),Int] = Map((MONTREAL,21) -> 1, (CALGARY,19) -> 1, (TORONTO,20) -> 2, (TORONTO,22) -> 1)
 */

upperCity.groupBy(emp => (emp.city, emp.age))
  .map({case(city, ls) => (city, ls.length)})
