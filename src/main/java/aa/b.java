package aa;

import java.io.File;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class b {
    public static MultipartBody a(List<File> list, c<Object> cVar) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (File next : list) {
            builder.addFormDataPart("file", next.getName(), new a(RequestBody.create(MediaType.parse("img/png"), next), cVar, next));
        }
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }
}
