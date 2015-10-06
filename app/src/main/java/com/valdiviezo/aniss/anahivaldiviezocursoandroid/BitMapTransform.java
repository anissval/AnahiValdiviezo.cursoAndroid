package com.valdiviezo.aniss.anahivaldiviezocursoandroid;
import android.graphics.Bitmap;
import com.squareup.picasso.Transformation;
/**
 * Created by Aniss on 05/10/2015.
 */
public class BitMapTransform implements Transformation {
    /**
     * Transformate the loaded image to avoid OutOfMemoryException
     */
        int maxWidth;
        int maxHeight;

        public BitMapTransform(int maxWidth, int maxHeight) {
            this.maxWidth = maxWidth;
            this.maxHeight = maxHeight;
        }

        @Override
        public Bitmap transform(Bitmap source) {
            int targetWidth, targetHeight;
            double aspectRatio;

            if (source.getWidth() > source.getHeight()) {
                targetWidth = maxWidth;
                aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                targetHeight = (int) (targetWidth * aspectRatio);
            } else {
                targetHeight = maxHeight;
                aspectRatio = (double) source.getWidth() / (double) source.getHeight();
                targetWidth = (int) (targetHeight * aspectRatio);
            }

            Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() {
            return maxWidth + "x" + maxHeight;
        }
}
