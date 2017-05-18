package local.gonzalez.clickadopta;

import android.graphics.Bitmap;

/**
 * Created by joel.gonzalez on 18/05/17.
 */

class Noticia {
    private Bitmap imageSource;

    public Bitmap getImageSource() {
        return imageSource;
    }

    public Noticia(Bitmap imageSource) {
        this.imageSource = imageSource;
    }
}

