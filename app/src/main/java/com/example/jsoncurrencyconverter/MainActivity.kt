package com.example.jsoncurrencyconverter

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.jsoncurrencyconverter.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currenciesValues: APIData? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currencies = arrayOf("inr", "usd", "aud", "sar", "cny", "jpy")

        var currencySelected = 0

        if (binding.spinner != null) {
            val arrAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, currencies)
            binding.spinner.adapter = arrAdapter
            binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    currencySelected = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        }
        binding.conBtn.setOnClickListener {
            val userInput = binding.userInputET.text.toString()
            val currValue: Double = userInput.toDouble()

            getCurrency(result = {
                currenciesValues = it

                when (currencySelected) {
                    0 -> showResult(
                        calculateResult(
                            currenciesValues?.eur?.inr?.toDouble(),
                            currValue
                        )
                    )
                    1 -> showResult(
                        calculateResult(
                            currenciesValues?.eur?.usd?.toDouble(),
                            currValue
                        )
                    )
                    2 -> showResult(
                        calculateResult(
                            currenciesValues?.eur?.sar?.toDouble(),
                            currValue
                        )
                    )
                    3 -> showResult(
                        calculateResult(
                            currenciesValues?.eur?.jpy?.toDouble(),
                            currValue
                        )
                    )
                    4 -> showResult(
                        calculateResult(
                            currenciesValues?.eur?.cny?.toDouble(),
                            currValue
                        )
                    )
                    5 -> showResult(
                        calculateResult(
                            currenciesValues?.eur?.aud?.toDouble(),
                            currValue
                        )
                    )
                }
            })
        }
    }

    private fun showResult(value: Double) {
        binding.ResultTV.text = "Result: $value"
    }

    private fun calculateResult(value: Double?, selected: Double): Double {
        var theResult = 0.0
        if(value != null) {
            theResult = (value * selected)
        }
    return theResult
    }


   private fun getCurrency(result: (APIData?) -> Unit) {
       val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

       if(apiInterface != null) {
           apiInterface.getCurrenciesValues()?.enqueue(object: Callback<APIData> {

               override fun onResponse(
                   call: Call<APIData>,
                   response: Response<APIData>
               ) {
                   result(response.body())
               }

               override fun onFailure(call: Call<APIData>, t: Throwable) {
                   result(null)
                   Toast.makeText(applicationContext, "" + t.message, Toast.LENGTH_LONG).show()

               }

           })
       }
   }
}