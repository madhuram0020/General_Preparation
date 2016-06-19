package sked.com.generalpreparation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.fiskur.simpleviewpager.ImageResourceLoader;
import eu.fiskur.simpleviewpager.SimpleViewPager;
import sked.com.generalpreparation.adapter.PagerAdapter;
import sked.com.generalpreparation.fragment.LoginFragment;
import sked.com.generalpreparation.fragment.RegisterFragment;

public class LoginActivity extends AppCompatActivity {

    /*bind viewpager indicator*/
    @BindView(R.id.simple_view_pager)
    SimpleViewPager simpleViewPager;
    String[] demoUrlArray = new String[]{
            "http://fiskur.eu/apps/simpleviewpagerdemo/001.jpg",
            "http://fiskur.eu/apps/simpleviewpagerdemo/002.jpg",
            "http://fiskur.eu/apps/simpleviewpagerdemo/003.jpg",
            "http://fiskur.eu/apps/simpleviewpagerdemo/004.jpg",
            "http://fiskur.eu/apps/simpleviewpagerdemo/005.jpg",
    };
    int[] resourceIds = new int[]{
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3,
            R.drawable.img_4,
            R.drawable.img_5
    };


/*get resource from drawable for viewpager imageView*/
    private TabLayout tabLayout;
/*get resource from drawable for viewpager imageView*/
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        /*ShimmerFrameLayout container =
                (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        container.startShimmerAnimation();*/
/*floating action button implementation*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

 /*fab close*/

/***
 * indicator ViewPager implementation
 */

                simpleViewPager.setImageIds(resourceIds, new ImageResourceLoader() {
                    @Override
                    public void loadImageResource(ImageView imageView, int i) {

                        imageView.setImageResource(i);
                    }
                });


//set indicator color of selected and unselected dots.

                int indicatorColor = Color.parseColor("#ffffff");
                int selectedIndicatorColor = Color.parseColor("#fff000");
                simpleViewPager.showIndicator(indicatorColor, selectedIndicatorColor);


//set the scale type of imageView on ViewPager

                simpleViewPager.setScaleType(ImageView.ScaleType.FIT_XY);

//call method of tabStrip
        setViewPager();
    }

    /*method to set TabStrip below the viewPager Indicator*/
                void setViewPager() {
                    viewPager = (ViewPager) findViewById(R.id.viewpager);
                    PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
                    adapter.addFragment(new LoginFragment(), "Existing User");
                    adapter.addFragment(new RegisterFragment(), "New User");
                    viewPager.setAdapter(adapter);

                    tabLayout = (TabLayout) findViewById(R.id.tabs);
                    tabLayout.setupWithViewPager(viewPager);
                }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       /* getMenuInflater().inflate(R.menu.menu_login, menu);*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       /* int id = item.getItemId();*/

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleViewPager.clearListeners();
    }
}
