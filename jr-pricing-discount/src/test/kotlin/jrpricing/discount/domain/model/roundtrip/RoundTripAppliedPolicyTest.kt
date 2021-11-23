package jrpricing.discount.domain.model.roundtrip

import jrpricing.discount.domain.roundtrip.RoundTripAppliedPolicy
import jrpricing.discount.domain.route.DistanceKilometer
import jrpricing.discount.domain.route.Route
import jrpricing.discount.domain.shared.TripType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RoundTripAppliedPolicyTest {
    @Test
    fun `片道移動の場合はfalseを返すこと`() {
        val route = Route(distanceKilometer = DistanceKilometer(601))
        val tripType = TripType.HALF_WAY

        Assertions.assertFalse(
            RoundTripAppliedPolicy.can(route, tripType)
        )
    }

    @Test
    fun `往復移動で片道の移動距離が601キロ未満の場合はfalseを返すこと`() {
        val route = Route(distanceKilometer = DistanceKilometer(600))
        val tripType = TripType.ROUND_TRIP

        Assertions.assertFalse(
            RoundTripAppliedPolicy.can(route, tripType)
        )
    }

    @Test
    fun `往復移動で片道の移動距離が601キロ以上の場合はtrueを返すこと`() {
        val route = Route(distanceKilometer = DistanceKilometer(601))
        val tripType = TripType.ROUND_TRIP

        Assertions.assertTrue(
            RoundTripAppliedPolicy.can(route, tripType)
        )
    }
}