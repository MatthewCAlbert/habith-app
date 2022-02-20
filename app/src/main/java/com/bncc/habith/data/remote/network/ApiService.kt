package com.bncc.habith.data.remote.network

import com.bncc.habith.data.remote.response.UserResponse
import com.bncc.habith.data.remote.response.BaseResponse
import com.bncc.habith.data.remote.response.HabithHistoryResponse
import com.bncc.habith.data.remote.response.HabithResponse
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun toLogin(
        @Field("username") username: String, @Field("password") password: String
    ): BaseResponse<UserResponse>

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun toRegister(
        @Field("name") name: String, @Field("email") email: String,
        @Field("username") username: String, @Field("password") password: String
    ): BaseResponse<UserResponse>

    @GET("auth/profile")
    suspend fun getUserDetail(): BaseResponse<UserResponse>

    @FormUrlEncoded
    @PUT("auth/profile")
    suspend fun updateUserDetail(
        @Header("Authorization") token: String, @Field("name") name: String
    ): BaseResponse<UserResponse>

    @FormUrlEncoded
    @POST("auth/change-password")
    suspend fun updateUserPassword(
        @Field("oldPassword") old: String,
        @Field("newPassword") new: String, @Field("rePassword") repeat: String,
    ): BaseResponse<UserResponse>


    @FormUrlEncoded
    @POST("habit")
    suspend fun createHabith(
        @Field("title") title: String,
        @Field("category") category: String, @Field("description") description: String,
        @Field("target") target: Int, @Field("target_type") targetType: String,
        @Field("start") start: String, @Field("end") end: String,
        @Field("repeat_every_day") repeat: Int
    ): BaseResponse<HabithResponse>

    @FormUrlEncoded
    @POST("habit")
    suspend fun createHabith(
        @Field("title") title: String,
        @Field("category") category: String, @Field("description") description: String,
        @Field("target") target: Int, @Field("target_type") targetType: String,
        @Field("repeat_every_day") repeat: Int
    ): BaseResponse<HabithResponse>

    @GET("habit?withHistory=false")
    suspend fun getAllHabithWithHistory(): BaseResponse<List<HabithResponse>>

    @GET("habit/{id}?withHistory=false")
    suspend fun getHabith(
        @Path("id") id: String
    ): BaseResponse<HabithResponse>

    @FormUrlEncoded
    @PUT("habit/{id}")
    suspend fun updateHabith(
        @Path("id") id: String,
        @Field("title") title: String, @Field("description") description: String,
        @Field("category") category: String, @Field("repeat_every_day") repeat: String,
        @Field("target") target: Int, @Field("target_type") targetType: String,
        @Field("start") start: String, @Field("end") end: String
    ): BaseResponse<HabithResponse>

    @DELETE("habit/{id}")
    suspend fun deleteHabith(
        @Path("id") id: String
    ): BaseResponse<HabithResponse>

    @FormUrlEncoded
    @POST("habit/{id}/history")
    suspend fun createHabithHistory(
        @Header("Authorization") token: String, @Path("id") id: String,
        @Field("date") date: String, @Field("value") value: Int
    ): BaseResponse<HabithHistoryResponse>

    @GET("habit/history/{id}")
    suspend fun getHabithHistory(
        @Path("id") id: String
    ): BaseResponse<HabithHistoryResponse>

    @DELETE("habit/history/{id}")
    suspend fun deleteHabithHistory(
        @Path("id") id: String
    ): BaseResponse<HabithHistoryResponse>

    @FormUrlEncoded
    @PUT("habit/history/{id}")
    suspend fun updateHabithHistory(
        @Path("id") id: String,
        @Field("value") value: Int
    ): BaseResponse<HabithHistoryResponse>
}