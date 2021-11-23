package com.gardner.honorsmobileapps.JustJava_ViewBinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.gardner.honorsmobileapps.JustJava_ViewBinding.databinding.ActivityMainBinding

var quantity =  1
lateinit var amount: TextView

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)
        amount = findViewById(R.id.num_products)

        binding.calculate.setOnClickListener {view -> submitOrder()}

        binding.minus.setOnClickListener { view -> updateQuantity(-1) }

        binding.plus.setOnClickListener { view -> updateQuantity(1) }


    }

    fun updateQuantity(num: Int) {
     //   val textSummary: TextView = findViewById(R.id.price)

        if (quantity + num < 1) {
            Toast.makeText(this, "You can't order less than 1 cup.", Toast.LENGTH_LONG).show()
        } else if (quantity + num > 10) {
            Toast.makeText(this, "You can't order more than 10 cups.", Toast.LENGTH_LONG).show()
        } else {
            quantity = quantity + num
        }
        amount.text = quantity.toString()
    }
    fun submitOrder(){
        val whip: CheckBox = binding.check1
        val chocolate: CheckBox = binding.check2
        val order: TextView = binding.price
        var receipt: String = "Thanks ${binding.edit1.text}! \n${quantity} coffees"
        if(quantity == 1){
            receipt = "Thanks ${binding.edit1.text}! \n${quantity} coffee"
        }
        var totalPrice = quantity*5
        if(whip.isChecked()){
            receipt = receipt + "\n+ Whipped Cream"
            totalPrice =totalPrice + quantity
        }
        if(chocolate.isChecked()){
            receipt = receipt + "\n+ Chocolate"
            totalPrice =totalPrice + quantity*2
        }
        receipt = receipt + "\nTotal: $${totalPrice}.00"
        order.text = receipt
    }
}