package com.example.formregister

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mtu.formregister.model.User
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class RegisterUser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_form)


        // only select one sex
        val radioGroup: RadioGroup = findViewById(R.id.genderRadioGroup)
        val rdBtnMale: RadioButton = findViewById(R.id.rdBtn_male)
        val rdBtnFemale: RadioButton = findViewById(R.id.rdBtn_female)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            {
                when (checkedId) {
                    R.id.rdBtn_female -> {
                        rdBtnMale.isChecked = false
                        rdBtnFemale.isChecked = true
                    }

                    R.id.rdBtn_male -> {
                        rdBtnMale.isChecked = true
                        rdBtnFemale.isChecked = false
                    }
                }
            }
        }

        // set birthday by select
        val etBirthday: EditText = findViewById(R.id.et_birthday)
        val btnSelectDate: Button = findViewById(R.id.btn_selectBirthday)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        btnSelectDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                    val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    etBirthday.setText(formattedDate)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        // validate and create intent for user
        val btnRegisterUser: Button = findViewById(R.id.btn_submitRegister)
        val et_brithday: EditText = findViewById(R.id.et_birthday)
        btnRegisterUser.setOnClickListener {
            if (validateForm()) {
                val user = createUserFromForm()
                val resultIntent = Intent()
                resultIntent.putExtra("newUserFirstname", user.firstName)
                resultIntent.putExtra("newUserLastname", user.lastName)
                resultIntent.putExtra("newUserGender", user.sex)
                resultIntent.putExtra("newUserDob", et_brithday.text.toString())
                resultIntent.putExtra("newUserAddress", user.address)
                resultIntent.putExtra("newUserEmail", user.email)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

    }

    // function to check if all fields is filled or not
    private fun validateForm(): Boolean {
        val et_firstName: EditText = findViewById(R.id.et_firstName)
        val et_lastName: EditText = findViewById(R.id.et_lastName)
        val radioGroupGender: RadioGroup = findViewById(R.id.genderRadioGroup)
        val et_brithday: EditText = findViewById(R.id.et_birthday)
        val et_address: EditText = findViewById(R.id.et_address)
        val et_email: EditText = findViewById(R.id.et_email)
        val checkBox: CheckBox = findViewById(R.id.checkbox_agree)

        if (et_firstName.text.isEmpty() || et_lastName.text.isEmpty()
            || radioGroupGender.checkedRadioButtonId == -1
            || et_brithday.text.isEmpty() || et_address.text.isEmpty() || et_email.text.isEmpty()
            || !checkBox.isChecked
        ) {
            Toast.makeText(
                this,
                "Please fill in all fields and agree to the terms.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        return true
    }

    private fun createUserFromForm(): User {
        val et_firstName: EditText = findViewById(R.id.et_firstName)
        val et_lastName: EditText = findViewById(R.id.et_lastName)
        val radioGroupGender: RadioGroup = findViewById(R.id.genderRadioGroup)
        val et_brithday: EditText = findViewById(R.id.et_birthday)
        val et_address: EditText = findViewById(R.id.et_address)
        val et_email: EditText = findViewById(R.id.et_email)
        val checkBox: CheckBox = findViewById(R.id.checkbox_agree)

        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        val userDob: Date = simpleDateFormat.parse(et_brithday.text.toString())
        val userSex: Boolean = radioGroupGender.checkedRadioButtonId == R.id.rdBtn_male
        return User(
            firstName = et_firstName.text.toString(),
            lastName = et_lastName.text.toString(),
            sex = userSex,
            dob = userDob,
            address = et_address.text.toString(),
            email = et_email.text.toString()
        )
    }
}