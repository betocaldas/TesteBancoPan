package corp.bcapc.top100pan

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import corp.bcapc.top100pan.databinding.RowRankBinding
import corp.bcapc.top100pan.model.Rank

class TwitchAdapter(val listener: TwitchClick): PagedListAdapter<Rank, TwitchViewHolder>(TwitchDiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwitchViewHolder {
        val binding = RowRankBinding.inflate(LayoutInflater.from(parent.context))
        return TwitchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TwitchViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, listener)
        }
    }

}

class TwitchViewHolder(private val binding: RowRankBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(rank: Rank, listener: TwitchClick) {
        binding.game = rank.game
        binding.ranking = layoutPosition+1
        binding.fimgGame.setImageURI(rank.game.box.large)
        binding.executePendingBindings()
        binding.root.setOnClickListener { listener.onClick(rank) }
    }
}

interface TwitchClick {
    fun onClick(rank: Rank)
}

class TwitchDiffUtilCallBack : DiffUtil.ItemCallback<Rank>() {
    override fun areItemsTheSame(oldItem: Rank, newItem: Rank): Boolean {
        return oldItem.game.name == newItem.game.name
    }

    override fun areContentsTheSame(oldItem: Rank, newItem: Rank): Boolean {
        return oldItem.game.box.large == newItem.game.box.large
    }
}