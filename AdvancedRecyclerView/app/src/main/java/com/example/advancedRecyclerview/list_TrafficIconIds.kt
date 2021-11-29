package com.example.advancedRecyclerview

fun listTrafficIcons(size: Int): List<TrafficSigns> {
    val list = mutableListOf<TrafficSigns>()
    for (i in 1..size) {
        when {
            i % 7 == 1 -> list.add(TrafficSigns(R.drawable.tr_airport_ahead, "Airport"))
            i % 7 == 2 -> list.add(TrafficSigns(R.drawable.tr_bridge, "Bridge"))
            i % 7 == 3 -> list.add(TrafficSigns(R.drawable.tr_no_horn, "No Horn"))
            i % 7 == 4 -> list.add(TrafficSigns(R.drawable.tr_one_way, "One Way"))
            i % 7 == 5 -> list.add(TrafficSigns(R.drawable.tr_school_ahead, "School Ahead"))
            i % 7 == 6 -> list.add(TrafficSigns(R.drawable.tr_speed_limit_80, "Speed limit 80"))
            else -> list.add(TrafficSigns(R.drawable.tr_two_way, "Two Way"))
        }
    }
    return list
}