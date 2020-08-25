package cn.bingoogolapple.qrcode.core;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.support.v7.widget.ActivityChooserView;
import android.view.Display;
import android.view.WindowManager;
import com.alibaba.fastjson.asm.Opcodes;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

final class CameraConfigurationManager {
    private static final Pattern COMMA_PATTERN = Pattern.compile(",");
    private static final int TEN_DESIRED_ZOOM = 27;
    private Point cameraResolution;
    private final Context mContext;
    private Point mScreenResolution;

    public CameraConfigurationManager(Context context) {
        this.mContext = context;
    }

    public void initFromCameraParameters(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        Display defaultDisplay = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
        this.mScreenResolution = new Point(defaultDisplay.getWidth(), defaultDisplay.getHeight());
        Point point = new Point();
        point.x = this.mScreenResolution.x;
        point.y = this.mScreenResolution.y;
        if (this.mScreenResolution.x < this.mScreenResolution.y) {
            point.x = this.mScreenResolution.y;
            point.y = this.mScreenResolution.x;
        }
        this.cameraResolution = getCameraResolution(parameters, point);
    }

    public Point getCameraResolution() {
        return this.cameraResolution;
    }

    public void setDesiredCameraParameters(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        parameters.setPreviewSize(this.cameraResolution.x, this.cameraResolution.y);
        setZoom(parameters);
        camera.setDisplayOrientation(getDisplayOrientation());
        camera.setParameters(parameters);
    }

    public void openFlashlight(Camera camera) {
        doSetTorch(camera, true);
    }

    public void closeFlashlight(Camera camera) {
        doSetTorch(camera, false);
    }

    private void doSetTorch(Camera camera, boolean z) {
        String str;
        Camera.Parameters parameters = camera.getParameters();
        if (z) {
            str = findSettableValue(parameters.getSupportedFlashModes(), "torch", "on");
        } else {
            str = findSettableValue(parameters.getSupportedFlashModes(), "off");
        }
        if (str != null) {
            parameters.setFlashMode(str);
        }
        camera.setParameters(parameters);
    }

    private static String findSettableValue(Collection<String> collection, String... strArr) {
        if (collection != null) {
            for (String str : strArr) {
                if (collection.contains(str)) {
                    return str;
                }
            }
        }
        return null;
    }

    public int getDisplayOrientation() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int i = 0;
        Camera.getCameraInfo(0, cameraInfo);
        switch (((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation()) {
            case 1:
                i = 90;
                break;
            case 2:
                i = Opcodes.GETFIELD;
                break;
            case 3:
                i = 270;
                break;
        }
        if (cameraInfo.facing == 1) {
            return (360 - ((cameraInfo.orientation + i) % 360)) % 360;
        }
        return ((cameraInfo.orientation - i) + 360) % 360;
    }

    private static Point getCameraResolution(Camera.Parameters parameters, Point point) {
        Point findBestPreviewSizeValue = findBestPreviewSizeValue(parameters.getSupportedPreviewSizes(), point);
        return findBestPreviewSizeValue == null ? new Point((point.x >> 3) << 3, (point.y >> 3) << 3) : findBestPreviewSizeValue;
    }

    private static Point findBestPreviewSizeValue(List<Camera.Size> list, Point point) {
        Iterator<Camera.Size> it = list.iterator();
        int i = 0;
        int i2 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i3 = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Camera.Size next = it.next();
            int i4 = next.width;
            int i5 = next.height;
            int abs = Math.abs(i4 - point.x) + Math.abs(i5 - point.y);
            if (abs == 0) {
                i3 = i5;
                i = i4;
                break;
            } else if (abs < i2) {
                i3 = i5;
                i = i4;
                i2 = abs;
            }
        }
        if (i <= 0 || i3 <= 0) {
            return null;
        }
        return new Point(i, i3);
    }

    private static int findBestMotZoomValue(CharSequence charSequence, int i) {
        String[] split = COMMA_PATTERN.split(charSequence);
        int length = split.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            try {
                double parseDouble = Double.parseDouble(split[i2].trim());
                int i4 = (int) (10.0d * parseDouble);
                double d = (double) i;
                Double.isNaN(d);
                if (Math.abs(d - parseDouble) < ((double) Math.abs(i - i3))) {
                    i3 = i4;
                }
                i2++;
            } catch (NumberFormatException unused) {
                return i;
            }
        }
        return i3;
    }

    private void setZoom(Camera.Parameters parameters) {
        String str = parameters.get("zoom-supported");
        if (str == null || Boolean.parseBoolean(str)) {
            int i = 27;
            String str2 = parameters.get("max-zoom");
            if (str2 != null) {
                try {
                    int parseDouble = (int) (Double.parseDouble(str2) * 10.0d);
                    if (27 > parseDouble) {
                        i = parseDouble;
                    }
                } catch (NumberFormatException unused) {
                }
            }
            String str3 = parameters.get("taking-picture-zoom-max");
            if (str3 != null) {
                try {
                    int parseInt = Integer.parseInt(str3);
                    if (i > parseInt) {
                        i = parseInt;
                    }
                } catch (NumberFormatException unused2) {
                }
            }
            String str4 = parameters.get("mot-zoom-values");
            if (str4 != null) {
                i = findBestMotZoomValue(str4, i);
            }
            String str5 = parameters.get("mot-zoom-step");
            if (str5 != null) {
                try {
                    int parseDouble2 = (int) (Double.parseDouble(str5.trim()) * 10.0d);
                    if (parseDouble2 > 1) {
                        i -= i % parseDouble2;
                    }
                } catch (NumberFormatException unused3) {
                }
            }
            if (!(str2 == null && str4 == null)) {
                double d = (double) i;
                Double.isNaN(d);
                parameters.set("zoom", String.valueOf(d / 10.0d));
            }
            if (str3 != null) {
                parameters.set("taking-picture-zoom", i);
            }
        }
    }
}
