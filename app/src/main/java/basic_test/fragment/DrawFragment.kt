package basic_test.fragment

import android.graphics.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mvvm.R
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.fragment_draw.*

class DrawFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_draw, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDraw()
    }

    private fun setDraw() {
        //캔버스 생성
        val bitmap = Bitmap.createBitmap(2000, 2000, Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bitmap)
        canvas.drawColor(Color.WHITE)

        //ImageView에 비트맵 연결
        iv_draw.setImageBitmap(bitmap)

        //붓의 색과 두께
        val paint = Paint()
        paint.color = Color.RED
        paint.strokeWidth = 30f

        //좌표
        val rect = RectF(150f,150f,300f,300f)
        //해당 좌표에 사각형 그리기
        canvas.drawRect(rect, paint)

        //해당 좌표에 타원 그리기
        val rect1 = RectF(300f,300f,450f,450f)
        canvas.drawArc(rect1, 0f, 360f, true, paint)

        //해당 좌표에 원 그리기
        val rect2 = RectF(450f,450f,600f,600f)
        canvas.drawArc(rect2, 0f, 360f, true, paint)

        //덜찬 원 그리기
        val rect3 = RectF(600f,600f,750f,750f)
        canvas.drawArc(rect3, 90f, 180f, false, paint)
    }
}