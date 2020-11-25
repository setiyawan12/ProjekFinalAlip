package com.setiyawan.finalprojectalip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.setiyawan.finalprojectalip.adapter.UserAdapter
import com.setiyawan.finalprojectalip.model.realm.User
import io.realm.Realm
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var userAdapter:UserAdapter
    lateinit var realm: Realm
    val lm = LinearLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initview()
        btn_add.setOnClickListener {
            realm.beginTransaction()
            var count = 0
            realm.where(User::class.java).findAll().let {
                for (i in it){
                    count ++
                }
            }
            try {
                var user = realm.createObject(User::class.java)
                user.setId(count+1)
                user.setNama(et_nama.text.toString())
                user.setEmail(et_email.text.toString())
                et_nama.setText("")
                et_email.setText("")
                realm.commitTransaction()
                getAllUser()
            }catch (e : RealmException){
            }
        }
        btn_update.setOnClickListener {
            realm.beginTransaction()
            realm.where(User::class.java).equalTo("id",et_id.text.toString().toInt()).findFirst().let {
                it!!.setNama(et_nama.text.toString())
                it!!.setEmail(et_email.text.toString())
            }
            realm.commitTransaction()
            getAllUser()
        }
        btn_delete.setOnClickListener {
            realm.beginTransaction()
            realm.where(User::class.java).equalTo("id",et_id.text.toString().toInt()).findFirst().let {
                it!!.deleteFromRealm()
            }
            realm.commitTransaction()
            getAllUser()
        }
    }

    private fun initview(){
        rv_user.layoutManager =lm
        userAdapter =UserAdapter(this)
        rv_user.adapter = userAdapter
        Realm.init(applicationContext)
        realm = Realm.getDefaultInstance()
        getAllUser()
    }
    private fun getAllUser(){
        realm.where(User::class.java).findAll().let {
            userAdapter.setUser(it)
        }
    }

}