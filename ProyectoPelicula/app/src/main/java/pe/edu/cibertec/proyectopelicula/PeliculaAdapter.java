package pe.edu.cibertec.proyectopelicula;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaCelda> {

    List<Pelicula> peliculas;

    public PeliculaAdapter(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public PeliculaAdapter.PeliculaCelda onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_pelicula_buscar,viewGroup,false);

        return new PeliculaCelda(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaAdapter.PeliculaCelda peliculaCelda, int i) {
        //peliculaCelda.tvNombre.setText(contacts.get(posicion).getName());
        peliculaCelda.texttitle.setText(peliculas.get(i).getTitle());
        peliculaCelda.txtAnio.setText(peliculas.get(i).getYear());
        peliculaCelda.textresumen.setText(peliculas.get(i).getPlot());
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public class PeliculaCelda extends RecyclerView.ViewHolder {
        TextView texttitle,txtAnio,textresumen;

        public PeliculaCelda(@NonNull View itemView) {
            super(itemView);
            texttitle = itemView.findViewById(R.id.texttitle);
            txtAnio = itemView.findViewById(R.id.txtAnio);
            textresumen = itemView.findViewById(R.id.textresumen);
        }
    }
}
