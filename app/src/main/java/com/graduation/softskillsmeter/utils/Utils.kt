package com.graduation.softskillsmeter.utils

object Utils {

    fun formatSkillScore(score: Double): String {
        return String.format("%.1f", score * 10)
    }
}