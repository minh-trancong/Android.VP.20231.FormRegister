package com.example.formregister

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtu.formregister.adapter.UserCardAdapter
import com.mtu.formregister.model.User
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_CODE = 123 // Use any unique request code
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserCardAdapter

    @SuppressLint("SimpleDateFormat")
    private var simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    private var userList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // populate the list
        userList.add(
            User(
                firstName = "Cong Minh 1",
                lastName = "TRAN",
                sex = true,
                dob = simpleDateFormat.parse("25/01/2002"),
                email = "tc.tranminh@gmail.com",
                address = "abcded"
            )
        )
        userList.add(
            User(
                firstName = "Cong Minh 2",
                lastName = "TRAN",
                sex = true,
                dob = simpleDateFormat.parse("25/01/2002"),
                email = "tc.tranminh@gmail.com",
                address = "abcded"
            )
        )
        userList.add(
            User(
                firstName = "Cong Minh 3",
                lastName = "TRAN",
                sex = true,
                dob = simpleDateFormat.parse("25/01/2002"),
                email = "tc.tranminh@gmail.com",
                address = "abcded"
            )
        )

        recyclerView = findViewById(R.id.rv_cards)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserCardAdapter(userList)
        recyclerView.adapter = adapter

        val btnAddUser: Button = findViewById(R.id.btn_addUser)
        btnAddUser.setOnClickListener {
            val intent = Intent(this, RegisterUser::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val newUser = data?.let {
                User(
                    data.getStringExtra("newUserFirstname").toString(),
                    data.getStringExtra("newUserLastname").toString(),
                    it.getBooleanExtra("newUserGender", true),
                    simpleDateFormat.parse(data.getStringExtra("newUserDob").toString())!!,
                    data.getStringExtra("newUserAddress").toString(),
                    data.getStringExtra("newUserEmail").toString()
                )
            }
            if (newUser != null) {
                userList.add(newUser)
                adapter.notifyItemInserted(userList.size - 1)
            }
        }
    }


}