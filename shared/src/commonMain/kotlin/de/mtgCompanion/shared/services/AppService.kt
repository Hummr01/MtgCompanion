package de.mtgCompanion.shared.services

import de.mtgCompanion.shared.Constants
import de.mtgCompanion.shared.model.Counter
import de.mtgCompanion.shared.model.Player

class AppService() {

    var startLife: Int = Constants.LIFE_STANDARD
    val playerList = ArrayList<Player>()

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

        // remove players if too many
        while (this.playerList.size > numberOfPlayers) {
            this.playerList.removeLast();
        }

        // reset all counters of current players and add or remove players as needed
        startNewGame()

        // add players if needed
        while (this.playerList.size < numberOfPlayers) {
            playerList.add(Player(startLife))
        }
    }

    /**
     * Resets the counters (life counter etc.)
     */
    fun startNewGame() {
        for (player in playerList) {
            player.resetCounters()
        }
    }

    /**
     *
     */
    fun setPlayerLifeTo(newLife : Int) {
        this.startLife = newLife
        for (player in playerList) {
            player.startLifeAmount = newLife
            player.lifeCounter.amount = newLife
        }
    }

    /**
     * Chooses a player at random.
     * @return the ID of the chosen player
     */
    fun choosePlayerAtRandom(): Player? {
        return playerList.asSequence().shuffled().find { true }
    }
}