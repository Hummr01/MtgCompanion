package de.mtg.companion.androidApp

import de.mtg.companion.shared.Greeting
import org.junit.Assert.assertTrue
import org.junit.Test


internal class MainActivityKtTest {

    @Test
    fun greet() {
        assertTrue("Check Android is mentioned", Greeting().greeting().contains("Android"))

    }
}