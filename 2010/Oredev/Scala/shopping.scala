val data =
    <shopping>
        <item name="bread" quantity="3" price="2.50" />
        <item name="milk" quantity="2" price="3.50" />
    </shopping>

val lineItems = for (item <- data \ "item"; price = (item \ "@price").text.toDouble; quantity= (item\"@quantity").text.toInt) yield (price * quantity)
val sum = lineItems.sum
println("Total cost = " + sum)
