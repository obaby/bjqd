package anet.channel;

import anet.channel.entity.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: Taobao */
class d {

    /* renamed from: a  reason: collision with root package name */
    private final Map<SessionRequest, List<Session>> f160a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private final ReentrantReadWriteLock f161b = new ReentrantReadWriteLock();

    /* renamed from: c  reason: collision with root package name */
    private final ReentrantReadWriteLock.ReadLock f162c = this.f161b.readLock();
    private final ReentrantReadWriteLock.WriteLock d = this.f161b.writeLock();

    d() {
    }

    public void a(SessionRequest sessionRequest, Session session) {
        if (sessionRequest != null && sessionRequest.a() != null && session != null) {
            this.d.lock();
            try {
                List list = this.f160a.get(sessionRequest);
                if (list == null) {
                    list = new ArrayList();
                    this.f160a.put(sessionRequest, list);
                }
                if (list.indexOf(session) == -1) {
                    list.add(session);
                    Collections.sort(list);
                    this.d.unlock();
                }
            } finally {
                this.d.unlock();
            }
        }
    }

    public void b(SessionRequest sessionRequest, Session session) {
        this.d.lock();
        try {
            List list = this.f160a.get(sessionRequest);
            if (list != null) {
                list.remove(session);
                if (list.size() == 0) {
                    this.f160a.remove(sessionRequest);
                }
                this.d.unlock();
            }
        } finally {
            this.d.unlock();
        }
    }

    public List<Session> a(SessionRequest sessionRequest) {
        this.f162c.lock();
        try {
            List list = this.f160a.get(sessionRequest);
            if (list != null) {
                return new ArrayList(list);
            }
            List<Session> list2 = Collections.EMPTY_LIST;
            this.f162c.unlock();
            return list2;
        } finally {
            this.f162c.unlock();
        }
    }

    public Session a(SessionRequest sessionRequest, int i) {
        this.f162c.lock();
        try {
            List list = this.f160a.get(sessionRequest);
            Session session = null;
            if (list != null) {
                if (!list.isEmpty()) {
                    Iterator it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Session session2 = (Session) it.next();
                        if (session2 != null && session2.isAvailable()) {
                            if (i == c.f183c || session2.i.getType() == i) {
                                session = session2;
                            }
                        }
                    }
                    this.f162c.unlock();
                    return session;
                }
            }
            return null;
        } finally {
            this.f162c.unlock();
        }
    }

    public List<SessionRequest> a() {
        List<SessionRequest> list = Collections.EMPTY_LIST;
        this.f162c.lock();
        try {
            if (this.f160a.isEmpty()) {
                return list;
            }
            ArrayList arrayList = new ArrayList(this.f160a.keySet());
            this.f162c.unlock();
            return arrayList;
        } finally {
            this.f162c.unlock();
        }
    }

    public boolean c(SessionRequest sessionRequest, Session session) {
        this.f162c.lock();
        try {
            List list = this.f160a.get(sessionRequest);
            boolean z = false;
            if (list != null) {
                if (list.indexOf(session) != -1) {
                    z = true;
                }
            }
            return z;
        } finally {
            this.f162c.unlock();
        }
    }
}
