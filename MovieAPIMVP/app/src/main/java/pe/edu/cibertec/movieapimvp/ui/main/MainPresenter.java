package pe.edu.cibertec.movieapimvp.ui.main;

import pe.edu.cibertec.movieapimvp.data.network.ApiClient;
import pe.edu.cibertec.movieapimvp.data.network.ApiInterface;
import pe.edu.cibertec.movieapimvp.data.network.model.Movie;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.MainPresenter{

    MainContract.MainView mainView;

    public MainPresenter(MainContract.MainView mainview) {
        this.mainView = mainview;
    }

    @Override
    public void searchMovie(String name) {

//        btSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {

                String apikey = "6fc43ba7";
                //String name = etMovie.getText().toString();
//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl("https://www.omdbapi.com/")
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<Movie> methodSearch = apiInterface.searchMovie(apikey, name);

                methodSearch.enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (response.isSuccessful()) {
                            Movie movie = response.body();
                            MainPresenter.this.mainView.showMovie(movie);
//                            tvTitle.setText(movie.getTitle());
//                            tvYear.setText(movie.getYear());
//                            tvPlot.setText(movie.getPlot());

//                            Glide.with(MainActivity.this)
//                                    .load(movie.getPoster())
//                                    .into(ivPoster);
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {

                    }
                });

//
//            }
//        });

    }
}
