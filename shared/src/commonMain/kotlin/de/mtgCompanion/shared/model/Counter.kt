package de.mtgCompanion.shared.model


class Counter(
    // amount of counters
    var amount: Int = 0
) {


    // raises counters by given amount
    fun raiseBy(addAmountBy: Int) {
        this.amount += addAmountBy
    }

    // decrease counters by given amount
    fun decreaseBy(decreaseAmountBy: Int) {
        this.amount -= decreaseAmountBy
    }
}
