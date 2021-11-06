package domain.model.discount.distance

import domain.model.fare.Fare
import domain.model.shared.Passengers
import domain.model.shared.Price
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DistanceDiscountTest {

    @Nested
    inner class afterDiscountFare() {
        @Test
        fun `運賃が10%割引されること`() {
            val fare = Fare(Price(10010))

            val distanceDiscount = DistanceDiscount(fare)

            val afterDiscountedFare = distanceDiscount.afterDiscountFare()

            Assertions.assertEquals(Price(9000), afterDiscountedFare.fare.price(false))
            Assertions.assertEquals(Price(4500), afterDiscountedFare.fare.price(true))
        }
    }
}