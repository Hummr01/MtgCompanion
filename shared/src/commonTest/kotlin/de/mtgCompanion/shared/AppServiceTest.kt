package de.mtgCompanion.shared

import de.mtgCompanion.shared.model.AppService
import kotlin.test.*

class AppServiceTest {
    private val appService = AppService(Constants.LIFE_COMMANDER)

    @BeforeTest
    fun initTests() {
        appService.setNumberOfPlayers(4)
    }


    // tests for life counter
    @Test
    fun testAlterPlayersLifeTotalBy_ExpectAmountToBeDecreased() {
        val result = appService.alterPlayersLifeTotalBy(0, -5)
        assertEquals(35, result)
    }

    @Test
    fun testAlterPlayersLifeTotalBy_ExpectAmountToBeIncreased() {
        val result = appService.alterPlayersLifeTotalBy(0, 5)
        assertEquals(45, result)
    }

    @Test
    fun testAlterPlayersLifeTotalBy_ExpectPlayerNotFoundException() {
        assertFailsWith<PlayerNotFoundException> { appService.alterPlayersLifeTotalBy(9999, 5) }
    }

    // tests for poison counters
    @Test
    fun testAlterPlayersPoisonCounterBy_ExpectAmountToBeDecreased() {
        appService.alterPlayersPoisonCounterAmountBy(0, 5)
        val result = appService.alterPlayersPoisonCounterAmountBy(0, -1)
        assertEquals(4, result)
    }

    @Test
    fun testAlterPlayersPoisonCounterBy_ExpectAmountToBeIncreased() {
        val result = appService.alterPlayersPoisonCounterAmountBy(0, 1)
        assertEquals(1, result)
    }

    @Test
    fun testAlterPlayersPoisonCounterBy_ExpectPlayerNotFoundException() {
        assertFailsWith<PlayerNotFoundException> {
            appService.alterPlayersPoisonCounterAmountBy(
                9999,
                5
            )
        }
    }

    // tests for experience counters
    @Test
    fun testAlterPlayersExperienceCounterBy_ExpectAmountToBeDecreased() {
        appService.alterPlayersExperienceCounterAmountBy(0, 5)
        val result = appService.alterPlayersExperienceCounterAmountBy(0, -1)
        assertEquals(4, result)
    }

    @Test
    fun testAlterPlayersExperienceCounterBy_ExpectAmountToBeIncreased() {
        val result = appService.alterPlayersExperienceCounterAmountBy(0, 1)
        assertEquals(1, result)
    }

    @Test
    fun testAlterPlayersExperienceCounterBy_ExpectPlayerNotFoundException() {
        assertFailsWith<PlayerNotFoundException> {
            appService.alterPlayersExperienceCounterAmountBy(
                9999,
                5
            )
        }
    }

    // tests for energy counters
    @Test
    fun testAlterPlayersEnergyCounterBy_ExpectAmountToBeDecreased() {
        appService.alterPlayersEnergyCounterAmountBy(0, 5)
        val result = appService.alterPlayersEnergyCounterAmountBy(0, -1)
        assertEquals(4, result)
    }

    @Test
    fun testAlterPlayersEnergyCounterBy_ExpectAmountToBeIncreased() {
        val result = appService.alterPlayersEnergyCounterAmountBy(0, 1)
        assertEquals(1, result)
    }

    @Test
    fun testAlterPlayersEnergyCounterBy_ExpectPlayerNotFoundException() {
        assertFailsWith<PlayerNotFoundException> {
            appService.alterPlayersEnergyCounterAmountBy(
                9999,
                5
            )
        }
    }

    // tests for random player
    @Test
    fun testChoosePlayerAtRandom_ExpectOneRandomPlayerIDReturned() {
        assertTrue(appService.playerList.keys.contains(appService.choosePlayerAtRandom()))
    }

    // test for new game
    @Test
    fun testStartNewGame_ExpectPlayerCountersToBeReset() {
        // life
        appService.alterPlayersLifeTotalBy(0, -12)
        appService.alterPlayersLifeTotalBy(1, -20)
        appService.alterPlayersLifeTotalBy(2, -35)
        appService.alterPlayersLifeTotalBy(3, -30)

        // poison
        appService.alterPlayersPoisonCounterAmountBy(0, 1)
        appService.alterPlayersPoisonCounterAmountBy(1, 2)
        appService.alterPlayersPoisonCounterAmountBy(2, 3)
        appService.alterPlayersPoisonCounterAmountBy(3, 4)

        // experience
        appService.alterPlayersExperienceCounterAmountBy(0, 1)
        appService.alterPlayersExperienceCounterAmountBy(1, 2)
        appService.alterPlayersExperienceCounterAmountBy(2, 3)
        appService.alterPlayersExperienceCounterAmountBy(3, 4)

        // energy
        appService.alterPlayersEnergyCounterAmountBy(0, 1)
        appService.alterPlayersEnergyCounterAmountBy(1, 2)
        appService.alterPlayersEnergyCounterAmountBy(2, 3)
        appService.alterPlayersEnergyCounterAmountBy(3, 4)


        appService.startNewGame()
        for ((id, player) in appService.playerList) {
            assertEquals(40, player.lifeCounter.amount)
            assertEquals(0, player.poisonCounter.amount)
            assertEquals(0, player.experienceCounter.amount)
            assertEquals(0, player.energyCounter.amount)
        }
    }
}