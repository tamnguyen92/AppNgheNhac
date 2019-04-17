package com.example.jerem.appnghenhac.designe;

import android.graphics.Color;

import com.example.jerem.appnghenhac.R;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;

/**
 * Created by jerem on 02/02/2018.
 */

public class BuilderManager {

    public BuilderManager() {

    }
//    static HamButton.Builder getHamButtonBuilderWithDifferentPieceColor() {
//        return new HamButton.Builder()
//                .normalImageRes(R.drawable.apple)
//                .normalTextRes(R.string.text_ham_button_text_normal)
//                .subNormalTextRes(R.string.text_ham_button_sub_text_normal)
//                .pieceColor(Color.WHITE);
//    }
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
                .textSize(16)
                .rotateImage(true)
                .pieceColor(Color.WHITE);
    }
//    static TextOutsideCircleButton.Builder getTextOutsideCircleButtonBuilderWithDifferentPieceColor2() {
//        return new TextOutsideCircleButton.Builder()
//                .normalTextRes(R.string.text_inside_circle_button_text_normal)
//                .pieceColor(Color.WHITE);
//    }

}
