package com.example.androiddevchallenge.model

import java.io.Serializable

data class DogBrief(
    val id: String,
    val name: String,
    val photo: String,
    val age: Int,
    val breed: String,
    val gender: Gender,
    val desc: String = ""): Serializable

enum class Gender {
    Female, Male
}


fun Gender.getString(): String {
    return if (this == Gender.Female) {
        "female"
    } else {
        "male"
    }
}