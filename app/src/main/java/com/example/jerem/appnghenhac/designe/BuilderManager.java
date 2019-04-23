package com.example.jerem.appnghenhac.designe;

import android.graphics.Color;
import android.graphics.Rect;

import com.example.jerem.appnghenhac.R;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.Util;

/**
 * Created by jerem on 02/02/2018.
 */

public class BuilderManager {

    public BuilderManager() {

    }
   public static HamButton.Builder getHamButtonBuilderWithDifferentPieceColor(int hinh,String text,String subtext,int mau) {
        return new HamButton.Builder()
                .normalImageRes(hinh)
                .normalText(text)
                .subNormalText(subtext)
                .normalColorRes(mau)
                .imagePadding(new Rect(10, 10, 10, 10))
                .pieceColor(Color.WHITE);
    }
//
//    static TextInsideCircleButton.Builder getTextInsideCircleButtonBuilderWithDifferentPieceColor() {
//        return new TextInsideCircleButton.Builder()
//                .normalTextRes(R.string.text_inside_circle_button_text_normal)
//                .pieceColor(Color.WHITE);
//    }
  public   static TextOutsideCircleButton.Builder getTextOutsideCircleButtonBuilderWithDifferentPieceColor(String s, int hinh,int mau) {
        return new TextOutsideCircleButton.Builder()
                .normalImageRes(R.drawable.playlist)
                .normalText(s)
                .normalColorRes(mau)
                .shadowEffect(true)
                .shadowRadius(Util.dp2px(20))
                .shadowOffsetX(20)
                .textSize(16)
                .imagePadding(new Rect(2, 2, 2, 2))
                .rotateImage(true)
                .pieceColor(Color.WHITE);
    }
//    static TextOutsideCircleButton.Builder getTextOutsideCircleButtonBuilderWithDifferentPieceColor2() {
//        return new TextOutsideCircleButton.Builder()
//                .normalTextRes(R.string.text_inside_circle_button_text_normal)
//                .pieceColor(Color.WHITE);
//    }

}
