package pe.edu.cibertec.proyectopelicula;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PeliculaInterface {
    @GET("?")
    Call<Pelicula> searchPelicula(@Query("apikey") String apikey, @Query("t") String filtro);
}
