package com.example.sungdongwalk.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.sungdongwalk.api.Dto.*
import retrofit2.http.PATCH

interface Api {
    // User
    @POST("/user/login")
    suspend fun postUserLogin(
        @Body request : TokenRequestDTO
    ): Response<TokenResponseDTO>
    @PATCH("/user/nickname")
    suspend fun updateUserNickname(
        @Query("userId") userId: Int,
        @Body request: NicknameUpdateRequestDTO
    ): Response<UserResponseDTO>
    @GET("/user/places")
    suspend fun getUserPlaces(
        @Query("userId") userId: Int
    ): Response<PlacesResponseDTO>
    @POST("/user/signup")
    suspend fun postUserSignup(
        @Body request: UserRequestDTO
    ): Response<UserResponseDTO>
    @GET("/user/stats")
    suspend fun getUserStats(): Response<StatsResponseDTO>
    // Badge
    @GET("/user/badges")
    suspend fun getUserBadges(
        @Query("userId") userId: Int
    ): Response<BadgesResponseDTO>

    // SubPlace
    @GET("/sub-places")
    suspend fun getSubPlaces(
        @Query("placeId") placeId: Int
    ): Response<SubPlacesResponseDTO>
    // Place
    @GET("/places")
    suspend fun getPlaces(
        @Query("xCoordinate") xCoordinate : String,
        @Query("yCoordinate") yCoordinate : String,
    ): Response<PlaceListResponseDTO>
    @GET("/places/{id}")
    suspend fun getPlace(
        @Path("id") id: Int
    ): Response<PlaceResponseDTO>
    @GET("/places/marker")
    suspend fun getPlacesMarker(
        @Query("xCoordinate") xCoordinate: String,
        @Query("yCoordinate") yCoordinate: String,
        @Query("limit") limit: Int
    ): Response<MarkerListResponseDTO>
    @GET("/places/search")
    suspend fun getPlacesSearch(
        @Query("keyword") keyword : String,
    ): Response<MarkerListResponseDTO>
    // Walk
    @GET("/places/{placeId}/walk")
    suspend fun getPlaceWalk(
        @Path("placeId") placeId: Int,
    ): Response<Any>
    // Mission
    @GET("/missions")
    suspend fun getMissions(
        @Query("subPlaceId") subPlaceId: Int
    ): Response<MissionsResponseDTO>
    @GET("/missions/{missionId}")
    suspend fun getMissionCompleted(
        @Path("missionId") missionId: Int
    ): Response<Any>
    // Event
    @GET("/events")
    suspend fun getEvents(
        @Query("category") category: Category,
        @Query("place_id") place_id: Int?
    ): Response<EventListResponseDTO>

}