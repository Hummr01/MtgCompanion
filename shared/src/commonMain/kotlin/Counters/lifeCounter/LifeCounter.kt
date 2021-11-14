package Counters.lifeCounter

class LifeCounter(private val currentLife: Int) {

    private fun addLife(amount: Int): Int {
        return currentLife + amount
    }

    private fun removeLife(amount: Int): Int {
        return currentLife - amount
    }

    fun addOneLife(): Int {
        return addLife(1)
    }

    fun removeOneLife(): Int {
        return removeLife(1)
    }

    fun addFiveLife(): Int {
        return addLife(5)
    }

    fun removeFiveLife(): Int {
        return removeLife(5)
    }

    fun setLifeTo(amount: Int): Int {
        return amount;
    }
}