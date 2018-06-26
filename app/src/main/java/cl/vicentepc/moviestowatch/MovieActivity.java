package cl.vicentepc.moviestowatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import cl.vicentepc.moviestowatch.models.Movie;

public class MovieActivity extends AppCompatActivity {

    private CheckBox watchedCb;
    private Movie movie;

    private TextView movieNameResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        movieNameResult = findViewById(R.id.movieNameResult);

        Intent intent = getIntent();

        long movieId = intent.getLongExtra(MainActivity.MOVIE_ID, 0);
        Log.e("MOVIEID", String.valueOf(movieId));

        movie = Movie.findById(Movie.class, movieId);

        movieNameResult.setText(movie.getName());

        getSupportActionBar().setTitle(movie.getName());

        Log.e("TAG", movie.getName());

        watchedCb = findViewById(R.id.watchedCb);

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(watchedCb.isChecked()){
            movie.setWatched(true);
            movie.save();
            Toast.makeText(this, "Dato actualizado", Toast.LENGTH_LONG).show();
        }

    }
}
