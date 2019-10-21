package com.bargaintechnology.vantagecirclr.giftcard.model

data class Voucher(
    val dealId: Int?,
    val name: String?,
    val denomination: IntArray?,
    val termsandCondition: String?,
    val writeUp: String?,
    val highLight: String?,
    val imageCloudRootThumb: String?,
    val imageCloudRootBig: String?,
    val deliveryTime: String?,
    val inStock: Boolean?,
    val tags: String?,
    val giftVoucherDescription: String?,
    val giftVoucherSeoContent: String?,
    val currencyName: String?,
    val currencyHex: String?,
    val usdEquivalent: Float?,
    val quantity: IntArray?,
    val buttonText: String?,
    val redeemText: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Voucher

        if (dealId != other.dealId) return false
        if (name != other.name) return false
        if (denomination != null) {
            if (other.denomination == null) return false
            if (!denomination.contentEquals(other.denomination)) return false
        } else if (other.denomination != null) return false
        if (termsandCondition != other.termsandCondition) return false
        if (writeUp != other.writeUp) return false
        if (highLight != other.highLight) return false
        if (imageCloudRootThumb != other.imageCloudRootThumb) return false
        if (imageCloudRootBig != other.imageCloudRootBig) return false
        if (deliveryTime != other.deliveryTime) return false
        if (inStock != other.inStock) return false
        if (tags != other.tags) return false
        if (giftVoucherDescription != other.giftVoucherDescription) return false
        if (giftVoucherSeoContent != other.giftVoucherSeoContent) return false
        if (currencyName != other.currencyName) return false
        if (currencyHex != other.currencyHex) return false
        if (usdEquivalent != other.usdEquivalent) return false
        if (quantity != null) {
            if (other.quantity == null) return false
            if (!quantity.contentEquals(other.quantity)) return false
        } else if (other.quantity != null) return false
        if (buttonText != other.buttonText) return false
        if (redeemText != other.redeemText) return false

        return true
    }

    override fun hashCode(): Int {
        var result = dealId ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (denomination?.contentHashCode() ?: 0)
        result = 31 * result + (termsandCondition?.hashCode() ?: 0)
        result = 31 * result + (writeUp?.hashCode() ?: 0)
        result = 31 * result + (highLight?.hashCode() ?: 0)
        result = 31 * result + (imageCloudRootThumb?.hashCode() ?: 0)
        result = 31 * result + (imageCloudRootBig?.hashCode() ?: 0)
        result = 31 * result + (deliveryTime?.hashCode() ?: 0)
        result = 31 * result + (inStock?.hashCode() ?: 0)
        result = 31 * result + (tags?.hashCode() ?: 0)
        result = 31 * result + (giftVoucherDescription?.hashCode() ?: 0)
        result = 31 * result + (giftVoucherSeoContent?.hashCode() ?: 0)
        result = 31 * result + (currencyName?.hashCode() ?: 0)
        result = 31 * result + (currencyHex?.hashCode() ?: 0)
        result = 31 * result + (usdEquivalent?.hashCode() ?: 0)
        result = 31 * result + (quantity?.contentHashCode() ?: 0)
        result = 31 * result + (buttonText?.hashCode() ?: 0)
        result = 31 * result + redeemText.hashCode()
        return result
    }
}
