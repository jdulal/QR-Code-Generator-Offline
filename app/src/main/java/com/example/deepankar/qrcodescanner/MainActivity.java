package com.example.deepankar.qrcodescanner;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

   public void getQRCode(View v)
   {
       try{

           TextView tv = (TextView) findViewById(R.id.editText);

           //Create a Writter
           Writer writer = new QRCodeWriter();
           // Generate a QR Code in BitMatrix
           BitMatrix bitMatrix = writer.encode(String.valueOf(tv.getText()), BarcodeFormat.QR_CODE, 200, 200);

           //add it to the image view
           ImageView iv;
           iv = (ImageView) findViewById(R.id.imageView);
           iv.setImageBitmap(toBitmap(bitMatrix));
       }
       catch(WriterException writerException)
       {
           Log.d("LOG",writerException.toString());
       }


   }

    //convert the BitMatrix to Bitmap
    public static Bitmap toBitmap(BitMatrix matrix){
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                bmp.setPixel(x, y, matrix.get(x,y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bmp;
    }
}
