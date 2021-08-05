package com.example.synergyfinance

class Loan(
    accumulatedAmount: String,
    interestRate: String,
    principalAmount: String,
    timeInYears: String,
    repaymentMonth: String
) : LoanCalculation {
    //
    var accumulatedAmount = 0.0
    var interestRate = 0.0
    var principalAmount = 0.0
    var timeInYears = 0.0
    var repaymentMonth = 0.0
    //
    override fun calculateAccumulatedAmount1(
        repaymentMonth: Double,
        timeInYears: Double
    ): Double {
        return repaymentMonth * 12 * timeInYears
    }

    override fun calculateAccumulatedAmount2(
        principalAmount: Double,
        interestRate: Double,
        timeInYears: Double
    ): Double {
        return principalAmount * Math.pow(1 + interestRate, timeInYears)
    }

    override fun calculateInterestRate(
        accumulatedAmount: Double,
        principalAmount: Double,
        timeInYears: Double
    ): Double {
        return Math.pow(accumulatedAmount / principalAmount, 1 / timeInYears) - 1
    }

    override fun calculateRepaymentAmount(
        accumulatedAmount: Double,
        timeInYears: Double
    ): Double {
        return accumulatedAmount / (timeInYears * 12)
    }

    override fun toString(): String {
        return "Total Repayment Amount: R " + "%.2f".format(accumulatedAmount) + "\r\nInterest Rate: " + "%.2f".format(interestRate * 100) + "%\r\nMonthly Repayment: R " + "%.2f".format(repaymentMonth)
    }

    init {
        if (timeInYears !== "NaN") {
            this.timeInYears = timeInYears.toDouble()
            println("Time in Years: $timeInYears")
            if (accumulatedAmount !== "NaN") {
                this.accumulatedAmount = accumulatedAmount.toDouble()
                println("Accumulated Amount_01: $accumulatedAmount")
                if (repaymentMonth !== "NaN") {
                    this.repaymentMonth = repaymentMonth.toDouble()
                    println("Repayment per Month_01: $repaymentMonth")
                } else {
                    val answer = calculateRepaymentAmount(accumulatedAmount.toDouble(), timeInYears.toDouble())
                    this.repaymentMonth = answer
                    println("Repayment per Month_02: $repaymentMonth")
                }
                if (principalAmount !== "NaN") {
                    this.principalAmount = principalAmount.toDouble()
                    println("Principal Amount: $principalAmount")
                    if (interestRate !== "NaN") {
                        this.interestRate = interestRate.toDouble() / 100
                        println("Interest Rate_01: $interestRate")
                    } else {
                        val answer =
                            calculateInterestRate(accumulatedAmount.toDouble(), principalAmount.toDouble(), timeInYears.toDouble())
                        this.interestRate = answer
                        println("Interest Rate_02: $interestRate")
                    }
                } else throw InsufficientLoanInformationException("Insufficient Loan Information Provided: Invalid Principal Amount")
            } else {
                var accumulatedAmountDone = false

                // Fix bug: Accumulated Amount is calculated twice
                // Attended: boolean value inserted to determine if it is true that the Accumulated Amount has already been attended to
                // Attended: if-statement added to test if Accumulated Amount has been attended ot or not
                // Solved
                if (repaymentMonth !== "NaN") {
                    this.repaymentMonth = repaymentMonth.toDouble()
                    println("Repayment per Month: $repaymentMonth")
                    val answer = calculateAccumulatedAmount1(repaymentMonth.toDouble(), timeInYears.toDouble())
                    this.accumulatedAmount = answer
                    accumulatedAmountDone = true
                    println("Accumulated Amount_02: $accumulatedAmount")
                }

                // Fix Bug: if interest rate is NaN, but all else is available
                // Solved

                if (principalAmount !== "NaN") {
                    this.principalAmount = principalAmount.toDouble()
                    println("Principal Amount: $principalAmount")
                    if (interestRate !== "NaN") {
                        this.interestRate = interestRate.toDouble() / 100
                        println("Interest Rate_03: $interestRate")
                        if (!accumulatedAmountDone) {
                            val answer = calculateAccumulatedAmount2(
                                principalAmount.toDouble(), interestRate.toDouble(), timeInYears.toDouble()
                            )
                            this.accumulatedAmount = answer
                            println("Accumulated Amount_03: $accumulatedAmount")
                        }

                        // Fix bug: Repayment Amount is calculated twice
                        // Attended: if-statement added to determine if Repayment Amount is "NaN" or not
                        // Solved
                        if (repaymentMonth === "NaN") {
                            val answer = calculateRepaymentAmount(
                                accumulatedAmount.toDouble(), timeInYears.toDouble()
                            )
                            this.repaymentMonth = answer
                            println("Repayment per Month_03: $repaymentMonth")
                        }
                    } else throw InsufficientLoanInformationException()
                } else throw InsufficientLoanInformationException("Insufficient Loan Information Provided: Invalid Principal Amount")
            }
        } else throw InsufficientLoanInformationException("Insufficient Loan Information Provided: Invalid Time Data")
    }
}