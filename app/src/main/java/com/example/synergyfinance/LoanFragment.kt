package com.example.synergyfinance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment

class LoanFragment : Fragment() {

    private lateinit var seekBar: SeekBar
    private lateinit var editText: EditText

    /*private var listener: LoanFragmentListener? = null

    interface LoanFragmentListener {
        fun onInputSent(input: CharSequence)
    }*/

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_loan, container, false)

        //listener?.onInputSent("Loan Calculator")

        seekBar = v.findViewById(R.id.seekBar)
        editText = v.findViewById(R.id.editTextInterestRate)

        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                editText.setText("$progress%")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        return v
    }

    /*override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (context is LoanFragmentListener) {
            context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement LoanFragmentListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }*/
}