package com.music.mazika.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.music.mazika.model.SongModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val song: DatabaseReference,
    private val boolean: MutableLiveData<Boolean>,
    private val string: MutableLiveData<String>,
    private val songModel: ArrayList<SongModel>,
    private val songs: MutableLiveData<ArrayList<SongModel>>
) : ViewModel() {


    fun getAllSongs(): LiveData<ArrayList<SongModel>> {
        song
            .child("Songs")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    songModel.clear()
                    for (snap in snapshot.children) {
                        val model = snap.getValue(SongModel::class.java)
                        songModel.add(model!!)
                    }
                    songs.postValue(songModel)
                    boolean.postValue(true)
                }

                override fun onCancelled(error: DatabaseError) {
                    boolean.postValue(false)
                    string.value = error.message
                }
            })
        return songs
    }


}