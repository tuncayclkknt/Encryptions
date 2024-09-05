package com.example.encryptions

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.encryptions.databinding.ActivityDencShiftCipherBinding

class DencShiftCipher : AppCompatActivity() {
    private lateinit var binding: ActivityDencShiftCipherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDencShiftCipherBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val e1 = EncMethods()
        e1.initializeAlphabet()

        binding.button5.setOnClickListener {
            val message: String
            if (binding.editTextTextMultiLine2.text.isNotEmpty()){
                message = binding.editTextTextMultiLine2.text.toString()
                if (e1.checkNumbers(message))
                    Toast.makeText(this,"Your message cannot include numbers.", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Please enter a text without turkish chars.", Toast.LENGTH_LONG).show()
                message = " "
            }

            val shiftTimes: Int
            if (binding.editTextNumber2.text.isNotEmpty()){

                shiftTimes = Integer.parseInt(binding.editTextNumber2.text.toString())
                if (shiftTimes in 0..26)
                    binding.textView.text = e1.dencShiftCipher(message, shiftTimes).toString()
                else
                    Toast.makeText(this,"Please enter a number between 0 and 26.", Toast.LENGTH_LONG).show()
            }
            else{
                binding.textView.text = e1.dencShiftCipher(message).toString()
            }
        }
    }
}