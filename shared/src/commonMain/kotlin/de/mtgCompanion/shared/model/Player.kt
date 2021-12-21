package de.mtgCompanion.shared.model

import de.mtgCompanion.shared.Constants.PLAYER_COLOR_WHITE

class Player(val startLifeAmount: Int = 0) {

    val lifeCounter: Counter = Counter(startLifeAmount)
    val poisonCounter: Counter = Counter()
    val experienceCounter: Counter = Counter()
    val energyCounter: Counter = Counter()

    var color = PLAYER_COLOR_WHITE

    // resets the counters to the default value
    fun resetCounters() {
        lifeCounter.amount = startLifeAmount
        poisonCounter.amount = 0
        experienceCounter.amount = 0
        energyCounter.amount = 0
    }
}