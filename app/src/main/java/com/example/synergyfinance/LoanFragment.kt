package com.example.synergyfinance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import org.w3c.dom.Text

class LoanFragment : Fragment() {

    private lateinit var seekBar: SeekBar
    private lateinit var textView: TextView
    private lateinit var button: Button
    private lateinit var answer: TextView

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_loan, container, false)

        //listener?.onInputSent("Loan Calculator")

        seekBar = v.findViewById(R.id.seekBar)
        textView = v.findViewById(R.id.textViewInterestRate)
        button = v.findViewById(R.id.button2)
        answer = v.findViewById(R.id.textViewAnswer)

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textView.setText("$progress%")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        button.setOnClickListener {

            val accumulatedAmountEditText = v.findViewById<EditText>(R.id.editTextTotalRepay)
            val principalAmountEditText = v.findViewById<EditText>(R.id.editTextPrincipalAmount)
            val timeInYearsEditText = v.findViewById<EditText>(R.id.editTextDurationYears)
            val repaymentMonthEditText = v.findViewById<EditText>(R.id.editTextRepay)

            var accumulatedAmount: String = accumulatedAmountEditText.text.toString()
            var interestRate: String = textView.text.toString()
            var principalAmount: String = principalAmountEditText.text.toString()
            var timeInYears: String = timeInYearsEditText.text.toString()
            var repaymentMonth: String = repaymentMonthEditText.text.toString()

            if (accumulatedAmount == "") {
                accumulatedAmount = "NaN"
            }
            interestRate = if (interestRate == "") { "NaN" } else {
                interestRate.substring(0, interestRate.length - 1)
            }
            if (principalAmount == "") {
                principalAmount = "NaN"
            }
            if (timeInYears == "") {
                timeInYears = "NaN"
            }
            if (repaymentMonth == "") {
                repaymentMonth = "NaN"
            }

            try {
                val loaner = Loan(accumulatedAmount, interestRate, principalAmount, timeInYears, repaymentMonth)
                val output: String = loaner.toString()
                answer.text = output
            }
            catch (e: InsufficientLoanInformationException) {
                Toast.makeText(activity, e.toString(), Toast.LENGTH_LONG).show()
            }
        }

        return v
    }
}