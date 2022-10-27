package com.example.infoaboutcountries

import java.text.NumberFormat


fun formatNumber(number: Long): String {
    return NumberFormat.getInstance().format(number)
}