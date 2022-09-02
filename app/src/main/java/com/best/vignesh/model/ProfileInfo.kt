package com.best.vignesh.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


// tableName is your table name
@Entity(tableName = "ProfileInfo")
@Parcelize
data class ProfileInfo(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "age") var age: String,
    @ColumnInfo(name = "language") var language: String,
    @ColumnInfo(name = "caste") var caste: String,
    @ColumnInfo(name = "height") var height: String,
    @ColumnInfo(name = "qualification") var qualification: String,
    @ColumnInfo(name = "job") var job: String,
    @ColumnInfo(name = "city") var city: String,
    @ColumnInfo(name = "state") var state: String,
    @ColumnInfo(name = "country") var country: String,
    @ColumnInfo(name = "pro_image") var pro_image: String
    //@ColumnInfo(name = "pro_images") var pro_images: Array<String>? = null,
): Parcelable
{
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}


