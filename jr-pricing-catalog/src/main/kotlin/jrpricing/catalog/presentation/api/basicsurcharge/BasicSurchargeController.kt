package jrpricing.catalog.presentation.api.basicsurcharge

import jrpricing.catalog.domain.model.station.StationId
import jrpricing.catalog.usecase.basicsurcharge.FindBasicSurchargeUsecase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/catalog/surcharge")
class BasicSurchargeController(
    private val findBasicSurchargeUsecase: FindBasicSurchargeUsecase
) {

    @GetMapping("/search")
    fun search(
        @RequestParam(name = "departureStationId") departureStationId: String,
        @RequestParam(name = "arrivalStationId") arrivalStationId: String
    ): SearchSurchargeResponse {
        val result = findBasicSurchargeUsecase.execute(
            getStationId(departureStationId),
            getStationId(arrivalStationId)
        )

        return SearchSurchargeResponse(
            result.routeId.value,
            result.amount.value
        )
    }

    private fun getStationId(stationId: String): StationId {
        return StationId.reConstructor(stationId)
    }
}