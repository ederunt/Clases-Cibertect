package pe.edu.cibertec.proyectopelicula;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import pe.edu.cibertec.proyectopelicula.Pelicula;
import pe.edu.cibertec.proyectopelicula.PeliculaDao;


@Database(entities = {Pelicula.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instancia;

    public static AppDatabase nuevaInstancia(Context contexto) {
        if (instancia == null) {
            instancia = Room.databaseBuilder(contexto, AppDatabase.class, "pelicula.db")
                    .build();
        }
        return instancia;
    }

    public abstract PeliculaDao getPeliculaDao();

}
