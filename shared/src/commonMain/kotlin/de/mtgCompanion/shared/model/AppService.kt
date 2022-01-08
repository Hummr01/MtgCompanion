package de.mtgCompanion.shared.model

import de.mtgCompanion.shared.Constants


class AppService(private val playerLife: Int = Constants.LIFE_STANDARD) {

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
        this.playerList.clear()
        for (i in 0 until numberOfPlayers) {
            playerList.add(Player(playerLife))
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
     * Chooses a player at random.
     * @return the ID of the chosen player
     */
    fun choosePlayerAtRandom(): Player? {
        return playerList.asSequence().shuffled().find { true }
    }
}