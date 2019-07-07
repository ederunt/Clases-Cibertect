package pe.edu.cibertec.gestures;

import android.content.Context;
import android.graphics.Matrix;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;


public class ScaleImageView extends AppCompatImageView {

    private GestureDetectorCompat scrollGestureDetector;
    private ScaleGestureDetector scaleGestureDetector;

    private float scaleFactor = 1f;

    public ScaleImageView(Context context) {
        super(context);
        init();
    }

    public ScaleImageView(Context context, AttributeSet attrs, GestureDetectorCompat gestureDetector) {
        super(context, attrs);
        init();
    }

    public ScaleImageView(Context context, AttributeSet attrs, int defStyleAttr, GestureDetectorCompat gestureDetector) {
        super(context, attrs, defStyleAttr);
        init();
        //this.gestureDetector = gestureDetector;
    }

    private void init() {
        setScaleType(ScaleType.MATRIX);
        this.scrollGestureDetector =  new GestureDetectorCompat(getContext(),new ScrollListener());
        this.scaleGestureDetector = new ScaleGestureDetector(getContext(),new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scrollGestureDetector.onTouchEvent(event);
        scaleGestureDetector.onTouchEvent(event);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        //Detecta la accion del dedo y lo multiplica por el detector.
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *=detector.getScaleFactor();
            //defina un maximo y un minimo que tiene que tener la imagen para que no se pase de la pantalla
            //no permite que el objeto se vuelva muy pequeo o  muy grande
            scaleFactor = Math.max(0.1f,Math.min(scaleFactor,0.5f));

            //Matrix matrix = new Matrix(getImageMatrix());

            //Obtener la matriz inicial de la imagen
            Matrix matrix = new Matrix(getImageMatrix());
            //Calcular la nueva matrix en la base al factor de escala
            matrix.setScale(scaleFactor,scaleFactor,detector.getFocusX(),detector.getFocusY());
            //establecer la nueva  matrix
            setImageMatrix(matrix);
            return  true;
            //return super.onScale(detector);
        }
    }


    float scrollX = getScrollX();
    float scrollY = getScrollY();

    private class ScrollListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            scrollX += distanceX;
            scrollY += distanceY;

            scrollX = Math.max(0f,Math.min(scrollX,getDrawable().getIntrinsicWidth()));
            scrollY = Math.max(0f,Math.min(scrollY,getDrawable().getIntrinsicHeight()));

            scrollTo((int)scrollX,(int)scrollY);
            return true;
        }
    }
}
