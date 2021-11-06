package domain.model.discount.group

import domain.model.discount.DiscountRate
import domain.model.fare.Fare
import domain.model.shared.Price
import domain.model.ticket.DepartureDate
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDate


class GroupDiscountTest {
    @Nested
    inner class AfterDiscountFareTest() {
        @Test
        fun `割引率が10%の割引金額が正しいこと`() {
            val fare = Fare(Price(10000))
            val discountRate = DiscountRate.RATE_10

            val discount = GroupDiscount(fare, discountRate)

            val afterDiscountedFare = discount.afterDiscountFare()

            Assertions.assertEquals(Price(9000), afterDiscountedFare.fare.price(false))
            Assertions.assertEquals(Price(4500), afterDiscountedFare.fare.price(true))
        }

        @Test
        fun `割引率が15%の割引金額が正しいこと`() {
            val fare = Fare(Price(10050))
            val discountRate = DiscountRate.RATE_15

            val discount = GroupDiscount(fare, discountRate)

            val afterDiscountedFare = discount.afterDiscountFare()

            Assertions.assertEquals(Price(8540), afterDiscountedFare.fare.price(false))
            Assertions.assertEquals(Price(4270), afterDiscountedFare.fare.price(true))
        }
    }
}