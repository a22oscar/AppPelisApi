import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.griddepelis.R
import com.example.griddepelis.TmdbMovie

class PeliAdapter(private var movies: List<TmdbMovie>) :
    RecyclerView.Adapter<PeliAdapter.PeliViewHolder>() {

    inner class PeliViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    fun setData(newData: List<TmdbMovie>) {
        movies = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_peli, parent, false)
        return PeliViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PeliViewHolder, position: Int) {
        val movie = movies[position]

        // Utiliza Glide para cargar la imagen desde la URL proporcionada por la API
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
            .placeholder(R.drawable.placeholder_image)
            .into(holder.imageView)

        holder.textView.text = movie.title
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

