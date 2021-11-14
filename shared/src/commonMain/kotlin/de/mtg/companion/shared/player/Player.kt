package de.mtg.companion.shared.player

import de.mtg.companion.shared.counters.lifeCounter.LifeCounter

class Player(private val startLife: Int) {
    val lifeCounter: LifeCounter = LifeCounter(startLife)
}