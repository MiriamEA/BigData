/**
  * var and val
  */

var x = 234
//x = "abc" not possible because x is Int
x = 4

val y = "abc"
//y = "abcde" not possbile because val cannot be changed


/**
  * Expressions
  */

println{
  val y = "abc"
  "$y test"
}
println{
  val y = "abc"
  s"$y test"
}


/**
  * if-else
  */

if(3 > 9){
  println("crazy")
}else{
  println("3 is less than 9")
}

if(3 > 10) println("crazy") else println("3 is less than 10")


/**
  * Methods
  */

def meLike(thing: String = "chocolate") : String = {
  s"I like $thing!"
}
meLike()  //default value is used
meLike("ice cream")

def twoArgs(animal: String, color : String): String ={
  s"The $animal is $color."
}
twoArgs("green", "turtle")    //order of parameters matters
twoArgs(color = "green", animal = "turtle")  //named call, order not important

def meLike2(thing1: String = "cats", thing2 : String) : String = {
  s"I like $thing1 and $thing2."
}
meLike2(thing2 = "dogs")


/**
  * Classes
  */

class Carrot(val flavour: String)
var c = new Carrot("weird")
c.flavour //flavour can only be accessed if val or var

abstract class Mammal {
  val warmBlooded: Boolean = true
}
class Pig extends Mammal
var missPiggy = new Pig
missPiggy.warmBlooded

class Animal {
  def makesSound() : Unit = {
    println("Makes a sound")
  }
}
//overriding super method
class Dog extends Animal {
  @Override
  override def makesSound(): Unit = {
    println("Barks")
  }
}
var d = new Dog
d.makesSound()
var a = new Animal
a.makesSound()


/**
  * apply() function
  */

class Cloud {
  def apply() = {
    "floating"
  }
}
var cl = new Cloud
cl() //apply() is called


/**
  * Objects
  */
//objects cannot be instantiated, they are singletons

object Hello {
  def speak = "Hello!"
}
println(Hello.speak)

class Simpson {
  var color = "yellow"
}

object Bart extends Simpson{
  def speak = {
    s"I am $color"
  }
}
println(Bart.speak)
//objects can inherit from classes, but classes cannot inherit from objects



/**
  * Traits
  */
//traits cannot be instantiated directly

trait Cool {
  var speak = "I am cool"
}

object JoeCamel extends Cool
println(JoeCamel.speak)

//traits allow multiple inheritance
trait Speed {
  def run = "really fast"
}
trait Jump {
  def leap ="really high"
}

object Spiderman extends Speed with Jump {
  def describe = {
    s"I can run $run and jum $leap"
  }
}
println(Spiderman.describe)


/**
  * More functions
  */

def triple(x: Int): Int = x * 3
val tripleCopy: (Int) => Int = triple
println(tripleCopy(5))

def quadruple(x: Int): Int = x * 4
val quadrupleCopy = quadruple _ //wildcard necessary
println(quadrupleCopy(5))

var fullName = (first: String, last: String) => {
  s"$first $last"
}
println(fullName("bob", "loblaw"))
//variable fullName is assigned to an anonymous function (function literal)

def play(thing: String): String = {
  s"Let's play with $thing"
}
def funify(thing: String, f: String => String): String = {
  f(thing) + " and have fun"
}
println(funify("cats", play))
//funify takes string and function as input


/**
  * Partial application
  */

def adder(m: Int, n: Int) = m + n
val add5 = adder(5, _:Int)
println(add5(5))


/**
  * Pattern matching
  */

val times = 1
times match {
  case 1 => "one"
  case 2 => "two"
  case _ => "some other number"
}


/**
  * Case classes
  */
//used to conveniently store and match on contents of a class
//case classes automatically have equality and nice toString methods based on the constructor arguments

case class Calculator(brand: String, model: String)

val hp20b = new Calculator("HP", "20B")
val hp30b = Calculator("HP", "30B")
//case classes are immutable
//hp20b.brand = "AB"  --> error

def calcType(calc: Calculator) = calc match {
  case Calculator("HP", "20B") => "financial"
  case Calculator("HP", "48G") => "scientific"
  case Calculator("HP", "30B") => "business"
  case Calculator(ourBrand, ourModel) => "Calculator: %s %s is of unknown type".format(ourBrand, ourModel)
}
println(calcType(hp20b))

val hp = hp20b.copy(model ="AB")


/**
  * Implicit values
  */

object Omg {
  def speak(implicit emotion: String) = {
    println(s"Pikachu is $emotion")
  }
}

implicit val abcdefg = "confused"

Omg.speak //searches for an implicit String to use as input

def evil(implicit scared: String) = {
  println(s"Scala is $scared")
}

evil
evil("cool")


/**
  * Pure functions
  */
//output of pure function depends only on input,
//no side effects (I/O, changes a variable/object, ...

def add(x: Int, y :Int) :Int = {
  x + y
}   //pure

def changeStuff: Unit = {
  x = x + 10
}   //not pure, because x is changed


/**
  * Option, Some, and None
  */

//instead of returning one object when a function succeeds and null when it fails
//your function should instead return an instance of an Option,
// where the instance is either an instance of the Scala Some class or the Scala None class

val opt: Option[String] = Some("hi")
println(opt.get)

val bestNumber: Option[Int] = None
println(bestNumber.getOrElse(8))

def completeName(firstName: String, lastName: String): String = {
  Seq(firstName, lastName).flatMap(Option[String]).mkString(" ")
}

println(completeName(null, "barker"))
println(completeName("coolio", null))
println(completeName("cat", "dog"))


/**
  * Returning a function
  */

def addingX(x:Int) : Int => Int = {
  z => x + z
}

val adding4 = addingX(4)
adding4(4)
adding4(5)
val adding9 = addingX(9)
adding9(4)


/**
  * Casting
  */

val lo: Long = 987654321
val f: Float = lo   //long is automatically cast to float
println(lo % 2)
println(f % 2)  //precision loss


/**
  * Class with two constructors
  */
//second constructor has to have message

class Greeter(message: String, secondaryMessage: String) {
  def this(message: String) = this(message, "")

  def SayHi() = println(message + secondaryMessage)
}

val greeter = new Greeter("Hello world!")
greeter.SayHi()
val greeter1 = new Greeter ( "Hello", "world!")
greeter1.SayHi()


/**
  * Companion objects
  */
//Classes and Objects can have the same name. The object is called a Companion Object

class Bar(foo: String){
  def pri() = {
    println(foo)
  }
}

object Bar {
  def apply(foo: String) = new Bar(foo)
}

var l = Bar("hi")
l.pri()


/**
  * Lists
  */
//lists are immutable, unless specified otherwise

val ls = List(1,2,3,4,5,6,7,8)
ls.tail
ls.head
ls.headOption
val ls1 = List(1).tail
ls1.headOption
val ls2 = List()
//ls2.head throws exception because the list is empty

val days = "Monday" :: "Tuesday" :: "Wednesday" :: List()
days match{
  case firstDay :: otherDays => println("First day is " + firstDay)
  case List() => println("No days")
}

val numbers = List(1,2,3,4,5,4,3)
println(numbers.map((x:Int) => 2 *x))

var sum = 0;
numbers.foreach((x:Int) => sum = sum + x)
println(sum)

val singers = List("shakira", "nicky jam")
var result = singers.map( (s: String) => s"$s is cool" )
singers.size
singers.length

val list = List(3,6,36)
println(list.foldRight(36*36){(x:Int, y :Int) =>
{
  println(x + " " + y)
  y / x}})

println(list.foldLeft(1){(x:Int, y:Int) => {
  println(x + " " + y)
  y / x}})


/**
  * map, reduce, fold
  */

//find longest string in list
var names = List("tuco", "jesse", "hank", "gustavo")
var res = ""
names.foreach((s:String) => {
  if(s.length() > res.length()){
    res = s
  }
})
println(res)
names.reduceRight{(s:String, t:String )=>
  {
    if(s.length > t.length()){
      s
    }else{
      t
    }
  }
}

//fold requires start value
var letters = List("g", "o", "a", "t")
letters.foldLeft(""){(s,t) => s+t}
var huh = List("l", "o", "o", "c")
huh.foldRight("")((s,t) => t +s )
var countries = List("brazil", "argentina", "colombia")
countries.reduceLeft{(s, t) => s + ", " + t}

var me = List("i", "am", "quiet")
me.map(s => s.toUpperCase())

var whatever = List(1, 2, 3, 4, 5, 6, 7)
whatever.foldLeft(0){(n,m) => if(m % 2 == 0) n + m else n }


/**
  * Function composition
  */

def f(s: String) = "f(" + s + ")"
def g(s: String) = "g(" + s + ")"
val fComposeg = f _ compose g _       //like math f(g(x))
fComposeg("yay")
val fAndTheng = f _ andThen g _       //like reading the definition g(f(x))
fAndTheng("yay")


/**
  * Semicolon & formatting
  */
if(true){println("hi"); println("more")}
//if(true){println("Hi") println"more"} doesn't work
if(true)
  println("HI")
  println("More")

