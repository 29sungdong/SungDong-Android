package com.example.sungdongwalk.api
class Dto {
    data class UserRequestDTO(
        val username: String,
        val nickname: String,
        val password: String,
        val deviceToken: String
    )
    data class UserResponseDTO(
        val id: Int,
        val username: String,
        val nickname: String
    )
    data class TokenRequestDTO(
        val username: String,
        val password: String
    )
    data class TokenResponseDTO(
        val id: Int,
        val token: String,
    )
    data class NicknameUpdateRequestDTO(
        val nickname: String
    )
    data class StatsResponseDTO(
        val placePercent: Int,
        val missionPercent: Int
    )
    data class PlaceResponseDTO(
        val id : Int,
        val name: String,
        val isVisited: Boolean
    )
    data class PlacesResponseDTO(
        val places: List<PlaceResponseDTO>
    )
    data class BadgeResponseDTO(
        val id: Int,
        val category: String,
        val imageUrl: String,
        val achievedBadges: List<UserBadgeResponseDTO>
    )
    data class BadgesResponseDTO(
        val badges: List<BadgeResponseDTO>
    )
    data class UserBadgeResponseDTO(
        val id: Int,
        val name: String
    )
    data class SubPlaceResponseDTO(
        val id: Int,
        val name: String,
        val xcoordinate: String?,
        val ycoordinate: String?
    )
    data class SubPlacesResponseDTO(
        val subPlaces: List<SubPlaceResponseDTO>
    )
    data class PlaceListResponseDTO(
        val places: List<SimplePlaceVo>
    )
    data class SimplePlaceVo(
        val id: Int,
        val name: String,
        val image: String,
        val address: String,
        val tel: String,
        val openingTime: String,
        val closingTime: String,
        val xcoordinate: String,
        val ycoordinate: String,
    )
    data class MarkerListResponseDTO(
        val markers: List<MarkerVo>
    )
    data class MarkerVo(
        val id: Int,
        val name: String,
        val image: String,
        val openingTime: String,
        val closingTime: String,
        val hasEvent: Boolean,
        val xcoordinate: String,
        val ycoordinate: String,
    )
    data class WalkPathResponseDTO(
        val id: Int,
        val name: String,
        val totalDistance: Int,
        val totalTime: Int,
        val coordinates: List<List<Double>>,
        val xcoordinate: Double,
        val ycoordinate: Double
    )
    data class WalkPathsResponseDTO(
        val paths : List<WalkPathResponseDTO>
    )
    data class ShortestPathResponseDTO(
        val totalDistance: Int,
        val totalTime: Int,
        val coordinates: List<List<Double>>
    )
    data class MissionResponseDTO(
        val id: Int,
        val content: String
    )
    data class MissionsResponseDTO(
        val missions: List<MissionResponseDTO>
    )
    data class SimpleEventVo(
        val placeId: Int,
        val placeName: String,
        val name: String,
        val placeImage: String,
        val endDate: String,
        val url: String
    )
    data class CalenderVo(
        val date: String,
        val events: List<SimpleEventVo>
    )
    data class CalenderResponseDTO(
        val data: List<CalenderVo>
    )
    data class EventListResponseDTO(
        val events: List<SimpleEventVo>
    )
}