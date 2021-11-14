package counters.lifeCounter

import counters.Counter

class LifeCounter(private val currentLife: Int): Counter {
    override fun addAmount(amount: Int): Int {
        return currentLife + amount
    }

    override fun removeAmount(amount: Int): Int {
        return currentLife - amount
    }

    override fun addOne(): Int {
        return addAmount(1)
    }

    override fun removeOne(): Int {
        return removeAmount(1)
    }

    override fun addFive(): Int {
        return addAmount(5)
    }

    override fun removeFive(): Int {
        return removeAmount(5)
    }

    override fun setAmountTo(amount: Int): Int {
        return amount;
    }
}