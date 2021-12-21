package de.mtgCompanion.shared.model

import de.mtgCompanion.shared.Constants.PLAYER_COLOR_WHITE

class Player(lifeAmount : Int = 0)  {

    val lifeCounter : Counter = Counter(lifeAmount)
    val poisonCounter : Counter = Counter()
    val experienceCounter : Counter = Counter()
    val energyCounter : Counter = Counter()

    var color = PLAYER_COLOR_WHITE
}