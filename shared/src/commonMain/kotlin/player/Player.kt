package player

import lifecounter.LifeCounter

class Player(private val startLife: Int) {
    val lifeCounter: LifeCounter = LifeCounter(startLife)
}