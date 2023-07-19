package com.osamaaftab.nutmeg.presentation.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.osamaaftab.nutmeg.R
import com.osamaaftab.nutmeg.databinding.ActivityAlbumListBinding
import com.osamaaftab.nutmeg.domain.model.AlbumModel
import com.osamaaftab.nutmeg.presentation.adapter.GenericListAdapter
import com.osamaaftab.nutmeg.presentation.diffutil.AlbumDiffUtil
import com.osamaaftab.nutmeg.presentation.viewholder.AlbumViewHolder
import com.osamaaftab.nutmeg.presentation.viewmodel.AlbumViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumListActivity : AppCompatActivity() {

    private val albumViewModel: AlbumViewModel by viewModel()

    private var albumListAdapter: GenericListAdapter<AlbumModel>? = null
    private lateinit var activityAlbumListBinding: ActivityAlbumListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAlbumListBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_album_list)
        initAdapter()
        initObserver()
    }

    private fun initAdapter() {
        albumListAdapter = object : GenericListAdapter<AlbumModel>(AlbumDiffUtil()) {

            override fun getViewHolder(viewDataBinding: ViewDataBinding): RecyclerView.ViewHolder {
                return AlbumViewHolder<AlbumModel>(
                    viewDataBinding
                )
            }

            override fun getLayoutId(): Int {
                return R.layout.item_album
            }
        }
        activityAlbumListBinding.albumRecyclerView.adapter = albumListAdapter
    }

    private fun initObserver() {
        observeAlbumListLiveData()
        observerProgressLiveData()
        observeShowErrorLiveData()
    }

    private fun observeShowErrorLiveData() {
        albumViewModel.getShowErrorLiveData().observe(this) {
            if (it == true) {
                activityAlbumListBinding.statusLbl.visibility = View.VISIBLE
            } else activityAlbumListBinding.statusLbl.visibility = View.GONE
        }
    }

    private fun observerProgressLiveData() {
        albumViewModel.getShowProgressLiveData().observe(this) {
            if (it == true) {
                activityAlbumListBinding.indeterminateBar.visibility = View.VISIBLE
            } else activityAlbumListBinding.indeterminateBar.visibility = View.GONE
        }
    }

    private fun observeAlbumListLiveData() {
        albumViewModel.getAlbumListLiveData().observe(this) {
            albumListAdapter?.submitList(it.reversed()) {
                activityAlbumListBinding.albumRecyclerView.scrollToPosition(0)
            }
            activityAlbumListBinding.albumRecyclerView.visibility = View.VISIBLE
        }
    }
}