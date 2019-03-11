package corp.bcapc.top100pan

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class PanApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }

}