package pe.edu.cibertec.movieapimvp.ui.main;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import pe.edu.cibertec.movieapimvp.R;
import pe.edu.cibertec.movieapimvp.data.network.model.Movie;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    TextInputEditText etMovie;
    Button btSearch;
    TextView tvTitle, tvYear, tvPlot;
    ImageView ivPoster;

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListeners();
        configureMvp();
    }

    private void configureMvp() {
        this.presenter = new MainPresenter(this);
    }

    private void setListeners() {

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.presenter.searchMovie(etMovie.getText().toString());
//
//                String apikey = "6fc43ba7";
//                String name = etMovie.getText().toString();
//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("https://www.omdbapi.com/")
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//                ApiInterface movieInterface = retrofit.create(ApiInterface.class);
//                Call<Movie> methodSearch = movieInterface.searchMovie(apikey, name);
//
//                methodSearch.enqueue(new Callback<Movie>() {
//                    @Override
//                    public void onResponse(Call<Movie> call, Response<Movie> response) {
//                        if (response.isSuccessful()) {
//                            Movie movie = response.body();
//
////                            tvTitle.setText(movie.getTitle());
////                            tvYear.setText(movie.getYear());
////                            tvPlot.setText(movie.getPlot());
//
//                            Glide.with(MainActivity.this)
//                                    .load(movie.getPoster())
//                                    .into(ivPoster);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Movie> call, Throwable t) {
//
//                    }
//                });
//
//
            }
        });

    }

    private void initView() {
        etMovie = findViewById(R.id.etMovie);
        btSearch = findViewById(R.id.btSearch);
        tvTitle = findViewById(R.id.tvTitle);
        tvYear = findViewById(R.id.tvYear);
        tvPlot = findViewById(R.id.tvPlot);
        ivPoster = findViewById(R.id.ivPoster);
    }

    @Override
    public void showMovie(Movie movie) {
        tvTitle.setText(movie.getTitle());
        tvYear.setText(movie.getYear());
        tvPlot.setText(movie.getPlot());

        Glide.with(MainActivity.this)
                .load(movie.getPoster())
                .into(ivPoster);
    }
}