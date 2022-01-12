package de.mtgCompanion.shared.model

import de.mtgCompanion.shared.Constants.PLAYER_COLOR_WHITE
import kotlin.math.abs

class Player(var startLifeAmount: Int = 0) {

    val lifeCounter: Counter = Counter(startLifeAmount)
    val poisonCounter: Counter = Counter()
    val experienceCounter: Counter = Counter()
    val energyCounter: Counter = Counter()

    var color = PLAYER_COLOR_WHITE

    /**
     * changes the amount of life for the player by the amount.
     * @param playerID the ID of the player whose life is about to be changed
     * @param amount the amount by which the life gonna be changed
     * @return the new amount for the player - null if the player doesn't exist
     */
    fun alterPlayersLifeTotalBy(amount: Int): Int {
        val lifeCounter = this.lifeCounter

        return alterAmountOfCounterBy(lifeCounter, amount)
    }

    fun alterPlayersPoisonCounterAmountBy(amount: Int): Int {
        val poisonCounter = this.poisonCounter

        return alterAmountOfCounterBy(poisonCounter, amount)
    }

    fun alterPlayersExperienceCounterAmountBy(amount: Int): Int {
        val experienceCounter = this.experienceCounter

        return alterAmountOfCounterBy(experienceCounter, amount)
    }

    fun alterPlayersEnergyCounterAmountBy(amount: Int): Int {
        val energyCounter = this.energyCounter

        return alterAmountOfCounterBy(energyCounter, amount)
    }

    private fun alterAmountOfCounterBy(counter: Counter, amount: Int): Int {
        if (amount > 0) {
            // amount is positive
            counter.raiseBy(amount)
        } else {
            // amount is negative
            counter.decreaseBy(abs(amount))
        }

        return counter.amount
    }

    // resets the counters to the default value
    fun resetCounters() {
        lifeCounter.amount = 40
        poisonCounter.amount = 0
        experienceCounter.amount = 0
        energyCounter.amount = 0
    }
}