package cat.ereza.customactivityoncrash.activity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.R;
import com.stub.StubApp;

public final class DefaultErrorActivity extends AppCompatActivity {
    static {
        StubApp.interface11(2726);
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"PrivateResource"})
    public native void onCreate(@Nullable Bundle bundle);

    /* access modifiers changed from: private */
    public void copyErrorToClipboard() {
        String allErrorDetailsFromIntent = CustomActivityOnCrash.getAllErrorDetailsFromIntent(this, getIntent());
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService("clipboard");
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(ClipData.newPlainText(getString(R.string.customactivityoncrash_error_activity_error_details_clipboard_label), allErrorDetailsFromIntent));
            Toast.makeText(this, R.string.customactivityoncrash_error_activity_error_details_copied, 0).show();
        }
    }
}
