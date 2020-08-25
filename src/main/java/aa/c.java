package aa;

import java.io.File;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class c<T> implements Callback<T> {
    public void a(long j) {
    }

    public void a(File file, long j, long j2) {
    }

    public abstract void a(Call<T> call, Response<T> response);

    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            a(call, response);
        } else {
            onFailure(call, new Throwable(response.message()));
        }
    }
}
