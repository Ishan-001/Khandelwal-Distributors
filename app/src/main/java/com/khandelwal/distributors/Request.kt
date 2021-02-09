package com.khandelwal.distributors

data class Request(
    val title: String?,
    val date: String?,
    val description: String?,
    val type: String?,
    val name: String?,
    val bill: String?,
    val isDone: Boolean?,
    val rating: Int?,
    val feedback: String?
){
    constructor(): this(null, null, null, null, null, null, null, null, null)
}