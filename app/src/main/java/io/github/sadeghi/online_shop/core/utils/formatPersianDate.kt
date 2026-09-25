package io.github.sadeghi.online_shop.core.utils

import java.util.Calendar

fun formatPersianDate(timestamp: Long): String {

    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timestamp

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val (persianYear, persianMonth, persianDay) =
        gregorianToPersian(
            year,
            month,
            day
        )

    val monthName = when (persianMonth) {
        1 -> "فروردین"
        2 -> "اردیبهشت"
        3 -> "خرداد"
        4 -> "تیر"
        5 -> "مرداد"
        6 -> "شهریور"
        7 -> "مهر"
        8 -> "آبان"
        9 -> "آذر"
        10 -> "دی"
        11 -> "بهمن"
        12 -> "اسفند"
        else -> ""
    }

    return "$persianDay $monthName $persianYear"
}

private fun gregorianToPersian(
    gy: Int,
    gm: Int,
    gd: Int
): Triple<Int, Int, Int> {

    val gDaysInMonth = intArrayOf(
        31, 28, 31, 30, 31, 30,
        31, 31, 30, 31, 30, 31
    )

    val jDaysInMonth = intArrayOf(
        31, 31, 31, 31, 31, 31,
        30, 30, 30, 30, 30, 29
    )

    val gyTemp = gy - 1600
    val gmTemp = gm - 1
    val gdTemp = gd - 1

    var gDayNo = 365 * gyTemp +
            (gyTemp + 3) / 4 -
            (gyTemp + 99) / 100 +
            (gyTemp + 399) / 400

    for (i in 0 until gmTemp) {
        gDayNo += gDaysInMonth[i]
    }

    if (
        gmTemp > 1 &&
        (
                gy % 4 == 0 &&
                        gy % 100 != 0 ||
                        gy % 400 == 0
                )
    ) {
        gDayNo++
    }

    gDayNo += gdTemp

    var jDayNo = gDayNo - 79

    val jNp = jDayNo / 12053
    var jy = 979 + 33 * jNp

    jDayNo %= 12053

    jy += 4 * (jDayNo / 1461)

    jDayNo %= 1461

    if (jDayNo >= 366) {
        jy += (jDayNo - 1) / 365
        jDayNo = (jDayNo - 1) % 365
    }

    var jm = 0

    while (
        jm < 11 &&
        jDayNo >= jDaysInMonth[jm]
    ) {
        jDayNo -= jDaysInMonth[jm]
        jm++
    }

    val jd = jDayNo + 1

    return Triple(
        jy,
        jm + 1,
        jd
    )
}