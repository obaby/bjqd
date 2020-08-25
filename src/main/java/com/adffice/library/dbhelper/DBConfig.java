package com.adffice.library.dbhelper;

import android.content.Context;
import com.stub.StubApp;

public class DBConfig {
    final Context context;
    final String dbName;
    final String dbPath;
    final int dbVersion;
    final boolean debug;

    private DBConfig(Builder builder) {
        this.debug = builder.debug;
        this.context = builder.context;
        this.dbName = builder.dbName;
        this.dbPath = builder.dbPath;
        this.dbVersion = builder.dbVersion;
    }

    /* synthetic */ DBConfig(Builder builder, DBConfig dBConfig) {
        this(builder);
    }

    public static DBConfig createDefault(Context context2) {
        return new Builder(context2).build();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String dbName = "dbhelper.db";
        /* access modifiers changed from: private */
        public String dbPath;
        /* access modifiers changed from: private */
        public int dbVersion = 1;
        /* access modifiers changed from: private */
        public boolean debug = false;

        public Builder(Context context2) {
            this.context = StubApp.getOrigApplicationContext(context2.getApplicationContext());
        }

        public Builder dbName(String str) {
            this.dbName = str;
            return this;
        }

        public Builder dbVersion(int i) {
            this.dbVersion = i;
            return this;
        }

        public Builder dbPath(String str) {
            this.dbPath = str;
            return this;
        }

        public Builder debug(boolean z) {
            this.debug = z;
            return this;
        }

        public DBConfig build() {
            return new DBConfig(this, (DBConfig) null);
        }
    }
}
