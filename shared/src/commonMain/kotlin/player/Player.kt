package player

import Counters.lifeCounter.LifeCounter

class Player(private val startLife: Int) {
    val lifeCounter: LifeCounter = LifeCounter(startLife)
}