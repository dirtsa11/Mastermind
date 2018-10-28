package com.example.astrid.mastermind;

import android.graphics.drawable.Drawable;

/**
 * Created by Astrid.
 */

class Line {
    private Drawable drawableCase1;
    private Drawable drawableCase2;
    private Drawable drawableCase3;
    private Drawable drawableCase4;
    private Drawable drawableVerif1;
    private Drawable drawableVerif2;
    private Drawable drawableVerif3;
    private Drawable drawableVerif4;

    Line(Drawable dc1, Drawable dc2, Drawable dc3, Drawable dc4, Drawable dv1, Drawable dv2, Drawable dv3, Drawable dv4){
        drawableCase1=dc1;
        drawableCase2=dc2;
        drawableCase3=dc3;
        drawableCase4=dc4;
        drawableVerif1=dv1;
        drawableVerif2=dv2;
        drawableVerif3=dv3;
        drawableVerif4=dv4;
    }

    Drawable getDrawableCase1(){ return drawableCase1; }
    Drawable getDrawableCase2(){ return drawableCase2; }
    Drawable getDrawableCase3(){ return drawableCase3; }
    Drawable getDrawableCase4(){ return drawableCase4; }
    Drawable getDrawableVerif1(){ return drawableVerif1; }
    Drawable getDrawableVerif2(){ return drawableVerif2; }
    Drawable getDrawableVerif3(){ return drawableVerif3; }
    Drawable getDrawableVerif4(){ return drawableVerif4; }
}
