package player

import counters.lifeCounter.LifeCounter

class Player(private val startLife: Int) {
    val lifeCounter: LifeCounter = LifeCounter(startLife)
}