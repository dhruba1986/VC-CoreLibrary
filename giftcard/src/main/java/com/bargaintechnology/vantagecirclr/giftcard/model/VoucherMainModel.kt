package com.bargaintechnology.vantagecirclr.giftcard.model

import java.util.ArrayList

data class VoucherMainModel (
    var count: Int?,
    var vouchers: ArrayList<Voucher>?,
    var tags: ArrayList<Tag>?
)