package cn.bingoogolapple.bgabanner.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

public abstract class BGAPageTransformer implements ViewPager.PageTransformer {
    public abstract void handleInvisiblePage(View view, float f);

    public abstract void handleLeftPage(View view, float f);

    public abstract void handleRightPage(View view, float f);

    public void transformPage(View view, float f) {
        if (f < -1.0f) {
            handleInvisiblePage(view, f);
        } else if (f <= 0.0f) {
            handleLeftPage(view, f);
        } else if (f <= 1.0f) {
            handleRightPage(view, f);
        } else {
            handleInvisiblePage(view, f);
        }
    }

    public static BGAPageTransformer getPageTransformer(TransitionEffect transitionEffect) {
        switch (transitionEffect) {
            case Default:
                return new DefaultPageTransformer();
            case Alpha:
                return new AlphaPageTransformer();
            case Rotate:
                return new RotatePageTransformer();
            case Cube:
                return new CubePageTransformer();
            case Flip:
                return new FlipPageTransformer();
            case Accordion:
                return new AccordionPageTransformer();
            case ZoomFade:
                return new ZoomFadePageTransformer();
            case Fade:
                return new FadePageTransformer();
            case ZoomCenter:
                return new ZoomCenterPageTransformer();
            case ZoomStack:
                return new ZoomStackPageTransformer();
            case Stack:
                return new StackPageTransformer();
            case Depth:
                return new DepthPageTransformer();
            case Zoom:
                return new ZoomPageTransformer();
            default:
                return new DefaultPageTransformer();
        }
    }
}
