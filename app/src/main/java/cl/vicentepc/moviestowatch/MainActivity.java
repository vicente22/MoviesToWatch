package cl.vicentepc.moviestowatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cl.vicentepc.moviestowatch.models.Movie;

public class MainActivity extends AppCompatActivity {

    private List<Movie> moviesList;

    private EditText nameMovieEt;
    private Button saveBtn;
    private Button lastMovieBtn;

    public static final String MOVIE_ID = "cl.vicentepc.moviestowatch.KEY.MOVIE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameMovieEt = findViewById(R.id.movieNameEt);
        saveBtn = findViewById(R.id.saveBtn);
        lastMovieBtn = findViewById(R.id.lastMovieBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameMovie = nameMovieEt.getText().toString();

                if(nameMovie.trim().length() > 0){

                    List<Movie> checkMovie = Movie.find(Movie.class, "name = ?", nameMovie);

                    if(checkMovie.size() > 0){

                        Toast.makeText(MainActivity.this, "Esta pelicula ya existe", Toast.LENGTH_LONG).show();

                    }else{

                        Movie newMovie = new Movie();
                        newMovie.setName(nameMovie);
                        newMovie.setWatched(false);
                        newMovie.save();
                        moviesList = getMovies();
                        nameMovieEt.setText("");
                        Toast.makeText(MainActivity.this, "Pelicula guardada con exito", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(MainActivity.this, "Campo película está vacío", Toast.LENGTH_SHORT).show();
                }
            }
        });
        lastMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = moviesList.size();
                if(count > 0){
                    long movieId = moviesList.get(count-1).getId();
                    Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                    intent.putExtra(MOVIE_ID, movieId);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "NO HAY PELICULAS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public List<Movie> getMovies(){
        return Movie.find(Movie.class, "watched = ?", "0");
    }

    @Override
    protected void onResume() {
        super.onResume();
        moviesList = getMovies();
    }
}
