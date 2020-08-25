package com.alipay.android.phone.mrpc.core;

import anet.channel.util.HttpConstant;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public final class j extends a {
    private g g;

    public j(g gVar, Method method, int i, String str, byte[] bArr, boolean z) {
        super(method, i, str, bArr, "application/x-www-form-urlencoded", z);
        this.g = gVar;
    }

    public final Object a() {
        o oVar = new o(this.g.a());
        oVar.a(this.f503b);
        oVar.a(this.e);
        oVar.a(this.f);
        oVar.a("id", String.valueOf(this.d));
        oVar.a("operationType", this.f504c);
        oVar.a(HttpConstant.GZIP, String.valueOf(this.g.d()));
        oVar.a((Header) new BasicHeader("uuid", UUID.randomUUID().toString()));
        List<Header> b2 = this.g.c().b();
        if (b2 != null && !b2.isEmpty()) {
            for (Header a2 : b2) {
                oVar.a(a2);
            }
        }
        StringBuilder sb = new StringBuilder("threadid = ");
        sb.append(Thread.currentThread().getId());
        sb.append("; ");
        sb.append(oVar.toString());
        try {
            u uVar = this.g.b().a(oVar).get();
            if (uVar != null) {
                return uVar.b();
            }
            throw new RpcException((Integer) 9, "response is null");
        } catch (InterruptedException e) {
            throw new RpcException(13, "", e);
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause == null || !(cause instanceof HttpException)) {
                throw new RpcException(9, "", e2);
            }
            HttpException httpException = (HttpException) cause;
            int code = httpException.getCode();
            switch (code) {
                case 1:
                    code = 2;
                    break;
                case 2:
                    code = 3;
                    break;
                case 3:
                    code = 4;
                    break;
                case 4:
                    code = 5;
                    break;
                case 5:
                    code = 6;
                    break;
                case 6:
                    code = 7;
                    break;
                case 7:
                    code = 8;
                    break;
                case 8:
                    code = 15;
                    break;
                case 9:
                    code = 16;
                    break;
            }
            throw new RpcException(Integer.valueOf(code), httpException.getMsg());
        } catch (CancellationException e3) {
            throw new RpcException(13, "", e3);
        }
    }
}
