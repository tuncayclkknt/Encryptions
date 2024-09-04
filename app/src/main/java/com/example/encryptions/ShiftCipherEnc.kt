package com.example.encryptions

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.encryptions.databinding.ActivityShiftcipherencBinding

class ShiftCipherEnc : AppCompatActivity() {
    private lateinit var binding: ActivityShiftcipherencBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShiftcipherencBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val e1 = EncMethods()
        e1.initializeAlphabet()

        binding.button.setOnClickListener {
            val message: String
            if (binding.editTextTextMultiLine.text.isNotEmpty()){
                message = binding.editTextTextMultiLine.text.toString()
                if (checkNumbers(message))
                    Toast.makeText(this,"Your message cannot include numbers.",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Please enter a text without turkish chars.",Toast.LENGTH_LONG).show()
                message = " "
            }

            val shiftTimes: Int
            if (binding.editTextNumber.text.isNotEmpty()){

                shiftTimes = Integer.parseInt(binding.editTextNumber.text.toString())
                if (shiftTimes in 0..26)
                    binding.textView2.text = e1.shiftCipher(message, shiftTimes)
                else
                    Toast.makeText(this,"Please enter a number between 0 and 26.",Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(this,"Please enter a number.",Toast.LENGTH_LONG).show()
        }
    }

    private fun checkNumbers(text: String): Boolean{
        val numbersString: String = "1234567890"
        for (i in text.indices){
            if (numbersString.contains(text[i]))
                return true
        }
        return false
    }

}