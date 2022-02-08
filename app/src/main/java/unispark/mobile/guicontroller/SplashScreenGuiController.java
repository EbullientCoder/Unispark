package unispark.mobile.guicontroller;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import unispark.mobile.Session;
import unispark.mobile.view.LoginView;
import unispark.mobile.view.SplashScreenView;


public class SplashScreenGuiController {

    private SplashScreenView splashScreenView;


    public SplashScreenGuiController(SplashScreenView splashScreenView) {
        this.splashScreenView = splashScreenView;
    }


    public void showLoginView(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Window window = this.splashScreenView.getWindow();

        //Make Status Bar Translucent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //Remove Button Bottom Bar
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        new Handler().postDelayed(() -> {
            Intent i = new Intent(this.getSplashScreenView(), LoginView.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("session", new Session());
            this.splashScreenView.startActivity(i);
        }, 2000);



    }

    public SplashScreenView getSplashScreenView() {
        return splashScreenView;
    }
}
