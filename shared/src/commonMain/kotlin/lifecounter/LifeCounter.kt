package lifecounter

class LifeCounter {
    private fun addLife(amount: Int): Int {
        // getCurrentLifeTotal
        val newLifeTotal: Int = 0
        return newLifeTotal + amount
    }

    fun addOneLife(): Int {
        return addLife(1)
    }

    fun addFiveLife(): Int {
        return addLife(5)
    }

    fun setLifeTo(amount: Int): Int {
        return amount;
    }
}