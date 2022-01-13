package de.mtgCompanion.shared.services

import de.mtgCompanion.shared.Constants
import de.mtgCompanion.shared.model.Player

class AppService() {

    var startLife: Int = Constants.LIFE_STANDARD
    val playerMap = ArrayList<Player>()

    /**
     * sets the number of players.
     * if the the numberOfPlayers didn't change nothing will happen
     * @param numberOfPlayers the number of players for the game
     */
    fun setNumberOfPlayers(numberOfPlayers: Int) {
        if (numberOfPlayers == this.playerMap.size) {
            // number of players didn't change -> abort
            return
        }

        // remove players if too many
        while (this.playerMap.size > numberOfPlayers) {
            this.playerMap.removeLast();
        }

        // reset all counters of current players and add or remove players as needed
        startNewGame()

        // add players if needed
        while (this.playerMap.size < numberOfPlayers) {
            playerMap.add(Player(startLife))
        }
    }

    /**
     * Resets the counters (life counter etc.) of each player
     */
    fun startNewGame() {
        for (player in playerMap) {
            player.resetCounters()
        }
    }

    /**
     * sets the start life amount for each player to newLife
     * @param newLife the new start life of each player
     */
    fun setPlayerStartLifeAmountTo(newLife : Int) {
        this.startLife = newLife
        for (player in playerMap) {
            player.startLifeAmount = newLife
            player.lifeCounter.amount = newLife
        }
    }

    /**
     * Chooses a player at random.
     * @return the ID of the chosen player
     */
    fun choosePlayerAtRandom(): Player? {
        return playerMap.asSequence().shuffled().find { true }
    }

    fun getPlayerByIndex(index: Int): Player {
        return playerMap[index]
    }
}