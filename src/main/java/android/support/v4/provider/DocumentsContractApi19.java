package android.support.v4.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;

@RequiresApi(19)
class DocumentsContractApi19 {
    private static final int FLAG_VIRTUAL_DOCUMENT = 512;
    private static final String TAG = "DocumentFile";

    DocumentsContractApi19() {
    }

    public static boolean isDocumentUri(Context context, Uri uri) {
        return DocumentsContract.isDocumentUri(context, uri);
    }

    public static boolean isVirtual(Context context, Uri uri) {
        if (isDocumentUri(context, uri) && (getFlags(context, uri) & 512) != 0) {
            return true;
        }
        return false;
    }

    public static String getName(Context context, Uri uri) {
        return queryForString(context, uri, "_display_name", (String) null);
    }

    private static String getRawType(Context context, Uri uri) {
        return queryForString(context, uri, "mime_type", (String) null);
    }

    public static String getType(Context context, Uri uri) {
        String rawType = getRawType(context, uri);
        if ("vnd.android.document/directory".equals(rawType)) {
            return null;
        }
        return rawType;
    }

    public static long getFlags(Context context, Uri uri) {
        return queryForLong(context, uri, "flags", 0);
    }

    public static boolean isDirectory(Context context, Uri uri) {
        return "vnd.android.document/directory".equals(getRawType(context, uri));
    }

    public static boolean isFile(Context context, Uri uri) {
        String rawType = getRawType(context, uri);
        return !"vnd.android.document/directory".equals(rawType) && !TextUtils.isEmpty(rawType);
    }

    public static long lastModified(Context context, Uri uri) {
        return queryForLong(context, uri, "last_modified", 0);
    }

    public static long length(Context context, Uri uri) {
        return queryForLong(context, uri, "_size", 0);
    }

    public static boolean canRead(Context context, Uri uri) {
        return context.checkCallingOrSelfUriPermission(uri, 1) == 0 && !TextUtils.isEmpty(getRawType(context, uri));
    }

    public static boolean canWrite(Context context, Uri uri) {
        if (context.checkCallingOrSelfUriPermission(uri, 2) != 0) {
            return false;
        }
        String rawType = getRawType(context, uri);
        int queryForInt = queryForInt(context, uri, "flags", 0);
        if (TextUtils.isEmpty(rawType)) {
            return false;
        }
        if ((queryForInt & 4) != 0) {
            return true;
        }
        if ("vnd.android.document/directory".equals(rawType) && (queryForInt & 8) != 0) {
            return true;
        }
        if (TextUtils.isEmpty(rawType) || (queryForInt & 2) == 0) {
            return false;
        }
        return true;
    }

    public static boolean exists(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        boolean z = false;
        Cursor cursor = null;
        try {
            Cursor query = contentResolver.query(uri, new String[]{"document_id"}, (String) null, (String[]) null, (String) null);
            try {
                if (query.getCount() > 0) {
                    z = true;
                }
                closeQuietly(query);
                return z;
            } catch (Exception e) {
                e = e;
                cursor = query;
                try {
                    Log.w(TAG, "Failed query: " + e);
                    closeQuietly(cursor);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    closeQuietly(cursor);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = query;
                closeQuietly(cursor);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            Log.w(TAG, "Failed query: " + e);
            closeQuietly(cursor);
            return false;
        }
    }

    private static String queryForString(Context context, Uri uri, String str, String str2) {
        Cursor cursor = null;
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{str}, (String) null, (String[]) null, (String) null);
            try {
                if (!query.moveToFirst() || query.isNull(0)) {
                    closeQuietly(query);
                    return str2;
                }
                String string = query.getString(0);
                closeQuietly(query);
                return string;
            } catch (Exception e) {
                e = e;
                cursor = query;
                try {
                    Log.w(TAG, "Failed query: " + e);
                    closeQuietly(cursor);
                    return str2;
                } catch (Throwable th) {
                    th = th;
                    closeQuietly(cursor);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = query;
                closeQuietly(cursor);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            Log.w(TAG, "Failed query: " + e);
            closeQuietly(cursor);
            return str2;
        }
    }

    private static int queryForInt(Context context, Uri uri, String str, int i) {
        return (int) queryForLong(context, uri, str, (long) i);
    }

    private static long queryForLong(Context context, Uri uri, String str, long j) {
        Cursor cursor = null;
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{str}, (String) null, (String[]) null, (String) null);
            try {
                if (!query.moveToFirst() || query.isNull(0)) {
                    closeQuietly(query);
                    return j;
                }
                long j2 = query.getLong(0);
                closeQuietly(query);
                return j2;
            } catch (Exception e) {
                e = e;
                cursor = query;
                try {
                    Log.w(TAG, "Failed query: " + e);
                    closeQuietly(cursor);
                    return j;
                } catch (Throwable th) {
                    th = th;
                    closeQuietly(cursor);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = query;
                closeQuietly(cursor);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            Log.w(TAG, "Failed query: " + e);
            closeQuietly(cursor);
            return j;
        }
    }

    private static void closeQuietly(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }
}
