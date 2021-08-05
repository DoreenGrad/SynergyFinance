package com.example.synergyfinance

interface LoanCalculation {
    fun calculateAccumulatedAmount1(repaymentMonth: Double, timeInYears: Double): Double
    fun calculateAccumulatedAmount2(
        principalAmount: Double,
        interestRate: Double,
        timeInYears: Double
    ): Double

    fun calculateRepaymentAmount(accumulatedAmount: Double, timeInYears: Double): Double
    fun calculateInterestRate(
        accumulatedAmount: Double,
        principalAmount: Double,
        timeInYears: Double
    ): Double
}