package com.example.sungdongwalk.api.retrofit

import com.example.sungdongwalk.api.Dto.*
import com.example.sungdongwalk.api.utils.API.EVENTS
import com.example.sungdongwalk.api.utils.API.EVENTS_CALENDAR
import com.example.sungdongwalk.api.utils.API.MISSIONS
import com.example.sungdongwalk.api.utils.API.MISSIONS_ID
import com.example.sungdongwalk.api.utils.API.PLACES
import com.example.sungdongwalk.api.utils.API.PLACES_ID
import com.example.sungdongwalk.api.utils.API.PLACES_MARKER
import com.example.sungdongwalk.api.utils.API.PLACES_SEARCH
import com.example.sungdongwalk.api.utils.API.SUB_PLACES
import com.example.sungdongwalk.api.utils.API.USER_BADGES
import com.example.sungdongwalk.api.utils.API.USER_LOGIN
import com.example.sungdongwalk.api.utils.API.USER_NICKNAME
import com.example.sungdongwalk.api.utils.API.USER_PLACES
import com.example.sungdongwalk.api.utils.API.USER_SIGNUP
import com.example.sungdongwalk.api.utils.API.USER_STATS
import com.example.sungdongwalk.api.utils.API.WALK
import com.example.sungdongwalk.api.utils.API.WALK_SHORTEST
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface IRetrofit {
    // User
    @POST(USER_LOGIN)
    suspend fun postUserLogin(
        @Body request : TokenRequestDTO
    ): Response<TokenResponseDTO>
    @PATCH(USER_NICKNAME)
    suspend fun updateUserNickname(
        @Query("userId") userId: Int,
        @Body request: NicknameUpdateRequestDTO
    ): Response<UserResponseDTO>
    @GET(USER_PLACES)
    suspend fun getUserPlaces(
        @Query("userId") userId: Int
    ): Response<PlacesResponseDTO>
    @POST(USER_SIGNUP)
    suspend fun postUserSignup(
        @Body request: UserRequestDTO
    ): Response<UserResponseDTO>
    @GET(USER_STATS)
    suspend fun getUserStats(): Response<StatsResponseDTO>
    // Badge
    @GET(USER_BADGES)
    suspend fun getUserBadges(
        @Query("userId") userId: Int
    ): Response<BadgesResponseDTO>

    // SubPlace
    @GET(SUB_PLACES)
    suspend fun getSubPlaces(
        @Query("placeId") placeId: Int
    ): Response<SubPlacesResponseDTO>
    // Place
    @GET(PLACES)
    suspend fun getPlaces(
        @Query("xCoordinate") xCoordinate : String,
        @Query("yCoordinate") yCoordinate : String,
    ): Response<PlaceListResponseDTO>
    @GET(PLACES_ID)
    suspend fun getPlace(
        @Path("id") id: Int
    ): Response<PlaceResponseDTO>
    @GET(PLACES_MARKER)
    suspend fun getPlacesMarker(
        @Query("xCoordinate") xCoordinate: String,
        @Query("yCoordinate") yCoordinate: String,
        @Query("limit") limit: Int
    ): Response<MarkerListResponseDTO>
    @GET(PLACES_SEARCH)
    suspend fun getPlacesSearch(
        @Query("keyword") keyword : String,
    ): Response<MarkerListResponseDTO>
    // Walk
    @GET(WALK)
    suspend fun getWalkPath(
        @Path("placeId") placeId: Int,
        @Query("xCoordinate") xCoordinate: String,
        @Query("yCoordinate") yCoordinate: String,
    ): Response<WalkPathsResponseDTO>
    @GET(WALK_SHORTEST)
    suspend fun getShortestWalkPath(
        @Path("placeId") placeId: Int,
        @Query("xCoordinate") xCoordinate: String,
        @Query("yCoordinate") yCoordinate: String,
    ): Response<ShortestPathResponseDTO>
    @POST(WALK)
    suspend fun postWalkRecord(
        @Path("placeId") placeId: Int,
    ): Response<Any>
    // Mission
    @GET(MISSIONS)
    suspend fun getMissions(
        @Query("subPlaceId") subPlaceId: Int
    ): Response<MissionsResponseDTO>
    @GET(MISSIONS_ID)
    suspend fun getMissionCompleted(
        @Path("missionId") missionId: Int
    ): Response<Any>
    // Event
    @GET(EVENTS)
    suspend fun getEvents(
        @Query("category") category: String? = null,
        @Query("placeId") placeId: Int? = null,
    ): Response<EventListResponseDTO>
    @GET(EVENTS_CALENDAR)
    suspend fun getEventsCalendar(
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
    ): Response<CalenderResponseDTO>
}