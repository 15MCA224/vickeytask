package com.best.vignesh.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.best.vignesh.R
import com.best.vignesh.databinding.ActivityGestureScreenBinding
import com.best.vignesh.databinding.ActivityProfileDetailsScreenBinding
import com.best.vignesh.model.ProfileInfo
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

class ProfileDetailsScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileDetailsScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_profile_details_screen)
        binding = ActivityProfileDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageList = ArrayList<SlideModel>()
        val item = intent.getParcelableExtra("ProfileInfo") as? ProfileInfo

        if(item!=null) {
            binding.mDetails.text =
                item.age.toString() + "Yrs, " + item.height + ", " + item.language + ", " + item.caste + ", " + item.qualification + ", " + item.job + ", " + item.city + ", " + item.state + ", " + item.country + "."
            binding.profileid.text = "142" + item.name + "345"
            val uri = "@drawable/" + item.pro_image
            val imageResource = resources.getIdentifier(
                uri,
                null,
                this@ProfileDetailsScreenActivity.getPackageName()
            )
            for (i in 0..5) {
                imageList.add(SlideModel(imageResource, ""))
            }



            binding.imageSlider.setImageList(imageList, ScaleTypes.FIT)
            binding.mName.setText(item.name)
        }else
        {
            Toast.makeText(this@ProfileDetailsScreenActivity, "Nodata", Toast.LENGTH_LONG)
                .show()
        }


        binding.back.setOnClickListener(View.OnClickListener {
//            val fram = requireActivity().supportFragmentManager.beginTransaction()
//            fram.replace(
//                com.best.vignesh.R.id.nav_host_fragment_content_main,
//                HomeFragment()
//            )
//            fram.addToBackStack(null);
//            fram.commit()
            //findNavController().navigateUp()
            // Navigation.findNavController(requireView()).popBackStack()
            //findNavController().navigate(R.id.action_profileDetails_pop)
            onBackPressed()
            // findNavController().navigate(R.id.action_profileDetails_pop)
        })

    }
}