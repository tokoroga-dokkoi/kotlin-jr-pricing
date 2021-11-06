package domain.model.discount

import domain.model.fare.Fare
import domain.model.shared.Passengers
import domain.model.shared.Price
import domain.model.surcharge.Surcharge

/**
 * 割引インターフェース
 */
interface Discount {
    val discountName: DiscountName
}

interface GroupDiscount: Discount {
    override val discountName: DiscountName
    val passengers: Passengers
}