package de.mtgCompanion.shared.services

import de.mtgCompanion.shared.Constants
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
     * Resets the counters (life counter etc.) of each player
     */
    fun startNewGame() {
        for (player in playerList) {
            player.startLifeAmount = startLife
            player.resetCounters()
        }
    }

    /**
     * sets the start life amount for each player to newLife
     * @param newLife the new start life of each player
     */
    fun setPlayerStartLifeAmountTo(newLife : Int) {
        this.startLife = newLife
        startNewGame()
    }

    /**
     * Chooses a player at random.
     * @return the ID of the chosen player
     */
    fun choosePlayerAtRandom(): Player? {
        return playerList.asSequence().shuffled().find { true }
    }

    fun getPlayerByIndex(index: Int): Player {
        return playerList[index]
    }
}