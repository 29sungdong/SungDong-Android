package com.example.sungdongwalk.api.utils

object API {
    const val BASE_URL : String = "http://13.125.49.125"

    const val USER_LOGIN : String = "/user/login"
    const val USER_NICKNAME : String = "/user/nickname"
    const val USER_PLACES : String = "/user/places"
    const val USER_SIGNUP : String = "/user/signup"
    const val USER_STATS : String = "/user/stats"
    const val USER_BADGES : String = "/user/badges"

    const val SUB_PLACES : String = "/sub-places"
    const val PLACES : String = "/places"
    const val PLACES_ID : String = "/places/{id}"
    const val PLACES_MARKER : String = "/places/marker"
    const val PLACES_SEARCH : String = "/places/search"
    const val PLACES_WALK : String = "/places/{placeId}/walk"

    const val MISSIONS : String = "/missions"
    const val MISSIONS_ID : String = "/missions/{missionId}"

    const val EVENTS : String = "/events"
}