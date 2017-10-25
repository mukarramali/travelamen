package prashushi.travelamen.utils;

import prashushi.travelamen.model.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Dell User on 1/4/2017.
 */

public interface ApiInterface {
    @GET("popular/")
    Call<Post> getWebsites(@Query("size") int size);

//    @GET("movie/{id}")
  //  Call<WebSite> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
