package com.adkar.salah;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private final String[] adhkar = {
        "أستغفر الله",
        "اللهم أنت السلام ومنك السلام، تباركت يا ذا الجلال والإكرام",
        "سبحان الله",
        "الحمد لله",
        "الله أكبر",
        "لا إله إلا الله وحده لا شريك له، له الملك وله الحمد وهو على كل شيء قدير",
        "اللهم لا مانع لما أعطيت، ولا معطي لما منعت، ولا ينفع ذا الجد منك الجد",
        "لا إله إلا الله وحده لا شريك له، له الملك وله الحمد وهو على كل شيء قدير، لا حول ولا قوة إلا بالله",
        "لا إله إلا الله ولا نعبد إلا إياه، له النعمة وله الفضل وله الثناء الحسن",
        "اللهم أعني على ذكرك وشكرك وحسن عبادتك"
    };

    private final int[] goals = {3, 1, 33, 33, 33, 1, 1, 1, 1, 1};
    private final int[] counts = new int[10];

    private LinearLayout listLayout;
    private TextView[] countViews = new TextView[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);
        scrollView.setBackgroundColor(Color.rgb(245, 248, 245));

        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(24, 24, 24, 24);
        main.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        TextView heading = new TextView(this);
        heading.setText("أذكار ما بعد الصلاة");
        heading.setTextSize(26);
        heading.setTextColor(Color.rgb(20, 100, 70));
        heading.setGravity(Gravity.CENTER);
        heading.setPadding(8, 20, 8, 20);
        main.addView(heading);

        TextView subtitle = new TextView(this);
        subtitle.setText("اضغط على الذكر لتسجيل التسبيح");
        subtitle.setTextSize(16);
        subtitle.setTextColor(Color.DKGRAY);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(8, 0, 8, 20);
        main.addView(subtitle);

        Button resetButton = new Button(this);
        resetButton.setText("تصفير جميع العدادات");
        main.addView(resetButton);

        listLayout = new LinearLayout(this);
        listLayout.setOrientation(LinearLayout.VERTICAL);
        main.addView(listLayout);

        for (int i = 0; i < adhkar.length; i++) {
            addDhikrCard(i);
        }

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < counts.length; i++) {
                    counts[i] = 0;
                    updateCount(i);
                }
                Toast.makeText(MainActivity.this,
                    "تم تصفير العدادات", Toast.LENGTH_SHORT).show();
            }
        });

        scrollView.addView(main);
        setContentView(scrollView);
    }

    private void addDhikrCard(final int index) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(18, 18, 18, 18);
        card.setGravity(Gravity.CENTER);
        card.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams cardParams =
            new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            );
        cardParams.setMargins(0, 10, 0, 10);

        TextView dhikrText = new TextView(this);
        dhikrText.setText(adhkar[index]);
        dhikrText.setTextSize(19);
        dhikrText.setTextColor(Color.rgb(35, 65, 50));
        dhikrText.setGravity(Gravity.CENTER);
        dhikrText.setPadding(8, 10, 8, 10);
        card.addView(dhikrText);

        TextView targetText = new TextView(this);
        targetText.setText("العدد المطلوب: " + goals[index]);
        targetText.setTextSize(14);
        targetText.setTextColor(Color.GRAY);
        targetText.setGravity(Gravity.CENTER);
        card.addView(targetText);

        Button countButton = new Button(this);
        countButton.setText("اضغط للتسبيح");
        card.addView(countButton);

        TextView countText = new TextView(this);
        countText.setText("0 / " + goals[index]);
        countText.setTextSize(17);
        countText.setTextColor(Color.rgb(20, 100, 70));
        countText.setGravity(Gravity.CENTER);
        countText.setPadding(8, 8, 8, 8);
        card.addView(countText);

        countViews[index] = countText;

        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counts[index]++;

                if (counts[index] >= goals[index]) {
                    Toast.makeText(MainActivity.this,
                        "أحسنت، أكملت هذا الذكر",
                        Toast.LENGTH_SHORT).show();
                }

                updateCount(index);
            }
        });

        listLayout.addView(card, cardParams);
    }

    private void updateCount(int index) {
        if (countViews[index] != null) {
            countViews[index].setText(
                counts[index] + " / " + goals[index]
            );
        }
    }
}