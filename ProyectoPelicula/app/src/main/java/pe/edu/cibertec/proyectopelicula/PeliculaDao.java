package pe.edu.cibertec.proyectopelicula;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PeliculaDao {
    @Insert
    void insertContacts(Pelicula... peliculas);

    @Update
    void updateContact(Pelicula peliculas);

    @Delete
    void deleteContact(Pelicula peliculas);

    @Query("select * from pelicula")
    List<Pelicula> getAll();

    @Query("select * from Pelicula where:nombre")
    List<Pelicula> searchByName(String nombre);
}
