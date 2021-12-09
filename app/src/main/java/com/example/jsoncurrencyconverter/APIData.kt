package com.example.jsoncurrencyconverter

import com.google.gson.annotations.SerializedName

class APIData {

   // @SerializedName("date")
    //var date: String? = null

    @SerializedName("eur")
    var eur: Eur? = null

    class Eur {                     // this class is inside the APIData class
        @SerializedName("inr")
        var inr: String? = null

        @SerializedName("usd")
        var usd: String? = null

        @SerializedName("sar")
        var sar: String? = null

        @SerializedName("jpy")
        var jpy: String? = null

        @SerializedName("cny")
        var cny: String? = null

        @SerializedName("aud")
        var aud: String? = null
    }
}