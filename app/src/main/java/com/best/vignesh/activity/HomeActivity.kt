package com.best.vignesh.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.best.vignesh.databinding.ActivityHomeBinding
import com.best.vignesh.databinding.RowHomeBinding
import com.best.vignesh.model.AppDatabase
import com.best.vignesh.model.ProfileInfo
import kotlinx.coroutines.*

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding


    lateinit var mProfileListAdapter: ProfileListAdapter

    var data: MutableList<ProfileInfo>? = null
    lateinit var db: AppDatabase;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = AppDatabase.getDatabase(this)
        mProfileListAdapter = ProfileListAdapter()
        binding.mProfileList.adapter = mProfileListAdapter
        GlobalScope.launch {

            get()

        }
        binding!!.mMore.setOnClickListener {
            val intent = Intent(this@HomeActivity, GestureScreenActivity::class.java)
            startActivity(intent)
        }
    }

    suspend fun get() =                 // Dispatchers.Main
        withContext(Dispatchers.IO) {              // Dispatchers.IO (main-safety block)

            if (db.getProfileInfoDao().getList().size == 0)
                db.getProfileInfoDao().insertAll(mylist())
            delay(0);
            data = db.getProfileInfoDao().getList()
            Log.e("data", data!!.toString())

            withContext(Dispatchers.Main) {

                mProfileListAdapter.submitList(data)// Dispatchers.IO (main-safety block)
            }
        }


    fun mylist(): List<ProfileInfo> {

        val mylist = mutableListOf<ProfileInfo>()
        mylist.add(
            ProfileInfo(
                name = "Priya",
                age = "27",
                height = "5 ft 2 in",
                language = "Tamil",
                caste = "Nayakar",
                job = "MBBS",
                qualification = "Doctor",
                city = "Chennai",
                state = "Tamil Nadu",
                country = "India",
                pro_image = "priya"
            )
        )
        mylist.add(
            ProfileInfo(
                name = "Mohana",
                age = "28",
                height = "5 ft 3 in",
                language = "Tamil",
                caste = "Nair",
                job = "BE",
                qualification = "Teacher",
                city = "Coimbatore",
                state = "Tamil Nadu",
                country = "India",
                pro_image = "mohana"
            )
        )
        mylist.add(
            ProfileInfo(
                name = "Selvi",
                age = "29",
                height = "6 ft 2 in",
                language = "Telugu",
                caste = "Devar",
                job = "BSc",
                qualification = "Teacher",
                city = "Palani",
                state = "Tamil Nadu",
                country = "India",
                pro_image = "selvi"
            )
        )
        mylist.add(
            ProfileInfo(
                name = "Radha",
                age = "30",
                height = "5 ft 6 in",
                language = "Malayalam",
                caste = "Nair",
                job = "B.Tech",
                qualification = "Proffser",
                city = "Madurai",
                state = "Tamil Nadu",
                country = "India",
                pro_image = "radha"
            )
        )
        mylist.add(
            ProfileInfo(
                name = "Priyanka",
                age = "31",
                height = "5 ft 4 in",
                language = "Kannada",
                caste = "Chettiyar",
                job = "MBBS",
                qualification = "Doctor",
                city = "Kaniyur",
                state = "Tamil Nadu",
                country = "India",
                pro_image = "priyanka"
            )
        )

        return mylist

    }





    inner class ProfileListAdapter :
        ListAdapter<ProfileInfo, ProfileListAdapter.ItemViewholder>(DiffCallback()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
            return ItemViewholder(RowHomeBinding.inflate(LayoutInflater.from(parent.context)))
        }

        override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
            val data = getItem(position)
            val cxt = holder.itemView.context
            if (data != null) {
                holder.bind(data)
            }

        }


        inner class ItemViewholder(val bindingData: RowHomeBinding) :
            RecyclerView.ViewHolder(bindingData.root) {

            fun bind(item: ProfileInfo) {


                bindingData.profilecard.setOnClickListener(View.OnClickListener {
                    val intent = Intent(this@HomeActivity, ProfileDetailsScreenActivity::class.java)
                    intent.putExtra("ProfileInfo",item)
                    startActivity(intent)
                })

                bindingData.mYes.setOnClickListener(View.OnClickListener {
                    removeAt(adapterPosition)


                })

                bindingData.mNo.setOnClickListener(View.OnClickListener {
                    removeAt(adapterPosition)


                })

                val uri =
                    "@drawable/"+item.pro_image

                val imageResource = resources.getIdentifier(uri, null, this@HomeActivity!!.getPackageName())

                val res = resources.getDrawable(imageResource)


                bindingData.profile.setImageDrawable(res)

                bindingData.mName.text = item.name.toString()
                bindingData.mDetails.text =
                    item.age.toString() + "Yrs, " + item.height + ", " + item.language + ", " + item.caste + ", " + item.qualification + ", " + item.job + ", " + item.city + ", " + item.state + ", " + item.country + "."

            }
        }

        fun removeAt(position: Int) {
            data!!.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, data!!.size)


            Toast.makeText(this@HomeActivity, "Thanks for your support", Toast.LENGTH_SHORT).show()
        }

    }


    class DiffCallback : DiffUtil.ItemCallback<ProfileInfo>() {

        override fun areItemsTheSame(oldItem: ProfileInfo, newItem: ProfileInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProfileInfo, newItem: ProfileInfo): Boolean {
            return oldItem == newItem
        }

    }


}