package corp.bcapc.top100pan

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import corp.bcapc.top100pan.databinding.ActivityMainBinding
import corp.bcapc.top100pan.model.Rank

class MainActivity : AppCompatActivity(), TwitchClick {

    lateinit var twitchViewModel: TwitchViewModel
    lateinit var binding: ActivityMainBinding
    val adapter = TwitchAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        twitchViewModel = ViewModelProviders.of(this).get(TwitchViewModel::class.java)

        binding.rvRank.layoutManager = GridLayoutManager(this, 2,
            GridLayoutManager.VERTICAL, false)
        binding.rvRank.adapter = adapter
        binding.rvRank.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        twitchViewModel.liveData.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    override fun onClick(rank: Rank) {
        startActivity(Intent(this, DetailActivity::class.java).apply { putExtra(Rank.TAG, rank) })
    }

}
