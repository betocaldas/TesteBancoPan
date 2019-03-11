package corp.bcapc.top100pan

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import corp.bcapc.top100pan.databinding.ActivityDetailBinding
import corp.bcapc.top100pan.model.Rank

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var rank: Rank

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        rank = intent.getParcelableExtra(Rank.TAG)
        binding.rank = rank

        binding.fimgGame.setImageURI(rank.game.box.large)
    }

}
