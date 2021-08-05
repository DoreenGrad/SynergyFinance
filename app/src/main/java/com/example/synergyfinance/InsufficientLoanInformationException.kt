package com.example.synergyfinance

class InsufficientLoanInformationException : RuntimeException {
    constructor() : super("Insufficient Loan Information Provided") {}
    constructor(message: String?) : super(message) {}
}