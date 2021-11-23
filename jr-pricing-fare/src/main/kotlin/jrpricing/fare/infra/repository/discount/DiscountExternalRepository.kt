package jrpricing.fare.infra.repository.discount

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import jrpricing.fare.domain.discount.DiscountRepository
import jrpricing.fare.domain.fare.BasicFare
import jrpricing.fare.domain.shared.Amount
import jrpricing.fare.domain.shared.DepartureDate
import jrpricing.fare.domain.shared.Passenger
import jrpricing.fare.domain.shared.TripRoute
import jrpricing.fare.infra.external.ExternalApiClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Repository
class DiscountExternalRepository(
    private val externalApiClient: ExternalApiClient,
    @Value("\${jr-fare-app-conf.discountBaseUrl}") private val discountBaseUrl: String
): DiscountRepository {

    override fun calc(
        basicFare: BasicFare,
        route: TripRoute,
        passenger: Passenger,
        departureDate: DepartureDate
    ): Amount {
        val uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(discountBaseUrl)
            .queryParam("arrivalStationId", route.arrivalStationId)
            .queryParam("departureStationId", route.departureStationId)
            .queryParam("passengers", passenger.total)
            .queryParam("departureMonthDay", departureDate.monthDay())

        val result = externalApiClient.callGet(uriComponentsBuilder)

        val response = jacksonObjectMapper().readValue(result, DiscountCalcResponse::class.java)

        return Amount.of(response.amount)
    }
}