package de.mtgCompanion.shared.model

import de.mtgCompanion.shared.PlayerNotFoundException
import kotlin.math.abs

class AppService(private val playerLife: Int = 0) {

    val playerList = HashMap<Int, Player>()

    /**
     * sets the number of players.
     * if the the numberOfPlayers didn't change nothing will happen
     * @param numberOfPlayers the number of players for the game
     */
    fun setNumberOfPlayers(numberOfPlayers: Int) {
        if (numberOfPlayers == this.playerList.size) {
            // number of players didn't change -> abort
            return
        }
        this.playerList.clear()
        for (i in 0 until numberOfPlayers) {
            playerList[i] = Player(playerLife)
        }
    }

    /**
     * Resets the counters (life counter etc.)
     */
    fun startNewGame() {
        for ((id, player) in playerList) {
            player.resetCounters()
        }
    }

    /**
     * Chooses a player at random.
     * @return the ID of the chosen player
     */
    fun choosePlayerAtRandom(): Int {
        return playerList.keys.shuffled().first()
    }

    /**
     * changes the amount of life for the player by the amount.
     * @param playerID the ID of the player whose life is about to be changed
     * @param amount the amount by which the life gonna be changed
     * @return the new amount for the player - null if the player doesn't exist
     */
    fun alterPlayersLifeTotalBy(playerID: Int, amount: Int): Int {
        val lifeCounter = playerList[playerID]?.lifeCounter
            ?: throw PlayerNotFoundException("Player with ID $playerID could not be found in the list")

        return alterAmountOfCounterBy(lifeCounter, amount)
    }

    fun alterPlayersPoisonCounterAmountBy(playerID: Int, amount: Int): Int? {
        val poisonCounter = playerList[playerID]?.poisonCounter
            ?: throw PlayerNotFoundException("Player with ID $playerID could not be found in the list")

        return alterAmountOfCounterBy(poisonCounter, amount)
    }

    fun alterPlayersExperienceCounterAmountBy(playerID: Int, amount: Int): Int? {
        val experienceCounter = playerList[playerID]?.experienceCounter
            ?: throw PlayerNotFoundException("Player with ID $playerID could not be found in the list")

        return alterAmountOfCounterBy(experienceCounter, amount)
    }

    fun alterPlayersEnergyCounterAmountBy(playerID: Int, amount: Int): Int? {
        val energyCounter = playerList[playerID]?.energyCounter
            ?: throw PlayerNotFoundException("Player with ID $playerID could not be found in the list")

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
}