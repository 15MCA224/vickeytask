package com.best.vignesh.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.best.vignesh.R
import com.best.vignesh.databinding.ActivityGestureScreenBinding
import com.best.vignesh.model.AppDatabase
import com.best.vignesh.model.ProfileInfo
import com.google.android.material.chip.Chip
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import link.fls.swipestack.SwipeStack

class GestureScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGestureScreenBinding;

    // This property is only valid between onCreateView and
    // onDestroyView.

    lateinit var mProfileListAdapter: ProfileSwipeAdapter;
    lateinit var db: AppDatabase;
    var slideLenght = 0
    var data: MutableList<ProfileInfo>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_gesture_screen)
        binding = ActivityGestureScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(this)

        mProfileListAdapter = ProfileSwipeAdapter()
        binding.swipeStack.adapter = mProfileListAdapter

        binding.back.setOnClickListener(View.OnClickListener {
         onBackPressed()
        })





        binding.swipeStack.setListener(object : SwipeStack.SwipeStackListener {
            override fun onViewSwipedToLeft(position: Int) {
                Log.e("kdkcleft", position.toString())


                if ((position) == data!!.size - 1) {
                   onBackPressed()
                }
            }

            override fun onViewSwipedToRight(position: Int) {
                if ((position) == data!!.size - 1) {
                   onBackPressed()
                }
            }

            override fun onStackEmpty() {}
        })




        GlobalScope.launch {
            get()
        }



    }

    suspend fun get() =   // Dispatchers.Main
        withContext(Dispatchers.IO) {              // Dispatchers.IO (main-safety block)
            data = db.getProfileInfoDao().getList()
        }




    inner class ProfileSwipeAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return data!!.size
        }

        override fun getItem(p0: Int): Any {
            TODO("Not yet implemented")

        }

        override fun getItemId(p0: Int): Long {
            TODO("Not yet implemented")


        }

        @SuppressLint("ViewHolder")
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View? {

            //mYes
            var view: View? = null




            view = layoutInflater.inflate(R.layout.swipescreen_details, p2, false)

            val yes = view.findViewById<Chip>(R.id.mYes)
            val no = view.findViewById<Chip>(R.id.mNo)
            val mName = view.findViewById<TextView>(R.id.mName);
            val mDetails = view.findViewById<TextView>(R.id.mDetails);
            val profile = view.findViewById<ImageView>(R.id.profile);

            mName.setText(data!!.get(p0).name)


            val uri =
                "@drawable/" + data!!.get(p0).pro_image

            val imageResource = resources.getIdentifier(uri, null, this@GestureScreenActivity.getPackageName())

            val res = resources.getDrawable(imageResource)


            profile.setImageDrawable(res)


            mDetails.setText(
                data!!.get(p0).age.toString() + "Yrs, " + data!!.get(p0).height + ", " + data!!.get(
                    p0
                ).language + ", " + data!!.get(p0).caste + ", " + data!!.get(p0).qualification + ", " + data!!.get(
                    p0
                ).job + ", " + data!!.get(p0).city + ", " + data!!.get(p0).state + ", " + data!!.get(
                    p0
                ).country + "."
            )





            yes.setOnClickListener(View.OnClickListener {
                binding.swipeStack.onViewSwipedToLeft()
                Toast.makeText(this@GestureScreenActivity, "This profile added in your Like list", Toast.LENGTH_LONG)
                    .show()
            })

            no.setOnClickListener(View.OnClickListener {
                binding.swipeStack.onViewSwipedToLeft()
                Toast.makeText(this@GestureScreenActivity, "Thanks for your support", Toast.LENGTH_LONG).show()
            })
            return view
        }


    }


}