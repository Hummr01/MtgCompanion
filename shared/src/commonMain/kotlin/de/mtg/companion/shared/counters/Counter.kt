package de.mtg.companion.shared.counters

interface Counter {
    //Return amount of life
    fun getAmount(amount: Int): Int
    fun addAmount(amount: Int): Int
    fun removeAmount(amount: Int): Int
    fun addOne(): Int
    fun removeOne(): Int
    fun addFive(): Int
    fun removeFive(): Int
    fun setAmountTo(amount: Int): Int
}